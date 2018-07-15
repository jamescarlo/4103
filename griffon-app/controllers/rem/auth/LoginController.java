package rem.auth;

import griffon.core.artifact.GriffonController;
import griffon.core.controller.ControllerAction;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import griffon.transform.Threading;
import javax.annotation.Nonnull;

import griffon.inject.MVCMember;
import javax.inject.Inject;
import rem.Util;
import rem.DBQuery;
import rem.PasswordHash;
import java.util.Map;
import org.apache.commons.collections4.MultiMap;

@ArtifactProviderFor(GriffonController.class)
public class LoginController extends AbstractGriffonController {
    private LoginModel model;
    private LoginView view;

    @MVCMember
    public void setModel(@Nonnull LoginModel model) {
        this.model = model;
    }

    @MVCMember
    public void setView(@Nonnull LoginView view) {
        this.view = view;
    }

    @Inject
    private Util util;

    @Inject
    private DBQuery dbquery;

    @Inject
    private PasswordHash pwhash;

    @ControllerAction
    public void login() {
        /**
         * Get username from LoginView
         */
        String username = view.username.getText();
        /**
         * Get password from LoginView
         */
        String password = view.password.getText();

        MultiMap query = dbquery.map();
        query.put("table",         "users");
        query.put("condition",     "username = "+ username);

        Map<String, Map> data = dbquery.get(query);
        /**
         * Check if account exist
         */
        if (data.size() != 0) {
            /**
             * Check if password match
             */
            Boolean match = false;
            try {
                match = pwhash.verifyPassword(password, data.get(0).get("password") +"");
            } catch(Exception e) {}
            
            if (match) {
                util.toggleView("login", "dashboard");  
            } else {
                util.toast("Password does not match");
            }
        } else {
            util.toast("Username does not exist");
        }
    }

}
