package us.drullk.industrialsystems;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import us.drullk.industrialsystems.block.ISBlocks;
import us.drullk.industrialsystems.crafting.ISCrafting;
import us.drullk.industrialsystems.item.ISItems;
import us.drullk.industrialsystems.proxy.CommonProxy;

@Mod(modid = ISProperties.MOD_ID, version = ISProperties.VERSION, name = ISProperties.MOD_NAME)
public class IndustrialSystems implements ISProperties
{
	public static final Logger logger = LogManager.getLogger(MOD_NAME);

	@Mod.Instance(MOD_ID)
	public static IndustrialSystems instance;

	@SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY, modId = MOD_ID)
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ISBlocks.preInit();
		ISItems.preInit();
		ISCrafting.preInit();
		proxy.preInit();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		ISBlocks.init();
		ISItems.init();
		ISCrafting.init();
		proxy.init();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		ISBlocks.postInit();
		ISItems.postInit();
		ISCrafting.postInit();
		proxy.postInit();
	}
}
