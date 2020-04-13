package io.trickey.SSAGOCraftProxy;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.io.IOException;

public class LoginListener implements Listener {

    Plugin plugin;
    WhitelistChecker wc;

    public LoginListener(Plugin plugin){
        this.plugin = plugin;
        this.wc = new WhitelistChecker(plugin);
    }

    @EventHandler
    public void onNetworkJoin(LoginEvent event) {
        PendingConnection p = event.getConnection();
        plugin.getLogger().info("Checking whitelist status of " + p.getName() + "(" + p.getUniqueId() + ")");
        try {
            if (!wc.isWhitelisted(p)) {
                plugin.getLogger().info(p.getName() + "(" + p.getUniqueId() + ") was not found on the whitelist.");
                disconnect(p);
            } else {
                plugin.getLogger().info(p.getName() + " was found on the whitelist.");
            }
        } catch(IOException e){
            plugin.getLogger().severe("Whitelist Error: " + e.toString());
            disconnect(p, "There was an error whilst joining. Please contact the Minecraft Team.");
        }
    }

    public void disconnect(PendingConnection p, String message){
        disconnect(p, new TextComponent(message));
    }

    public void disconnect(PendingConnection p, BaseComponent message){
        plugin.getLogger().info("Disconnecting " + p.getName());
        p.disconnect(message);
    }

    public void disconnect(PendingConnection p){
        TextComponent message = new TextComponent("Please register your Minecraft Account at https://minecraft.ssago.org");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.ssago.org/"));
        disconnect(p, message);
    }
}
