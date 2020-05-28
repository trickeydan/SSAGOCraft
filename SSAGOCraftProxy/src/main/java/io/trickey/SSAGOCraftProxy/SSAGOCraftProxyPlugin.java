package io.trickey.SSAGOCraftProxy;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class SSAGOCraftProxyPlugin extends Plugin {

    @Override
    public void onEnable() {
        PluginManager pm = getProxy().getPluginManager();
        pm.registerListener(this, new LoginListener(this));
        pm.registerListener(this, new KickListener(this));
        pm.registerCommand(this, new HubCommand());
    }
}
