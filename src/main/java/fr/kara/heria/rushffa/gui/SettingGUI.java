package fr.kara.heria.rushffa.gui;

import fr.heriamc.bukkit.HeriaBukkit;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.kara.heria.rushffa.config.ItemStorage;
import fr.kara.heria.rushffa.config.MessageConfigEnum;
import fr.kara.heria.rushffa.data.PlayerInfo;
import fr.kara.heria.rushffa.gui.subgui.BlockGUI;
import fr.kara.heria.rushffa.gui.subgui.EffetGui;
import fr.kara.heria.rushffa.gui.subgui.SongGUI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class SettingGUI extends HeriaMenu {
    public SettingGUI(Player player) {
        super(player, "Settings", 54, true);
    }

    @Override
    public void contents(Inventory inventory) {
        setBorder(inventory, 14);

        inventory.setItem(21, new ItemBuilder(Material.JUKEBOX, 1).setName("§8» §dSon §8(§7kill§8)").build());
        inventory.setItem(22, new ItemBuilder(Material.SKULL_ITEM, 1, SkullType.PLAYER.ordinal()).setName("§8» §aBlocks").setCustomHeadData("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTBmN2Y5YTgwNTk0ZGMyMGU4YWEzZDQ5YTZmMGQwMzkzYTM5Y2I5N2IzNjljOTZlZDkzM2U0Zjk2MTBhZjliZSJ9fX0=").build());
        inventory.setItem(23, new ItemBuilder(Material.BLAZE_POWDER, 1).setName("§8» §bEffet §8(§7kill§8)").build());
        inventory.setItem(31, new ItemBuilder(Material.FEATHER, 1).setName("§8» §fKits").build());

        inventory.setItem(49, new ItemBuilder(Material.ARROW, 1).setName("§8» §cFermé").build());
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        if (event.getSlot() == 49){
            getPlayer().closeInventory();
        }

        if (event.getSlot() == 21) {
            HeriaBukkit.get().getMenuManager().open(new SongGUI(getPlayer()));
        }

        if (event.getSlot() == 22) {
            HeriaBukkit.get().getMenuManager().open(new BlockGUI(getPlayer()));
        }

        if (event.getSlot() == 23){
            HeriaBukkit.get().getMenuManager().open(new EffetGui(getPlayer()));
        }

        if (event.getSlot() == 31){
            getPlayer().closeInventory();
            getPlayer().getInventory().clear();

            final PlayerInfo data = PlayerInfo.getPlayerData(getPlayer());
            data.setEditing(true);
            data.setWaiting(false);

            getPlayer().teleport(new Location(Bukkit.getWorld("world"), 13.5, 85, 229.5, -180, 2));

            getPlayer().sendMessage(MessageConfigEnum.PREFIX + " §7Bienvenue dans le mode §ed'édition de kit");
            getPlayer().sendMessage("§8■ §fCliquer sur la §cBarrière §fpour §asauvegarder§f le §7kit");
            getPlayer().sendMessage(" ");

            getPlayer().getInventory().setItem(0, ItemStorage.sword);
            getPlayer().getInventory().setItem(1, ItemStorage.pickaxe);
            getPlayer().getInventory().setItem(2, new ItemBuilder(Material.GOLDEN_APPLE).setName("§7Pomme en Or").build());
            getPlayer().getInventory().setItem(3, new ItemBuilder(Material.TNT).setName("§cTNT").build());
            getPlayer().getInventory().setItem(4, ItemStorage.flint);
            getPlayer().getInventory().setItem(5, new ItemBuilder(Material.NAME_TAG).setName("§aBlock").build());

            getPlayer().getInventory().setItem(8, new ItemBuilder(Material.BARRIER).setName("§8» §aSauvegarde§8・§7Clic droit").build());

            for (int i = 9; i < getPlayer().getInventory().getSize(); i++) {
                getPlayer().getInventory().setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.ordinal()).setName("§cBloqué").build());
            }
        }
    }
}

//(.)(.) Le code de ma copine