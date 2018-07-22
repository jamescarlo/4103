package rem.auth;

import griffon.core.artifact.GriffonController;
import griffon.core.controller.ControllerAction;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import rem.Util;
import rem.DBQuery;
import rem.CipherCrypt;
import rem.Storage;

import griffon.transform.Threading;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.util.Map;

@ArtifactProviderFor(GriffonController.class)
public class GuardController extends AbstractGriffonController {
    private GuardModel model;

    @MVCMember
    public void setModel(@Nonnull GuardModel model) {
        this.model = model;
        guard();
    }

    @Inject
    private Util util;
    @Inject
    private DBQuery db;
    @Inject 
    private CipherCrypt ciphercrypt;
    @Inject
    private Storage storage;

    public void guard() {
        String id = storage.getItem("id");
        if (id == "") {
            createMVCGroup("login");
        } else {
            createMVCGroup("dashboard");
        }
    }
}