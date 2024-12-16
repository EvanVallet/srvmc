// FILE: plugin/src/main/java/com/helloworld/HelloWorldPlugin.java
package com.helloworld;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.apache.http.client.utils.URIBuilder;
import java.net.URISyntaxException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import org.bukkit.entity.Player;
import java.util.UUID;

public class HelloWorldPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("HelloWorldPlugin activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("HelloWorldPlugin désactivé.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("create_server")) {
            sender.sendMessage("send create");
            
            // Exécuter la requête HTTP de manière asynchrone pour ne pas bloquer le thread principal
            getServer().getScheduler().runTaskAsynchronously(this, () -> {
                try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                    // Vérifier que le sender est un joueur
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        UUID uuid = player.getUniqueId();

                        // Construire l'URI avec les paramètres
                        URIBuilder uriBuilder = new URIBuilder("http://172.24.121.147:5000/create_server");
                        uriBuilder.addParameter("uuid", uuid.toString());
                        HttpPost request = new HttpPost(uriBuilder.build());
                        String response = httpClient.execute(request, httpResponse ->
                            EntityUtils.toString(httpResponse.getEntity())
                        );
                        getLogger().info("Réponse de l'API : " + response);
                        sender.sendMessage(response);
                    } else {
                        sender.sendMessage("Cette commande doit être exécutée par un joueur.");
                    }
                } catch (IOException | URISyntaxException e) {
                    getLogger().severe("Erreur lors de l'envoi de la requête HTTP : " + e.getMessage());
                }
            });
            return true;
        }
        if (command.getName().equalsIgnoreCase("stop_server")) {
            sender.sendMessage("send stop");

            // Exécuter la requête HTTP de manière asynchrone pour ne pas bloquer le thread principal
            getServer().getScheduler().runTaskAsynchronously(this, () -> {
                try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                    // Vérifier que le sender est un joueur
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        UUID uuid = player.getUniqueId();

                        // Construire l'URI avec les paramètres
                        URIBuilder uriBuilder = new URIBuilder("http://172.24.121.147:5000/stop_server");
                        uriBuilder.addParameter("uuid", uuid.toString());
                        HttpPost request = new HttpPost(uriBuilder.build());

                        // Exécuter la requête et obtenir la réponse
                        String response = httpClient.execute(request, httpResponse ->
                            EntityUtils.toString(httpResponse.getEntity())
                        );

                        getLogger().info("Réponse de l'API : " + response);
                        sender.sendMessage(response);
                    } else {
                        sender.sendMessage("Cette commande doit être exécutée par un joueur.");
                    }
                } catch (IOException | URISyntaxException e) {
                    getLogger().severe("Erreur lors de l'envoi de la requête HTTP : " + e.getMessage());
                    sender.sendMessage("Une erreur s'est produite.");
                }
            });
            return true;
        }
        return false;
    }
}