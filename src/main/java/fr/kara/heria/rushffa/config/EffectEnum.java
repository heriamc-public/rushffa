package fr.kara.heria.rushffa.config;

import org.bukkit.Effect;
import org.bukkit.Material;

public enum EffectEnum {
    NULL("null", null, "§cReset", "§7Gratuit",  Material.BARRIER),
    BLOOD("bloods", Effect.STEP_SOUND, "§cSang", "§3VIP+", Material.REDSTONE),
    ;

    private final String id;
    private final Effect effect;
    private final String name;
    private final String price;
    private final Material item;

    EffectEnum(String id, Effect effect, String name, String price, Material item) {
        this.id = id;
        this.effect = effect;
        this.name = name;
        this.price = price;
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public Effect getEffect() {
        return effect;
    }

    public Material getItem() {
        return item;
    }

    public String getPrice() {
        return price;
    }

    public String getId() {
        return "rushffa.effect." + id;
    }
}
