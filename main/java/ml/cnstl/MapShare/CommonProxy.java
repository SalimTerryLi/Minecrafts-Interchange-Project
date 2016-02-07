package ml.cnstl.MapShare;

import org.lwjgl.input.Keyboard;

import ml.cnstl.MapShare.GUI.*;
import ml.cnstl.MapShare.GUI.guiMapEdit.GuiPositionGet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.event.world.WorldEvent;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class CommonProxy
{
	public void init() 
    {
    	
    }
	
	//Server proxy above
	public static class ClientProxy extends CommonProxy
	{
		public void init()
		{
			ClientRegistry.registerKeyBinding(Data.kbShowMainMenu);
		}
		@SubscribeEvent
        public void keyListener(KeyInputEvent event) {
            if (Data.kbShowMainMenu.isPressed())
            {
                Minecraft mc = Minecraft.getMinecraft();
                mc.displayGuiScreen(new MainMenu());
            }
            if(Keyboard.getEventKey()==Data.kbShowGuiPositionGet.getKeyCode())//链接到
            {								//ml.cnstl.MapShare.GUI.guiMapEdit.GuiPositionGet
            	if(Keyboard.getEventKeyState())//实现KeyDown打开GUI
            	{
            		Minecraft mc = Minecraft.getMinecraft();
                    mc.displayGuiScreen(new GuiPositionGet());
            	}
            }
        }
		@SubscribeEvent
        public void WorldLoad(WorldEvent.Load event)
		{
			
		}
	}//Client proxy above
}
