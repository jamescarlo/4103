package rem.modules.logistic_2;

import javax.inject.Named;
import griffon.core.mvc.MVCGroup;
import org.codehaus.griffon.runtime.core.mvc.AbstractTypedMVCGroup;
import javax.annotation.Nonnull;

@Named("asset-management")
public class AssetManagementMVCGroup extends AbstractTypedMVCGroup<AssetManagementModel, AssetManagementView, AssetManagementController> {
    public AssetManagementMVCGroup(@Nonnull MVCGroup delegate) {
        super(delegate);
    }
}