package auth;

import griffon.core.artifact.GriffonController;
import griffon.core.controller.ControllerAction;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import griffon.transform.Threading;
import javax.annotation.Nonnull;

import griffon.inject.MVCMember;
import javax.inject.Inject;
import test.Util;
import test.DBQuery;
import java.util.Map;
import org.apache.commons.collections4.MultiMap;


@ArtifactProviderFor(GriffonController.class)
public class RegisterController extends AbstractGriffonController {
    private RegisterModel model;
    private RegisterView view;
    

    @MVCMember
    public void setModel(@Nonnull RegisterModel model) {
        this.model = model;
    }


    @MVCMember
    public void setView(@Nonnull RegisterView view) {
        this.view = view;
    }


    @Inject
    private Util util;

    @Inject
    private DBQuery dbquery;


    @ControllerAction
    public void register() {
       
        String regusername  = view.regusername.getText();
        
        String regpassword  = view.regpassword.getText();

        
        MultiMap query = dbquery.map();
        query.put("table", "users");
        query.put("set",   "username = "+ regusername);
        query.put("set",   "password = "+ regpassword);

        dbquery.save(query);
                    
        util.toast(regusername);
        util.toast(regpassword);
        
    }

    @ControllerAction
    public void back(){

        util.toggleView("register","login");
    }


   


    @ControllerAction
    public void helloClick() {

        /**
         * Display a message
         */
        util.toast("HELLO WORLD");
    }

    
}