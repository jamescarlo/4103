package rem.admin;

import griffon.core.artifact.GriffonView;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.codehaus.griffon.runtime.javafx.artifact.AbstractJavaFXGriffonView;
import javafx.stage.StageStyle;

import java.util.Collections;
import javax.annotation.Nonnull;
import java.util.Map;
import javafx.scene.control.Button;

@ArtifactProviderFor(GriffonView.class)
public class DashboardView extends AbstractJavaFXGriffonView {
    private DashboardController controller;
    private DashboardModel model;

    @MVCMember
    public void setController(@Nonnull DashboardController controller) {
        this.controller = controller;
    }

    @MVCMember
    public void setModel(@Nonnull DashboardModel model) {
        this.model = model;
    }

    public Stage stage;

    @FXML
    public AnchorPane main;
    @FXML
    public Text employee_name;
    @FXML
    public Button module1ActionTarget;
    @FXML
    public Button module2ActionTarget;
    @FXML
    public Button module3ActionTarget;
    @FXML
    public Button module4ActionTarget;
    @FXML
    public Button module5ActionTarget;

    @Override
    public void initUI() {
        stage = (Stage) getApplication()
            .createApplicationContainer(Collections.<String,Object>emptyMap());
        stage.setTitle(getApplication().getConfiguration().getAsString("application.title"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(init());
        stage.sizeToScene();
        getApplication().getWindowManager().attach("dashboard", stage);
    }

    // build the UI
    private Scene init() {
        Scene scene = new Scene(new Group());
        scene.setFill(Color.WHITE);

        Node node = loadFromFXML();
        //model.clickCountProperty().bindBidirectional(clickLabel.textProperty());
        if (node instanceof Parent) {
            scene.setRoot((Parent) node);
        } else {
            ((Group) scene.getRoot()).getChildren().addAll(node);
        }
        connectActions(node, controller);
        connectMessageSource(node);

        return scene;
    }
}
