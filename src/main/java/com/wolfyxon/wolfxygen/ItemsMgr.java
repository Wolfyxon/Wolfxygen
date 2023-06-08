package com.wolfyxon.wolfxygen;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;

public class ItemsMgr {
    Wolfxygen plugin;
    ConfigMgr config;
    public ItemsMgr(Wolfxygen plugin){
        this.plugin = plugin;
        config = plugin.config;
        oxygenKey = new NamespacedKey(plugin,"oxygenAmount");
    }
    NamespacedKey oxygenKey;

    public ItemStack getOxygenBottle(double oxygenAmount){
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.getPersistentDataContainer().set(oxygenKey, PersistentDataType.DOUBLE,oxygenAmount);
        meta.setDisplayName(ConfigMgr.format("&lOxygen bottle"));
        meta.setColor(Color.WHITE);
        
        item.setItemMeta(meta);
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
