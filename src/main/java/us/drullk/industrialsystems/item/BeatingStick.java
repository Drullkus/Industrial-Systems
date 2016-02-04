package us.drullk.industrialsystems.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
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

		if(target instanceof EntityWither)
		{
			((EntityWither) target).setInvulTime(0);
		}

		target.attackEntityFrom(DamageSource.generic, target.getHealth() * 4f);
		target.setHealth(0f);
		return true;
	}
}
