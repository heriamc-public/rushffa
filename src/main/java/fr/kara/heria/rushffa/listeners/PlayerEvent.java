package fr.kara.heria.rushffa.listeners;

import fr.heriamc.api.HeriaAPI;
import fr.heriamc.api.server.HeriaServer;
import fr.heriamc.api.server.HeriaServerType;
import fr.heriamc.bukkit.HeriaBukkit;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.heriamc.proxy.packet.SendPlayerPacket;
import fr.kara.heria.rushffa.RushFFA;
import fr.kara.heria.rushffa.config.ItemStorage;
import fr.kara.heria.rushffa.config.KitEnum;
import fr.kara.heria.rushffa.config.SpawnLocation;
import fr.kara.heria.rushffa.data.PlayerBlock;
import fr.kara.heria.rushffa.data.PlayerInfo;
import fr.kara.heria.rushffa.data.api.RushFFAData;
import fr.kara.heria.rushffa.data.api.kit.PlayerKits;
import fr.kara.heria.rushffa.gui.SettingGUI;
import lombok.Getter;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class PlayerEvent implements Listener {

    @Getter
    private static final List<Block> blocks = new ArrayList<>();
    @Getter
    private static final List<Block> pblocks = new ArrayList<>();

    @EventHandler
    public void DamageEvent(EntityDamageByEntityEvent e) {
        Player attacker = (Player) e.getDamager();
        Player victim = (Player) e.getEntity();
        final PlayerInfo data = PlayerInfo.getPlayerData(attacker);
        final PlayerInfo data1 = PlayerInfo.getPlayerData(victim);

        if (data.isSpectateur() || data.isWaiting()) {
            e.setCancelled(true);
        }

        if (attacker.getGameMode().equals(GameMode.CREATIVE) || attacker.getGameMode().equals(GameMode.SPECTATOR)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void voidCancel(EntityDamageEvent e){
        Player victim = (Player) e.getEntity();
        final PlayerInfo data = PlayerInfo.getPlayerData(victim);

        if (data.isSpectateur() || data.isWaiting()){
            e.setCancelled(true);
        }

        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }

        if (e.getCause() == EntityDamageEvent.DamageCause.FIRE){
            e.setCancelled(true);
        }

        if (e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK){
            e.setCancelled(true);
        }

        if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void placeBlock(BlockPlaceEvent e) {
        Player victim = e.getPlayer();
        final PlayerInfo data = PlayerInfo.getPlayerData(victim);

        if (data.isWaiting()) {
            e.setCancelled(true);
        } else {
            if (e.getBlock().getType().equals(Material.SANDSTONE) || e.getBlock().getType().equals(Material.BOOKSHELF) || e.getBlock().getType().equals(Material.MELON_BLOCK) || e.getBlock().getType().equals(Material.SNOW_BLOCK) || e.getBlock().getType().equals(Material.GRASS) || e.getBlock().getType().equals(Material.BRICK)) {
                Material blockType = e.getBlock().getType();

                Bukkit.getScheduler().runTaskLater(RushFFA.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                if (blockType.equals(Material.SANDSTONE)) {
                                    victim.getInventory().addItem(ItemStorage.adefaultblocks);
                                } else if (blockType.equals(Material.BRICK)) {
                                    victim.getInventory().addItem(ItemStorage.abblocks);
                                } else if (blockType.equals(Material.BOOKSHELF)) {
                                    victim.getInventory().addItem(ItemStorage.apblocks);
                                } else if (blockType.equals(Material.GRASS)) {
                                    victim.getInventory().addItem(ItemStorage.agblocks);
                                } else if (blockType.equals(Material.MELON_BLOCK)) {
                                    victim.getInventory().addItem(ItemStorage.amblocks);
                                } else if (blockType.equals(Material.SNOW_BLOCK)) {
                                    victim.getInventory().addItem(ItemStorage.asblocks);
                                }
                            }
                        }, 1L);

                Bukkit.getScheduler().runTaskLater(RushFFA.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        e.getBlock().setType(Material.AIR);
                    }
                }, 200L);
            }

            if (e.getBlock().getLocation().getBlockY() >= 72) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Material blockType = e.getBlock().getType();

        if (blockType == Material.SANDSTONE || blockType.equals(Material.BRICK) || blockType.equals(Material.BOOKSHELF) || blockType.equals(Material.SNOW_BLOCK) || blockType.equals(Material.GRASS) || blockType.equals(Material.MELON_BLOCK)) {
            e.getBlock().setType(Material.AIR);
        } else if (blockType.equals(Material.TNT)) {
            e.getBlock().setType(Material.AIR);
            e.getPlayer().getInventory().addItem(ItemStorage.atnt);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void WeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void dropItem(PlayerDropItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockSpread(BlockSpreadEvent event) {
        if (event.getSource().getType() == Material.FIRE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void interactionItem(PlayerInteractEvent e){
        Player player = e.getPlayer();
        final PlayerInfo data = PlayerInfo.getPlayerData(player);

        if (player.getGameMode().equals(GameMode.CREATIVE)){
            Bukkit.getPlayer(player.getName()).kickPlayer("pk tu triche fdp");
        }

        if (data.isWaiting()) {
            if (e.hasItem()) {
                e.setCancelled(true);
                if (e.getAction() != Action.LEFT_CLICK_AIR) {
                    if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Jouer§8・§7Clic droit")) {
                        RushFFA.getInstance().startFFA(player);
                    }
                    if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fSpectateur§8・§7Clic droit")) {
                        RushFFA.getInstance().spectateur(player);
                    }
                    if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cParamètres§8・§7Clic droit")) {
                        HeriaBukkit.get().getMenuManager().open(new SettingGUI(player));
                    }
                    if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cQuitter§8・§7Clic droit")) {
                        HeriaServer heriaServer = HeriaAPI.get().getServerManager().getWithLessPlayers(HeriaServerType.HUB);
                        HeriaAPI.get().getMessaging().send(new SendPlayerPacket(player.getUniqueId(), heriaServer.getName()));
                    }
                    if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cSortir§8・§7Clic droit")) {
                        for (Player online : Bukkit.getOnlinePlayers()){
                            online.showPlayer(player);
                        }

                        player.setAllowFlight(false);
                        player.setFlying(false);
                        player.teleport(SpawnLocation.HUB.getLocation());
                        player.getInventory().setHeldItemSlot(0);
                        player.getInventory().setItem(0, new ItemBuilder(Material.IRON_AXE, 1).setName("§6Jouer§8・§7Clic droit").setInfinityDurability().build());
                        player.getInventory().setItem(1, new ItemBuilder(Material.FEATHER, 1).setName("§fSpectateur§8・§7Clic droit").build());
                        player.getInventory().setItem(7, new ItemBuilder(Material.REDSTONE_COMPARATOR, 1).setName("§cParamètres§8・§7Clic droit").build());
                        player.getInventory().setItem(8, new ItemBuilder(Material.BARRIER, 1).setName("§cQuitter§8・ §7Clic droit").build());
                    }
                }
            }
        } else if (data.isEditing()) {
            if (e.hasItem()) {
                e.setCancelled(true);
                if (e.getAction() != Action.LEFT_CLICK_AIR) {
                    if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aSauvegarde§8・§7Clic droit")) {
                        RushFFAData rushFFAData = RushFFA.getInstance().getRushFFADataManager().get(player.getUniqueId());
                        PlayerKits playerKits = rushFFAData.getPlayerKits();

                        //System.out.println("1");
                        for (int i = 0; i < 8; i++) {
                            if (player.getInventory().getItem(i) != null) {
                                Material item = player.getInventory().getItem(i).getType();
                                //System.out.println("Name: " + player.getInventory().getItem(i).getItemMeta().getDisplayName() + " | Slot: " + i + " | Item: " + player.getInventory().getItem(i).getType());
                                if (item.equals(Material.IRON_SWORD)) {playerKits.setSword(i);}
                                if (item.equals(Material.IRON_PICKAXE)) {playerKits.setPickaxe(i);}
                                if (item.equals(Material.GOLDEN_APPLE)) {playerKits.setApple(i);}
                                if (item.equals(Material.TNT)) {playerKits.setTnt(i);}
                                if (item.equals(Material.FLINT_AND_STEEL)) {playerKits.setFlint(i);}
                                if (item.equals(Material.NAME_TAG)) {playerKits.setBlocks(i);}
                            }
                        }
                        RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                        //System.out.println("2");
                        data.setEditing(false);
                        data.setWaiting(true);
                        player.getInventory().clear();
                        player.playSound(player.getLocation(), "note.pling", 10, 1);
                        player.teleport(SpawnLocation.HUB.getLocation());
                        player.getInventory().setHeldItemSlot(0);
                        player.getInventory().setItem(0, new ItemBuilder(Material.IRON_AXE, 1).setName("§6Jouer§8・§7Clic droit").setInfinityDurability().build());
                        player.getInventory().setItem(1, new ItemBuilder(Material.FEATHER, 1).setName("§fSpectateur§8・§7Clic droit").build());
                        player.getInventory().setItem(7, new ItemBuilder(Material.REDSTONE_COMPARATOR, 1).setName("§cParamètres§8・§7Clic droit").build());
                        player.getInventory().setItem(8, new ItemBuilder(Material.BARRIER, 1).setName("§cQuitter§8・ §7Clic droit").build());
                    }
                }
            }
        }
    }

    @EventHandler
    public void hungerMeterChange(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryChange(InventoryClickEvent e) {
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem != null && clickedItem.hasItemMeta()) {
            ItemMeta meta = clickedItem.getItemMeta();

            if (meta.hasDisplayName()) {
                String displayName = meta.getDisplayName();

                if (displayName.equals("§cBloqué") || displayName.equals("§8» §aSauvegarde§8・§7Clic droit")) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onTNTExplode(EntityExplodeEvent e) {
        if (e.getEntity().getType() == EntityType.PRIMED_TNT) {
            e.blockList().clear();

            e.setYield(5.0f);

            for (Player player : e.getEntity().getWorld().getPlayers()) {
                if (player.getLocation().distance(e.getLocation()) < 5) {
                    Vector direction = player.getLocation().toVector().subtract(e.getLocation().toVector()).normalize();
                    direction.setY(1.2);
                    direction.setX(direction.getX() * 0.5);
                    direction.setZ(direction.getZ() * 0.5);

                    player.setVelocity(direction.multiply(10.0));
                }
            }
        }
    }
}
