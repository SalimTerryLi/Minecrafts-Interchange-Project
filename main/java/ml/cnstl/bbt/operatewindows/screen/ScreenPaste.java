package ml.cnstl.bbt.operatewindows.screen;

import ml.cnstl.bbt.Info;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

public class ScreenPaste extends Screen {

	GuiButton btnPasting,btnPosGetonlyone,btnIgnoreempyt,btnPreview,btnChangePreview;
	
	public ScreenPaste(GuiScreen parent) {
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
			tfPosition.setText(""+Info.posset[0]+","+Info.posset[1]+","+Info.posset[2]);
			buttonList.add(btnPosGetonlyone = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.1), 45, 15, StatCollector.translateToLocal("btnposselect.button.gui.bbt")));
			buttonList.add(btnIgnoreempyt = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.2), 45, 15, StatCollector.translateToLocal("btnignoreemptydisabled.paste.gui.bbt")));
			buttonList.add(btnPreview = new GuiButton(0, (int)(width*0.5), (int)(height*0.2), (int) (width*0.25-8), 15, StatCollector.translateToLocal("btnpreview.paste.gui.bbt")));
			buttonList.add(btnChangePreview = new GuiButton(0, (int)(width*0.75), (int)(height*0.2), (int) (width*0.25-8), 15, StatCollector.translateToLocal("btnchangepreview.paste.gui.bbt")));
			buttonList.add(btnPasting = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.7), 100, 20, StatCollector.translateToLocal("btnpasting.button.gui.bbt")));
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
		drawString(fontRendererObj, StatCollector.translateToLocal("ignoreempty.paste.gui.bbt"),(int)(20), (int)(height*0.2), 0xFFFFFF);
		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("subtitle.gui.bbt")+"-"+StatCollector.translateToLocal("name.paste.mode.bbt"), width/2, (int)(2), 0xFFFF00);
    }
	@Override
    protected void actionPerformed(GuiButton button) {
		if(button==btnPasting)
        {
        	op.clearpreview();
        	String bm;
        	if(btnIgnoreempyt.displayString==StatCollector.translateToLocal("btnignoreemptydisabled.paste.gui.bbt")){bm="clearempty";}else{bm="none";}
        	String[] posstr=tfPosition.getText().split(",");
        	int[] pos=op.strarr2intarr(posstr);
    		/*for(int i=0;i<=2;i++)
    		{
    			pos[i]=Integer.parseInt(posstr[i]);
    		}*/
        	op.Paste(pos[0],pos[1],pos[2],Info.pastemode,bm);
        }
        else if(button==btnPosGetonlyone)
        {
        	Info.Tool_Mode="setpositiononlyone";
        	mc.displayGuiScreen(parentScreen);
        }
        else if(button==btnIgnoreempyt)
        {
        	if(btnIgnoreempyt.displayString==StatCollector.translateToLocal("btnignoreemptydisabled.paste.gui.bbt")){btnIgnoreempyt.displayString=StatCollector.translateToLocal("btnignoreemptyenabled.paste.gui.bbt");}else{btnIgnoreempyt.displayString=StatCollector.translateToLocal("btnignoreemptydisabled.paste.gui.bbt");}
        }
        else if(button==btnPreview)
        {
        	op.clearpreview();
        	String[] posstr=tfPosition.getText().split(",");
        	int[] pos=op.strarr2intarr(posstr);
    		/*for(int i=0;i<=2;i++)
    		{
    			pos[i]=Integer.parseInt(posstr[i]);
    		}*/
        	op.Paste(pos[0],pos[1],pos[2],Info.pastemode,"preview");
        }
        else if(button==btnChangePreview)
        {
        	op.clearpreview();
        	if(Info.pastemode=="diagonal_1"){Info.pastemode="diagonal_2";}
    		else if(Info.pastemode=="diagonal_2"){Info.pastemode="diagonal_3";}
    		else if(Info.pastemode=="diagonal_3"){Info.pastemode="diagonal_4";}
    		else if(Info.pastemode=="diagonal_4"){Info.pastemode="top_center";}
    		else if(Info.pastemode=="top_center"){Info.pastemode="buttom_center";}
    		else if(Info.pastemode=="buttom_center"){Info.pastemode="buttom_side_center_1";}
    		else if(Info.pastemode=="buttom_side_center_1"){Info.pastemode="buttom_side_center_2";}
    		else if(Info.pastemode=="buttom_side_center_2"){Info.pastemode="buttom_side_center_3";}
    		else if(Info.pastemode=="buttom_side_center_3"){Info.pastemode="buttom_side_center_4";}
    		else if(Info.pastemode=="buttom_side_center_4"){Info.pastemode="diagonal_1";}
        	String[] posstr=tfPosition.getText().split(",");
        	int[] pos=op.strarr2intarr(posstr);
    		/*for(int i=0;i<=2;i++)
    		{
    			pos[i]=Integer.parseInt(posstr[i]);
    		}*/
        	op.Paste(pos[0],pos[1],pos[2],Info.pastemode,"preview");
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
