package rem.admin;

import griffon.core.artifact.GriffonController;
import griffon.core.controller.ControllerAction;
import griffon.inject.MVCMember;
import griffon.metadata.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import griffon.transform.Threading;
import javax.annotation.Nonnull;

import rem.DBQuery;
import rem.Util;
import rem.Storage;
import rem.CipherCrypt;

import javax.inject.Inject;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import java.util.Map;
import java.util.HashMap;
import org.apache.commons.collections4.MultiMap;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

@ArtifactProviderFor(GriffonController.class)
public class DashboardController extends AbstractGriffonController {
    private DashboardModel model;
    private DashboardView view;

    @Inject
    private DBQuery db;
    @Inject
    private Util util;
    @Inject
    private Storage storage;
    @Inject
    private CipherCrypt ciphercrypt;

    @MVCMember
    public void setModel(@Nonnull DashboardModel model) {
        this.model = model;
    }

    @MVCMember
    public void setView(@Nonnull DashboardView view) {
        this.view = view;
    }

    @Override
    public void mvcGroupInit(@Nonnull Map<String, Object> args) {
        updateComponents();
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void minimize() {
        view.stage.setIconified(true);
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void maximize() {
        toggleSize();
    }

    public Boolean isMaximized = false;
    public double stage_width;
    public double stage_height;
    public double stage_x;
    public double stage_y;

    public void toggleSize() {
        if(stage_width <= 0) {
            stage_width = view.stage.getWidth();
            stage_height = view.stage.getHeight();
            stage_x = view.stage.getX();
            stage_y = view.stage.getY();
        }

        if(isMaximized) {
            view.stage.setWidth(stage_width);
            view.stage.setHeight(stage_height);
            view.stage.setX(stage_x);
            view.stage.setY(stage_y);
            isMaximized = false;
        } else {
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();

            view.stage.setX(bounds.getMinX());
            view.stage.setY(bounds.getMinY());
            view.stage.setWidth(bounds.getWidth());
            view.stage.setHeight(bounds.getHeight());
            isMaximized = true;
        }
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void close() {
    
    int response = JOptionPane.showConfirmDialog(null, "Do you want to Exit ?", "Warning",
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    if (response == JOptionPane.YES_OPTION) {
            view.stage.close();
    }
        
           
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void account() {
        storage.saveItem("id", "");
        util.toggleView("dashboard", "login");
    }

    private String[] modules = { "" };
    private String last_opened_module = "";

    private void open_module(String module_name) {
        try {
            createMVCGroup(module_name);

            if (last_opened_module != "") {
                getApplication().getMvcGroupManager().findGroup(last_opened_module).destroy();
            }
            last_opened_module = module_name;
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void module1() {
        open_module(modules[0]);
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void module2() {
        open_module(modules[1]);
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void module3() {
        open_module(modules[2]);
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void module4() {
        open_module(modules[3]);
    }

    @ControllerAction
    @Threading(Threading.Policy.INSIDE_UITHREAD_ASYNC)
    public void module5() {
        open_module(modules[4]);
    }

    public String[] getModules(String role) {
        Map<String, String[]> modules = new HashMap();

        String[] hr_2 = {
            "Employee Self-service", 
            ""
        };
        String[] logistic_2 = {
            "Project Management", 
            "Warehouse", 
            "Procurement", 
            "Asset Management"
        };

        modules.put("hr2_admin", hr_2);
        modules.put("logistic2_admin", logistic_2);

        return modules.get(role);
    }

    public void updateModulesButton() {
        view.module1ActionTarget.setText(modules[0]);
        view.module2ActionTarget.setText(modules[1]);
        view.module3ActionTarget.setText(modules[2]);
        view.module4ActionTarget.setText(modules[3]);

        if (modules.length == 5) {
            view.module5ActionTarget.setText(modules[4]);
        } else {
            view.module5ActionTarget.setVisible(false);
        }
    }

    public void updateComponents() {
        //byte[] encrypted_id = (storage.getItem("id")).getBytes();
        //System.out.println(encrypted_id);
        //String id = ciphercrypt.decrypt(encrypted_id);
        //System.out.println(id);
        String id = storage.getItem("id");

        MultiMap query = db.map();
        query.put("table",         "employees");
        query.put("condition",     "user_id = "+ id);

        Map<String, Map> employee_data = db.get(query);

        String employee_name = 
            employee_data.get(0).get("first_name") +" "+
            employee_data.get(0).get("last_name");
        view.employee_name.setText(employee_name);

        MultiMap query2 = db.map();
        query2.put("table",         "users");
        query2.put("condition",     "id = "+ id);

        Map<String, Map> user_data = db.get(query2);
        modules = getModules(user_data.get(0).get("role") +"");
        updateModulesButton();
    }

}