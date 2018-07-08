package me.focusedchaos.mythicmobsregionex.conditions;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicConditionLoadEvent;
import me.focusedchaos.mythicmobsregionex.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;

public class ConditionsExRegister implements Listener {
    public ConditionsExRegister()
    {
        Bukkit.getServer().getPluginManager().registerEvents(this, Main.getPluginInstance());
    }
    @EventHandler
    public void onMythicMobsConditionsLoadEvent(MythicConditionLoadEvent e) {
        Main.logger.logDebug("Listen Event recieved for condition " + e.getConditionName(), 1);
        if (e.getConditionName().equalsIgnoreCase("INREGIONS")) {
            SkillCondition c = new InRegionsCondition(e.getConditionName(), e.getConfig());
            e.register(c);
            Main.logger.logDebug("Registering REx Condition " + e.getConditionName(), 0);
        }
        else if (e.getConditionName().equalsIgnoreCase("NOTINREGIONS")) {
            SkillCondition c = new NotInRegionsCondition(e.getConditionName(), e.getConfig());
            e.register(c);
            Main.logger.logDebug("Registering REx Condition " + e.getConditionName(), 0);
        }

    }
}
