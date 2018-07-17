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
    @Nonnull 
    private DashboardView parentView;

    @FXML
    private Label clickLabel;

    @MVCMember
    public void setController(@Nonnull WarehouseController controller) {
        this.controller = controller;
    }

    @MVCMember
    public void setModel(@Nonnull WarehouseModel model) {
        this.model = model;
    }

    @Override
    public void initUI() {
        parentView.main.getChildren().setAll(init());
    }

    // build the UI
    private Node init() {
        Node node = loadFromFXML();
        model.clickCountProperty().bindBidirectional(clickLabel.textProperty());
        connectActions(node, controller);
        connectMessageSource(node);

        return node;
    }
}
