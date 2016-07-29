package ml.cnstl.MapShare.GUI.guiMapEdit;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import ml.cnstl.MapShare.Data;
import ml.cnstl.MapShare.GUI.MainMenu;
import ml.cnstl.MapShare.utils.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiPositionGet extends GuiScreen
{
	ArrayList textList= new ArrayList(),textListtext= new ArrayList();
	private GuiButton btnpohApplytoP1,btnpohApplytoP2,btnpufApplytoP1,btnpufApplytoP2,btnpftApplytoP1,btnpftApplytoP2;
	private GuiTextField tfPosition1,tfPosition2;
	private static String tfp1text=",,",tfp2text=",,";
	/**
     * Handles keyboard input & 实现按键松开关闭GUI.
     */
	@Override
	public void handleKeyboardInput()
	{
        if(Keyboard.getEventKey()==Data.kbShowGuiPositionGet.getKeyCode())
        {
        	if(!Keyboard.getEventKeyState())
        	{
        		Minecraft mc = Minecraft.getMinecraft();
        		mc.setIngameFocus();
        	}
        }
		super.handleKeyboardInput();
	}
	@Override
    public boolean doesGuiPauseGame() {
        return false;
    }
	//以上为实现Gui的打开
	@Override
	protected void keyTyped(char par1, int par2)
	{
		/*
		int k;

        for (k = 0; k < this.textList.size(); ++k)
        {
            if(((GuiTextField)this.textList.get(k)).textboxKeyTyped(par1, par2)){} //向文本框传入输入的内容
                //return;
        }
        for (k = 0; k < this.textList.size(); ++k)
        {
        	textListtext.set(k, ((GuiTextField)this.textList.get(k)).getText());
        }
        */
		tfPosition1.textboxKeyTyped(par1, par2); //向文本框传入输入的内容
            //return;
		tfPosition2.textboxKeyTyped(par1, par2); //向文本框传入输入的内容
            //return;
        tfp1text=tfPosition1.getText();
        tfp2text=tfPosition2.getText();
        super.keyTyped(par1, par2);
	}
	@Override
	protected void mouseClicked(int par1, int par2, int par3)
	{
		/*int k;

        for (k = 0; k < this.textList.size(); ++k)
        {
            ((GuiTextField)this.textList.get(k)).mouseClicked(par1, par2, par3);
        }*/
        tfPosition1.mouseClicked(par1, par2, par3);
        tfPosition2.mouseClicked(par1, par2, par3);
        super.mouseClicked(par1, par2, par3);
	}
	public GuiTextField addGuiTextField(FontRenderer p_i1032_1_, int p_i1032_2_, int p_i1032_3_, int p_i1032_4_, int p_i1032_5_)
	{
		GuiTextField newGuiTextField = new GuiTextField(p_i1032_1_,  p_i1032_2_,  p_i1032_3_,  p_i1032_4_,  p_i1032_5_);
		textList.add(newGuiTextField);
		textListtext.add("");
		return newGuiTextField;
	}
	@Override
	public void initGui()
	{
		//Keyboard.enableRepeatEvents(true);
		textList.clear();
		tfPosition1=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.15)-4, 100, 15);
		tfPosition1.setText(tfp1text);
		tfPosition2=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.25)-4, 100, 15);
		tfPosition2.setText(tfp2text);
		buttonList.add(btnpohApplytoP1 = new GuiButton(0, (int)(22+3), (int)(height*0.74), (int)((width-44)/3-6), 20, StatCollector.translateToLocal("position1.string.guipositionget.gui.ms")));
		buttonList.add(btnpohApplytoP2 = new GuiButton(0, (int)(22+3), (int)(height*0.74+21), (int)((width-44)/3-6), 20, StatCollector.translateToLocal("position2.string.guipositionget.gui.ms")));
		buttonList.add(btnpufApplytoP1 = new GuiButton(0, (int)(22+(width-44)/3+3), (int)(height*0.74), (int)((width-44)/3-6), 20, StatCollector.translateToLocal("position1.string.guipositionget.gui.ms")));
		buttonList.add(btnpufApplytoP2 = new GuiButton(0, (int)(22+(width-44)/3+3), (int)(height*0.74+21), (int)((width-44)/3-6), 20, StatCollector.translateToLocal("position2.string.guipositionget.gui.ms")));
		buttonList.add(btnpftApplytoP1 = new GuiButton(0, (int)(22+(width-44)/3*2+4), (int)(height*0.74), (int)((width-44)/3-6), 20, StatCollector.translateToLocal("position1.string.guipositionget.gui.ms")));
		buttonList.add(btnpftApplytoP2 = new GuiButton(0, (int)(22+(width-44)/3*2+4), (int)(height*0.74+21), (int)((width-44)/3-6), 20, StatCollector.translateToLocal("position2.string.guipositionget.gui.ms")));
		super.initGui();
	}
	public void onGuiClosed()
	{
		int[] pos=new int[6];
		if(tfp1text.split(",").length!=3){tfp1text="0,0,0";}
		if(tfp2text.split(",").length!=3){tfp2text="0,0,0";}
		pos[0]=Integer.valueOf(tfp1text.split(",")[0]);pos[1]=Integer.valueOf(tfp1text.split(",")[1]);pos[2]=Integer.valueOf(tfp1text.split(",")[2]);
		pos[3]=Integer.valueOf(tfp2text.split(",")[0]);pos[4]=Integer.valueOf(tfp2text.split(",")[1]);pos[5]=Integer.valueOf(tfp2text.split(",")[2]);
		Data.Position=pos;
		//Keyboard.enableRepeatEvents(false);
	}
	@Override
	public void drawScreen(int par1,int par2,float par3)
	{
		drawString(fontRendererObj, "", 0, 0, 0x80FFFFFF);
		/*int k;
        for (k = 0; k < this.textList.size(); ++k)
        {
        	//((GuiTextField)this.textList.get(k)).setText(((String)(textListtext.get(k))));
            ((GuiTextField)this.textList.get(k)).drawTextBox();
        }*/

		//tfPosition1.setText(tfp1text);
        tfPosition1.drawTextBox();
		//tfPosition2.setText(tfp2text);
        tfPosition2.drawTextBox();
        
		drawRect((int)((width-20-fontRendererObj.getStringWidth(StatCollector.translateToLocal("title.string.guipositionget.gui.ms")))*0.5-1), 2, (int)(width-(width-20-fontRendererObj.getStringWidth(StatCollector.translateToLocal("title.string.guipositionget.gui.ms")))*0.5+1), 21, 0x80111111);
		drawString(fontRendererObj, "", 0, 0, 0x80FFFFFF);
		ResourceLocation texture = new ResourceLocation(Data.MOD_ID, "textures/gui/guipositionget/compass.png");
		mc.renderEngine.bindTexture(texture);
		func_146110_a((int)((width-20-fontRendererObj.getStringWidth(StatCollector.translateToLocal("title.string.guipositionget.gui.ms")))*0.5), 2, 0, 0, 20, 20, 20, 20);
		drawString(fontRendererObj, StatCollector.translateToLocal("title.string.guipositionget.gui.ms"), (int)((width-20-fontRendererObj.getStringWidth(StatCollector.translateToLocal("title.string.guipositionget.gui.ms")))*0.5+20), 7, 0x80FFFFFF);
		drawString(fontRendererObj, "", 0, 0, 0x80FFFFFF);
		
		//System.out.println(tfPosition1.getText());
		if(tfPosition1.getText().split(",").length!=3){tfPosition1.setTextColor(0x80FF0000);}else{tfPosition1.setTextColor(0x80FFFFFF);}
		if(tfPosition2.getText().split(",").length!=3){tfPosition2.setTextColor(0x80FF0000);}else{tfPosition2.setTextColor(0x80FFFFFF);}
		
		drawRect((int)(20), 22, (int)(width-20), height-20, 0x80111111);
		drawString(fontRendererObj, StatCollector.translateToLocal("position1.string.guipositionget.gui.ms"), (int) (width*0.3), (int)(height*0.15), 0x80FFFFFF);
		drawString(fontRendererObj, StatCollector.translateToLocal("position2.string.guipositionget.gui.ms"), (int) (width*0.3), (int)(height*0.25), 0x80FFFFFF);
		drawRect((int)(22), (int)(height*0.3+5), (int)(width-22), (int)(height*0.3+4), 0x80AAAAAA);
		drawRect((int)(22+(width-44)/3), (int)(height*0.3+5), (int)(22+(width-44)/3+1), (int)(height-22), 0x80AAAAAA);
		drawRect((int)(22+(width-44)/3*2), (int)(height*0.3+5), (int)(22+(width-44)/3*2+1), (int)(height-22), 0x80AAAAAA);
		Player player=new Player(mc);
		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("poh.string.guipositionget.gui.ms"), (int)(22+(width-44)/3/2), (int)(height*0.3+7), 0x80FFFFFF);
		drawCenteredString(fontRendererObj, player.getHeadPosition()[0]+","+player.getHeadPosition()[1]+","+player.getHeadPosition()[2], (int)(22+(width-44)/3/2), (int)(height*0.3+20), 0x80FFFFFF);
		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("puf.string.guipositionget.gui.ms"), (int)(22+(width-44)/3*1.5), (int)(height*0.3+7), 0x80FFFFFF);
		drawCenteredString(fontRendererObj, player.getFootPosition()[0]+","+player.getFootPosition()[1]+","+player.getFootPosition()[2], (int)(22+(width-44)/3*1.5), (int)(height*0.3+20), 0x80FFFFFF);
		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("pft.string.guipositionget.gui.ms"), (int)(22+(width-44)/3*2.5), (int)(height*0.3+7), 0x80FFFFFF);
		drawCenteredString(fontRendererObj, player.getFacetoPosition()[0]+","+player.getFacetoPosition()[1]+","+player.getFacetoPosition()[2], (int)(22+(width-44)/3*2.5), (int)(height*0.3+20), 0x80FFFFFF);
		drawString(fontRendererObj, StatCollector.translateToLocal("applyto.string.guipositionget.gui.ms"), (int)(22+3), (int)(height*0.7), 0x80FFFFFF);
		drawString(fontRendererObj, StatCollector.translateToLocal("applyto.string.guipositionget.gui.ms"),(int)(22+(width-44)/3+3), (int)(height*0.7), 0x80FFFFFF);
		drawString(fontRendererObj, StatCollector.translateToLocal("applyto.string.guipositionget.gui.ms"), (int)(22+(width-44)/3*2+3), (int)(height*0.7), 0x80FFFFFF);
		
		super.drawScreen(par1, par2, par3);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) 
	{
		
		Player player=new Player(mc);
		if(button==btnpohApplytoP1){tfp1text=player.getHeadPosition()[0]+","+player.getHeadPosition()[1]+","+player.getHeadPosition()[2];tfPosition1.setText(tfp1text);}
		else if(button==btnpohApplytoP2){tfp2text=(player.getHeadPosition()[0]+","+player.getHeadPosition()[1]+","+player.getHeadPosition()[2]);tfPosition2.setText(tfp2text);}
		else if(button==btnpufApplytoP1){tfp1text=(player.getFootPosition()[0]+","+player.getFootPosition()[1]+","+player.getFootPosition()[2]);tfPosition1.setText(tfp1text);}
		else if(button==btnpufApplytoP2){tfp2text=(player.getFootPosition()[0]+","+player.getFootPosition()[1]+","+player.getFootPosition()[2]);tfPosition2.setText(tfp2text);}
		else if(button==btnpftApplytoP1){tfp1text=(player.getFacetoPosition()[0]+","+player.getFacetoPosition()[1]+","+player.getFacetoPosition()[2]);tfPosition1.setText(tfp1text);}
		else if(button==btnpftApplytoP2){tfp2text=(player.getFacetoPosition()[0]+","+player.getFacetoPosition()[1]+","+player.getFacetoPosition()[2]);tfPosition2.setText(tfp2text);}
	}
}
