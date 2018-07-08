package me.focusedchaos.mythicmobsregionex.conditions;

import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
import me.focusedchaos.mythicmobsregionex.Main;

public class InRegionsCondition extends SkillCondition implements ILocationCondition {
    private String regionListing;
    public InRegionsCondition(String line, MythicLineConfig mythicLineConfig){
        super(line);
        this.regionListing = mythicLineConfig.getString(new String[]{"region", "r", "name", "n"}, this.conditionVar);
    }
    public boolean check(AbstractLocation targetLocation) {
        try {
            boolean inRegionExResult = Main.getWorldGuardHelper().InRegions(BukkitAdapter.adapt(targetLocation), this.regionListing);
            Main.logger.logDebug(String.format("InRegionsCondition check fired for region \"%s\".  Result: %s", this.regionListing, inRegionExResult), 3);
            return inRegionExResult;
        }
        catch (Exception ex)
        {
            Main.logger.logSevere(ex.toString());
            return false;
        }
    }
}
