package rem.modules.hr_3;

import javax.inject.Named;
import griffon.core.mvc.MVCGroup;
import org.codehaus.griffon.runtime.core.mvc.AbstractTypedMVCGroup;
import javax.annotation.Nonnull;

@Named("leave-management")
public class LeaveManagementMVCGroup extends AbstractTypedMVCGroup<LeaveManagementModel, LeaveManagementView, LeaveManagementController> {
    public LeaveManagementMVCGroup(@Nonnull MVCGroup delegate) {
        super(delegate);
    }
}