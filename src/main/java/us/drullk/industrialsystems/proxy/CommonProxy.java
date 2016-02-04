package us.drullk.industrialsystems.proxy;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import us.drullk.industrialsystems.container.ContainerSmartTrashcan;
import us.drullk.industrialsystems.tile.TileSmartTrashCan;

import java.util.HashMap;

public class CommonProxy implements IGuiHandler
{
	protected HashMap<Item, String> itemModelsToRegister = new HashMap<Item, String>();

	public void preInit()
	{

	}

	public void init()
	{

	}

	public void postInit()
	{

	}

	public void registerSimpleModel()
	{

	}

	public void addModel(Block block, String resourceLocation)
	{
		addModel(Item.getItemFromBlock(block), resourceLocation);
	}

	public void addModel(Item item)
	{
		addModel(item, item.getUnlocalizedName());
	}

	public void addModel(Item item, String resourceLocation)
	{
		itemModelsToRegister.put(item, resourceLocation);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));

		if(tile != null && tile instanceof TileSmartTrashCan)
		{
			TileSmartTrashCan tileSmartTrash = (TileSmartTrashCan) tile;

			return new ContainerSmartTrashcan(player.inventory, tileSmartTrash);
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
}
