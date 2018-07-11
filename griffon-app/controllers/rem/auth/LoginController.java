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
        query.put("condition:and", "password = "+ password);

        Map<String, Map> data = dbquery.get(query);

        /**
         * Check if account exist
         */
        if(data.size() != 0) {

            /**
             * Hide LoginView and Show AppView
             */
            util.toggleView("login", "dashboard");
        }else {

            /**
             * Display a message
             */
            util.toast("Failed to Log In");
        }
    }


    @ControllerAction
    public void close() {

        /**
         * Close application
         */
        getApplication().shutdown();
    }

    @ControllerAction
    public void register(){
    }

  
}