package fr.kara.heria.rushffa;

import fr.heriamc.bukkit.HeriaBukkit;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.kara.heria.rushffa.config.ItemStorage;
import fr.kara.heria.rushffa.config.SpawnLocation;
import fr.kara.heria.rushffa.data.PlayerBlock;
import fr.kara.heria.rushffa.data.PlayerInfo;
import fr.kara.heria.rushffa.config.MessageConfigEnum;
import fr.kara.heria.rushffa.data.api.RushFFAData;
import fr.kara.heria.rushffa.data.api.RushFFADataManager;
import fr.kara.heria.rushffa.data.api.kit.PlayerKits;
import fr.kara.heria.rushffa.listeners.*;
import fr.kara.heria.rushffa.scorboard.ScoreboardManager;
import fr.kara.heria.rushffa.task.DeathVoidTask;
import lombok.Getter;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Getter
public class RushFFA extends JavaPlugin {

    @Getter
    private static RushFFA instance;

    private ScoreboardManager scoreboardManager;
    private ScheduledExecutorService executorMonoThread;
    private ScheduledExecutorService scheduledExecutorService;

    private RushFFADataManager rushFFADataManager;

    @Override
    public void onEnable() {
        instance = this;
        System.out.println("RushFFA Loading - made by kara");
        this.registerListeners();

        Bukkit.getWorld("world").setTime(6000);

        scheduledExecutorService = Executors.newScheduledThreadPool(16);
        executorMonoThread = Executors.newScheduledThreadPool(1);
        scoreboardManager = new ScoreboardManager();
        new DeathVoidTask(this);

        HeriaBukkit bukkit = HeriaBukkit.get();
        this.rushFFADataManager = new RushFFADataManager(bukkit.getApi().getRedisConnection(), bukkit.getApi().getMongoConnection());
    }

    @Override
    public void onDisable() {
        getScoreboardManager().onDisable();
    }

    public void stop() {
    }

    public void registerListeners() {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerJoin(this), this);
        pluginManager.registerEvents(new PlayerQuit(this), this);
        pluginManager.registerEvents(new PlayerDeath(this), this);
        pluginManager.registerEvents(new PlayerEvent(), this);
        pluginManager.registerEvents(new PlayerChat(), this);
    }

    public void startFFA(Player player) {
        final PlayerInfo data = PlayerInfo.getPlayerData(player);
        RushFFAData rushFFAData = RushFFA.getInstance().getRushFFADataManager().get(player.getUniqueId());
        PlayerKits playerKits = rushFFAData.getPlayerKits();
        data.setWaiting(false);
        player.teleport(SpawnLocation.getRandomSpawnLocation());
        player.getInventory().clear();
        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
        player.setMaxHealth(20);
        player.setHealth(player.getMaxHealth());
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().setItem(playerKits.getSword(), ItemStorage.sword);
        player.getInventory().setItem(playerKits.getPickaxe(), ItemStorage.pickaxe);
        player.getInventory().setItem(playerKits.getApple(), ItemStorage.apple);
        player.getInventory().setItem(playerKits.getTnt(), ItemStorage.tnt);
        player.getInventory().setItem(playerKits.getFlint(), ItemStorage.flint);

        new PlayerBlock(player, playerKits.getBlocks());

        player.getInventory().setHelmet(ItemStorage.helmet);
        player.getInventory().setChestplate(ItemStorage.chestplate);
        player.getInventory().setLeggings(ItemStorage.leggins);
        player.getInventory().setBoots(ItemStorage.boots);
    }

    public void spectateur(Player player){
        player.sendMessage(" ");
        player.sendMessage(MessageConfigEnum.PREFIX + " §fBienvenue dans le mode §7Spectateur");
        player.sendMessage("§8■ §fCliquer sur la §cBarrière §fpour quitter le mode §7Spectateur");
        player.sendMessage(" ");

        for (Player online : Bukkit.getOnlinePlayers()){
            online.hidePlayer(player);
        }

        player.playSound(player.getLocation(), Sound.BURP, 10, 1);
        player.getInventory().clear();
        player.getEquipment().clear();
        player.teleport(SpawnLocation.SPEC.getLocation());
        player.setAllowFlight(true);
        player.setFlying(true);
        player.getInventory().setItem(8, new ItemBuilder(Material.BARRIER, 1).setName("§8» §cSortir§8・§7Clic droit").build());
    }
}
