package fr.kara.heria.rushffa.listeners;

import fr.kara.heria.rushffa.RushFFA;
import fr.kara.heria.rushffa.data.PlayerInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    private final RushFFA plugin;

    public PlayerQuit(RushFFA plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        final PlayerInfo data = PlayerInfo.getPlayerData(player);
        e.setQuitMessage(null);

        RushFFA.getInstance().getScoreboardManager().onLogout(player);
    }
}
