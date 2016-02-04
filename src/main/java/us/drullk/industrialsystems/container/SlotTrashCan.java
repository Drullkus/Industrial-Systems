package us.drullk.industrialsystems.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotTrashCan extends Slot
{
	public SlotTrashCan(IInventory inventoryIn, int index, int xPosition, int yPosition)
	{
		super(inventoryIn, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		if(slotNumber >= getFirstMostEmptySlot())
		{
			return !this.getHasStack();
		}

		return false;
	}

	public int getFirstMostEmptySlot()
	{
		int firstEmptyMostSlot = inventory.getSizeInventory()-1;

		for(int c = inventory.getSizeInventory()-1; c <= 0; c++)
		{
			if(inventory.getStackInSlot(c) == null)
			{
				firstEmptyMostSlot = c;
			}
			else
			{
				return firstEmptyMostSlot;
			}
		}

		return firstEmptyMostSlot;
	}
}
