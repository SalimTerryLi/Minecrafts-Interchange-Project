package ml.cnstl.MapShare.GUI.guiMapEdit;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.StatCollector;

public class GuiBuild extends MapEditMain
{
	private GuiButton btnBuilding,btnPosGet;
	private GuiTextField tfPosition,tfBlockID,tfBlockType;
	@Override
	protected void mouseClicked(int par1, int par2, int par3)
	{
		System.out.println("Build "+width+" "+height);//Testing
		super.mouseClicked(par1, par2, par3);
	}
	public void initSubGui()
	{
		tfBlockID=addGuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.1), (int) (width*0.5-20), 15);
		tfBlockID.setText("");
		tfBlockType=addGuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.2), (int) (width*0.5-20), 15);
		tfBlockType.setText("");
		tfPosition=addGuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.3), (int) (width*0.5-20), 15);
		tfPosition.setText("");
		buttonList.add(btnPosGet = addGuiButton(0, (int)(width-width*0.5-50), (int)(height*0.3), 45, 15, StatCollector.translateToLocal("btnposselect.button.gui.bbt")));
		buttonList.add(btnBuilding = addGuiButton(0, (int)(width-width*0.5-50), (int)(height*0.8), 100, 20, StatCollector.translateToLocal("btnbuilding.button.gui.bbt")));
	}
	public void drawSubScreen(int par1, int par2, float par3)
	{
		drawSubCenteredString(fontRendererObj, StatCollector.translateToLocal("subtitle.gui.bbt")+"-"+StatCollector.translateToLocal("name.build.mode.bbt"), width/2, (int)(2), 0xFFFF00);
    	drawSubString(fontRendererObj, StatCollector.translateToLocal("blockid.build.gui.bbt"),(int)(20), (int)(height*0.1), 0xFFFFFF);
    	drawSubString(fontRendererObj, StatCollector.translateToLocal("blocktype.build.gui.bbt"),(int)(20), (int)(height*0.2), 0xFFFFFF);
    	drawSubString(fontRendererObj, StatCollector.translateToLocal("position.build.gui.bbt"),(int)(20), (int)(height*0.3), 0xFFFFFF);
    	
    	super.drawSubScreen(par1, par2, par3);
	}
	@Override
    protected void actionPerformed(GuiButton button) 
	{
		
	}
}
