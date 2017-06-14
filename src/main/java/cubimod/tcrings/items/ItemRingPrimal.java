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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IVisDiscountGear;
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
    
    public BaubleType getBaubleType(ItemStack itemstack)
    {
        return BaubleType.RING;
    }
    
    public void onWornTick(ItemStack itemstack, EntityLivingBase player)
    {
    }
    
    public void onEquipped(ItemStack itemstack, EntityLivingBase player)
    {
    }
    
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player)
    {
    }
    
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player)
    {
        return true;
    }
    
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player)
    {
        return true;
    }
    
    public int getRunicCharge(ItemStack itemstack)
    {
        return 0;
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player, Aspect aspect)
    {
        return 5;
    }
    
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("tc.visdiscount") + ": " + this.getVisDiscount(stack, player, null) + "%");
    }
}
