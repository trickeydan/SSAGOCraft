package io.trickey.SSAGOCraftProxy;

import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WhitelistChecker {

    private final Plugin plugin;

    public WhitelistChecker(Plugin plugin){
        this.plugin = plugin;
    }

    public WhitelistResult isWhitelisted(PendingConnection p) throws IOException {
        URL endpoint = new URL("http://localhost:8000/api/check?uuid=" + p.getUniqueId().toString());
        HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(500);
        con.setReadTimeout(500);
        int status = con.getResponseCode();
        switch(status){
            case 200:
                return WhitelistResult.WHITELISTED;
            case 401:
                return WhitelistResult.NOT_WHITELISTED;
            case 403:
                return WhitelistResult.BANNED;
            default:
                throw new IOException("Received error code " + Integer.toString(status));
        }
    }
}
