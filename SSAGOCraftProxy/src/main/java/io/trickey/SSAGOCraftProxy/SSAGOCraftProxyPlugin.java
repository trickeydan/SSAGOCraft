package io.trickey.SSAGOCraftProxy;

import net.md_5.bungee.api.plugin.Plugin;

public class SSAGOCraftProxyPlugin extends Plugin {

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, new LoginListener(this));
        getProxy().getPluginManager().registerListener(this, new KickListener(this));
    }
}
