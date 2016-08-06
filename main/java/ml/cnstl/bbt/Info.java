package ml.cnstl.bbt;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;

public class Info {
	public static final String MOD_NAME="Block Build Tool";
	public static final String MOD_ID="Block_Build_Tool";
	public static final String MOD_VERSION="Beta2";
	//Minecraft Mod's main info
	static public Item PosSelector;
	
	public static String Tool_Mode="outgame";
	public static Boolean whethercopied=false;
	public static int[] posset=new int[6];
	public static int[][][] BlockType;
	public static int[][][] BlockData;
	public static String previewstr="";
	public static int[] previewposx=new int[8];
	public static int[] previewposy=new int[8];
	public static int[] previewposz=new int[8];
	public static String pastemode="buttom_center";
}
