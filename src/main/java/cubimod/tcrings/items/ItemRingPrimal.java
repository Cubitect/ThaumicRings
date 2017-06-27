package cubimod.tcrings.items;

import java.util.List;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cubimod.tcrings.ThaumicRings;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;

public class ItemRingPrimal extends Item implements IRunicArmor, IBauble, IVisDiscountGear
{
    public ItemRingPrimal()
    {
        super();
        this.maxStackSize = 1;
        setCreativeTab(ThaumicRings.tab);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir)
    {
        this.itemIcon = ir.registerIcon("tcrings:ring_primal");
    }
    
    public BaubleType getBaubleType(ItemStack stack)
    {
        return BaubleType.RING;
    }
    
    public void onWornTick(ItemStack stack, EntityLivingBase player)
    {
    }
    
    public void onEquipped(ItemStack stack, EntityLivingBase player)
    {
        int discount = this.getAddedDiscount(stack, Aspect.ORDER) + 1;
        ((ItemRingPrimal) stack.getItem()).storeDiscount(stack, Aspect.ORDER, discount);
    }
    
    public void onUnequipped(ItemStack stack, EntityLivingBase player)
    {
    }
    
    public boolean canEquip(ItemStack stack, EntityLivingBase player)
    {
        if(player instanceof EntityPlayer)
            return ThaumcraftApiHelper.isResearchComplete(((EntityPlayer) player).getDisplayName(), "PRIMALRING");
        return true;
    }
    
    public boolean canUnequip(ItemStack stack, EntityLivingBase player)
    {
        return true;
    }
    
    public int getRunicCharge(ItemStack stack)
    {
        return 0;
    }
    
    public int getAddedDiscount(ItemStack stack, Aspect aspect)
    {
        int out = 0;
        if(aspect != null && stack.hasTagCompound() && stack.stackTagCompound.hasKey(aspect.getTag()))
        {
            out = stack.stackTagCompound.getInteger(aspect.getTag());
        }
        return out;
    }
    
    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect)
    {
        int discount = 2 + this.getAddedDiscount(stack, aspect);
        return discount;
        // return (int)(1.5f + 4.0f * player.worldObj.getCurrentMoonPhaseFactor());
    }
    
    public void storeDiscount(ItemStack stack, Aspect aspect, int amount)
    {
        stack.setTagInfo(aspect.getTag(), (NBTBase)new NBTTagInt(amount));
    }
    
    public void clearDiscount(ItemStack stack)
    {
        for(Aspect aspect : Aspect.getPrimalAspects())
        {
            this.storeDiscount(stack, aspect, 0);
        }
    }
    
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + " :");
        
        String s = "";
        
        for(Aspect aspect : Aspect.getPrimalAspects())
        {
            int discount = getVisDiscount(stack, player, aspect);
            
            if(s.length() > 0)
                s += " | ";
            
            s += "\u00a7" + aspect.getChatcolor() + discount + "%\u00a7r";
        }
        
        list.add(s);
    }
}
