package your.package.path;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;

public class ServerCreateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande doit être exécutée par un joueur !");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("Usage: /createserver <port>");
            return true;
        }

        String port = args[0];

        try {
            // Prépare la requête HTTP
            URL url = new URL("http://<API-SERVER-IP>:5000/create_server");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            // Corps de la requête
            String jsonInputString = "{\"port\": \"" + port + "\"}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Vérifie la réponse
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                sender.sendMessage("Serveur créé avec succès sur le port " + port + " !");
            } else {
                sender.sendMessage("Erreur lors de la création du serveur. Code: " + responseCode);
            }

        } catch (Exception e) {
            sender.sendMessage("Une erreur est survenue : " + e.getMessage());
            e.printStackTrace();
        }

        return true;
    }
}
