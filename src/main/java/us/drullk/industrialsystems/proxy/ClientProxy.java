package us.drullk.industrialsystems.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import us.drullk.industrialsystems.IndustrialSystems;
import us.drullk.industrialsystems.block.ISBlocks;
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
		registerCustomItemModel(ISItems.pickaxeRF, 0, new RFWrappedToolRender());

		registerModel(ISBlocks.smartTrashCan, 0);
	}

	@Override
	public void postInit()
	{

	}

	private void registerModel(Block block, int meta)
	{
		registerModel(block, meta, new ModelResourceLocation(IndustrialSystems.MOD_ID + ":" + block.getUnlocalizedName().substring(5), "inventory"));
	}

	private void registerModel(Block block, int meta, ModelResourceLocation rescLocation)
	{
		registerModel(Item.getItemFromBlock(block), meta, rescLocation);
	}

	private void registerModel(Item item, int meta, ModelResourceLocation rescLocation)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, rescLocation);
	}

	private void registerCustomItemModel(Item item, int meta, IBakedModel model)
	{
		ModelResourceLocation rescLocation = new ModelResourceLocation(IndustrialSystems.MOD_ID + ":" + item.getUnlocalizedName(), "inventory");
		registerModel(item, meta, rescLocation);
		ModelRegistry.instance.register(rescLocation, model);
	}
}
