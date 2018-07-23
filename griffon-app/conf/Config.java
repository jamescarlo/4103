import griffon.util.AbstractMapResourceBundle;

import javax.annotation.Nonnull;
import java.util.Map;

import static java.util.Arrays.asList;
import static griffon.util.CollectionUtils.map;

public class Config extends AbstractMapResourceBundle {
    @Override
    protected void initialize(@Nonnull Map<String, Object> entries) {
        map(entries)
            .e("application", map()
                .e("title", "griffon-app")
                .e("startupGroups", asList("guard"))
                .e("autoShutdown", false)
            )
            .e("mvcGroups", map()
                .e("login", map()
                    .e("model", "rem.auth.LoginModel")
                    .e("view", "rem.auth.LoginView")
                    .e("controller", "rem.auth.LoginController")
                )
                .e("guard", map()
                    .e("model", "rem.auth.GuardModel")
                    .e("view", "rem.auth.GuardView")
                    .e("controller", "rem.auth.GuardController")
                )
                .e("register", map()
                    .e("model", "rem.auth.RegisterModel")
                    .e("view", "rem.auth.RegisterView")
                    .e("controller", "rem.auth.RegisterController")
                )
                .e("dashboard", map()
                    .e("model", "rem.admin.DashboardModel")
                    .e("view", "rem.admin.DashboardView")
                    .e("controller", "rem.admin.DashboardController")
                )
                /**
                 * Logistic 2
                 */
                .e("Project Management", map()
                    .e("model", "rem.modules.logistic_2.ProjectManagementModel")
                    .e("view", "rem.modules.logistic_2.ProjectManagementView")
                    .e("controller", "rem.modules.logistic_2.ProjectManagementController")
                )
                .e("Warehouse", map()
                    .e("model", "rem.modules.logistic_2.WarehouseModel")
                    .e("view", "rem.modules.logistic_2.WarehouseView")
                    .e("controller", "rem.modules.logistic_2.WarehouseController")
                )
                .e("Procurement", map()
                    .e("model", "rem.modules.logistic_2.ProcurementModel")
                    .e("view", "rem.modules.logistic_2.ProcurementView")
                    .e("controller", "rem.modules.logistic_2.ProcurementController")
                )
                .e("Asset Management", map()
                    .e("model", "rem.modules.logistic_2.AssetManagementModel")
                    .e("view", "rem.modules.logistic_2.AssetManagementView")
                    .e("controller", "rem.modules.logistic_2.AssetManagementController")
                )
                /**
                 * Demos
                 */
                .e("howtopopulatetable", map()
                    .e("model", "rem.demos.HowToPopulateTableModel")
                    .e("view", "rem.demos.HowToPopulateTableView")
                    .e("controller", "rem.demos.HowToPopulateTableController")
                )
            );
    }
}
