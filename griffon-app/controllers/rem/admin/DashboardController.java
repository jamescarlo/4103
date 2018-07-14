package rem.admin;

import griffon.core.artifact.GriffonController;
import griffon.core.controller.ControllerAction;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import griffon.transform.Threading;
import javax.annotation.Nonnull;

import javax.inject.Inject;
import rem.Util;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;

@ArtifactProviderFor(GriffonController.class)
public class DashboardController extends AbstractGriffonController {
    private DashboardModel model;
    private DashboardView view;

    @Inject
    private Util util;

    @MVCMember
    public void setModel(@Nonnull DashboardModel model) {
        this.model = model;
    }

    @MVCMember
    public void setView(@Nonnull DashboardView view) {
        this.view = view;
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void minimize_window() {
        view.stage.setIconified(true);
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void maximize_window() {
        toggleSize();
    }

    public Boolean isMaximized = false;
    public double stage_width;
    public double stage_height;
    public double stage_x;
    public double stage_y;

    public void toggleSize() {
        if(stage_width <= 0) {
            stage_width = view.stage.getWidth();
            stage_height = view.stage.getHeight();
            stage_x = view.stage.getX();
            stage_y = view.stage.getY();
        }

        if(isMaximized) {
            view.stage.setWidth(stage_width);
            view.stage.setHeight(stage_height);
            view.stage.setX(stage_x);
            view.stage.setY(stage_y);
            isMaximized = false;
        } else {
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            view.stage.setX(bounds.getMinX());
            view.stage.setY(bounds.getMinY());
            view.stage.setWidth(bounds.getWidth());
            view.stage.setHeight(bounds.getHeight());
            isMaximized = true;
        }
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void close_window() {
        view.stage.close();
    }
}