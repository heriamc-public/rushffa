package fr.kara.heria.rushffa.gui.subgui;

import fr.heriamc.api.HeriaAPI;
import fr.heriamc.api.user.unlock.HeriaUnlockable;
import fr.heriamc.bukkit.HeriaBukkit;
import fr.heriamc.bukkit.menu.HeriaMenu;
import fr.heriamc.bukkit.menu.confirm.ConfirmMenu;
import fr.kara.heria.rushffa.RushFFA;
import fr.kara.heria.rushffa.config.BlockEnum;
import fr.kara.heria.rushffa.data.api.RushFFAData;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.function.Consumer;

public class ConfirmPurchaseGUI extends ConfirmMenu {

    private final BlockEnum blockEnum;

    public ConfirmPurchaseGUI(Player player, HeriaBukkit bukkit, HeriaMenu before, BlockEnum blockEnum) {
        super(player, "Confirmer l'achat de " + blockEnum.getId(), bukkit, before, confirmPlayer -> {
            player.closeInventory();
            player.sendMessage("missing-string");


            HeriaUnlockable heriaUnlockable = HeriaAPI.get().getUnlockableManager().get(player.getUniqueId());
            heriaUnlockable.unlock(blockEnum.getId());

            HeriaAPI.get().getUnlockableManager().save(heriaUnlockable);
        });

        this.blockEnum = blockEnum;
    }


    @Override
    public void inventory(Inventory inventory) {
        setBorder(inventory, DyeColor.ORANGE.getData());
    }
}
