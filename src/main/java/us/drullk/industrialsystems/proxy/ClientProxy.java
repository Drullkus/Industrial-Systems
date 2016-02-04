package us.drullk.industrialsystems.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import us.drullk.industrialsystems.IndustrialSystems;
import us.drullk.industrialsystems.block.ISBlocks;
import us.drullk.industrialsystems.container.gui.GUISmartTrashcan;
import us.drullk.industrialsystems.item.ISItems;
import us.drullk.industrialsystems.rendering.ModelRegistry;
import us.drullk.industrialsystems.rendering.RFWrappedToolRender;
import us.drullk.industrialsystems.tile.TileSmartTrashCan;

import java.util.Map;

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
		registerModel(ISItems.beatingStick, 0, new ModelResourceLocation("stick", "inventory"));

		//registerModel(ISBlocks.smartTrashCan, 0, new ModelResourceLocation(IndustrialSystems.MOD_ID + ":SmartTrashbin", "inventory"));

		for(Map.Entry<Item, String> entry : itemModelsToRegister.entrySet())
		{
			registerModel(entry.getKey(), 0, new ModelResourceLocation(IndustrialSystems.MOD_ID + ":" + entry.getValue(), "inventory"));
		}
	}

	@Override
	public void postInit()
	{

	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

		if(tile != null && tile instanceof TileSmartTrashCan)
		{
			return new GUISmartTrashcan(((TileSmartTrashCan) tile));
		}

		return null;
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
		ModelResourceLocation rescLocation = new ModelResourceLocation(IndustrialSystems.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory");
		registerModel(item, meta, rescLocation);
		ModelRegistry.instance.register(rescLocation, model);
	}
}
