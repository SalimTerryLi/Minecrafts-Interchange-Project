package ml.cnstl.bbt.operatewindows.screen;

import org.lwjgl.input.Keyboard;

import ml.cnstl.bbt.Block_operate;
import ml.cnstl.bbt.Info;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.StatCollector;

public class ScreenBuild extends GuiScreen{

	private GuiScreen parentScreen;
	private GuiButton btnBuilding,btnPosGet,btnClose;
	private GuiTextField tfBlockID,tfBlockType,tfPosition;
	protected Block_operate op=new Block_operate();

	public ScreenBuild(GuiScreen parent) {
		this.parentScreen = parent;
		// TODO 自动生成的构造函数存根
	}

	public void initGui()
	{
		//每当界面被打开时调用
		Keyboard.enableRepeatEvents(true); //打开键盘连续输入
		if(Info.Tool_Mode=="game")
		{
			tfBlockID=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.1), (int) (width*0.5-20), 15);
			tfBlockType=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.2), (int) (width*0.5-20), 15);
			tfPosition=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.3), (int) (width*0.5-20), 15);
			tfPosition.setText(""+Info.posset[0]+","+Info.posset[1]+","+Info.posset[2]+","+Info.posset[3]+","+Info.posset[4]+","+Info.posset[5]);
			buttonList.add(btnPosGet = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.3), 45, 15, StatCollector.translateToLocal("btnposselect.button.gui.bbt")));
			buttonList.add(btnBuilding = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.8), 100, 20, StatCollector.translateToLocal("btnbuilding.button.gui.bbt")));
			buttonList.add(btnClose = new GuiButton(0, (int)(width-width*0.01-80), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btnClose.button.gui.bbt")));
		}
	}
	protected void keyTyped(char par1, int par2) {
		if(tfBlockID.textboxKeyTyped(par1, par2)) //向文本框传入输入的内容
			return;
		if(tfBlockType.textboxKeyTyped(par1, par2)) //向文本框传入输入的内容
			return;
		if(tfPosition.textboxKeyTyped(par1, par2)) //向文本框传入输入的内容
			return;
		super.keyTyped(par1, par2);
	}
	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		tfBlockID.mouseClicked(par1, par2, par3); //调用文本框的鼠标点击检查
		tfBlockType.mouseClicked(par1, par2, par3);
		tfPosition.mouseClicked(par1, par2, par3);
		super.mouseClicked(par1, par2, par3);
	}
	public void drawScreen(int par1, int par2, float par3)
    {
		tfBlockID.drawTextBox();
    	tfBlockType.drawTextBox();
    	tfPosition.drawTextBox();
    	
    	drawCenteredString(fontRendererObj, StatCollector.translateToLocal("subtitle.gui.bbt")+"-"+StatCollector.translateToLocal("name.build.mode.bbt"), width/2, (int)(2), 0xFFFF00);
    	drawString(fontRendererObj, StatCollector.translateToLocal("blockid.build.gui.bbt"),(int)(20), (int)(height*0.1), 0xFFFFFF);
    	drawString(fontRendererObj, StatCollector.translateToLocal("blocktype.build.gui.bbt"),(int)(20), (int)(height*0.2), 0xFFFFFF);
    	drawString(fontRendererObj, StatCollector.translateToLocal("position.build.gui.bbt"),(int)(20), (int)(height*0.3), 0xFFFFFF);
    }
	@Override
    protected void actionPerformed(GuiButton button) {
		if(button==btnPosGet)
        {
        	Info.Tool_Mode="setpositiontwo";
        	mc.displayGuiScreen(parentScreen);
        }
        else if(button==btnBuilding)
        {
        	boolean paraabled=false;
        	int BlockID,BlockType;
        	if((tfBlockID.getText()!="")&(tfBlockType.getText()!=""))
        	{
        		//BlockID=Integer.parseInt(tfBlockID.getText());BlockType=Integer.parseInt(tfBlockType.getText());paraabled=true;
        		try
        		{
        			BlockID=Integer.parseInt(tfBlockID.getText());BlockType=Integer.parseInt(tfBlockType.getText());paraabled=true;
        			String[] posstr=tfPosition.getText().split(",");
            		int[] pos=new int[6];
            		for(int i=0;i<=5;i++)
            		{
            			pos[i]=Integer.parseInt(posstr[i]);
            		}
            		op.Build(pos[0], pos[1], pos[2], pos[3], pos[4], pos[5], BlockID, BlockType);
        		}catch(Exception exc){}
        	}
        }
        else if(button == btnClose)
        {
        	mc.displayGuiScreen(parentScreen);
    	}
	}
	
	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false); //关闭键盘连续输入
	}
	
	@Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
