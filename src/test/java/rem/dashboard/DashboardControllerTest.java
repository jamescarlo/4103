package rem.dashboard;

import griffon.core.test.GriffonUnitRule;
import griffon.core.test.TestFor;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.fail;

@TestFor(DashboardController.class)
public class DashboardControllerTest {
    static {
        // force initialization JavaFX Toolkit
        new javafx.embed.swing.JFXPanel();
    }

    private DashboardController controller;

    @Rule
    public final GriffonUnitRule griffon = new GriffonUnitRule();

    @Test
    public void smokeTest() {
        fail("Not yet implemented!");
    }
}