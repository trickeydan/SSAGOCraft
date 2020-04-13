package io.trickey.SSAGOCraftProxy;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class LoginListener implements Listener {

    Plugin plugin;
    WhitelistChecker wc;

    public LoginListener(Plugin plugin){
        this.plugin = plugin;
        this.wc = new WhitelistChecker(plugin);
    }

    @EventHandler
    public void onNetworkJoin(LoginEvent e) {
        PendingConnection p = e.getConnection();
        plugin.getLogger().info("Checking whitelist status of " + p.getName() + "(" + p.getUniqueId() + ")");
        if(!wc.isWhitelisted(p)){
            plugin.getLogger().info(p.getName() + "(" + p.getUniqueId() + ") was not found on the whitelist.");
            disconnect(p);
        } else {
            plugin.getLogger().info(p.getName() + " was found on the whitelist.");
        }
    }

    public void disconnect(PendingConnection p){
        TextComponent message = new TextComponent("Please register your Minecraft Account at https://minecraft.ssago.org");
        message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://minecraft.ssago.org/"));
        plugin.getLogger().info("Disconnecting " + p.getName());
        p.disconnect(message);
    }
}
