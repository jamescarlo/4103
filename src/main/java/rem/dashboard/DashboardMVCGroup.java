package rem.dashboard;

import javax.inject.Named;
import griffon.core.mvc.MVCGroup;
import org.codehaus.griffon.runtime.core.mvc.AbstractTypedMVCGroup;
import javax.annotation.Nonnull;

@Named("dashboard")
public class DashboardMVCGroup extends AbstractTypedMVCGroup<DashboardModel, DashboardView, DashboardController> {
    public DashboardMVCGroup(@Nonnull MVCGroup delegate) {
        super(delegate);
    }
}