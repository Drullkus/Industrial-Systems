package us.drullk.industrialsystems.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class BeatingStick extends Item
{
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		if(attacker.worldObj.isRemote)
			return true;

		target.attackEntityFrom(DamageSource.generic, target.getHealth() + 10OT f);
		return true;
	}
}
