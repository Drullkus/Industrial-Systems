package us.drullk.industrialsystems.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import us.drullk.industrialsystems.IndustrialSystems;
import us.drullk.industrialsystems.item.ISItems;
import us.drullk.industrialsystems.rendering.ModelRegistry;
import us.drullk.industrialsystems.rendering.RFWrappedToolRender;

public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit()
	{
		MinecraftForge.EVENT_BUS.register(ModelRegistry.instance);
	}

	@Override
	public void init()
	{
		ModelResourceLocation toolLoc = new ModelResourceLocation(IndustrialSystems.MOD_ID + ":" + ISItems.pickaxeRF.getUnlocalizedName(), "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ISItems.pickaxeRF, 0, toolLoc);
		ModelRegistry.instance.register(toolLoc, new RFWrappedToolRender());
	}

	@Override
	public void postInit()
	{

	}
}
