package us.drullk.industrialsystems.item.Wrapper;

import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import us.drullk.industrialsystems.IndustrialSystems;
import us.drullk.industrialsystems.item.ISItems;

import java.util.List;
import java.util.Random;

public class ItemWrapper extends Item
{
	//TODO: Add warning labels

	@Deprecated
	public Item fallbackItem;

	private String realUnlocalizedName;

	public ItemWrapper(Item defaultItem, String realUnlocalizedName)
	{
		fallbackItem = defaultItem;
		this.setMaxStackSize(1);
		this.realUnlocalizedName = realUnlocalizedName;
	}

	public ItemStack wrapStack(ItemStack wrapperStack, ItemStack wrappedStack){
		NBTTagCompound wrappedCompound = new NBTTagCompound();
		wrappedStack.writeToNBT(wrappedCompound);
		NBTTagCompound tagCompound = wrapperStack.getTagCompound();
		if (tagCompound == null){
			tagCompound = new NBTTagCompound();
		}
		tagCompound.setTag("wrappedStack", wrappedCompound);
		wrapperStack.setTagCompound(tagCompound);
		return wrapperStack;
	}

	public ItemStack unwrapThisItemStack(ItemStack wrappedStack)
	{
		ItemStack unwrappedStack = new ItemStack(fallbackItem, 0);

		if(wrappedStack != null && wrappedStack.getItem() instanceof ItemWrapper)
		{
			NBTTagCompound tagComp = wrappedStack.getTagCompound();

			if(tagComp != null)
			{
				unwrappedStack = unloadStackFromWrappedNBT(tagComp);
			}
			else
			{
				IndustrialSystems.logger.info("The TagComp retrieved from wrapper was bad!");
			}
		}

		if(wrappedStack == null)
		{
			IndustrialSystems.logger.info("ItemStack Wrapper is null!");
		}

		if(unwrappedStack == null)
		{
			IndustrialSystems.logger.info("Unwrapped Itemstack is null!");
		}

		return unwrappedStack;
	}

	public NBTTagCompound unwrapThisNBTComp(NBTTagCompound tagComp)
	{

		NBTTagCompound wrappedCompound = tagComp.getCompoundTag("wrappedStack");

		if(wrappedCompound == null)
		{
			IndustrialSystems.logger.info("Wrapped compound is null! :(");

			return null;
		}

		return wrappedCompound;
	}

	public ItemStack unloadStackFromWrappedNBT(NBTTagCompound tagComp)
	{
		ItemStack unwrappedStack = ItemStack.loadItemStackFromNBT(unwrapThisNBTComp(tagComp));

		if(unwrappedStack == null)
		{
			IndustrialSystems.logger.info("There was a problem unwrapping stack from NBT!");
		}
		else
		{
			//IndustrialSystems.logger.info("Got ItemStack containing Item " + unwrappedStack.getItem().getUnlocalizedName(unwrappedStack) + " from NBT!");
		}

		return unwrappedStack;
	}

	@Override
	public boolean updateItemStackNBT(NBTTagCompound nbt)
	{
		ItemStack unwrappedStack = unloadStackFromWrappedNBT(nbt);
		boolean result;

		result = unwrappedStack.getItem().updateItemStackNBT(unwrappedStack.getTagCompound());

		return result;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		boolean value = unwrappedStack.getItem().onItemUse(unwrappedStack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
		wrapStack(stack, unwrappedStack);
		return value;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(itemStackIn);

		return wrapStack(itemStackIn, unwrappedStack.getItem().onItemRightClick(unwrappedStack, worldIn, playerIn));
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityPlayer playerIn)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return wrapStack(stack, unwrappedStack.getItem().onItemUseFinish(unwrappedStack, worldIn, playerIn));
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		boolean value = unwrappedStack.getItem().hitEntity(unwrappedStack, target, attacker);
		wrapStack(stack, unwrappedStack);
		return value;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos, EntityLivingBase playerIn)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		boolean value = unwrappedStack.getItem().onBlockDestroyed(unwrappedStack, worldIn, blockIn, pos, playerIn);
		wrapStack(stack, unwrappedStack);
		return value;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		boolean value = unwrappedStack.getItem().itemInteractionForEntity(unwrappedStack, playerIn, target);
		wrapStack(stack, unwrappedStack);
		return value;
	}

	@Override
	public String getUnlocalizedNameInefficiently(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getUnlocalizedNameInefficiently(unwrappedStack);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getUnlocalizedName(unwrappedStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getColorFromItemStack(unwrappedStack, renderPass);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		unwrappedStack.getItem().onUpdate(unwrappedStack, worldIn, entityIn, itemSlot, isSelected);
		wrapStack(stack, unwrappedStack);
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		unwrappedStack.getItem().onCreated(unwrappedStack, worldIn, playerIn);
		wrapStack(stack, unwrappedStack);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		EnumAction value = unwrappedStack.getItem().getItemUseAction(unwrappedStack);
		wrapStack(stack, unwrappedStack);
		return value;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		int value = unwrappedStack.getItem().getMaxItemUseDuration(unwrappedStack);
		wrapStack(stack, unwrappedStack);
		return value;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityPlayer playerIn, int timeLeft)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		unwrappedStack.getItem().onPlayerStoppedUsing(unwrappedStack, worldIn, playerIn, timeLeft);
		wrapStack(stack, unwrappedStack);
	}

	@Override
	public String getPotionEffect(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getPotionEffect(unwrappedStack);
	}

	@Override
	public boolean isPotionIngredient(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().isPotionIngredient(unwrappedStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		if(stack == null)
		{
			stack = new ItemStack(fallbackItem);
		}

		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		unwrappedStack.getItem().addInformation(unwrappedStack, playerIn, tooltip, advanced);

		wrapStack(stack, unwrappedStack);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getItemStackDisplayName(unwrappedStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().hasEffect(unwrappedStack);
	}

	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getRarity(unwrappedStack);
	}

	@Override
	public boolean isItemTool(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().isItemTool(unwrappedStack);
	}

	/*@Override
	protected MovingObjectPosition getMovingObjectPositionFromPlayer(World worldIn, EntityPlayer playerIn, boolean useLiquids)
	{
		return fallbackItem.getMovingObjectPositionFromPlayer(worldIn, playerIn, useLiquids);
	}//*/

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
	{
		// Stack Overflowing!
		// itemIn.getSubItems(itemIn, tab, subItems);

		fallbackItem.getSubItems(itemIn, tab, subItems);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
	{
		// TODO: Fuss with unwrapping then rewrapping

		ItemStack unwrappedStack = unwrapThisItemStack(toRepair);

		boolean value = unwrappedStack.getItem().getIsRepairable(unwrappedStack, repair);
		wrapStack(toRepair, unwrappedStack);
		return value;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean isFull3D()
	{
		return fallbackItem.isFull3D();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldRotateAroundWhenRendering()
	{
		return fallbackItem.shouldRotateAroundWhenRendering();
	}

	@Override
	public int getMetadata(int damage)
	{
		// Can't do much here :/

		return fallbackItem.getMetadata(damage);
	}

	@Override
	public boolean getHasSubtypes()
	{
		// Can't do much here :/

		return fallbackItem.getHasSubtypes();
	}

	@Override
	public int getMaxDamage()
	{
		//TODO: RF "Damage" Bar
		return fallbackItem.getMaxDamage();
	}

	@Override
	public boolean isDamageable()
	{
		//TODO: RF "Damage" Bar
		return fallbackItem.isDamageable();
	}

	@Override
	public boolean canHarvestBlock(Block blockIn)
	{
		// Can't do much here :/

		return fallbackItem.canHarvestBlock(blockIn);
	}

	@Override
	public boolean getShareTag()
	{
		return fallbackItem.getShareTag();
	}

	@Override
	public Item getContainerItem()
	{
		return fallbackItem.getContainerItem();
	}

	@Override
	public boolean hasContainerItem()
	{
		return fallbackItem.hasContainerItem();
	}

	@Override
	public boolean isMap()
	{
		return fallbackItem.isMap();
	}

	@Override
	public String getUnlocalizedName()
	{
		return this.realUnlocalizedName;
	}

	@Override
	public Item setPotionEffect(String potionEffect)
	{
		// Can't do much

		return fallbackItem.setPotionEffect(potionEffect);
	}

	@Override
	public int getItemEnchantability()
	{
		return fallbackItem.getItemEnchantability();
	}

	@Override
	public Item setCreativeTab(CreativeTabs tab)
	{
		return fallbackItem.setCreativeTab(tab);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public CreativeTabs getCreativeTab()
	{
		return fallbackItem.getCreativeTab();
	}

	@Override
	public boolean canItemEditBlocks()
	{
		return fallbackItem.canItemEditBlocks();
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers()
	{
		return fallbackItem.getItemAttributeModifiers();
	}

	// Overridden Forge Methods

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getAttributeModifiers(unwrappedStack);
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(item);

		return unwrappedStack.getItem().onDroppedByPlayer(unwrappedStack, player);
	}

	@Override
	public String getHighlightTip( ItemStack item, String displayName )
	{
		ItemStack unwrappedStack = unwrapThisItemStack(item);

		return unwrappedStack.getItem().getHighlightTip(unwrappedStack, displayName);
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		boolean value = unwrappedStack.getItem().onItemUseFirst(unwrappedStack, player, world, pos, side, hitX, hitY, hitZ);
		wrapStack(stack, unwrappedStack);
		return value;
	}

	@Override
	public float getDigSpeed(ItemStack itemstack, net.minecraft.block.state.IBlockState state)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(itemstack);

		return unwrappedStack.getItem().getDigSpeed(unwrappedStack, state);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(itemstack);

		boolean value = unwrappedStack.getItem().onBlockStartBreak(unwrappedStack, pos, player);
		wrapStack(itemstack, unwrappedStack);
		return value;
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		unwrappedStack.getItem().onUsingTick(unwrappedStack, player, count);
		wrapStack(stack, unwrappedStack);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		boolean value = unwrappedStack.getItem().onLeftClickEntity(unwrappedStack, player, entity);
		wrapStack(stack, unwrappedStack);
		return value;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.client.resources.model.ModelResourceLocation getModel(ItemStack stack, EntityPlayer player, int useRemaining)
	{
		//TODO: Hm!

		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getModel(unwrappedStack, player, useRemaining);
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(itemStack);

		return unwrappedStack.getItem().getContainerItem(unwrappedStack);
	}

	@Override
	public boolean hasContainerItem(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().hasContainerItem(unwrappedStack);
	}

	@Override
	public int getEntityLifespan(ItemStack itemStack, World world)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(itemStack);

		return unwrappedStack.getItem().getEntityLifespan(unwrappedStack, world);
	}

	@Override
	public boolean hasCustomEntity(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().hasCustomEntity(unwrappedStack);
	}

	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(itemstack);

		return unwrappedStack.getItem().createEntity(world, location, unwrappedStack);
	}

	@Override
	public boolean onEntityItemUpdate(net.minecraft.entity.item.EntityItem entityItem)
	{
		// TODO: There... MIGHT... be a way...

		ItemStack unwrappedStack = unwrapThisItemStack(entityItem.getEntityItem());

		boolean result = unwrappedStack.getItem().onEntityItemUpdate(entityItem);
		wrapStack(entityItem.getEntityItem(), unwrappedStack);
		return result;
	}

	@Override
	public float getSmeltingExperience(ItemStack item)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(item);

		return unwrappedStack.getItem().getSmeltingExperience(unwrappedStack);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(itemStack);

		unwrappedStack.getItem().onArmorTick(world, player, unwrappedStack);
		wrapStack(itemStack, unwrappedStack);
	}

	@Override
	public boolean isValidArmor(ItemStack stack, int armorType, Entity entity)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().isValidArmor(unwrappedStack, armorType, entity);
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().isBookEnchantable(unwrappedStack, book);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getArmorTexture(unwrappedStack, entity, slot, type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.client.gui.FontRenderer getFontRenderer(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getFontRenderer(unwrappedStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(itemStack);

		return unwrappedStack.getItem().getArmorModel(entityLiving, unwrappedStack, armorSlot);
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		boolean value = unwrappedStack.getItem().onEntitySwing(entityLiving, unwrappedStack);
		wrapStack(stack, unwrappedStack);
		return value;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, net.minecraft.client.gui.ScaledResolution resolution, float partialTicks)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		unwrappedStack.getItem().renderHelmetOverlay(unwrappedStack, player, resolution, partialTicks);
	}

	@Override
	public int getDamage(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getDamage(unwrappedStack);
	}

	@Override
	public int getMetadata(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getMetadata(unwrappedStack);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack)
	{
		// TODO: RF Durability bar...

		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().showDurabilityBar(unwrappedStack);
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack)
	{
		// TODO: RF Duraility Bar

		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getDurabilityForDisplay(unwrappedStack);
	}

	@Override
	public int getMaxDamage(ItemStack stack)
	{
		// TODO: RF Dura Bar

		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getMaxDamage(unwrappedStack);
	}

	@Override
	public boolean isDamaged(ItemStack stack)
	{
		// TODO: RF Dura Bar

		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().isDamaged(unwrappedStack);
	}

	@Override
	public void setDamage(ItemStack stack, int damage)
	{
		// TODO: RF Dura Bar

		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		unwrappedStack.getItem().setDamage(unwrappedStack, damage);
	}

	@Override
	public boolean canHarvestBlock(Block par1Block, ItemStack itemStack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(itemStack);

		return unwrappedStack.getItem().canHarvestBlock(par1Block, unwrappedStack);
	}

	@Override
	public int getItemStackLimit(ItemStack stack)
	{
		// TODO: Pretty sure it should be JUST one on the stacklimit

		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getItemStackLimit(unwrappedStack);
	}

	@Override
	public java.util.Set<String> getToolClasses(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getToolClasses(unwrappedStack);
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getHarvestLevel(unwrappedStack, toolClass);
	}

	@Override
	public int getItemEnchantability(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().getItemEnchantability(unwrappedStack);
	}

	@Override
	public boolean isBeaconPayment(ItemStack stack)
	{
		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		return unwrappedStack.getItem().isBeaconPayment(unwrappedStack);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged)
	{
		// TODO: Fuss with unwrapping and rewrapping

		return fallbackItem.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
	}

	@Override
	public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
	{
		// TODO: Perhaps may explode?

		/*

		if(stack == null)
		{
			return null;
		}

		ItemStack unwrappedStack = unwrapThisItemStack(stack);

		if(unwrappedStack == null)
		{
			return null;
		}

		return unwrappedStack.getItem().initCapabilities(unwrappedStack, unwrapThisNBTComp(nbt));

		*/

		return null;
	}

	@Override
	public boolean isRepairable()
	{
		return fallbackItem.isRepairable();
	}

	@Override
	public Item setNoRepair()
	{
		return fallbackItem.setNoRepair();
	}

	@Override
	public CreativeTabs[] getCreativeTabs()
	{
		return fallbackItem.getCreativeTabs();
	}

	@Override
	public net.minecraft.util.WeightedRandomChestContent getChestGenBase(net.minecraftforge.common.ChestGenHooks chest, Random rnd, net.minecraft.util.WeightedRandomChestContent original)
	{
		return fallbackItem.getChestGenBase(chest, rnd, original);
	}

	@Override
	public boolean doesSneakBypassUse(World world, BlockPos pos, EntityPlayer player)
	{
		if (player.getItemInUse().getItem() instanceof ItemWrapper){
			ItemStack unwrappedStack = unwrapThisItemStack(player.getItemInUse());
			return unwrappedStack.getItem().doesSneakBypassUse(world, pos, player);
		}
		return fallbackItem.doesSneakBypassUse(world, pos, player);
	}

	@Override
	public void setHarvestLevel(String toolClass, int level)
	{
		// Bleh!

		fallbackItem.setHarvestLevel(toolClass, level);
	}
}
