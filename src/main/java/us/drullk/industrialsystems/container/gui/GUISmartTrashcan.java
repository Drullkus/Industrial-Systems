package us.drullk.industrialsystems.container.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import us.drullk.industrialsystems.container.ContainerSmartTrashcan;
import us.drullk.industrialsystems.tile.TileSmartTrashCan;

public class GUISmartTrashcan extends GuiContainer
{
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
	private int inventoryRows = 3 * 18;
	private TileSmartTrashCan inventoryTrash;

	public GUISmartTrashcan(TileSmartTrashCan TrashCan)
	{
		super(new ContainerSmartTrashcan((IInventory) Minecraft.getMinecraft().thePlayer, TrashCan));
		this.inventoryTrash = TrashCan;
		this.allowUserInput = false;
		int i = 222;
		int j = i - 108;
		this.inventoryRows = inventoryTrash.getSizeInventory() / 9;
		this.ySize = j + this.inventoryRows * 18;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
	{
		this.fontRendererObj.drawString(this.inventoryTrash.getDisplayName().getUnformattedText(), 8, 6, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
		this.drawTexturedModalRect(i, j + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
	}
}
