package us.drullk.industrialsystems.item;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import us.drullk.industrialsystems.item.Wrapper.ItemWrapper;

public class ItemRFTool extends ItemWrapper implements IEnergyContainerItem
{
	public ItemRFTool(Item item)
	{
		super(item);

		if(item != null && item instanceof ItemPickaxe)
		{
			fallbackItem = item;
		}
		else
		{
			fallbackItem = Items.diamond_pickaxe;
		}
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate)
	{
		return 0;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate)
	{
		return 0;
	}

	@Override
	public int getEnergyStored(ItemStack container)
	{
		return 0;
	}

	@Override
	public int getMaxEnergyStored(ItemStack container)
	{
		return 0;
	}
}
