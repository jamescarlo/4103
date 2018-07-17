package rem.demos;

import javax.inject.Named;
import griffon.core.mvc.MVCGroup;
import org.codehaus.griffon.runtime.core.mvc.AbstractTypedMVCGroup;
import javax.annotation.Nonnull;

@Named("how-to-populate-table")
public class HowToPopulateTableMVCGroup extends AbstractTypedMVCGroup<HowToPopulateTableModel, HowToPopulateTableView, HowToPopulateTableController> {
    public HowToPopulateTableMVCGroup(@Nonnull MVCGroup delegate) {
        super(delegate);
    }
}