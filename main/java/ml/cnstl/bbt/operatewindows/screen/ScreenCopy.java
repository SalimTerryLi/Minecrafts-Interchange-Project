package ml.cnstl.bbt.operatewindows.screen;

import ml.cnstl.bbt.Info;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

public class ScreenCopy extends Screen {
	
	GuiButton btnCoping;
	
	public ScreenCopy(GuiScreen parent) {
		super(parent);
		parentScreen = parent;
		// TODO 自动生成的构造函数存根
	}

	public void initGui()
	{
		//每当界面被打开时调用
		Keyboard.enableRepeatEvents(true); //打开键盘连续输入
		if(Info.Tool_Mode=="game")
		{
			tfPosition=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.1), (int) (width*0.5-20), 15);
			tfPosition.setText(""+Info.posset[0]+","+Info.posset[1]+","+Info.posset[2]+","+Info.posset[3]+","+Info.posset[4]+","+Info.posset[5]);
			buttonList.add(btnPosGet = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.1), 45, 15, StatCollector.translateToLocal("btnposselect.button.gui.bbt")));
			buttonList.add(btnCoping = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.2), 100, 20, StatCollector.translateToLocal("btncoping.button.gui.bbt")));
			buttonList.add(btnClose = new GuiButton(0, (int)(width-width*0.01-80), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btnClose.button.gui.bbt")));
		}
	}
	protected void keyTyped(char par1, int par2) {
		if(tfPosition.textboxKeyTyped(par1, par2)) //向文本框传入输入的内容
            return;
		super.keyTyped(par1, par2);
	}
	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		tfPosition.mouseClicked(par1, par2, par3);
	}
	public void drawScreen(int par1, int par2, float par3)
    {
		tfPosition.drawTextBox();
    	
		drawString(fontRendererObj, StatCollector.translateToLocal("position.replace.gui.bbt"),(int)(20), (int)(height*0.1), 0xFFFFFF);
		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("subtitle.gui.bbt")+"-"+StatCollector.translateToLocal("name.copy.mode.bbt"), width/2, (int)(2), 0xFFFF00);
    }
	@Override
    protected void actionPerformed(GuiButton button) {
		if(button==btnCoping)
        {
        	try
    		{
        		String[] posstr=tfPosition.getText().split(",");
        		int[] pos=new int[6];
        		for(int i=0;i<=5;i++)
        		{
        			pos[i]=Integer.parseInt(posstr[i]);
        		}
        		op.Copy(pos[0], pos[1], pos[2], pos[3], pos[4], pos[5]);
        		Info.whethercopied=true;
    		}catch(Exception exc){}
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
