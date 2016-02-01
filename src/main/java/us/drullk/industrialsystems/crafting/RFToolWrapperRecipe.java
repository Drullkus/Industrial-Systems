package us.drullk.industrialsystems.crafting;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import us.drullk.industrialsystems.item.ISItems;
import us.drullk.industrialsystems.item.ItemRFTool;

public class RFToolWrapperRecipe implements IRecipe
{
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn)
	{
		return doesItMatch(inv);
	}

	private boolean doesItMatch(InventoryCrafting inv)
	{
		boolean foundItem = false;
		Item ingredient;

		if(inv.getStackInSlot(4) != null)
		{
			ingredient = inv.getStackInSlot(4).getItem();

			if(ingredient instanceof ItemRFTool)
			{
				return false;
			}
			else if(ingredient instanceof IEnergyContainerItem)
			{
				return false;
			}

			if(ingredient instanceof ItemTool)
			{
				for(int c = 0; c < inv.getSizeInventory(); c++)
				{
					if(c != 4)
					{
						if(inv.getStackInSlot(c) != null)
						{
							ingredient = inv.getStackInSlot(c).getItem();

							if(ingredient == ISItems.equipmentRF)
							{
								if(foundItem)
								{
									return false;
								}
								else if(!foundItem)
								{
									foundItem = true;
								}
							}
						}
					}
				}
			}
		}
		else
		{
			return false;
		}

		return foundItem;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		/*if(this.doesItMatch(inv))
		{*/

		NBTTagCompound tagCompound = new NBTTagCompound();

		ItemStack tool = inv.getStackInSlot(4);

		tool.writeToNBT(tagCompound); //TODO Write a real new itemstack, this is hacky

		NBTTagList tagList = new NBTTagList();
		NBTTagCompound wrappingCompound = new NBTTagCompound();

		tool.writeToNBT(wrappingCompound);
		tagList.appendTag(wrappingCompound);

		tagCompound.setTag("wrappedStack", tagList);

		return new ItemStack(ISItems.pickaxeRF, 1, 0, tagCompound);

		/*}
		else
		{
			return null;
		}*/
	}

	@Override
	public int getRecipeSize()
	{
		return 3;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return null;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv)
	{
		return new ItemStack[0];
	}
}
