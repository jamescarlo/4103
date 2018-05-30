package auth;

import javax.inject.Named;
import griffon.core.mvc.MVCGroup;
import org.codehaus.griffon.runtime.core.mvc.AbstractTypedMVCGroup;
import javax.annotation.Nonnull;

@Named("Register")
public class LoginMVCGroup extends AbstractTypedMVCGroup<RegisterModel, RegisterView, RegisterController> {
    public LoginMVCGroup(@Nonnull MVCGroup delegate) {
        super(delegate);
    }
}