package rem.modules.logistic_2;

import javax.inject.Named;
import griffon.core.mvc.MVCGroup;
import org.codehaus.griffon.runtime.core.mvc.AbstractTypedMVCGroup;
import javax.annotation.Nonnull;

@Named("procurement")
public class ProcurementMVCGroup extends AbstractTypedMVCGroup<ProcurementModel, ProcurementView, ProcurementController> {
    public ProcurementMVCGroup(@Nonnull MVCGroup delegate) {
        super(delegate);
    }
}