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
    


    @ControllerAction
    public void back(){

        util.toggleView("register","login");
    }


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
    public void helloClick() {

        /**
         * Display a message
         */
        util.toast("HELLO WORLD");
    }

    
}