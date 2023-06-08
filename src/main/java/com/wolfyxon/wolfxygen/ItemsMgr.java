package com.wolfyxon.wolfxygen;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class ItemsMgr {
    Wolfxygen plugin;
    ConfigMgr config;
    public ItemsMgr(Wolfxygen plugin){
        this.plugin = plugin;
        config = plugin.config;
        oxygenKey = new NamespacedKey(plugin,"oxygenAmount");
    }
    NamespacedKey oxygenKey;
    String potionName = "$lOxygen bottle";
    String potionLore = "&a+{oxygen}";
    Color potionColor = Color.WHITE;
    double defaultOxygen = 10;

    public void applyConfig(){
        potionName = config.getPotionName();
        potionColor = config.getOxygenPotionColor();
        potionLore = config.getPotionLore();
        defaultOxygen = config.getPotionOxygenAmount();
    }

    public ItemStack getOxygenBottle(double oxygenAmount){
        ItemStack item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.getPersistentDataContainer().set(oxygenKey, PersistentDataType.DOUBLE,oxygenAmount);
        meta.setDisplayName(ConfigMgr.format(potionName));
        meta.setLore(List.of( ConfigMgr.format( potionLore.replace("{oxygen}",String.valueOf(oxygenAmount)) ) ) );
        meta.setColor(potionColor);
        
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack getOxygenBottle(){
        return getOxygenBottle(defaultOxygen);
    }
    public boolean isOxygenBottle(ItemStack item){
        return item.getItemMeta().getPersistentDataContainer().has(oxygenKey,PersistentDataType.DOUBLE);
    }
    public double getOxygenAmount(ItemStack item){
        if(!isOxygenBottle(item)) return 0;
        return item.getItemMeta().getPersistentDataContainer().get(oxygenKey,PersistentDataType.DOUBLE);
    }
}
