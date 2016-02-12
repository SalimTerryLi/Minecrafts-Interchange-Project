package ml.cnstl.MapShare;

import org.lwjgl.input.Keyboard;
import ml.cnstl.MapShare.GUI.guiMapEdit.*;
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
	
	public static final GuiScreen GuiBuild=new GuiBuild();
	public static final GuiScreen GuiReplace=new GuiReplace();
	public static final GuiScreen GuiCopy=new GuiCopy();
	public static final GuiScreen GuiCut=new GuiCut();
	public static final GuiScreen GuiPaste=new GuiPaste();
	public static GuiScreen lastShowMapEditGui=GuiBuild;
}
