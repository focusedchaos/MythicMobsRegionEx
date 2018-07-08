package me.focusedchaos.mythicmobsregionex;

import me.focusedchaos.mythicmobsregionex.apis.WorldGuardHelper;
import me.focusedchaos.mythicmobsregionex.conditions.ConditionsExRegister;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;


public class Main extends JavaPlugin {
    public static RexLogger logger;
    private static Main pluginInstance;
    private static ConditionsExRegister conditionsExRegister;
    private static WorldGuardHelper worldGuardHelper;

    @Override
    public void onEnable(){
        pluginInstance = this;
        this.logger = new RexLogger(this.getLogger(), this.getName(), 0);
        PluginManager pluginmanager = this.getServer().getPluginManager();

        if (pluginmanager.isPluginEnabled("MythicMobs") && pluginmanager.isPluginEnabled("WorldGuard")) {
            this.logger.logInfo("MythicMobs, WorldGuard loaded.  Adding WorldGuard Region Extensions.");
            Main.worldGuardHelper = new WorldGuardHelper(pluginmanager);
            Main.conditionsExRegister = new ConditionsExRegister();
        }
    }

    @Override
    public void onDisable(){
        Main.logger = null;
        Main.conditionsExRegister = null;
        Main.worldGuardHelper = null;
        PluginManager pluginmanager = this.getServer().getPluginManager();
        pluginmanager.disablePlugin(this);
    }
    public static Main getPluginInstance()
    {
        return Main.pluginInstance;
    }
    public static WorldGuardHelper getWorldGuardHelper()
    {
        return Main.worldGuardHelper;
    }
}

