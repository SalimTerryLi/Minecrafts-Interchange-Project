package ml.cnstl.MapShare;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.KeyBinding;

public class Data
{
	public static final String MOD_NAME="MapShare";
	public static final String MOD_ID="mapshare";
	public static final String MOD_VERSION="dev0";
	//Minecraft Mod's main info
	public static final KeyBinding kbShowMainMenu = new KeyBinding("title.hot-key.option.ms", Keyboard.KEY_M, "MapShare");
	public static final KeyBinding kbShowGuiPositionGet = new KeyBinding("showguipositionget.hot-key.option.ms", Keyboard.KEY_P, "MapShare");
}
