package us.drullk.industrialsystems.block;

import net.minecraftforge.fml.common.registry.GameRegistry;
import us.drullk.industrialsystems.IndustrialSystems;
import us.drullk.industrialsystems.tile.TileSmartTrashCan;

public class ISBlocks
{
	public static BlockSmartTrashCan smartTrashCan;

	public static void preInit()
	{
		smartTrashCan = new BlockSmartTrashCan();

		smartTrashCan.setUnlocalizedName("SmartTrashbin");

		GameRegistry.registerBlock(smartTrashCan, "SmartTrashbin");
		GameRegistry.registerTileEntity(TileSmartTrashCan.class, "tileSmartTrashbin");
	}

	public static void init()
	{
		IndustrialSystems.proxy.makeModel(smartTrashCan, "SmartTrashbin");
	}

	public static void postInit()
	{

	}
}

