package rem.modules.core_2;

import javax.inject.Named;
import griffon.core.mvc.MVCGroup;
import org.codehaus.griffon.runtime.core.mvc.AbstractTypedMVCGroup;
import javax.annotation.Nonnull;

@Named("project-management")
public class ProjectManagementMVCGroup extends AbstractTypedMVCGroup<ProjectManagementModel, ProjectManagementView, ProjectManagementController> {
    public ProjectManagementMVCGroup(@Nonnull MVCGroup delegate) {
        super(delegate);
    }
}