package org.jivesoftware.openfire.plugin;

import org.igniterealtime.openfire.plugin.candy.CandyPlugin;
import org.igniterealtime.openfire.plugins.httpfileupload.HttpFileUploadPlugin;
import org.jivesoftware.openfire.container.Plugin;
import org.jivesoftware.openfire.container.PluginManager;
import java.io.File;

public class CombinedPlugin implements Plugin {
    private SearchPlugin searchPlugin;
    private MonitoringPlugin monitoringPlugin;
    private CandyPlugin candyPlugin;
    private HttpFileUploadPlugin httpFileUploadPlugin;

    @Override
    public void initializePlugin(PluginManager manager, File pluginDirectory) {
        // تهيئة Search Plugin
        searchPlugin = new SearchPlugin();
        searchPlugin.initializePlugin(manager, pluginDirectory);

        // تهيئة Monitoring Plugin
        monitoringPlugin = new MonitoringPlugin();
        monitoringPlugin.initializePlugin(manager, pluginDirectory);

        // تهيئة Candy Plugin
        candyPlugin = new CandyPlugin();
        candyPlugin.initializePlugin(manager, pluginDirectory);
        httpFileUploadPlugin = new HttpFileUploadPlugin();
        httpFileUploadPlugin.initializePlugin(manager,pluginDirectory);
        // كود إضافي للتهيئة إذا لزم الأمر
    }

    @Override
    public void destroyPlugin() {
        // تدمير Search Plugin
        if (searchPlugin != null) {
            searchPlugin.destroyPlugin();
        }

        // تدمير Monitoring Plugin
        if (monitoringPlugin != null) {
            monitoringPlugin.destroyPlugin();
        }

        // تدمير Candy Plugin
        if (candyPlugin != null) {
            candyPlugin.destroyPlugin();
        }

        if (httpFileUploadPlugin != null){
            httpFileUploadPlugin.destroyPlugin();
        }

        // كود إضافي للتنظيف إذا لزم الأمر
    }

    // الطرق الوصول إلى الإضافات الفرعية إذا لزم الأمر
    public SearchPlugin getSearchPlugin() {
        return searchPlugin;
    }

    public MonitoringPlugin getMonitoringPlugin() {
        return monitoringPlugin;
    }

    public CandyPlugin getCandyPlugin() {
        return candyPlugin;
    }
    public HttpFileUploadPlugin getHttpFileUploadPlugin(){
        return httpFileUploadPlugin;
    }
}
