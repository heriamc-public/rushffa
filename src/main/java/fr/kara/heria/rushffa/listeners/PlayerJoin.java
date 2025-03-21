package fr.kara.heria.rushffa.listeners;

import fr.heriamc.api.HeriaAPI;
import fr.heriamc.api.user.HeriaPlayer;
import fr.heriamc.bukkit.HeriaBukkit;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.kara.heria.rushffa.RushFFA;
import fr.kara.heria.rushffa.config.*;
import fr.kara.heria.rushffa.data.PlayerInfo;
import fr.kara.heria.rushffa.data.api.RushFFAData;
import fr.kara.heria.rushffa.data.api.RushFFADataManager;
import fr.kara.heria.rushffa.utils.Nametag;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private final RushFFA plugin;

    public PlayerJoin(RushFFA plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        PlayerInfo data = PlayerInfo.getPlayerData(player);
        e.setJoinMessage(null);
        RushFFAData rushFFAData = RushFFA.getInstance().getRushFFADataManager().createOrLoad(player.getUniqueId());

        RushFFA.getInstance().getScoreboardManager().onLogin(player);
        for (int i = 0; i < 100; i++) {
            player.sendMessage("");
        }

        player.sendMessage(MessageConfigEnum.PREFIX + " §f§lINFO");
        player.sendMessage(" §8■ §7Les alliances sont §cinterdites§7.");
        player.sendMessage(" §8■ §7Ne pas dépasser la limite de §a20 CPS");
        player.sendMessage(" §8■ §7Les Towers sont strictement §cinterdites§7.");
        player.sendMessage("");
        player.sendMessage(" §8■ §7Développeur: §bkara");
        player.sendMessage(" §8■ §fBon jeu §6!");
        player.sendMessage(" ");

        player.teleport(SpawnLocation.HUB.getLocation());
        HeriaPlayer heriaPlayer = HeriaAPI.get().getPlayerManager().get(player.getUniqueId());
        Nametag.setNameTag(player, heriaPlayer.getRank().getPrefix(), "", heriaPlayer.getRank().getTabPriority());
        data.setKill(rushFFAData.getKills());
        data.setDeath(rushFFAData.getDeaths());
        data.setWaiting(true);

        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.setHealth(20);
        player.setExp(0);
        player.setLevel(0);
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));


        player.setAllowFlight(false);
        player.setFlying(false);

        player.getInventory().setBoots(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setHelmet(null);

        player.getInventory().setHeldItemSlot(0);
        player.getInventory().setItem(0, new ItemBuilder(Material.IRON_AXE, 1).setName("§6Jouer§8・§7Clic droit").setInfinityDurability().build());
        player.getInventory().setItem(1, new ItemBuilder(Material.FEATHER, 1).setName("§fSpectateur§8・§7Clic droit").build());
        player.getInventory().setItem(7, new ItemBuilder(Material.REDSTONE_COMPARATOR, 1).setName("§cParamètres§8・§7Clic droit").build());
        player.getInventory().setItem(8, new ItemBuilder(Material.BARRIER, 1).setName("§cQuitter§8・§7Clic droit").build());

        for (Player online : Bukkit.getOnlinePlayers())
            online.showPlayer(player);
    }
}
