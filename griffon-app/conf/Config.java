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
                .e("app", map()
                    .e("model", "org.example.AppModel")
                    .e("view", "org.example.AppView")
                    .e("controller", "org.example.AppController")
                )
                .e("login", map()
                    .e("model", "auth.LoginModel")
                    .e("view", "auth.LoginView")
                    .e("controller", "auth.LoginController")
                )
            );
    }
}