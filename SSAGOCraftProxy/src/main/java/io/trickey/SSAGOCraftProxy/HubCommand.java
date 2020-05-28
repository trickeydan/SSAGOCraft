package io.trickey.SSAGOCraftProxy;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class HubCommand extends Command {

    private final ServerInfo target;

    public HubCommand(){
        super("hub", "bungeecord.server.hub");
        target = ProxyServer.getInstance().getServerInfo("hub");
    }

    @Override
    public void execute(CommandSender sender, String[] strings) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new ComponentBuilder("This command can only be run by a player!").color(ChatColor.RED).create());
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (player.getServer().getInfo().getName().equalsIgnoreCase("hub")) {
            player.sendMessage(new ComponentBuilder("You are already connected to the Hub!").color(ChatColor.RED).create());
            return;
        }
        player.connect(target);
        player.sendMessage(new ComponentBuilder("Welcome to the Hub!").color(ChatColor.GREEN).create());
    }
}
