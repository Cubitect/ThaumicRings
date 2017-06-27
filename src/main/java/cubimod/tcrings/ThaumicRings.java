package cubimod.tcrings;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cubimod.tcrings.items.TcRingsItems;
import cubimod.tcrings.recipies.TcRingsRecipies;
import cubimod.tcrings.recipies.TcRingsResearch;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

@Mod(modid = ThaumicRings.MODID, version = ThaumicRings.VERSION, dependencies = "required-after:Thaumcraft@[4.2.2.0,)")
public class ThaumicRings
{
    public static final String MODID = "ThaumicRings";
    public static final String VERSION = "dev 0.1";
    
    @SidedProxy(clientSide = "cubimod.tcrings.client.ClientProxy", serverSide = "cubimod.tcrings.CommonProxy")
    public static CommonProxy proxy;
    
    public static CreativeTabs tab = new CreativeTabs("ThaumicRings")
    {
        @Override
        public Item getTabIconItem()
        {
            return TcRingsItems.ringPrimal;
        }
    };
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Config.configurate(event.getSuggestedConfigurationFile());
        
        TcRingsItems.addItems();
        
        proxy.registerRenderInfo();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        
    }
    
    @EventHandler
    public void outro(FMLPostInitializationEvent event)
    {
        TcRingsRecipies.addRecipes();
        TcRingsResearch.addResearch();
    }
}
