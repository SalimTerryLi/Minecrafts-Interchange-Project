package ml.cnstl.MapShare.GUI.guiMapEdit;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import ml.cnstl.MapShare.Data;
import ml.cnstl.MapShare.GUI.MainMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

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
	
}
