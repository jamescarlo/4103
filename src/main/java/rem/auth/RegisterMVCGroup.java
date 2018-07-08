package rem.auth;

import javax.inject.Named;
import griffon.core.mvc.MVCGroup;
import org.codehaus.griffon.runtime.core.mvc.AbstractTypedMVCGroup;
import javax.annotation.Nonnull;

@Named("register")
public class RegisterMVCGroup extends AbstractTypedMVCGroup<RegisterModel, RegisterView, RegisterController> {
    public RegisterMVCGroup(@Nonnull MVCGroup delegate) {
        super(delegate);
    }
}