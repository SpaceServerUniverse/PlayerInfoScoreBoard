package space.yurisi.playerinfoscoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import space.yurisi.playerinfoscoreboard.task.ScoreBoardTask;

import java.util.HashMap;

public final class TaskManager {

    private final PlayerInfoScoreBoard main;

    private final HashMap<String, Integer> list = new HashMap<>();

    public TaskManager(PlayerInfoScoreBoard main) {
        this.main = main;
    }

    public void createScoreBoardTask(Player player) {
        ScoreBoardTask task = new ScoreBoardTask(player);
        task.runTaskTimer(this.main, 0L, 10L);
        this.list.put(player.getName(), task.getTaskId());
    }

    public void destroyScoreBoardTask(Player player) {
        Integer task_id = this.list.get(player.getName());
        if (task_id != null) {
            Bukkit.getScheduler().cancelTask(task_id);
        }

    }
}
