package rem.auth;

import javax.inject.Named;
import griffon.core.mvc.MVCGroup;
import org.codehaus.griffon.runtime.core.mvc.AbstractTypedMVCGroup;
import javax.annotation.Nonnull;

@Named("guard")
public class GuardMVCGroup extends AbstractTypedMVCGroup<GuardModel, GuardView, GuardController> {
    public GuardMVCGroup(@Nonnull MVCGroup delegate) {
        super(delegate);
    }
}