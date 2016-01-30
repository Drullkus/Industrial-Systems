package us.drullk.industrialsystems.item;

import net.minecraft.init.Items;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ISItems
{
	public static ItemRFTool pickaxeRF;
	public static ItemRFKit equipmentRF;

	public static void preInit()
	{
		pickaxeRF = new ItemRFTool(Items.diamond_pickaxe);
		equipmentRF = new ItemRFKit();

		pickaxeRF.setUnlocalizedName("RFEquippedTool");
		equipmentRF.setUnlocalizedName("RFKit");

		GameRegistry.registerItem(pickaxeRF, pickaxeRF.getUnlocalizedName());
		GameRegistry.registerItem(equipmentRF, equipmentRF.getUnlocalizedName());
	}

	public static void init()
	{

	}

	public static void postInit()
	{

	}
}
