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
public class ProcurementView extends AbstractJavaFXGriffonView {
    private ProcurementController controller;
    private ProcurementModel model;

    @MVCMember
    public void setController(@Nonnull ProcurementController controller) {
        this.controller = controller;
    }

    @MVCMember
    public void setModel(@Nonnull ProcurementModel model) {
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
