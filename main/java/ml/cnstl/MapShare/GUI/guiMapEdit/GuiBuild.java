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
		super.mouseClicked(par1, par2, par3);
	}
	public void initSubGui()
	{
		tfBlockID=addGuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.1), (int) (width*0.5-20), 15);
		tfBlockType=addGuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.2), (int) (width*0.5-20), 15);
		tfPosition=addGuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.3), (int) (width*0.5-20), 15);
		//tfPosition.setText("");
		buttonList.add(btnBuilding = addGuiButton(0, (int)(width-width*0.5-50), (int)(height*0.8), 100, 20, StatCollector.translateToLocal("btnbuilding.button.guibuild.gui.ms")));
	}
	public void drawSubScreen(int par1, int par2, float par3)
	{
    	drawSubString(fontRendererObj, StatCollector.translateToLocal("blockid.string.guibuild.gui.ms"),(int)(20), (int)(height*0.1), 0xFFFFFF);
    	drawSubString(fontRendererObj, StatCollector.translateToLocal("blocktype.string.guibuild.gui.ms"),(int)(20), (int)(height*0.2), 0xFFFFFF);
    	drawSubString(fontRendererObj, StatCollector.translateToLocal("position.string.guibuild.gui.ms"),(int)(20), (int)(height*0.3), 0xFFFFFF);
    	
    	super.drawSubScreen(par1, par2, par3);
	}
	@Override
    protected void actionPerformed(GuiButton button) 
	{
		
	}
}
