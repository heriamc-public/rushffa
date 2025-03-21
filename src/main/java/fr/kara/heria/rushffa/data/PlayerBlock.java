package fr.kara.heria.rushffa.data;

import fr.kara.heria.rushffa.RushFFA;
import fr.kara.heria.rushffa.config.BlockEnum;
import fr.kara.heria.rushffa.config.ItemStorage;
import fr.kara.heria.rushffa.data.api.RushFFAData;
import org.bukkit.entity.Player;

public class PlayerBlock {

    public PlayerBlock(Player player, int slot){
        RushFFAData rushFFAData = RushFFA.getInstance().getRushFFADataManager().get(player.getUniqueId());
        String block = rushFFAData.getActualBlock();

        if (block.equalsIgnoreCase(BlockEnum.SANDSTONE.getId())){
            player.getInventory().setItem(slot, ItemStorage.defaultblocks);
        } else if (block.equalsIgnoreCase(BlockEnum.MELON.getId())) {
            player.getInventory().setItem(slot, ItemStorage.mblocks);
        } else if (block.equalsIgnoreCase(BlockEnum.SNOW.getId())) {
            player.getInventory().setItem(slot, ItemStorage.sblocks);
        } else if (block.equalsIgnoreCase(BlockEnum.BRICK.getId())) {
            player.getInventory().setItem(slot, ItemStorage.bblocks);
        } else if (block.equalsIgnoreCase(BlockEnum.GRASS.getId())) {
            player.getInventory().setItem(slot, ItemStorage.gblocks);
        } else if (block.equalsIgnoreCase(BlockEnum.BOOKSHELF.getId())) {
            player.getInventory().setItem(slot, ItemStorage.pblocks);
        }
    }
}
