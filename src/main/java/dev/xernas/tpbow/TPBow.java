package dev.xernas.tpbow;

import dev.xernas.tpbow.commands.TPBowCommand;
import dev.xernas.tpbow.events.ArrowLandEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public final class TPBow extends JavaPlugin {

    private static final ItemStack TPBow = new ItemStack(Material.BOW);
    private static TPBow instance;

    @Override
    public void onEnable() {
        instance = this;
        ItemMeta im = TPBow.getItemMeta();
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES,
                ItemFlag.HIDE_UNBREAKABLE);
        im.setUnbreakable(true);
        im.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Teleport Bow");
        PersistentDataContainer pdc = im.getPersistentDataContainer();
        pdc.set(new NamespacedKey(this, "TPBow"), PersistentDataType.STRING, "");
        TPBow.setItemMeta(im);

        this.getCommand("tpbow").setExecutor(new TPBowCommand());
        Bukkit.getPluginManager().registerEvents(new ArrowLandEvent(), this);
    }

    @Override
    public void onDisable() {

    }

    public static ItemStack getTPBow() {
        return TPBow;
    }

    public static TPBow getInstance() {
        return instance;
    }
}
