package rem;

import griffon.core.artifact.GriffonService;
import griffon.core.resources.ResourceHandler;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonService;


import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.swing.JOptionPane;




@javax.inject.Singleton
@ArtifactProviderFor(GriffonService.class)
public class Util extends AbstractGriffonService {

    public void toggleView(@Nonnull String oldview, @Nonnull String newview) {

        getApplication().getMvcGroupManager().createMVCGroup(newview);
        getApplication().getWindowManager().show(newview);
        
        getApplication().getMvcGroupManager().findGroup(oldview).destroy(); 
        getApplication().getWindowManager().hide(oldview);
    }


    public void toast(@Nonnull String message) {
    	JOptionPane.showMessageDialog(null, message);
    }
}