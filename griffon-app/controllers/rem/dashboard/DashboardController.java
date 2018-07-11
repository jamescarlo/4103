package rem.dashboard;

import griffon.core.artifact.GriffonController;
import griffon.core.controller.ControllerAction;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import griffon.transform.Threading;
import javax.annotation.Nonnull;

import javax.inject.Inject;
import rem.Util;

@ArtifactProviderFor(GriffonController.class)
public class DashboardController extends AbstractGriffonController {
    private DashboardModel model;

    @Inject
    private Util util;

    @MVCMember
    public void setModel(@Nonnull DashboardModel model) {
        this.model = model;
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void minimize_window() {
        util.toast("Minimize Window");
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void maximize_window() {
        util.toast("Maximize Window");
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void close_window() {
        util.toast("Close Window");
    }
}