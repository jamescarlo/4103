package auth;

import javax.inject.Named;
import griffon.core.mvc.MVCGroup;
import org.codehaus.griffon.runtime.core.mvc.AbstractTypedMVCGroup;
import javax.annotation.Nonnull;

@Named("login")
public class LoginMVCGroup extends AbstractTypedMVCGroup<LoginModel, LoginView, LoginController> {
    public LoginMVCGroup(@Nonnull MVCGroup delegate) {
        super(delegate);
    }
}