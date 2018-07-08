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
                .e("startupGroups", asList("login"))
                .e("autoShutdown", false)
            )
            .e("mvcGroups", map()
                .e("login", map()
                    .e("model", "rem.auth.LoginModel")
                    .e("view", "rem.auth.LoginView")
                    .e("controller", "rem.auth.LoginController")
                )
                .e("register", map()
                    .e("model", "rem.auth.RegisterModel")
                    .e("view", "rem.auth.RegisterView")
                    .e("controller", "rem.auth.RegisterController")
                )
                .e("dashboard", map()
                    .e("model", "rem.dashboard.DashboardModel")
                    .e("view", "rem.dashboard.DashboardView")
                    .e("controller", "rem.dashboard.DashboardController")
                )
            );
    }
}
