package ml.cnstl.MapShare.GUI.guiMapEdit;

import net.minecraft.client.gui.*;
import net.minecraft.util.StatCollector;

public class GuiCopy extends MapEditMain
{
	private GuiButton btnCoping;
	private GuiTextField tfPosition;
	@Override
	protected void mouseClicked(int par1, int par2, int par3)
	{
		super.mouseClicked(par1, par2, par3);
	}
	public void initSubGui()
	{
		tfPosition=addGuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.1), (int) (width*0.5-20), 15);
		buttonList.add(btnCoping = addGuiButton(0, (int)(width-width*0.5-50), (int)(height*0.2), 100, 20, StatCollector.translateToLocal("btncoping.button.guicopy.gui.ms")));
	}
	public void drawSubScreen(int par1, int par2, float par3)
	{
		drawSubString(fontRendererObj, StatCollector.translateToLocal("position.string.guicopy.gui.ms"),(int)(20), (int)(height*0.1), 0xFFFFFF);
    	
    	super.drawSubScreen(par1, par2, par3);
	}
	@Override
    protected void actionPerformed(GuiButton button) 
	{
		
	}
}
