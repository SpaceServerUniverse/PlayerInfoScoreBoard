package space.yurisi.playerinfoscoreboard.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import space.yurisi.playerinfoscoreboard.TaskManager;

public final class JoinEvent implements Listener {

    private TaskManager taskManager;

    public JoinEvent(TaskManager taskManager){
        this.taskManager = taskManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        this.taskManager.createScoreBoardTask(player);
    }
}
