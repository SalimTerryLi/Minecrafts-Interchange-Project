package ml.cnstl.MapShare.GUI.guiMapEdit;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class MapEditMain extends GuiScreen
{
	private ResourceLocation texture;
	
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
	}
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}
	public void drawScreen(int par1, int par2, float par3)
	{
		
		super.drawScreen(par1, par2, par3);
	}
}
