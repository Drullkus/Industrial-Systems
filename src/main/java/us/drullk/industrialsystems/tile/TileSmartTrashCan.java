package us.drullk.industrialsystems.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;

public class TileSmartTrashCan extends TileEntity implements ITickable, IInventory
{
	private ItemStack[] itemsForRedemption = new ItemStack[27];
	private String defaultName = "Smart Trashcan";
	private String customName = defaultName;

	public boolean isSlotValid(int slot)
	{
		return !(slot < 0 || slot >= itemsForRedemption.length);
	}

	@Override
	public void update()
	{
		ItemStack[] newRedemptionList = new ItemStack[itemsForRedemption.length];

		System.arraycopy(itemsForRedemption, 0, newRedemptionList, 0, itemsForRedemption.length);

		for(int c = 0; c < newRedemptionList.length - 1; c++)
		{
			// Is entry not empty?
			if(newRedemptionList[c] != null)
			{
				// Try to find next empty slot
				for(int i = 0; i < c; i++)
				{
					if(newRedemptionList[i] == null && newRedemptionList[c] != null)
					{
						newRedemptionList[i] = newRedemptionList[c];
						newRedemptionList[c] = null;

						break;
					}
				}
			}
		}

		if(newRedemptionList[newRedemptionList.length-1] != null)
		{
			// Shove everything back!
			for(int c = 0; c < itemsForRedemption.length - 1; c++)
			{
				itemsForRedemption[c] = itemsForRedemption[c + 1];
			}

			newRedemptionList[newRedemptionList.length-1] = null;
		}

		itemsForRedemption = newRedemptionList;
	}

	@Override
	public int getSizeInventory()
	{
		return itemsForRedemption.length;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return (isSlotValid(index) ? itemsForRedemption[index] : null);
	}

	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		// Make sure slot exists first
		if(!isSlotValid(index))
		{
			return null;
		}

		// Is Itemstack existing in slot?
		if (this.itemsForRedemption[index] != null)
		{
			// Is the stacksize lesser or equal to amount requested to remove; This will remove the stack from inv
			if (this.itemsForRedemption[index].stackSize <= count)
			{
				// Make a new instance of particular itemstack to return
				ItemStack takenFullStack = this.itemsForRedemption[index];
				this.itemsForRedemption[index] = null;

				// Mark TE as modified, so world saver doesn't skip this
				this.markDirty();

				// Return!
				return takenFullStack;
			}
			else
			{
				// Make a new instance of particular itemstack to return
				ItemStack takenPartialStack = this.itemsForRedemption[index].splitStack(count);

				if (this.itemsForRedemption[index].stackSize == 0)
				{
					this.itemsForRedemption[index] = null;
				}

				// Mark TE as modified, so world saver doesn't skip this
				this.markDirty();

				// Return!
				return takenPartialStack;
			}
		}
		else
		{
			return null;
		}
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		// Make sure slot exists first
		if(!isSlotValid(index))
		{
			return null;
		}

		// Making sure Stack exists
		if (this.itemsForRedemption[index] != null)
		{
			// Make an instance of the Stack in slot for returning
			ItemStack returningStack = this.itemsForRedemption[index];

			// Delete taken stack!
			this.itemsForRedemption[index] = null;

			// Mark TE as modified, so world saver doesn't skip this
			this.markDirty();

			// Return!
			return returningStack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		// Make sure slot exists first
		if(!isSlotValid(index))
		{
			return;
		}

		this.itemsForRedemption[index] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit())
		{
			stack.stackSize = this.getInventoryStackLimit();
		}

		// Mark TE as modified, so world saver doesn't skip this
		this.markDirty();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return worldObj == null || this.worldObj.getTileEntity(this.pos) == this && player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player)
	{

	}

	@Override
	public void closeInventory(EntityPlayer player)
	{

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		return true;
	}

	@Override
	public int getField(int id)
	{
		return 0;
	}

	@Override
	public void setField(int id, int value)
	{

	}

	@Override
	public int getFieldCount()
	{
		return 0;
	}

	@Override
	public void clear()
	{
		for(int c = 0; c < itemsForRedemption.length; c++)
		{
			itemsForRedemption[c] = null;
		}
	}

	@Override
	public String getName()
	{
		return this.hasCustomName() ? this.customName : "industrialsystems.container.smartchest";
	}

	@Override
	public boolean hasCustomName()
	{
		return !(this.defaultName.equals(customName));
	}

	@Override
	public IChatComponent getDisplayName()
	{
		return (IChatComponent)(this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName()));
	}

	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);

		NBTTagList tagList = compound.getTagList("Items", 10);

		this.itemsForRedemption = new ItemStack[this.getSizeInventory()];

		if (compound.hasKey("CustomName", 8))
		{
			this.customName = compound.getString("CustomName");
		}

		for (int i = 0; i < tagList.tagCount(); ++i)
		{
			NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
			int j = tagCompound.getByte("Slot") & 255;

			if (j >= 0 && j < this.itemsForRedemption.length)
			{
				this.itemsForRedemption[j] = ItemStack.loadItemStackFromNBT(tagCompound);
			}
		}
	}

	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);

		NBTTagList tagList = new NBTTagList();

		for (int i = 0; i < this.itemsForRedemption.length; ++i)
		{
			if (this.itemsForRedemption[i] != null)
			{
				NBTTagCompound nbttagcompound = new NBTTagCompound();

				nbttagcompound.setByte("Slot", (byte)i);

				this.itemsForRedemption[i].writeToNBT(nbttagcompound);

				tagList.appendTag(nbttagcompound);
			}
		}

		compound.setTag("Items", tagList);

		if (this.hasCustomName())
		{
			compound.setString("CustomName", this.customName);
		}
	}
}
