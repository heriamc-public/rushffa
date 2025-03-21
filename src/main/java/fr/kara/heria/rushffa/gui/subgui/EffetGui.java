package fr.kara.heria.rushffa.gui.subgui;

import fr.heriamc.api.HeriaAPI;
import fr.heriamc.api.user.HeriaPlayer;
import fr.heriamc.api.user.unlock.HeriaUnlockable;
import fr.heriamc.bukkit.HeriaBukkit;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.utils.ItemBuilder;
import fr.kara.heria.rushffa.RushFFA;
import fr.kara.heria.rushffa.config.EffectEnum;
import fr.kara.heria.rushffa.config.MessageConfigEnum;
import fr.kara.heria.rushffa.config.SongEnum;
import fr.kara.heria.rushffa.data.api.RushFFAData;
import fr.kara.heria.rushffa.gui.SettingGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class EffetGui extends HeriaMenu {

    private final int[] slots = new int[]{
            4,21
    };

    public EffetGui(Player player) {
        super(player, "Effet", 54, true);
    }

    HeriaPlayer heriaPlayer = HeriaAPI.get().getPlayerManager().get(getPlayer().getUniqueId());
    HeriaUnlockable heriaUnlockable = HeriaAPI.get().getUnlockableManager().get(getPlayer().getUniqueId());

    @Override
    public void contents(Inventory inventory) {
        setBorder(inventory, 3);

        int index = 0;
        for (EffectEnum value : EffectEnum.values()) {
            this.insertInteractItem(inventory, slots[index], new ItemBuilder(value.getItem()).setName(value.getName()).setLoreWithList("", "§8» §7Effet " + value.getName(), "§8» §7Prix: " + value.getPrice(), "", "§e❱ Clique pour selectionner")
                    .onClick(inventoryClickEvent -> {
                        RushFFAData rushFFAData = RushFFA.getInstance().getRushFFADataManager().get(getPlayer().getUniqueId());
                        getPlayer().closeInventory();
                        if (value == EffectEnum.NULL) {
                            rushFFAData.setActualEffect(value.getId());
                            RushFFA.getInstance().getRushFFADataManager().save(rushFFAData);
                        } else {
                            if (heriaPlayer.getRank().getPower() > 2) {
                                rushFFAData.setActualEffect(value.getId());
                                getPlayer().sendMessage(MessageConfigEnum.PREFIX + " §fTu as équipé " + value.getName());
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
