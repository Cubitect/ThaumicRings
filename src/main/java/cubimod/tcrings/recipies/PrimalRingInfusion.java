package cubimod.tcrings.recipies;

import java.util.ArrayList;

import cubimod.tcrings.items.ItemRingPrimal;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.crafting.InfusionRunicAugmentRecipe;

public class PrimalRingInfusion extends InfusionRunicAugmentRecipe 
{
    public Aspect aspect;
    public ItemStack ring;
    private ItemStack components[];
    
    public PrimalRingInfusion(ItemStack input, Aspect aspect)
    {
        super(input);
        this.research = "PRIMALRING";
        this.aspect = aspect;
        int aspectIndex = Aspect.getPrimalAspects().indexOf(aspect);
        if(aspectIndex < 0)
        {
            // vis discount rings need to be primal
            return;
        }
        this.ring = ItemApi.getItem("itemBaubleBlanks", 3 + aspectIndex);
        this.components = getComponents(input);
    }
    
    @Override
    public Object getRecipeOutput(ItemStack stack)
    {
        if(stack == null)
        {
            return null;
        }
        
        ItemStack out = stack.copy();
        this.incDiscount(out, aspect);
        
        return out;
    }
    
    @Override
    public AspectList getAspects(ItemStack stack) 
    {
        AspectList out = new AspectList();
        int mult = (int)Math.pow(2, this.getAddedDiscount(stack, this.aspect));
        out.add(Aspect.AURA, 16);
        out.add(aspect, 32 * mult);
        return out;
    }
    
    public int getAddedDiscount(ItemStack stack, Aspect aspect)
    {
        return ((ItemRingPrimal) stack.getItem()).getAddedDiscount(stack, aspect);
    }
    
    public int incDiscount(ItemStack stack, Aspect aspect)
    {
        int discount = this.getAddedDiscount(stack, aspect) + 1;
        ((ItemRingPrimal) stack.getItem()).storeDiscount(stack, aspect, discount);
        return discount;
    }
    
    @Override
    public int getInstability(ItemStack stack) 
    {
        int instability = this.getAddedDiscount(stack, this.aspect);
        
        for(Aspect a : Aspect.getPrimalAspects())
        {
            instability += this.getAddedDiscount(stack, a);
        }
        
        return instability;
    }
    
    @Override
    public ItemStack[] getComponents(ItemStack stack)
    {
        ArrayList<ItemStack> com = new ArrayList<ItemStack>();
        com.add(this.ring);
        com.add(ItemApi.getItem("itemResource", 14));
        int dis = this.getAddedDiscount(stack, aspect);
        if (dis > 0)
        {
            for(int c = 0; c < dis; ++c)
            {
                com.add(ItemApi.getItem("itemResource", 14));
            }
        }
        
        return com.toArray(new ItemStack[0]);
    }
    
    @Override
    public ItemStack[] getComponents()
    {
        return components;
    }
}
