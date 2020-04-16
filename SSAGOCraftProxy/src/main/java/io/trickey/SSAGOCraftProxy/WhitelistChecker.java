package io.trickey.SSAGOCraftProxy;

import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WhitelistChecker {

    public URL endpoint;
    protected Plugin plugin;

    public WhitelistChecker(Plugin plugin){
        this.plugin = plugin;
    }

    public boolean isWhitelisted(PendingConnection p) throws IOException {
        try {
            endpoint = new URL("http://localhost:8000/api/check?uuid=" + p.getUniqueId().toString());
        } catch (MalformedURLException e) {
            plugin.getLogger().severe("Bad URL: " + e.toString());
        }
        HttpURLConnection con = (HttpURLConnection) endpoint.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(500);
        con.setReadTimeout(500);
        int status = con.getResponseCode();
        switch(status){
            case 200:
                return true;
            case 401:
                return false;
            default:
                throw new IOException("Received error code " + Integer.toString(status));
        }
    }
}
