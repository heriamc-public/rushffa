package fr.kara.heria.rushffa.config;

import fr.heriamc.bukkit.utils.ItemBuilder;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

@Getter
public class ItemStorage {
    public static final ItemStack sword = (new ItemBuilder(Material.IRON_SWORD)).setName("§7Épée en Fer").flag(ItemFlag.HIDE_ATTRIBUTES).setInfinityDurability().addEnchant(Enchantment.DAMAGE_ALL, 2).flag(ItemFlag.HIDE_UNBREAKABLE).build();

    public static final ItemStack pickaxe = (new ItemBuilder(Material.IRON_PICKAXE)).setName("§7Pioche en Fer").flag(ItemFlag.HIDE_ATTRIBUTES).setInfinityDurability().addEnchant(Enchantment.DIG_SPEED, 4).flag(ItemFlag.HIDE_UNBREAKABLE).build();

    public static final ItemStack apple = (new ItemBuilder(Material.GOLDEN_APPLE, 5)).setName("§7Pomme en Or").flag(ItemFlag.HIDE_ATTRIBUTES).build();
    public static final ItemStack tnt = (new ItemBuilder(Material.TNT, 7)).setName("§cTNT").flag(ItemFlag.HIDE_ATTRIBUTES).build();
    public static final ItemStack atnt = (new ItemBuilder(Material.TNT, 1)).setName("§cTNT").flag(ItemFlag.HIDE_ATTRIBUTES).build();
    public static final ItemStack flint = (new ItemBuilder(Material.FLINT_AND_STEEL)).setName("§eBriquet").flag(ItemFlag.HIDE_ATTRIBUTES).setInfinityDurability().build();

    public static final ItemStack defaultblocks = (new ItemBuilder(Material.SANDSTONE, 64)).setName("§7Sandstone").flag(ItemFlag.HIDE_ATTRIBUTES).build();
    public static final ItemStack adefaultblocks = (new ItemBuilder(Material.SANDSTONE, 1)).setName("§7Sandstone").flag(ItemFlag.HIDE_ATTRIBUTES).build();
    public static final ItemStack mblocks = (new ItemBuilder(Material.MELON_BLOCK, 64)).setName("§2Melon").flag(ItemFlag.HIDE_ATTRIBUTES).build();
    public static final ItemStack amblocks = (new ItemBuilder(Material.MELON_BLOCK, 1)).setName("§2Melon").flag(ItemFlag.HIDE_ATTRIBUTES).build();
    public static final ItemStack sblocks = (new ItemBuilder(Material.SNOW_BLOCK, 64)).setName("§fSnow").flag(ItemFlag.HIDE_ATTRIBUTES).build();
    public static final ItemStack asblocks = (new ItemBuilder(Material.SNOW_BLOCK, 1)).setName("§fSnow").flag(ItemFlag.HIDE_ATTRIBUTES).build();
    public static final ItemStack bblocks = (new ItemBuilder(Material.BRICK, 64)).setName("§cBrique").flag(ItemFlag.HIDE_ATTRIBUTES).build();
    public static final ItemStack abblocks = (new ItemBuilder(Material.BRICK, 1)).setName("§cBrique").flag(ItemFlag.HIDE_ATTRIBUTES).build();
    public static final ItemStack gblocks = (new ItemBuilder(Material.GRASS, 64)).setName("§aTerre").flag(ItemFlag.HIDE_ATTRIBUTES).build();
    public static final ItemStack agblocks = (new ItemBuilder(Material.GRASS, 1)).setName("§aTerre").flag(ItemFlag.HIDE_ATTRIBUTES).build();
    public static final ItemStack pblocks = (new ItemBuilder(Material.BOOKSHELF, 64)).setName("§dBlibliothèque").flag(ItemFlag.HIDE_ATTRIBUTES).build();
    public static final ItemStack apblocks = (new ItemBuilder(Material.BOOKSHELF, 1)).setName("§dBlibliothèque").flag(ItemFlag.HIDE_ATTRIBUTES).build();


    public static final ItemStack helmet = (new ItemBuilder(Material.LEATHER_HELMET, 1)).setName("§bCasque").flag(ItemFlag.HIDE_ATTRIBUTES).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setInfinityDurability().build();
    public static final ItemStack chestplate = (new ItemBuilder(Material.IRON_CHESTPLATE, 1)).setName("§bPull de kara").flag(ItemFlag.HIDE_ATTRIBUTES).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).setInfinityDurability().build();
    public static final ItemStack leggins = (new ItemBuilder(Material.LEATHER_LEGGINGS, 1)).setName("§bPantalons").flag(ItemFlag.HIDE_ATTRIBUTES).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setInfinityDurability().build();
    public static final ItemStack boots = (new ItemBuilder(Material.LEATHER_BOOTS, 1)).setName("§bCHaussure").flag(ItemFlag.HIDE_ATTRIBUTES).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setInfinityDurability().build();

    public static final ItemStack red_helmet = (new ItemBuilder(Material.LEATHER_HELMET)).setName(ChatColor.RED + "Casque").flag(ItemFlag.HIDE_ATTRIBUTES).setInfinityDurability().setLeatherArmorColor(Color.RED).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).build();

    public static final ItemStack red_chestplate = (new ItemBuilder(Material.LEATHER_CHESTPLATE)).setName(ChatColor.RED + "Plastron").flag(ItemFlag.HIDE_ATTRIBUTES).setInfinityDurability().setLeatherArmorColor(Color.RED).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).build();

    public static final ItemStack red_leggings = (new ItemBuilder(Material.LEATHER_LEGGINGS)).setName(ChatColor.RED + "Jambières").flag(ItemFlag.HIDE_ATTRIBUTES).setInfinityDurability().setLeatherArmorColor(Color.RED).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).build();

    public static final ItemStack red_boots = (new ItemBuilder(Material.LEATHER_BOOTS)).setName(ChatColor.RED + "Bottes").flag(ItemFlag.HIDE_ATTRIBUTES).setInfinityDurability().setLeatherArmorColor(Color.RED).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).build();

    public static final ItemStack blue_helmet = (new ItemBuilder(Material.LEATHER_HELMET)).setName(ChatColor.BLUE + "Casque").flag(ItemFlag.HIDE_ATTRIBUTES).setInfinityDurability().setLeatherArmorColor(Color.BLUE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).build();

    public static final ItemStack blue_chestplate = (new ItemBuilder(Material.LEATHER_CHESTPLATE)).setName(ChatColor.BLUE + "Plastron").flag(ItemFlag.HIDE_ATTRIBUTES).setInfinityDurability().setLeatherArmorColor(Color.BLUE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).build();

    public static final ItemStack blue_leggings = (new ItemBuilder(Material.LEATHER_LEGGINGS)).setName(ChatColor.BLUE + "Jambières").flag(ItemFlag.HIDE_ATTRIBUTES).setInfinityDurability().setLeatherArmorColor(Color.BLUE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).build();

    public static final ItemStack blue_boots = (new ItemBuilder(Material.LEATHER_BOOTS)).setName(ChatColor.BLUE + "Bottes").flag(ItemFlag.HIDE_ATTRIBUTES).setInfinityDurability().setLeatherArmorColor(Color.BLUE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).build();
}

