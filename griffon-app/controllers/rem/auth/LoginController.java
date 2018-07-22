package rem.auth;

import griffon.core.artifact.GriffonController;
import griffon.core.controller.ControllerAction;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import griffon.transform.Threading;
import javax.annotation.Nonnull;

import rem.Util;
import rem.DBQuery;
import rem.CipherCrypt;
import rem.PasswordHash;
import rem.Storage;

import griffon.inject.MVCMember;
import javax.inject.Inject;
import java.util.Map;
import org.apache.commons.collections4.MultiMap;
import java.lang.Byte;
import java.util.Arrays;

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
    private DBQuery db;
    @Inject 
    private CipherCrypt ciphercrypt;
    @Inject
    private PasswordHash pwhash;
    @Inject
    private Storage storage;

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void login() {
        /**
         * Get username from LoginView
         */
        String username = view.username.getText();
        /**
         * Get password from LoginView
         */
        String password = view.password.getText();

        MultiMap query = db.map();
        query.put("table",         "users");
        query.put("condition",     "username = "+ username);

        Map<String, Map> data = db.get(query);
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
                String id = data.get(0).get("id") +"";
                //byte[] encrypted_id = ciphercrypt.encrypt(id);
                //Storage.saveItem("id", encrypted_id +"");
                Storage.saveItem("id", id);
                util.toggleView("login", "dashboard");  
            } else {
                util.toast("Password does not match");
            }
        } else {
            util.toast("Username does not exist");
        }
    }

}
