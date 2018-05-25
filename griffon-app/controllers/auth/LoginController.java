package auth;

import griffon.core.artifact.GriffonController;
import griffon.core.controller.ControllerAction;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import griffon.transform.Threading;
import javax.annotation.Nonnull;

import javax.swing.JOptionPane;
import griffon.inject.MVCMember;


@ArtifactProviderFor(GriffonController.class)
public class LoginController extends AbstractGriffonController {
    private LoginModel model;
    private LoginView view;

    @MVCMember
    public void setModel(@Nonnull LoginModel model) {
        this.model = model;
    }

    @MVCMember
    public void setView(LoginView view) {
        this.view = view;
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void login() {

        System.out.println("PRINT PRINT PRINT");

        String username = "test";

        model.setUsername(username);
    }

    
}