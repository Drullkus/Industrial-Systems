package us.drullk.industrialsystems.block;

import net.minecraftforge.fml.common.registry.GameRegistry;
import us.drullk.industrialsystems.tile.TileSmartTrashCan;

public class ISBlocks
{
	public static BlockSmartTrashCan smartTrashCan;

	public static void preInit()
	{
		smartTrashCan = new BlockSmartTrashCan();

		smartTrashCan.setUnlocalizedName("SmartTrashbin");

		GameRegistry.registerBlock(smartTrashCan);

		GameRegistry.registerTileEntity(TileSmartTrashCan.class, "TileSmartTrashbin");
	}

	public static void init()
	{

	}

	public static void postInit()
	{

	}
}

