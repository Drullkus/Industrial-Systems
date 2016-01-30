package us.drullk.industrialsystems.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import us.drullk.industrialsystems.IndustrialSystems;
import us.drullk.industrialsystems.tile.TileSmartTrashCan;

public class BlockSmartTrashCan extends BlockContainer implements ITileEntityProvider
{
	public BlockSmartTrashCan()
	{
		super(Material.iron);
	}

	public int getRenderType()
	{
		return 3;
	}

	@Override
	public boolean hasTileEntity(IBlockState blockState)
	{
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileSmartTrashCan();
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		TileEntity te = worldIn.getTileEntity(pos);

		if (te == null || !(te instanceof TileSmartTrashCan))
		{
			return true;
		}

		if (worldIn.isRemote)
		{
			return true;
		}

		playerIn.openGui(IndustrialSystems.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());

		return true;
	}
}
