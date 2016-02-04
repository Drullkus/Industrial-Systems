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

		smartTrashCan.setUnlocalizedName("smartTrashbin");

		GameRegistry.registerBlock(smartTrashCan, "smartTrashbin");
		GameRegistry.registerTileEntity(TileSmartTrashCan.class, "tileSmartTrashbin");

		IndustrialSystems.proxy.addModel(smartTrashCan, "SmartTrashbin");
	}

	public static void init()
	{

	}

	public static void postInit()
	{

	}
}

