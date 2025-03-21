package fr.kara.heria.rushffa.gui.subgui;

import com.google.common.util.concurrent.ExecutionError;
import fr.heriamc.api.HeriaAPI;
import fr.heriamc.api.user.HeriaPlayer;
import fr.heriamc.api.user.rank.HeriaRank;
import fr.heriamc.api.user.unlock.HeriaUnlockable;
import fr.heriamc.bukkit.HeriaBukkit;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.kara.heria.rushffa.RushFFA;
import fr.kara.heria.rushffa.config.BlockEnum;
import fr.kara.heria.rushffa.config.MessageConfigEnum;
import fr.kara.heria.rushffa.data.api.RushFFAData;
import fr.kara.heria.rushffa.gui.SettingGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class BlockGUI extends HeriaMenu {

    private final int[] slots = new int[]{
            20,21,22,23,24,31
    };

    public BlockGUI(Player player) {
        super(player, "Block", 54, true);
    }

    HeriaPlayer heriaPlayer = HeriaAPI.get().getPlayerManager().get(getPlayer().getUniqueId());
    HeriaUnlockable heriaUnlockable = HeriaAPI.get().getUnlockableManager().get(getPlayer().getUniqueId());

    @Override
    public void contents(Inventory inventory) {
        setBorder(inventory, 5);

        int index = 0;
        for (BlockEnum value : BlockEnum.values()) {
            this.insertInteractItem(inventory, slots[index], new ItemBuilder(value.getRepresentation()).setName(value.getName())
                    .onClick(inventoryClickEvent -> {
                        RushFFAData rushFFAData = RushFFA.getInstance().getRushFFADataManager().get(getPlayer().getUniqueId());
                        getPlayer().closeInventory();
                        try {
                            if (value == BlockEnum.SANDSTONE) {
                                rushFFAData.setActualBlock(value.getId());
                                RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                            } else if (value == BlockEnum.GRASS) {
                                if (heriaPlayer.getRank().getPower() > 1) {
                                    rushFFAData.setActualBlock(value.getId());
                                    RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                                    getPlayer().sendMessage(MessageConfigEnum.PREFIX + " §fTu as équipé " + value.getName());
                                    return;
                                }
                            } else if (value == BlockEnum.MELON) {
                                if (heriaPlayer.getRank().getPower() > 1) {
                                    rushFFAData.setActualBlock(value.getId());
                                    RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                                    getPlayer().sendMessage(MessageConfigEnum.PREFIX + " §fTu as équipé " + value.getName());
                                    return;
                                }
                            } else if (value == BlockEnum.SNOW) {
                                if (heriaPlayer.getRank().getPower() > 2) {
                                    rushFFAData.setActualBlock(value.getId());
                                    RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                                    getPlayer().sendMessage(MessageConfigEnum.PREFIX + " §fTu as équipé " + value.getName());
                                    return;
                                }
                            } else {
                                if (heriaUnlockable.isUnlocked(value.getId())) {
                                    rushFFAData.setActualBlock(value.getId());
                                    RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                                    getPlayer().sendMessage(MessageConfigEnum.PREFIX + " §fTu as équipé " + value.getName());
                                    return;
                                }

                                if (rushFFAData.getPoint() >= Integer.parseInt(value.getPrice())) {
                                    HeriaBukkit.get().getMenuManager().open(new ConfirmPurchaseGUI(getPlayer(), HeriaBukkit.get(), this, value));
                                } else {
                                    getPlayer().sendMessage(MessageConfigEnum.PREFIX + " §cVous ne possedez pas assez d'argent !");
                                }
                            }
                        } catch (ExecutionError e){
                            e.printStackTrace();
                        }
                    }).setLoreWithList("", "§8» §7Bloc " + value.getName(), "§8» §7Prix: §d" + value.getPrice() + " " + value.getSubprice(), "", "§e❱ Clique pour acheter"));

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
