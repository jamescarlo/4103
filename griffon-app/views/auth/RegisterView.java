package auth;

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

import javafx.stage.StageStyle;
import javafx.scene.control.TextField;


@ArtifactProviderFor(GriffonView.class)
public class RegisterView extends AbstractJavaFXGriffonView {
    private RegisterController controller;
    private RegisterModel model;

    @FXML
    public TextField regusername;
    
    @FXML
    public TextField regpassword;

    @MVCMember
    public void setController(@Nonnull RegisterController controller) {
        this.controller = controller;
    }

    @MVCMember
    public void setModel(@Nonnull RegisterModel model) {
        this.model = model;
    }


    /*
     * No need to edit initUI
     */
    @Override
    public void initUI() {
        Stage stage = (Stage) getApplication()
            .createApplicationContainer(Collections.<String,Object>emptyMap());
        stage.setTitle(getApplication().getConfiguration().getAsString("application.title"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(init());
        stage.sizeToScene();
        getApplication().getWindowManager().attach("register", stage);
    }

    
    /*
     * No need to edit init
     */
    private Scene init() {
        Scene scene = new Scene(new Group());
        scene.setFill(Color.WHITE);

        Node node = loadFromFXML();
        
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
