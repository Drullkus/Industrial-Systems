package us.drullk.industrialsystems.item.Wrapper;

import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class ItemWrapper extends Item
{
	public Item wrappedItem;

	public ItemWrapper()
	{
		this.setMaxStackSize(1);
	}

	@Override
	public boolean updateItemStackNBT(NBTTagCompound nbt)
	{
		return wrappedItem.updateItemStackNBT(nbt);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		return wrappedItem.onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
	{
		return wrappedItem.onItemRightClick(itemStackIn, worldIn, playerIn);
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn)
	{
		return wrappedItem.onItemUseFinish(stack, worldIn, playerIn);
	}

	@Override
	public int getMetadata(int damage)
	{
		return wrappedItem.getMetadata(damage);
	}

	@Override
	public boolean getHasSubtypes()
	{
		return wrappedItem.getHasSubtypes();
	}

	@Override
	public int getMaxDamage()
	{
		return wrappedItem.getMaxDamage();
	}

	@Override
	public boolean isDamageable()
	{
		return wrappedItem.isDamageable();
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		return wrappedItem.hitEntity(stack, target, attacker);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
	{
		return wrappedItem.onBlockDestroyed(stack, worldIn, blockIn, pos, playerIn);
	}

	@Override
	public boolean canHarvestBlock(Block blockIn)
	{
		return wrappedItem.canHarvestBlock(blockIn);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target)
	{
		return wrappedItem.itemInteractionForEntity(stack, playerIn, target);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean isFull3D()
	{
		return wrappedItem.isFull3D();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldRotateAroundWhenRendering()
	{
		return wrappedItem.shouldRotateAroundWhenRendering();
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack stack)
	{
		return wrappedItem.getUnlocalizedNameInefficiently(stack);
	}

	@Override
	public String getUnlocalizedName()
	{
		return wrappedItem.getUnlocalizedName();
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return wrappedItem.getUnlocalizedName(stack);
	}

	@Override
	public boolean getShareTag()
	{
		return wrappedItem.getShareTag();
	}

	@Override
	public Item getContainerItem()
	{
		return wrappedItem.getContainerItem();
	}

	@Override
	public boolean hasContainerItem()
	{
		return wrappedItem.hasContainerItem();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass)
	{
		return wrappedItem.getColorFromItemStack(stack, renderPass);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		wrappedItem.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
	{
		wrappedItem.onCreated(stack, worldIn, playerIn);
	}

	@Override
	public boolean isMap()
	{
		return wrappedItem.isMap();
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return wrappedItem.getItemUseAction(stack);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return wrappedItem.getMaxItemUseDuration(stack);
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft)
	{
		wrappedItem.onPlayerStoppedUsing(stack, worldIn, playerIn, timeLeft);
	}

	@Override
	public Item setPotionEffect(String potionEffect)
	{
		return wrappedItem.setPotionEffect(potionEffect);
	}

	@Override
	public String getPotionEffect(ItemStack stack)
	{
		return wrappedItem.getPotionEffect(stack);
	}

	@Override
	public boolean isPotionIngredient(ItemStack stack)
	{
		return wrappedItem.isPotionIngredient(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		wrappedItem.addInformation(stack, playerIn, tooltip, advanced);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		return wrappedItem.getItemStackDisplayName(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return wrappedItem.hasEffect(stack);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return wrappedItem.getRarity(stack);
	}

	@Override
	public boolean isItemTool(ItemStack stack)
	{
		return wrappedItem.isItemTool(stack);
	}

	/*@Override
	protected MovingObjectPosition getMovingObjectPositionFromPlayer(World worldIn, EntityPlayer playerIn, boolean useLiquids)
	{
		return wrappedItem.getMovingObjectPositionFromPlayer(worldIn, playerIn, useLiquids);
	}//*/

	@Override
	public int getItemEnchantability()
	{
		return wrappedItem.getItemEnchantability();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
	{
		wrappedItem.getSubItems(itemIn, tab, subItems);
	}

	@Override
	public Item setCreativeTab(CreativeTabs tab)
	{
		return wrappedItem.setCreativeTab(tab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public CreativeTabs getCreativeTab()
	{
		return wrappedItem.getCreativeTab();
	}

	@Override
	public boolean canItemEditBlocks()
	{
		return wrappedItem.canItemEditBlocks();
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		return wrappedItem.getIsRepairable(toRepair, repair);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers()
	{
		return wrappedItem.getItemAttributeModifiers();
	}

	// Overridden Forge Methods

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack)
	{
		return wrappedItem.getAttributeModifiers(stack);
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
	{
		return wrappedItem.onDroppedByPlayer(item, player);
	}

	@Override
	public String getHighlightTip( ItemStack item, String displayName )
	{
		return wrappedItem.getHighlightTip(item, displayName);
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		return wrappedItem.onItemUseFirst(stack, player, world, pos, side, hitX, hitY, hitZ);
	}

	@Override
	public float getDigSpeed(ItemStack itemstack, net.minecraft.block.state.IBlockState state)
	{
		return wrappedItem.getDigSpeed(itemstack, state);
	}

	@Override
	public boolean isRepairable()
	{
		return wrappedItem.isRepairable();
	}

	@Override
	public Item setNoRepair()
	{
		return wrappedItem.setNoRepair();
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
	{
		return wrappedItem.onBlockStartBreak(itemstack, pos, player);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{
		wrappedItem.onUsingTick(stack, player, count);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		return wrappedItem.onLeftClickEntity(stack, player, entity);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.client.resources.model.ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining)
	{
		return wrappedItem.getModel(stack, player, useRemaining);
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		return wrappedItem.getContainerItem(itemStack);
	}

	@Override
	public boolean hasContainerItem(ItemStack stack)
	{
		return wrappedItem.hasContainerItem(stack);
	}

	@Override
	public int getEntityLifespan(ItemStack itemStack, World world)
	{
		return wrappedItem.getEntityLifespan(itemStack, world);
	}

	@Override
	public boolean hasCustomEntity(ItemStack stack)
	{
		return wrappedItem.hasContainerItem(stack);
	}

	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack)
	{
		return wrappedItem.createEntity(world, location, itemstack);
	}

	@Override
	public boolean onEntityItemUpdate(net.minecraft.entity.item.EntityItem entityItem)
	{
		return wrappedItem.onEntityItemUpdate(entityItem);
	}

	@Override
	public CreativeTabs[] getCreativeTabs()
	{
		return wrappedItem.getCreativeTabs();
	}

	@Override
	public float getSmeltingExperience(ItemStack item)
	{
		return wrappedItem.getSmeltingExperience(item);
	}

	@Override
	public net.minecraft.util.WeightedRandomChestContent getChestGenBase(net.minecraftforge.common.ChestGenHooks chest, Random rnd, net.minecraft.util.WeightedRandomChestContent original)
	{
		return wrappedItem.getChestGenBase(chest, rnd, original);
	}

	@Override
	public boolean doesSneakBypassUse(World world, BlockPos pos, EntityPlayer player)
	{
		return wrappedItem.doesSneakBypassUse(world, pos, player);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		wrappedItem.onArmorTick(world, player, itemStack);
	}

	@Override
	public boolean isValidArmor(ItemStack stack, int armorType, Entity entity)
	{
		return wrappedItem.isValidArmor(stack, armorType, entity);
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return wrappedItem.isBookEnchantable(stack, book);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		return wrappedItem.getArmorTexture(stack, entity, slot, type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.client.gui.FontRenderer getFontRenderer(ItemStack stack)
	{
		return wrappedItem.getFontRenderer(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
	{
		return wrappedItem.getArmorModel(entityLiving, itemStack, armorSlot);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
	{
		return wrappedItem.onEntitySwing(entityLiving, stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, net.minecraft.client.gui.ScaledResolution resolution, float partialTicks)
	{
		wrappedItem.renderHelmetOverlay(stack, player, resolution, partialTicks);
	}

	@Override
	public int getDamage(ItemStack stack)
	{
		return wrappedItem.getDamage(stack);
	}

	@Override
	public int getMetadata(ItemStack stack)
	{
		return wrappedItem.getMetadata(stack);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		return wrappedItem.showDurabilityBar(stack);
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		return wrappedItem.getDurabilityForDisplay(stack);
	}

	@Override
	public int getMaxDamage(ItemStack stack)
	{
		return wrappedItem.getMaxDamage(stack);
	}

	@Override
	public boolean isDamaged(ItemStack stack)
	{
		return wrappedItem.isDamaged(stack);
	}

	@Override
	public void setDamage(ItemStack stack, int damage)
	{
		wrappedItem.setDamage(stack, damage);
	}

	@Override
	public boolean canHarvestBlock(Block par1Block, ItemStack itemStack)
	{
		return wrappedItem.canHarvestBlock(par1Block, itemStack);
	}

	@Override
	public int getItemStackLimit(ItemStack stack)
	{
		return wrappedItem.getItemStackLimit(stack);
	}

	@Override
	public void setHarvestLevel(String toolClass, int level)
	{
		wrappedItem.setHarvestLevel(toolClass, level);
	}

	@Override
	public java.util.Set<String> getToolClasses(ItemStack stack)
	{
		return wrappedItem.getToolClasses(stack);
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass)
	{
		return wrappedItem.getHarvestLevel(stack, toolClass);
	}

	@Override
	public int getItemEnchantability(ItemStack stack)
	{
		return wrappedItem.getItemEnchantability(stack);
	}

	@Override
	public boolean isBeaconPayment(ItemStack stack)
	{
		return wrappedItem.isBeaconPayment(stack);
	}


	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
	{
		return wrappedItem.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
	}

	@Override
	public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
	{
		return wrappedItem.initCapabilities(stack, nbt);
	}
}
