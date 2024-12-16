package your.package.path;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Enregistrement de la commande
        this.getCommand("createserver").setExecutor(new ServerCreateCommand());
        getLogger().info("ServerManager plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("ServerManager plugin disabled!");
    }
}
