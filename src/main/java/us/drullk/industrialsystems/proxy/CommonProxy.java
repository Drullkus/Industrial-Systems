package us.drullk.industrialsystems.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import us.drullk.industrialsystems.container.ContainerSmartTrashcan;
import us.drullk.industrialsystems.tile.TileSmartTrashCan;

public class CommonProxy implements IGuiHandler
{
	public static void preInit()
	{

	}

	public static void init()
	{

	}

	public static void postInit()
	{

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
