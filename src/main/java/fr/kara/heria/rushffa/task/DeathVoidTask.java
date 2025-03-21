package fr.kara.heria.rushffa.task;

import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.kara.heria.rushffa.RushFFA;
import fr.kara.heria.rushffa.config.*;
import fr.kara.heria.rushffa.data.PlayerInfo;
import fr.kara.heria.rushffa.data.api.RushFFAData;
import fr.kara.heria.rushffa.utils.Title;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathVoidTask extends BukkitRunnable {
    private final RushFFA plugin;

    public DeathVoidTask(final RushFFA plugin) {
        this.plugin = plugin;
        this.runTaskTimer(plugin, 0L, 1L);
    }

    @Override
    public void run() {
        for (Player player: Bukkit.getOnlinePlayers()) {
            if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                if (player.getLocation().getBlockY() < 60) {

                    final PlayerInfo data = PlayerInfo.getPlayerData(player);
                    RushFFAData rushFFAData = RushFFA.getInstance().getRushFFADataManager().get(player.getUniqueId());
                    data.increaseDeaths(1);
                    rushFFAData.setDeaths(rushFFAData.getDeaths() + 1);
                    RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                    data.setKs(0);
                    data.setWaiting(true);

                    //coté tuer
                    final Player killer = player.getKiller();
                    if (killer != null) {
                        final PlayerInfo data2 = PlayerInfo.getPlayerData(killer);
                        RushFFAData rushFFAData1 = RushFFA.getInstance().getRushFFADataManager().get(killer.getUniqueId());
                        killer.setHealth(killer.getMaxHealth());
                        data2.increaseKills(1);
                        rushFFAData1.setPoint(rushFFAData1.getPoint() + 1);
                        rushFFAData1.setKills(rushFFAData1.getKills() + 1);
                        data2.setKs(data2.getKs() + 1);
                        if (data2.getKs() > rushFFAData1.getRecordKS()){
                            rushFFAData1.setRecordKS(data2.getKs());
                        }
                        RushFFA.getInstance().getRushFFADataManager().save(rushFFAData1);
                        killer.playSound(killer.getLocation(), rushFFAData1.getActualSong(), 10, 1);
                        for (Player survival : Bukkit.getOnlinePlayers()){
                            if (data2.getKs() == 2) {
                                survival.sendMessage(MessageConfigEnum.PREFIX + " §c" + data2.getName() + " " + MessageEnum.getRandomDoubleMessage());
                            } else if (data2.getKs() == 3) {
                                survival.sendMessage(MessageConfigEnum.PREFIX + " §c" + data2.getName() + " " + MessageEnum.getRandomTripleMessage());
                            } else if (data2.getKs() == 4) {
                                survival.sendMessage(MessageConfigEnum.PREFIX + " §c" + data2.getName() + " " + MessageEnum.getRandomQuadraMessage());
                            } else if (data2.getKs() == 5) {
                                survival.sendMessage(MessageConfigEnum.PREFIX + " §c" + data2.getName() + " " + MessageEnum.getRandomPentaMessage());
                            } else if (data2.getKs() > 5) {
                                survival.sendMessage(MessageConfigEnum.PREFIX + " §c" + data2.getName() + " §7fait un carnage ! §8(§b" + data2.getKs() + "§8)");
                            } else {
                                survival.sendMessage(MessageConfigEnum.PREFIX + " §c" + data2.getName() + " §7a tué §c" + player.getName() + " §8(§b" + data2.getKs() + "§8)");
                            }
                        }
                    } else {
                        for (Player survival : Bukkit.getOnlinePlayers()) {
                            survival.sendMessage(MessageConfigEnum.PREFIX + " §c" + player.getName() + "§7 est mort par le §avide");
                        }
                    }

                    player.getInventory().clear();
                    player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
                    player.setMaxHealth(20);
                    player.teleport(SpawnLocation.HUB.getLocation());
                    player.setHealth(player.getMaxHealth());
                    player.setGameMode(GameMode.ADVENTURE);
                    player.getInventory().setHeldItemSlot(0);
                    player.getInventory().setItem(0, new ItemBuilder(Material.IRON_AXE, 1).setName("§6Jouer§8・§7Clic droit").setInfinityDurability().build());
                    player.getInventory().setItem(1, new ItemBuilder(Material.FEATHER, 1).setName("§fSpectateur§8・§7Clic droit").build());
                    player.getInventory().setItem(7, new ItemBuilder(Material.REDSTONE_COMPARATOR, 1).setName("§cParamètres§8・§7Clic droit").build());
                    player.getInventory().setItem(8, new ItemBuilder(Material.BARRIER, 1).setName("§cQuitter§8・§7Clic droit").build());
                }
            } else {
                if (player.getLocation().getBlockY() < 72) {
                    player.getInventory().clear();
                    player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
                    player.setMaxHealth(20);
                    player.teleport(SpawnLocation.HUB.getLocation());
                    player.setHealth(player.getMaxHealth());
                    player.setGameMode(GameMode.ADVENTURE);
                    player.getInventory().setHeldItemSlot(0);
                    player.getInventory().setItem(0, new ItemBuilder(Material.IRON_AXE, 1).setName("§6Jouer§8・§7Clic droit").setInfinityDurability().build());
                    player.getInventory().setItem(1, new ItemBuilder(Material.FEATHER, 1).setName("§fSpectateur§8・§7Clic droit").build());
                    player.getInventory().setItem(7, new ItemBuilder(Material.REDSTONE_COMPARATOR, 1).setName("§cParamètres§8・§7Clic droit").build());
                    player.getInventory().setItem(8, new ItemBuilder(Material.BARRIER, 1).setName("§cQuitter§8・§7Clic droit").build());
                }
            }
        }
    }
}
//><