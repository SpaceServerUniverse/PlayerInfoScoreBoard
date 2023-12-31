package space.yurisi.playerinfoscoreboard.task;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import space.yurisi.universecore.exception.MoneyNotFoundException;
import space.yurisi.universecore.exception.UserNotFoundException;
import space.yurisi.universeeconomy.UniverseEconomyAPI;

import java.util.Objects;

public final class ScoreBoardTask extends BukkitRunnable {

    private final Player player;

    private int tick = 1;

    private Long coin;

    public ScoreBoardTask(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        Scoreboard scoreboard = createScoreboard();
        Objective objective = scoreboard.getObjective("score");
        //blank
        Objects.requireNonNull(objective).getScore("§r　").setScore(6);
        setOnline(objective, 5);
        //blank
        Objects.requireNonNull(objective).getScore("§r　　").setScore(4);
        setMoney(objective, 3);
        setJob(objective, 2);
        //blank
        Objects.requireNonNull(objective).getScore("§r　　　　").setScore(1);
        setWorld(objective, 0);
        player.setScoreboard(scoreboard);
        changeTick();
    }

    private Scoreboard createScoreboard() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective o = scoreboard.registerNewObjective("score", Criteria.DUMMY, Component.text(this.getTickTitle()));
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        return scoreboard;
    }

    private void setMoney(Objective objective, int score) {
        UniverseEconomyAPI economyAPI = UniverseEconomyAPI.getInstance();
        String unit = economyAPI.getUnit();
        //40tickに1回だけ更新
        if (coin == null || tick % 4 == 0) {
            try {
                coin = economyAPI.getMoney(this.player);
            } catch (UserNotFoundException e) {
                Objects.requireNonNull(objective).getScore("§e所持金: ERROR1").setScore(score);
                return;
            } catch (MoneyNotFoundException e) {
                Objects.requireNonNull(objective).getScore("§e所持金: ERROR2").setScore(score);
                return;
            }
        }
        Objects.requireNonNull(objective).getScore("§e所持金: " + coin + unit).setScore(score);
    }

    private void setWorld(Objective objective, int score) {
        String world_name = this.player.getWorld().getName();
        Objects.requireNonNull(objective).getScore("§bワールド: " + world_name).setScore(score);
    }

    private void setJob(Objective objective, int score) {
        //TODO
        Objects.requireNonNull(objective).getScore("§6仕事: 無職").setScore(score);
    }

    private void setOnline(Objective objective, int score) {
        int online = Bukkit.getServer().getOnlinePlayers().size();
        int max = Bukkit.getServer().getMaxPlayers();
        Objects.requireNonNull(objective).getScore("§6人数: " + online + "/" + max).setScore(score);
    }

    private String getTickTitle() {
        return switch (tick) {
            case 5 -> "§f§l§e§lU§f§lNIVERSE";
            case 6 -> "§f§lU§e§lN§f§lIVERSE";
            case 7 -> "§f§lUN§e§lI§f§lVERSE";
            case 8 -> "§f§lUNI§e§lV§f§lERSE";
            case 9 -> "§f§lUNIV§e§lE§f§lRSE";
            case 10 -> "§f§lUNIVE§e§lR§f§lSE";
            case 11 -> "§f§lUNIVER§e§lS§f§lE";
            case 12 -> "§f§lUNIVERS§e§lE§f§l";
            case 13, 15 -> "§e§lUNIVERSE";
            case 14, 16 -> "§f§lUNIVERSE";
            default -> "§b§lSPACE§e§lSERVER";
        };
    }

    private void changeTick() {
        if (tick == 16) {
            this.tick = 0;
            return;
        }
        this.tick++;
    }
}
