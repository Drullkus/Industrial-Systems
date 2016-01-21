package us.drullk.industrialsystems.crafting;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ISCrafting
{
	public static void preInit()
	{
		GameRegistry.addRecipe(new RFToolWrapperRecipe());
	}

	public static void init()
	{

	}

	public static void postInit()
	{

	}
}
