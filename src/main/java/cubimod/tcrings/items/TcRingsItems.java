package cubimod.tcrings.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class TcRingsItems
{
    public static Item ringPrimal;
    
    public static void addItems()
    {
        ringPrimal = new ItemRingPrimal().setUnlocalizedName("PrimalRing");
        GameRegistry.registerItem(ringPrimal, "PrimalRing");
    }
}
