package fr.kara.heria.rushffa.listeners;

import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.kara.heria.rushffa.RushFFA;
import fr.kara.heria.rushffa.config.*;
import fr.kara.heria.rushffa.data.PlayerInfo;
import fr.kara.heria.rushffa.data.api.RushFFAData;
import fr.kara.heria.rushffa.utils.Title;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {


    private final RushFFA plugin;

    public PlayerDeath(RushFFA plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerDeath(PlayerDeathEvent e) {
        e.setDeathMessage(null);
        Player attacker = e.getEntity().getKiller();
        Player victim = e.getEntity().getPlayer();
        e.getDrops().clear();

        Bukkit.getScheduler().runTaskLater(this.plugin, new Runnable() {
            @Override
            public void run() {
                final PlayerInfo data = PlayerInfo.getPlayerData(victim);
                RushFFAData rushFFAData = RushFFA.getInstance().getRushFFADataManager().get(victim.getUniqueId());

                data.increaseDeaths(1);
                rushFFAData.setDeaths(rushFFAData.getDeaths() + 1);
                RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                data.setWaiting(true);
                data.setKs(0);

                // coté attanquant
                if (attacker != null) {
                    final PlayerInfo data1 = PlayerInfo.getPlayerData(attacker);
                    RushFFAData rushFFAData1 = RushFFA.getInstance().getRushFFADataManager().get(attacker.getUniqueId());
                    attacker.setHealth(attacker.getMaxHealth());
                    data1.increaseKills(1);
                    rushFFAData1.setKills(rushFFAData1.getKills() + 1);
                    rushFFAData1.setPoint(rushFFAData1.getPoint() + 1);
                    data1.setKs(data1.getKs() + 1);
                    if (data1.getKs() > rushFFAData1.getRecordKS()){
                        rushFFAData1.setRecordKS(data1.getKs());
                    }
                    RushFFA.getInstance().getRushFFADataManager().save(rushFFAData1);

                    if (rushFFAData1.getActualEffect().equals(EffectEnum.BLOOD.getId())) {
                        attacker.playEffect(victim.getLocation().add(0.0D, 1.0D, 0.0D), Effect.STEP_SOUND, 152);
                    }

                    attacker.playSound(attacker.getLocation(), rushFFAData1.getActualSong(), 10, 1);
                    for (Player player : Bukkit.getOnlinePlayers()){
                        if (data1.getKs() == 2) {
                            player.sendMessage(MessageConfigEnum.PREFIX + " §c" + data1.getName() + " " + MessageEnum.getRandomDoubleMessage());
                        } else if (data1.getKs() == 3) {
                            player.sendMessage(MessageConfigEnum.PREFIX + " §c" + data1.getName() + " " + MessageEnum.getRandomTripleMessage());
                        } else if (data1.getKs() == 4) {
                            player.sendMessage(MessageConfigEnum.PREFIX + " §c" + data1.getName() + " " + MessageEnum.getRandomQuadraMessage());
                        } else if (data1.getKs() == 5) {
                            player.sendMessage(MessageConfigEnum.PREFIX + " §c" + data1.getName() + " " + MessageEnum.getRandomPentaMessage());
                        } else if (data1.getKs() > 5) {
                            player.sendMessage(MessageConfigEnum.PREFIX + " §c" + data1.getName() + " §7fait un carnage ! §8(§b" + data1.getKs() + "§8)");
                        } else {
                            player.sendMessage(MessageConfigEnum.PREFIX + " §c" + data1.getName() + " §fa tué §c" + data.getName() + " §8(§b" + data1.getKs() + "§8)");
                        }
                    }
                } else {
                    for (Player survival : Bukkit.getOnlinePlayers()) {
                        survival.sendMessage(MessageConfigEnum.PREFIX + " §c" + victim.getName() + "§7 est mort par le §avide");
                    }
                }

                // coté victim
                victim.setHealth(20);
                victim.getInventory().clear();
                victim.getActivePotionEffects().forEach(potionEffect -> victim.removePotionEffect(potionEffect.getType()));
                victim.setMaxHealth(20);
                victim.setHealth(victim.getMaxHealth());
                victim.setGameMode(GameMode.ADVENTURE);
                victim.teleport(SpawnLocation.HUB.getLocation());
                victim.getInventory().setHeldItemSlot(0);
                victim.getInventory().setItem(0, new ItemBuilder(Material.IRON_AXE, 1).setName("§6Jouer§8・§7Clic droit").setInfinityDurability().build());
                victim.getInventory().setItem(1, new ItemBuilder(Material.FEATHER, 1).setName("§fSpectateur§8・§7Clic droit").build());
                victim.getInventory().setItem(7, new ItemBuilder(Material.REDSTONE_COMPARATOR, 1).setName("§cParamètres§8・§7Clic droit").build());
                victim.getInventory().setItem(8, new ItemBuilder(Material.BARRIER, 1).setName("§cQuitter§8・§7Clic droit").build());
            }
        }, 1L);
    }
}
