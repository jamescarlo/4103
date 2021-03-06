package rem.auth;

import griffon.core.artifact.GriffonModel;
import griffon.metadata.ArtifactProviderFor;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonModel;

import javax.annotation.Nonnull;
import java.util.Map;
import griffon.inject.MVCMember;


@ArtifactProviderFor(GriffonModel.class)
public class RegisterModel extends AbstractGriffonModel {

    private RegisterView view;


    @MVCMember
    public void setView(RegisterView view) {
        this.view = view;
    }
}