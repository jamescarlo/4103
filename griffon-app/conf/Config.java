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
                .e("startupGroups", asList("griffonApp"))
                .e("autoShutdown", true)
            )
            .e("mvcGroups", map()
                .e("griffonApp", map()
                    .e("model", "app.GriffonAppModel")
                    .e("view", "app.GriffonAppView")
                    .e("controller", "app.GriffonAppController")
                )
            );
    }
}