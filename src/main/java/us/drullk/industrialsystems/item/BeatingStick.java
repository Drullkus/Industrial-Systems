package us.drullk.industrialsystems.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
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

		target.attackEntityFrom(DamageSource.generic, target.getHealth() * 4f + 10f);
		target.setHealth(0f);
		return true;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity target)
	{
		if(target instanceof EntityLiving && !player.worldObj.isRemote)
		{
			if(target instanceof EntityWither)
			{
				((EntityWither) target).setInvulTime(0);
			}

			target.attackEntityFrom(DamageSource.generic, ((EntityLiving) target).getHealth() * 4f + 10f);

			((EntityLiving)target).setHealth(0f);

			return true;
		}

		return false;
	}
}
