package io.trickey.ssagocraft;

import org.bukkit.plugin.java.JavaPlugin;

public final class SSAGOCraftPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new TreeFeller(this), this);
    }
}
