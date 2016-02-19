package ml.cnstl.MapShare.GUI.guiMapEdit;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.StatCollector;

public class GuiReplace extends MapEditMain
{
	private GuiButton btnisKeptID,btnisKeptType,btnEnabledType,btnReplacing;
	private GuiTextField tfPosition,tfBlockID,tfBlockType,tfreBlockID,tfreBlockType;
	@Override
	protected void mouseClicked(int par1, int par2, int par3)
	{
		super.mouseClicked(par1, par2, par3);
	}
	public void initSubGui()
	{
		buttonList.add(btnisKeptID = addGuiButton(0, (int)(20), (int)(height*0.1), (int) (width*0.5-75), 15, StatCollector.translateToLocal("reblockid.button.guireplace.gui.ms")));
		buttonList.add(btnisKeptType = addGuiButton(0, (int)(20), (int)(height*0.2), (int) (width*0.5-75), 15, StatCollector.translateToLocal("reblocktype.button.guireplace.gui.ms")));
		btnisKeptType.enabled=false;
		tfreBlockID=addGuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.1), (int) (width*0.5-20), 15);
		tfreBlockType=addGuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.2), (int) (width*0.5-20), 15);
		buttonList.add(btnEnabledType = addGuiButton(0, (int)(width-width*0.5-50), (int)(height*0.2), 45, 15, StatCollector.translateToLocal("btnenabledtype.button.guireplace.gui.ms")));
		tfBlockID=addGuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.3), (int) (width*0.5-20), 15);
		tfBlockType=addGuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.4), (int) (width*0.5-20), 15);
		tfPosition=addGuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.5), (int) (width*0.5-20), 15);
		buttonList.add(btnReplacing = addGuiButton(0, (int)(width-width*0.5-50), (int)(height*0.8), 100, 20, StatCollector.translateToLocal("btnreplacing.button.guireplace.gui.ms")));
	}
	public void drawSubScreen(int par1, int par2, float par3)
	{
		drawSubString(fontRendererObj, StatCollector.translateToLocal("blockid.string.guireplace.gui.ms"),(int)(20), (int)(height*0.3), 0xFFFFFF);
		drawSubString(fontRendererObj, StatCollector.translateToLocal("blocktype.string.guireplace.gui.ms"),(int)(20), (int)(height*0.4), 0xFFFFFF);
		drawSubString(fontRendererObj, StatCollector.translateToLocal("position.string.guireplace.gui.ms"),(int)(20), (int)(height*0.5), 0xFFFFFF);

		super.drawSubScreen(par1, par2, par3);
	}
	@Override
	protected void actionPerformed(GuiButton button) 
	{
		if(button==btnEnabledType)
        {
        	tfreBlockType.setVisible(!tfreBlockType.getVisible());
        	if(!tfreBlockType.getVisible()){btnEnabledType.displayString=StatCollector.translateToLocal("btndisabledtype.button.guireplace.gui.ms");}else{btnEnabledType.displayString=StatCollector.translateToLocal("btnenabledtype.button.guireplace.gui.ms");};
        }
        else if(button==btnisKeptID)
        {
        	if(btnisKeptID.displayString==StatCollector.translateToLocal("reblockid.button.guireplace.gui.ms"))
        	{
        		btnisKeptID.displayString=StatCollector.translateToLocal("keepblockid.button.guireplace.gui.ms");
        		btnisKeptType.displayString=StatCollector.translateToLocal("keepblocktype.button.guireplace.gui.ms");
        	}
        	else
        	{
        		btnisKeptID.displayString=StatCollector.translateToLocal("reblockid.button.guireplace.gui.ms");
        		btnisKeptType.displayString=StatCollector.translateToLocal("reblocktype.button.guireplace.gui.ms");
        	}
        }
	}
}