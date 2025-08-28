package org.yusaki.villagertradeedit;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class VillagerTradeEdit extends JavaPlugin {

    //TODO stop villager moving if already pathfind
    //TODO add villager rotate to player
    //TODO add VillagerName
    //TODO Cancel Name change event
    //TODO Save Button Instead of Closing button
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("VillagerTradeEdit enabled!");
        VillagerEditListener villagerEditListener = new VillagerEditListener();
        getServer().getPluginManager().registerEvents(villagerEditListener, this);
        VTECommandExecutor vteCommandExecutor = new VTECommandExecutor(this, villagerEditListener);
        this.getCommand("vte").setExecutor(vteCommandExecutor);
        this.getCommand("vte").setTabCompleter(vteCommandExecutor);
    }

    @Override
    public void onDisable() {
        getLogger().info("VillagerTradeEdit disabled!");
    }

    public static VillagerTradeEdit getInstance() {
        return getPlugin(VillagerTradeEdit.class);
    }

    public String getMessage(String key) {
        return ChatColor.translateAlternateColorCodes('&', getConfig().getString("messages." + key));
    }

    public boolean canExecuteInWorld(World world) {
        return getConfig().getStringList("enabled-worlds").contains(world.getName());
    }

}
