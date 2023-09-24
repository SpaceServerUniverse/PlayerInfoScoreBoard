package space.yurisi.playerinfoscoreboard;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import space.yurisi.playerinfoscoreboard.event.player.JoinEvent;
import space.yurisi.playerinfoscoreboard.event.player.QuitEvent;

public final class PlayerInfoScoreBoard extends JavaPlugin {

    @Override
    public void onEnable() {
        TaskManager taskManager = new TaskManager(this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(taskManager), this);
        Bukkit.getPluginManager().registerEvents(new QuitEvent(taskManager), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
