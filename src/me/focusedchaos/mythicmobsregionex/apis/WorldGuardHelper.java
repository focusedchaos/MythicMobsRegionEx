package me.focusedchaos.mythicmobsregionex.apis;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.focusedchaos.mythicmobsregionex.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;

public class WorldGuardHelper {
    private WorldGuardPlugin _worldGuardPlugin;
    private final String PLUGIN_NAME = "WorldGuard";

    public WorldGuardHelper(PluginManager pluginManager) {
        this._worldGuardPlugin = (WorldGuardPlugin)Bukkit.getPluginManager().getPlugin(PLUGIN_NAME);
    }
    public boolean InRegions(Location loc, String searchRegionsDelimited)
    {
        RegionManager regionManager = this._worldGuardPlugin.getRegionManager(loc.getWorld());
        ApplicableRegionSet locRegionSet = regionManager.getApplicableRegions(loc);
        String[] searchRegionIds = searchRegionsDelimited.split(",");
        if (locRegionSet.size() > 0)
        {
            String regionListing = new String();
            for (ProtectedRegion region : locRegionSet) {
                String locRegionId = region.getId();
                for (String searchRegionId : searchRegionIds) {
                    if (searchRegionId.equalsIgnoreCase(locRegionId)) {
                        Main.logger.logDebug(String.format("Region \"%s\" found in region list [%s]", searchRegionId, regionListing), 2);
                        return true;
                    }
                    regionListing += locRegionId + "|";
                }
            }
            Main.logger.logDebug(String.format("Regions \"%s\" not found in location region list [%s]", searchRegionsDelimited, regionListing), 3);
        }
        return false;
    }
}
