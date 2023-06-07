package com.wolfyxon.wolfxygen;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class ItemsMgr {
    Wolfxygen plugin;
    public ItemsMgr(Wolfxygen plugin){
        this.plugin = plugin;
    }
    NamespacedKey oxygenKey = new NamespacedKey(plugin,"oxygenAmount");

    public ItemStack getOxygenBottle(double oxygenAmount){
        ItemStack item = new ItemStack(Material.POTION);
        item.getItemMeta().getPersistentDataContainer().set(oxygenKey, PersistentDataType.DOUBLE,oxygenAmount);
        item.getItemMeta().setDisplayName(ConfigMgr.format("&lOxygen bottle"));
        return item;
    }
    public boolean isOxygenBottle(ItemStack item){
        return item.getItemMeta().getPersistentDataContainer().has(oxygenKey,PersistentDataType.DOUBLE);
    }
    public double getOxygenAmount(ItemStack item){
        if(!isOxygenBottle(item)) return 0;
        return item.getItemMeta().getPersistentDataContainer().get(oxygenKey,PersistentDataType.DOUBLE);
    }
}
