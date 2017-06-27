package cubimod.tcrings.recipies;

import java.util.ArrayList;

import cubimod.tcrings.items.ItemRingPrimal;
import cubimod.tcrings.items.TcRingsItems;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;

public class TcRingsRecipies
{
    public static InfusionRecipe craftPrimalRing;
    public static PrimalRingInfusion primalInfusion[];
    public static PrimalRingInfusion primalExamples[];
    
    public static void addRecipes()
    {
        craftPrimalRing = new InfusionRecipe(
                "PRIMALRING",
                new ItemStack(TcRingsItems.ringPrimal), 5,
                (new AspectList())
                .add(Aspect.AIR, 12).add(Aspect.EARTH, 12).add(Aspect.ENTROPY, 12)
                .add(Aspect.FIRE, 12).add(Aspect.ORDER, 12).add(Aspect.WATER, 12)
                .add(Aspect.MAGIC, 16),
                ItemApi.getItem("itemResource", 15),
                new ItemStack[] { 
                        ItemApi.getItem("itemBaubleBlanks", 3),
                        ItemApi.getItem("itemBaubleBlanks", 4),
                        ItemApi.getItem("itemBaubleBlanks", 5),
                        ItemApi.getItem("itemBaubleBlanks", 6),
                        ItemApi.getItem("itemBaubleBlanks", 7),
                        ItemApi.getItem("itemBaubleBlanks", 8)
                        });
                        
        ThaumcraftApi.getCraftingRecipes().add(craftPrimalRing);
        
        ArrayList<Aspect> primals = Aspect.getPrimalAspects();
        primalInfusion = new PrimalRingInfusion[primals.size()];
        
        for(int i = 0; i < primals.size(); i++)
        {
            primalInfusion[i] = new PrimalRingInfusion(new ItemStack(TcRingsItems.ringPrimal), primals.get(i));
            ThaumcraftApi.getCraftingRecipes().add(primalInfusion[i]);
        }
        
        final int exnum = 4;
        primalExamples = new PrimalRingInfusion[primals.size() * exnum];
        for(int i = 0; i < primals.size(); i++)
        {
            Aspect aspect = primals.get(i);
            for(int j = 0; j < exnum; j++)
            {
                ItemStack ring = new ItemStack(TcRingsItems.ringPrimal);
                ((ItemRingPrimal) TcRingsItems.ringPrimal).storeDiscount(ring, aspect, j);
                primalExamples[i*exnum + j] = new PrimalRingInfusion(ring, aspect);
            }
        }
    }
}
