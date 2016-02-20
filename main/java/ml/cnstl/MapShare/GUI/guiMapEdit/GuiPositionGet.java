package ml.cnstl.MapShare.GUI.guiMapEdit;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import ml.cnstl.MapShare.Data;
import ml.cnstl.MapShare.GUI.MainMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiPositionGet extends GuiScreen
{
	/**
     * Handles keyboard input & 实现按键松开关闭GUI.
     */
	@Override
	public void handleKeyboardInput()
	{
        if(Keyboard.getEventKey()==Data.kbShowGuiPositionGet.getKeyCode())
        {
        	if(!Keyboard.getEventKeyState())
        	{
        		Minecraft mc = Minecraft.getMinecraft();
        		mc.setIngameFocus();
        	}
        }
		super.handleKeyboardInput();
	}
	@Override
    public boolean doesGuiPauseGame() {
        return false;
    }
	//以上为实现Gui的打开
	@Override
	public void initGui()
	{
		
	}
	@Override
	public void drawScreen(int par1,int par2,float par3)
	{
		drawRect((int)((width-20-fontRendererObj.getStringWidth(StatCollector.translateToLocal("title.string.guipositionget.gui.ms")))*0.5-1), 2, (int)(width-(width-20-fontRendererObj.getStringWidth(StatCollector.translateToLocal("title.string.guipositionget.gui.ms")))*0.5+1), 21, 0x80BBBBBB);
		drawString(fontRendererObj, "", 0, 0, 0xFFFFFF);
		ResourceLocation texture = new ResourceLocation(Data.MOD_ID, "textures/gui/guipositionget/compass.png");
		mc.renderEngine.bindTexture(texture);
		func_146110_a((int)((width-20-fontRendererObj.getStringWidth(StatCollector.translateToLocal("title.string.guipositionget.gui.ms")))*0.5), 2, 0, 0, 20, 20, 20, 20);
		drawString(fontRendererObj, StatCollector.translateToLocal("title.string.guipositionget.gui.ms"), (int)((width-20-fontRendererObj.getStringWidth(StatCollector.translateToLocal("title.string.guipositionget.gui.ms")))*0.5+20), 7, 0x80FFFFFF);
		
		
		
		super.drawScreen(par1, par2, par3);
	}
}
