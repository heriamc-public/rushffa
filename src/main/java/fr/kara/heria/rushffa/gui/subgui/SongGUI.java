package fr.kara.heria.rushffa.gui.subgui;

import com.google.common.util.concurrent.ExecutionError;
import fr.heriamc.api.HeriaAPI;
import fr.heriamc.api.user.HeriaPlayer;
import fr.heriamc.api.user.unlock.HeriaUnlockable;
import fr.heriamc.bukkit.HeriaBukkit;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.kara.heria.rushffa.RushFFA;
import fr.kara.heria.rushffa.config.BlockEnum;
import fr.kara.heria.rushffa.config.MessageConfigEnum;
import fr.kara.heria.rushffa.config.SongEnum;
import fr.kara.heria.rushffa.data.api.RushFFAData;
import fr.kara.heria.rushffa.gui.SettingGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class SongGUI extends HeriaMenu {
    private final int[] slots = new int[]{
            4,21,22,23,30,31,32
    };

    public SongGUI(Player player) {
        super(player, "Son", 54, true);
    }

    HeriaPlayer heriaPlayer = HeriaAPI.get().getPlayerManager().get(getPlayer().getUniqueId());
    HeriaUnlockable heriaUnlockable = HeriaAPI.get().getUnlockableManager().get(getPlayer().getUniqueId());

    @Override
    public void contents(Inventory inventory) {
        setBorder(inventory, 2);

        int index = 0;
        for (SongEnum value : SongEnum.values()) {
            this.insertInteractItem(inventory, slots[index], new ItemBuilder(value.getItem()).setName(value.getName()).setLoreWithList("", "§8» §7Song " + value.getName(), "§8» §7Prix: " + value.getPrice(), "", "§e❱ Clique pour selectionner")
                    .onClick(inventoryClickEvent -> {
                        RushFFAData rushFFAData = RushFFA.getInstance().getRushFFADataManager().get(getPlayer().getUniqueId());
                        getPlayer().closeInventory();
                        if (value == SongEnum.PLING) {
                            rushFFAData.setActualSong(value.getSong());
                            getPlayer().playSound(getPlayer().getLocation(), value.getSong(), 10, 1);
                            RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                        } else if (value == SongEnum.BURP) {
                            if (heriaPlayer.getRank().getPower() > 1) {
                                rushFFAData.setActualSong(value.getSong());
                                getPlayer().sendMessage(MessageConfigEnum.PREFIX + " §fTu as équipé " + value.getName());
                                getPlayer().playSound(getPlayer().getLocation(), value.getSong(), 10, 1);
                                RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                            }
                        } else if (value == SongEnum.EXP) {
                            if (heriaPlayer.getRank().getPower() > 1) {
                                rushFFAData.setActualSong(value.getSong());
                                getPlayer().sendMessage(MessageConfigEnum.PREFIX + " §fTu as équipé " + value.getName());
                                getPlayer().playSound(getPlayer().getLocation(), value.getSong(), 10, 1);
                                RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                            }
                        } else if (value == SongEnum.WATER) {
                            if (heriaPlayer.getRank().getPower() > 1) {
                                rushFFAData.setActualSong(value.getSong());
                                getPlayer().sendMessage(MessageConfigEnum.PREFIX + " §fTu as équipé " + value.getName());
                                getPlayer().playSound(getPlayer().getLocation(), value.getSong(), 10, 1);
                                RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                            }
                        } else if (value == SongEnum.ANVIL) {
                            if (heriaPlayer.getRank().getPower() > 2) {
                                rushFFAData.setActualSong(value.getSong());
                                getPlayer().sendMessage(MessageConfigEnum.PREFIX + " §fTu as équipé " + value.getName());
                                getPlayer().playSound(getPlayer().getLocation(), value.getSong(), 10, 1);
                                RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                            }
                        } else if (value == SongEnum.SKELETON) {
                            if (heriaPlayer.getRank().getPower() > 2) {
                                rushFFAData.setActualSong(value.getSong());
                                getPlayer().sendMessage(MessageConfigEnum.PREFIX + " §fTu as équipé " + value.getName());
                                getPlayer().playSound(getPlayer().getLocation(), value.getSong(), 10, 1);
                                RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                            }
                        } else if (value == SongEnum.BLAZE) {
                            if (heriaPlayer.getRank().getPower() > 2) {
                                rushFFAData.setActualSong(value.getSong());
                                getPlayer().sendMessage(MessageConfigEnum.PREFIX + " §fTu as équipé " + value.getName());
                                getPlayer().playSound(getPlayer().getLocation(), value.getSong(), 10, 1);
                                RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                            }
                        }
                    }));

            index++;
        }

        inventory.setItem(49, new ItemBuilder(Material.ARROW, 1).setName("§8» §cRetour").build());
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        if (event.getSlot() == 49) {
            HeriaBukkit.get().getMenuManager().open(new SettingGUI(getPlayer()));
        }
    }
}
