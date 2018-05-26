package auth;

import griffon.core.artifact.GriffonModel;
import griffon.metadata.ArtifactProviderFor;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel;

import javax.annotation.Nonnull;
import java.util.Map;
import griffon.inject.MVCMember;


@ArtifactProviderFor(GriffonModel.class)
public class LoginModel extends AbstractGriffonModel {

    private LoginView view;


    @MVCMember
    public void setView(LoginView view) {
        this.view = view;
    }


    /**
    @Nonnull
    public final StringProperty passwordProperty() {
        if (password == null) {
            password = new SimpleStringProperty(this, "password", "test");
        }
        return password;
    }

    
    public void setPassword(String password) {
        passwordProperty().set(password);
    }


    @Override
    public void mvcGroupInit(@Nonnull Map<String, Object> args) {
        passwordProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            runInsideUIAsync(() -> view.password.setText(newValue));   
        });
    }
     */
}