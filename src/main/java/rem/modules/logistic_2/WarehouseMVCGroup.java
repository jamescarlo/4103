package rem.modules.logistic_2;

import javax.inject.Named;
import griffon.core.mvc.MVCGroup;
import org.codehaus.griffon.runtime.core.mvc.AbstractTypedMVCGroup;
import javax.annotation.Nonnull;

@Named("warehouse")
public class WarehouseMVCGroup extends AbstractTypedMVCGroup<WarehouseModel, WarehouseView, WarehouseController> {
    public WarehouseMVCGroup(@Nonnull MVCGroup delegate) {
        super(delegate);
    }
}