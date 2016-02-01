package us.drullk.industrialsystems.rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ISmartItemModel;
import org.lwjgl.util.vector.Vector3f;
import us.drullk.industrialsystems.item.ISItems;
import us.drullk.industrialsystems.item.ItemRFTool;

import java.util.ArrayList;
import java.util.List;

public class RFWrappedToolRender implements ISmartItemModel
{


	private static final FaceBakery bakery = new FaceBakery();

	private IBakedModel parentModel;


	@Override
	public IBakedModel handleItemState(ItemStack stack)
	{
		if (this.parentModel == null && stack.getItem() instanceof ItemRFTool){
			ItemStack wrappedItem = ISItems.pickaxeRF.unwrapThisItemStack(stack);
			this.parentModel = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(wrappedItem);
		}
		return this;
	}

	public static BakedQuad getOverlayQuad(){
		//return bakery.makeBakedQuad(new Vector3f(0, 0, 0), new Vector3f(1, 1, 1))
		return null; //todo actually generate quad for overlay texture
	}

	private static List<BakedQuad> getAllQuads(IBakedModel model){
		List<BakedQuad> toReturn = new ArrayList<BakedQuad>();
		for (EnumFacing f : EnumFacing.values()){
			toReturn.addAll(model.getFaceQuads(f));
		}
		toReturn.addAll(model.getGeneralQuads());
		return toReturn;
	}

	@Override
	public List<BakedQuad> getFaceQuads(EnumFacing face)
	{
		//todo Add overlay quad
		return this.parentModel.getFaceQuads(face);
	}

	@Override
	public List<BakedQuad> getGeneralQuads()
	{
		return this.parentModel.getGeneralQuads();
	}

	@Override
	public boolean isAmbientOcclusion()
	{
		return this.parentModel.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d()
	{
		return this.parentModel.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer()
	{
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture()
	{
		return this.parentModel.getParticleTexture();
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms()
	{
		return this.parentModel.getItemCameraTransforms();
	}
}
