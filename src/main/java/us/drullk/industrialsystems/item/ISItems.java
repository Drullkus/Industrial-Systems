package us.drullk.industrialsystems.item;

import net.minecraft.init.Items;
import net.minecraftforge.fml.common.registry.GameRegistry;
import us.drullk.industrialsystems.IndustrialSystems;

public class ISItems
{
	public static ItemRFTool pickaxeRF;
	public static ItemRFKit equipmentRF;
	public static BeatingStick beatingStick;

	public static void preInit()
	{
		pickaxeRF = new ItemRFTool(Items.diamond_pickaxe);
		equipmentRF = new ItemRFKit();
		beatingStick = new BeatingStick();

		pickaxeRF.setUnlocalizedName("RFEquippedTool");
		equipmentRF.setUnlocalizedName("RFKit");
		beatingStick.setUnlocalizedName("BeatingStick");

		GameRegistry.registerItem(pickaxeRF, pickaxeRF.getUnlocalizedName());
		GameRegistry.registerItem(equipmentRF, equipmentRF.getUnlocalizedName());
		GameRegistry.registerItem(beatingStick, beatingStick.getUnlocalizedName());

		IndustrialSystems.proxy.addModel(equipmentRF);
		IndustrialSystems.proxy.addModel(beatingStick);
	}

	public static void init()
	{

	}

	public static void postInit()
	{

	}
}
