package app;

import javax.inject.Named;
import griffon.core.mvc.MVCGroup;
import org.codehaus.griffon.runtime.core.mvc.AbstractTypedMVCGroup;
import javax.annotation.Nonnull;

@Named("griffonApp")
public class GriffonAppMVCGroup extends AbstractTypedMVCGroup<GriffonAppModel, GriffonAppView, GriffonAppController> {
    public GriffonAppMVCGroup(@Nonnull MVCGroup delegate) {
        super(delegate);
    }
}