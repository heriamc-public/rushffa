package fr.kara.heria.rushffa.config;

import org.bukkit.Material;

public enum BlockEnum {
    SANDSTONE("sandstone", Material.SANDSTONE, "§7Sandstone", "§7Gratuit", "Gratuit", ""),
    BRICK("brics", Material.BRICK, "§cBrique", "§6200 point", String.valueOf(200), "points"),
    BOOKSHELF("bookshelf", Material.BOOKSHELF, "§dBlibliothèque", "§6300 point", String.valueOf(300), "points"),
    GRASS("grass", Material.GRASS, "§aTerre", "§61000 point", "VIP", ""),
    MELON("melon", Material.MELON_BLOCK, "§2Melon", "§61000 point", "VIP", ""),
    SNOW("snow", Material.SNOW_BLOCK, "§fSnow", "§61000 point", "VIP+", "")
    ;

    private final String id;
    private final Material representation;
    private final String name, pricegui;
    private final String price, subprice;
    

    BlockEnum(String id, Material representation, String name, String pricegui, String price, String subprice) {
        this.id = id;
        this.representation = representation;
        this.name = name;
        this.pricegui = pricegui;
        this.price = price;
        this.subprice = subprice;
    }

    public String getName() {
        return name;
    }

    public String getPricegui() {
        return pricegui;
    }

    public String getPrice() {
        return price;
    }

    public String getId() {
        return "rushffa.blocks." + id;
    }

    public Material getRepresentation() {
        return representation;
    }

    public String getSubprice() {
        return subprice;
    }
}
