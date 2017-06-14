package cubimod.tcrings.items;

import java.util.HashMap;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;

public class TcRingsRecipies
{
    public static InfusionRecipe primalRing;
    
    public static void addRecipes()
    {
        primalRing = ThaumcraftApi.addInfusionCraftingRecipe(
                "PRIMALRING",
                new ItemStack(TcRingsItems.ringPrimal), 5,
                (new AspectList())
                .add(Aspect.AIR, 12).add(Aspect.EARTH, 12).add(Aspect.ENTROPY, 12)
                .add(Aspect.FIRE, 12).add(Aspect.ORDER, 12).add(Aspect.WATER, 12),
                ItemApi.getItem("itemResource", 15),
                new ItemStack[] { 
                        ItemApi.getItem("itemBaubleBlanks", 3),
                        ItemApi.getItem("itemBaubleBlanks", 4),
                        ItemApi.getItem("itemBaubleBlanks", 5),
                        ItemApi.getItem("itemBaubleBlanks", 6),
                        ItemApi.getItem("itemBaubleBlanks", 7),
                        ItemApi.getItem("itemBaubleBlanks", 8),
                        });
    }
}
