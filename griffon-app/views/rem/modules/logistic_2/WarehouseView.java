package rem.modules.logistic_2;

import griffon.core.artifact.GriffonView;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView;

import java.util.Collections;
import javax.annotation.Nonnull;
import rem.admin.DashboardView;

@ArtifactProviderFor(GriffonView.class)
public class WarehouseView extends AbstractJavaFXGriffonView {
    private WarehouseController controller;
    private WarehouseModel model;

    @MVCMember
    public void setController(@Nonnull WarehouseController controller) {
        this.controller = controller;
    }

    @MVCMember
    public void setModel(@Nonnull WarehouseModel model) {
        this.model = model;
    }

    @MVCMember 
    @Nonnull 
    private DashboardView parentView;

    @Override
    public void initUI() {
        parentView.main.getChildren().setAll(init());
    }

    private Node init() {
        Node node = loadFromFXML();
        connectActions(node, controller);
        connectMessageSource(node);

        return node;
    }
}
