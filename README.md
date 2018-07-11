# MythicMobsRegionEx
Mythic Mobs WorldGuard Region Extensions

This is a small plugin which adds a couple new conditions to Mythic Mobs for WorldGuard regions.

```YAML
Conditions:
- InRegions{r=Region1,Region2,Region3}
- NotInRegions{r=Region4,Region5}
```

*Region1,2,3,4,5,etc can be either WorldGuard <a href="https://worldguard.enginehub.org/en/latest/regions/quick-start/#creating-regions">Regions<a> or <a href="https://worldguard.enginehub.org/en/latest/regions/priorities/#template-regions">Template Regions</a>

<h3>There's already "region" and "notinregion" conditions in Mythic Mobs, why would you bother doing this?</h3>

Two reasons:
<ol>
  <li>I wanted to control a large range of mob levels based on the region they spawned in instead of relying on Mythic Mobs distance from spawn point auto leveling mechanic.  While Mythic Mobs aready includes this functionality, you can only specify one region per condition.  This gets tedious quickly if you have a lot of regions.</li>
  <li>More importantly, I noticed that <b>Mythic Mob's conditions do not respect parent regions, or global <a href="https://worldguard.enginehub.org/en/latest/regions/priorities/#template-regions">Template Regions</a></b>.  Without this plugin extension, I would have had to create condition checks for every individual physical region I defined.  Combine that with the only one region per condition statement restriction, and your Mythic Mobs configs become unmanageable quickly.</li>
</ol>

<h3>So what does it all mean?</h3>

For me, it meant I could decouple my physical regions from level ranges in Mythic Mobs.  I did this by creating templates for each level range, and using the new region conditions against them.

<h4>Basically, I went from this:</h4>

```YAML
Conditions:
- inregion{r=TheDarkWoods} true
- inregion{r=TheFlats} true
- inregion{r=TheRollingHills} true
- inregion{r=SomeOtherPhysicalRegionWithLeveledMobs} true
#exclude for overlapping regions
- notinregion{r=StartingArea} true
- notinregion{r=Homesteads} true
- notinregion{r=Farms} true
- notinregion{r=Farms} true
- inregion{r=SomeOtherPhysicalRegionWithoutLeveledMobs}
Skills:
- setlevel{a=set;l=5}
- setlevel{a=set;l=6} .33
- setlevel{a=set;l=7} .33
```

<h4>To this:</h4>

```YAML
Conditions:
- notinregions{r=leveledRegion_none,leveledRegion_1_3,leveledRegion_3_5} true
- inregions{r=leveledRegion_5_7} true
Skills:
- setlevel{a=set;l=5}
- setlevel{a=set;l=6} .33
- setlevel{a=set;l=7} .33
```
