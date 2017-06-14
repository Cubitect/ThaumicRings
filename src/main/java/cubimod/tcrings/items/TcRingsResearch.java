package cubimod.tcrings.items;

import java.util.HashMap;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class TcRingsResearch
{
    public static void addResearch()
    {
        ResearchPage pagePrimalRing = new ResearchPage(TcRingsRecipies.primalRing);
        
        ResearchItem researchItem = new ResearchItem("PRIMALRING", "ARTIFICE", 
                new AspectList()
                    .add(Aspect.AIR, 3).add(Aspect.EARTH, 3)
                    .add(Aspect.ENTROPY, 3).add(Aspect.FIRE, 3)
                    .add(Aspect.ORDER, 3).add(Aspect.WATER, 3)
                    .add(Aspect.MECHANISM, 3), 
                -2, -1, 0, new ItemStack(TcRingsItems.ringPrimal))
            .setPages(new ResearchPage("tcrings.RESEARCH.primal_ring"), pagePrimalRing)
            .setParents("BASICARTIFACE")
            .setConcealed()
            .registerResearchItem();
    }
}
