package io.trickey.SSAGOCraftProxy;

import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.plugin.Plugin;

import java.net.MalformedURLException;
import java.net.URL;

public class WhitelistChecker {

    public URL endpoint;
    protected Plugin plugin;

    public WhitelistChecker(Plugin plugin){
        this.plugin = plugin;
        try {
            endpoint = new URL("https://minecraft.ssago.org/");
        } catch(MalformedURLException e){
            plugin.getLogger().severe("Bad URL: " + e.toString());
        }
    }

    public boolean isWhitelisted(PendingConnection p){
        return false;
    }
}
