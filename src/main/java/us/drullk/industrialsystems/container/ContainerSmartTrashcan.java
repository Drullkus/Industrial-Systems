package us.drullk.industrialsystems.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSmartTrashcan extends Container
{
	private IInventory trash;
	private EntityPlayer player;

	public ContainerSmartTrashcan(IInventory playerInv, IInventory trashInventory)
	{
		player = ((InventoryPlayer) playerInv).player;
		trash = trashInventory;

		setupSlots();

		trash.openInventory(player);
	}

	private void setupSlots()
	{
		for (int j = 0; j < 3; ++j)
		{
			for (int k = 0; k < 9; ++k)
			{
				this.addSlotToContainer(new SlotTrashCan(trash, k + j * 9, 8 + k * 18, 18 + j * 18));
			}
		}

		for (int l = 0; l < 3; ++l)
		{
			for (int j1 = 0; j1 < 9; ++j1)
			{
				this.addSlotToContainer(new Slot(player.inventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 - 18));
			}
		}

		for (int i1 = 0; i1 < 9; ++i1)
		{
			this.addSlotToContainer(new Slot(player.inventory, i1, 8 + i1 * 18, 161  - 18));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return trash.isUseableByPlayer(playerIn);
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn)
	{
		super.onContainerClosed(playerIn);
		this.trash.closeInventory(playerIn);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	{
		ItemStack itemstack = null;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack())
		{
			itemstack = slot.getStack();
		}

		return itemstack;
	}
}
