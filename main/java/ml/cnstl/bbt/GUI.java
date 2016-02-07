package ml.cnstl.bbt;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.StatCollector;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.*;
import net.minecraft.item.ItemStack;

public class GUI extends GuiScreen{
	
	private static String Build_BlockID="0",Build_BlockType="0",Replace_BlockID="0",Replace_BlockType="0",Replace_reBlockID="0",Replace_reBlockType="0";
    private GuiScreen parentScreen;
    private String GUItype;
    Block_operate op=new Block_operate();
       
    public GUI(GuiScreen parent,String GUItype)
    {
         parentScreen = parent; //记下是哪个界面打开了它,以便以后返回那个界面
         this.GUItype=GUItype;
        //在这里初始化与界面无关的数据,或者是只需初始化一次的数据.
    }
    private GuiLabel lblMainTitle;
    private GuiButton btnClose,btnBuild,btnCopy,btnPaste,btnImport,btnExport,btnReplace,btnCancelSel,btngetBBTtool;//主菜单/全局按钮
    private GuiButton btnPosGet,btnBuilding,btnReplacing,btnEnabledType,btnCoping,btnisKeptID,btnisKeptType,btngetFootStandOnpos,btnPasting,btnPosGetonlyone,btnIgnoreempyt,btnPreview,btnChangePreview,btnClosePreview,btnExporting,btnImporting;
    private GuiTextField tfBlockID,tfBlockType,tfreBlockID,tfreBlockType,tfPosition;
    public void initGui()
    {
        //每当界面被打开时调用
    	Keyboard.enableRepeatEvents(true); //打开键盘连续输入
    	if(Info.Tool_Mode=="game")
    	{
    		if(GUItype=="GUI_main")
    		{
    			//labelList.add(lblMainTitle=new GuiLabel());//For MC Ver 1.8+
           		buttonList.add(btnBuild = new GuiButton(0, (int)(width*0.2), (int)(height*0.02+20), (int)(width-width*0.4), 20, StatCollector.translateToLocal("btnBuild.button.gui.bbt")));
        		buttonList.add(btnReplace = new GuiButton(0, (int)(width*0.2), (int)(height*0.03+40), (int)(width-width*0.4), 20, StatCollector.translateToLocal("btnReplace.button.gui.bbt")));
        		buttonList.add(btnCopy = new GuiButton(0, (int)(width*0.2), (int)(height*0.04+60), (int)(width-width*0.4), 20, StatCollector.translateToLocal("btnCopy.button.gui.bbt")));
        		buttonList.add(btnPaste = new GuiButton(0, (int)(width*0.2), (int)(height*0.05+80), (int)(width-width*0.4), 20, StatCollector.translateToLocal("btnPaste.button.gui.bbt")));
        		buttonList.add(btnImport = new GuiButton(0, (int)(width*0.2), (int)(height*0.06+100), (int)(width-width*0.4), 20, StatCollector.translateToLocal("btnImport.button.gui.bbt")));
        		buttonList.add(btnExport = new GuiButton(0, (int)(width*0.2), (int)(height*0.07+120), (int)(width-width*0.4), 20, StatCollector.translateToLocal("btnExport.button.gui.bbt")));
        		buttonList.add(btngetBBTtool = new GuiButton(0, (int)(width*0.01), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btngetbbttool.button.gui.bbt")));
        		buttonList.add(btnClose = new GuiButton(0, (int)(width-width*0.01-80), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btnClose.button.gui.bbt")));
    		}
    		else if(GUItype=="GUI_Build")
    		{
    			tfBlockID=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.1), (int) (width*0.5-20), 15);
    			tfBlockID.setText(Build_BlockID);
    			tfBlockType=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.2), (int) (width*0.5-20), 15);
    			tfBlockType.setText(Build_BlockType);
    			tfPosition=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.3), (int) (width*0.5-20), 15);
    			tfPosition.setText(""+Info.posset[0]+","+Info.posset[1]+","+Info.posset[2]+","+Info.posset[3]+","+Info.posset[4]+","+Info.posset[5]);
    			buttonList.add(btnPosGet = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.3), 45, 15, StatCollector.translateToLocal("btnposselect.button.gui.bbt")));
    			buttonList.add(btnBuilding = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.8), 100, 20, StatCollector.translateToLocal("btnbuilding.button.gui.bbt")));
    			buttonList.add(btnClose = new GuiButton(0, (int)(width-width*0.01-80), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btnClose.button.gui.bbt")));
    		}
    		else if(GUItype=="GUI_Replace")
    		{
    			buttonList.add(btnisKeptID = new GuiButton(0, (int)(20), (int)(height*0.1), (int) (width*0.5-75), 15, StatCollector.translateToLocal("reblockid.replace.gui.bbt")));
    			buttonList.add(btnisKeptType = new GuiButton(0, (int)(20), (int)(height*0.2), (int) (width*0.5-75), 15, StatCollector.translateToLocal("reblocktype.replace.gui.bbt")));
    			btnisKeptType.enabled=false;
    			tfreBlockID=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.1), (int) (width*0.5-20), 15);
    			tfreBlockID.setText(Replace_reBlockID);
    			tfreBlockType=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.2), (int) (width*0.5-20), 15);
    			tfreBlockType.setText(Replace_reBlockType);
    			buttonList.add(btnEnabledType = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.2), 45, 15, StatCollector.translateToLocal("btnenabledtype.button.gui.bbt")));
    			tfBlockID=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.3), (int) (width*0.5-20), 15);
    			tfBlockID.setText(Replace_BlockID);
    			tfBlockType=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.4), (int) (width*0.5-20), 15);
    			tfBlockType.setText(Replace_BlockType);
    			tfPosition=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.5), (int) (width*0.5-20), 15);
    			tfPosition.setText(""+Info.posset[0]+","+Info.posset[1]+","+Info.posset[2]+","+Info.posset[3]+","+Info.posset[4]+","+Info.posset[5]);
    			buttonList.add(btnPosGet = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.5), 45, 15, StatCollector.translateToLocal("btnposselect.button.gui.bbt")));
    			buttonList.add(btnReplacing = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.8), 100, 20, StatCollector.translateToLocal("btnreplacing.button.gui.bbt")));
    			buttonList.add(btnClose = new GuiButton(0, (int)(width-width*0.01-80), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btnClose.button.gui.bbt")));
    		}
    		else if(GUItype=="GUI_Copy")
    		{
    			tfPosition=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.1), (int) (width*0.5-20), 15);
    			tfPosition.setText(""+Info.posset[0]+","+Info.posset[1]+","+Info.posset[2]+","+Info.posset[3]+","+Info.posset[4]+","+Info.posset[5]);
    			buttonList.add(btnPosGet = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.1), 45, 15, StatCollector.translateToLocal("btnposselect.button.gui.bbt")));
    			buttonList.add(btnCoping = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.2), 100, 20, StatCollector.translateToLocal("btncoping.button.gui.bbt")));
    			buttonList.add(btnClose = new GuiButton(0, (int)(width-width*0.01-80), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btnClose.button.gui.bbt")));
    		}
    		else if(GUItype=="GUI_Paste")
    		{
    			tfPosition=new GuiTextField(fontRendererObj, (int)(width*0.5), (int)(height*0.1), (int) (width*0.5-20), 15);
    			tfPosition.setText(""+Info.posset[0]+","+Info.posset[1]+","+Info.posset[2]);
    			buttonList.add(btnPosGetonlyone = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.1), 45, 15, StatCollector.translateToLocal("btnposselect.button.gui.bbt")));
    			buttonList.add(btnIgnoreempyt = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.2), 45, 15, StatCollector.translateToLocal("btnignoreemptydisabled.paste.gui.bbt")));
    			buttonList.add(btnPreview = new GuiButton(0, (int)(width*0.5), (int)(height*0.2), (int) (width*0.25-8), 15, StatCollector.translateToLocal("btnpreview.paste.gui.bbt")));
    			buttonList.add(btnChangePreview = new GuiButton(0, (int)(width*0.75), (int)(height*0.2), (int) (width*0.25-8), 15, StatCollector.translateToLocal("btnchangepreview.paste.gui.bbt")));
    			buttonList.add(btnClosePreview = new GuiButton(0, (int)(width*0.75), (int)(height*0.3), (int) (width*0.25-8), 15, StatCollector.translateToLocal("btnclosepreview.paste.gui.bbt")));
    			buttonList.add(btnPasting = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.7), 100, 20, StatCollector.translateToLocal("btnpasting.button.gui.bbt")));
    			buttonList.add(btnClose = new GuiButton(0, (int)(width-width*0.01-80), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btnClose.button.gui.bbt")));
    		}
    		else if(GUItype=="GUI_Import")
    		{
    			buttonList.add(btnExporting = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.1), 100, 20, StatCollector.translateToLocal("btnexporting.button.gui.bbt")));
    			buttonList.add(btnClose = new GuiButton(0, (int)(width-width*0.01-80), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btnClose.button.gui.bbt")));
    		}
    		else if(GUItype=="GUI_Export")
    		{
    			buttonList.add(btnImporting = new GuiButton(0, (int)(width-width*0.5-50), (int)(height*0.1), 100, 20, StatCollector.translateToLocal("btnimporting.button.gui.bbt")));
    			buttonList.add(btnClose = new GuiButton(0, (int)(width-width*0.01-80), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btnClose.button.gui.bbt")));
    		}
    	}
    	else if(Info.Tool_Mode=="setpositiononlyone")
    	{
    		buttonList.add(btngetBBTtool = new GuiButton(0, (int)(width*0.01), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btngetbbttool.button.gui.bbt")));
    		buttonList.add(btnCancelSel = new GuiButton(0, (int)(width/2-40), (int)(height*0.7), 80, 20, StatCollector.translateToLocal("btncancelsel.button.gui.bbt")));
    		buttonList.add(btngetFootStandOnpos = new GuiButton(0, (int)(width/2-100), (int)(height*0.6), 200, 20, StatCollector.translateToLocal("btngetfootpos.button.gui.bbt")));
    		buttonList.add(btnClose = new GuiButton(0, (int)(width-width*0.01-80), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btnClose.button.gui.bbt")));
    	}
    	else if(Info.Tool_Mode=="setpositiontwo")
    	{
    		buttonList.add(btngetBBTtool = new GuiButton(0, (int)(width*0.01), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btngetbbttool.button.gui.bbt")));
    		buttonList.add(btnCancelSel = new GuiButton(0, (int)(width/2-40), (int)(height*0.7), 80, 20, StatCollector.translateToLocal("btncancelsel.button.gui.bbt")));
    		buttonList.add(btngetFootStandOnpos = new GuiButton(0, (int)(width/2-100), (int)(height*0.6), 200, 20, StatCollector.translateToLocal("btngetfootpos.button.gui.bbt")));
    		buttonList.add(btnClose = new GuiButton(0, (int)(width-width*0.01-80), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btnClose.button.gui.bbt")));
    	}
    	else if(Info.Tool_Mode=="setpositiontwosecond")
    	{
    		buttonList.add(btngetBBTtool = new GuiButton(0, (int)(width*0.01), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btngetbbttool.button.gui.bbt")));
    		buttonList.add(btnCancelSel = new GuiButton(0, (int)(width/2-40), (int)(height*0.7), 80, 20, StatCollector.translateToLocal("btncancelsel.button.gui.bbt")));
    		buttonList.add(btngetFootStandOnpos = new GuiButton(0, (int)(width/2-100), (int)(height*0.6), 200, 20, StatCollector.translateToLocal("btngetfootpos.button.gui.bbt")));
    		buttonList.add(btnClose = new GuiButton(0, (int)(width-width*0.01-80), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btnClose.button.gui.bbt")));
    	}
    	//这里部署控件
    	/*
    	tfInput = new GuiTextField(fontRendererObj, (int)(width*0.02+80), (int)(height*0.02+20), 300, 20);
    	tfInput.setMaxStringLength(64); //设置最大长度,可省略
    	tfInput.setFocused(false); //设置是否为焦点
    	tfInput.setCanLoseFocus(true); //设置为可以被取消焦点
    	*/
    }
    
    @Override
    protected void keyTyped(char par1, int par2) {
    	if(GUItype=="GUI_Build")
    	{
    		if(tfBlockID.textboxKeyTyped(par1, par2)) //向文本框传入输入的内容
                return;
            if(tfBlockType.textboxKeyTyped(par1, par2)) //向文本框传入输入的内容
                return;
            if(tfPosition.textboxKeyTyped(par1, par2)) //向文本框传入输入的内容
                return;
       	}
    	else if(GUItype=="GUI_Replace")
    	{
    		if(tfBlockID.textboxKeyTyped(par1, par2)) //向文本框传入输入的内容
                return;
            if(tfBlockType.textboxKeyTyped(par1, par2)) //向文本框传入输入的内容
                return;
            if(tfPosition.textboxKeyTyped(par1, par2)) //向文本框传入输入的内容
                return;
            if(tfreBlockID.textboxKeyTyped(par1, par2)) //向文本框传入输入的内容
                return;
            if(tfreBlockType.textboxKeyTyped(par1, par2)) //向文本框传入输入的内容
                return;
    	}
    	else if(GUItype=="GUI_Copy")
    	{
            if(tfPosition.textboxKeyTyped(par1, par2)) //向文本框传入输入的内容
                return;
       	}
    	else if(GUItype=="GUI_Paste")
    	{
            if(tfPosition.textboxKeyTyped(par1, par2)) //向文本框传入输入的内容
                return;
       	}
    	super.keyTyped(par1, par2);
    }
     
    @Override
    protected void mouseClicked(int par1, int par2, int par3) {
    	if(GUItype=="GUI_main")
        {
         	
        }
        else if(GUItype=="GUI_Build")
		{
          	tfBlockID.mouseClicked(par1, par2, par3); //调用文本框的鼠标点击检查
          	tfBlockType.mouseClicked(par1, par2, par3);
          	tfPosition.mouseClicked(par1, par2, par3);
        }
		else if(GUItype=="GUI_Replace")
		{
			tfBlockID.mouseClicked(par1, par2, par3); //调用文本框的鼠标点击检查
          	tfBlockType.mouseClicked(par1, par2, par3);
          	tfreBlockID.mouseClicked(par1, par2, par3); //调用文本框的鼠标点击检查
          	tfreBlockType.mouseClicked(par1, par2, par3);
          	tfPosition.mouseClicked(par1, par2, par3);
		}
		else if(GUItype=="GUI_Copy")
		{
			tfPosition.mouseClicked(par1, par2, par3);
		}
		else if(GUItype=="GUI_Paste")
		{
			tfPosition.mouseClicked(par1, par2, par3);
		}
		else if(GUItype=="GUI_Import")
		{
			
		}
		else if(GUItype=="GUI_Export")
		{
			
		}
        super.mouseClicked(par1, par2, par3);
    }
    
    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false); //关闭键盘连续输入
        if(GUItype=="GUI_main")
        {
         	
        }
        else if(GUItype=="GUI_Build")
		{
        	Build_BlockID=tfBlockID.getText();
        	Build_BlockType=tfBlockType.getText();
        }
		else if(GUItype=="GUI_Replace")
		{
			Replace_BlockID=tfBlockID.getText();
        	Replace_BlockType=tfBlockType.getText();
        	Replace_reBlockID=tfreBlockID.getText();
        	Replace_reBlockType=tfreBlockType.getText();
		}
		else if(GUItype=="GUI_Copy")
		{
			
		}
		else if(GUItype=="GUI_Paste")
		{
			
		}
		else if(GUItype=="GUI_Import")
		{
			
		}
		else if(GUItype=="GUI_Export")
		{
			
		}
    }
    public void drawScreen(int par1, int par2, float par3)
    {
        drawDefaultBackground();
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会被控件(即按键)盖住.
        super.drawScreen(par1,par2,par3);
        if(GUItype=="GUI_main")
        {
         	
        }
        else if(GUItype=="GUI_Build")
		{
        	tfBlockID.drawTextBox();
        	tfBlockType.drawTextBox();
        	tfPosition.drawTextBox();
        }
		else if(GUItype=="GUI_Replace")
		{
			tfBlockID.drawTextBox();
        	tfBlockType.drawTextBox();
        	tfPosition.drawTextBox();
        	tfreBlockID.drawTextBox();
        	tfreBlockType.drawTextBox();
		}
		else if(GUItype=="GUI_Copy")
		{
			tfPosition.drawTextBox();
		}
		else if(GUItype=="GUI_Paste")
		{
			tfPosition.drawTextBox();
		}
		else if(GUItype=="GUI_Import")
		{
			
		}
		else if(GUItype=="GUI_Export")
		{
			
		}
        //所有的文本框的绘制
        //在这里绘制文本或纹理等非控件内容,这里绘制的东西会盖在控件(即按键)之上.
        if(GUItype=="GUI_main")
        {
         	drawCenteredString(fontRendererObj, StatCollector.translateToLocal("title.gui.bbt"), width/2, (int)(2), 0xFFFF00);
         	if(Info.Tool_Mode=="setpositiononlyone")
        	{
         		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("maintip.setpositiononlyone.gui.bbt"), width/2, (int)(height*0.5), 0xFFFF00);
        	}
        	else if(Info.Tool_Mode=="setpositiontwo")
        	{
        		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("maintip.setpositiontwo.gui.bbt"), width/2, (int)(height*0.5), 0xFFFF00);
        	}
        	else if(Info.Tool_Mode=="setpositiontwosecond")
        	{
        		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("maintip.setpositiontwosecond.gui.bbt"), width/2, (int)(height*0.5), 0xFFFF00);
        	}
        }
        else if(GUItype=="GUI_Build")
		{
        	drawCenteredString(fontRendererObj, StatCollector.translateToLocal("subtitle.gui.bbt")+"-"+StatCollector.translateToLocal("name.build.mode.bbt"), width/2, (int)(2), 0xFFFF00);
        	drawString(fontRendererObj, StatCollector.translateToLocal("blockid.build.gui.bbt"),(int)(20), (int)(height*0.1), 0xFFFFFF);
        	drawString(fontRendererObj, StatCollector.translateToLocal("blocktype.build.gui.bbt"),(int)(20), (int)(height*0.2), 0xFFFFFF);
        	drawString(fontRendererObj, StatCollector.translateToLocal("position.build.gui.bbt"),(int)(20), (int)(height*0.3), 0xFFFFFF);
        }
		else if(GUItype=="GUI_Replace")
		{
			drawCenteredString(fontRendererObj, StatCollector.translateToLocal("subtitle.gui.bbt")+"-"+StatCollector.translateToLocal("name.replace.mode.bbt"), width/2, (int)(2), 0xFFFF00);
			//drawString(fontRendererObj, StatCollector.translateToLocal("reblockid.replace.gui.bbt"),(int)(20), (int)(height*0.1), 0xFFFFFF);
        	//drawString(fontRendererObj, StatCollector.translateToLocal("reblocktype.replace.gui.bbt"),(int)(20), (int)(height*0.2), 0xFFFFFF);
        	drawString(fontRendererObj, StatCollector.translateToLocal("blockid.replace.gui.bbt"),(int)(20), (int)(height*0.3), 0xFFFFFF);
        	drawString(fontRendererObj, StatCollector.translateToLocal("blocktype.replace.gui.bbt"),(int)(20), (int)(height*0.4), 0xFFFFFF);
        	drawString(fontRendererObj, StatCollector.translateToLocal("position.replace.gui.bbt"),(int)(20), (int)(height*0.5), 0xFFFFFF);
		}
		else if(GUItype=="GUI_Copy")
		{
        	drawString(fontRendererObj, StatCollector.translateToLocal("position.replace.gui.bbt"),(int)(20), (int)(height*0.1), 0xFFFFFF);
			drawCenteredString(fontRendererObj, StatCollector.translateToLocal("subtitle.gui.bbt")+"-"+StatCollector.translateToLocal("name.copy.mode.bbt"), width/2, (int)(2), 0xFFFF00);
		}
		else if(GUItype=="GUI_Paste")
		{
			drawString(fontRendererObj, StatCollector.translateToLocal("position.replace.gui.bbt"),(int)(20), (int)(height*0.1), 0xFFFFFF);
			drawString(fontRendererObj, StatCollector.translateToLocal("ignoreempty.paste.gui.bbt"),(int)(20), (int)(height*0.2), 0xFFFFFF);
			drawCenteredString(fontRendererObj, StatCollector.translateToLocal("subtitle.gui.bbt")+"-"+StatCollector.translateToLocal("name.paste.mode.bbt"), width/2, (int)(2), 0xFFFF00);
		}
		else if(GUItype=="GUI_Import")
		{
			drawCenteredString(fontRendererObj, StatCollector.translateToLocal("subtitle.gui.bbt")+"-"+StatCollector.translateToLocal("name.import.mode.bbt"), width/2, (int)(2), 0xFFFF00);
		}
		else if(GUItype=="GUI_Export")
		{
			drawCenteredString(fontRendererObj, StatCollector.translateToLocal("subtitle.gui.bbt")+"-"+StatCollector.translateToLocal("name.export.mode.bbt"), width/2, (int)(2), 0xFFFF00);
		}
    }
    
    
    @Override
    protected void actionPerformed(GuiButton button) {
        if(button == btnClose)
        {
        	mc.displayGuiScreen(parentScreen);
    	}
        else if(button==btnBuild)
        {
        	mc.displayGuiScreen(new GUI(mc.currentScreen,"GUI_Build"));
        }
        else if(button==btnCopy)
        {
            mc.displayGuiScreen(new GUI(mc.currentScreen,"GUI_Copy"));
        }
        else if(button==btnPaste)
        {
            mc.displayGuiScreen(new GUI(mc.currentScreen,"GUI_Paste"));
        }
        else if(button==btnImport)
        {
            mc.displayGuiScreen(new GUI(mc.currentScreen,"GUI_Import"));
        }
        else if(button==btnExport)
        {
            mc.displayGuiScreen(new GUI(mc.currentScreen,"GUI_Export"));
        }
        else if(button==btnReplace)
        {
            mc.displayGuiScreen(new GUI(mc.currentScreen,"GUI_Replace"));
        }
        else if(button==btnCancelSel)
        {
        	Info.Tool_Mode="game";
        	mc.displayGuiScreen(parentScreen);
        }
        else if(button==btngetBBTtool)
        {
        	EntityClientPlayerMP player=Minecraft.getMinecraft().thePlayer;
        	player.inventory.addItemStackToInventory(new ItemStack(Info.PosSelector));
        }
        else if(button==btngetFootStandOnpos)
        {
        	EntityClientPlayerMP player=Minecraft.getMinecraft().thePlayer;
        	int posx,posy,posz;
        	posx=(int)player.posX;
        	posy=(int) player.posY-2;
        	posz=(int) player.posZ;
        	if(Info.Tool_Mode=="setpositiononlyone")
        	{
        		Info.posset[0]=posx;
        		Info.posset[1]=posy;
        		Info.posset[2]=posz;
        		Info.Tool_Mode="game";
        		mc.displayGuiScreen(parentScreen);
        	}
        	else if(Info.Tool_Mode=="setpositiontwo")
        	{
        		Info.posset[0]=posx;
        		Info.posset[1]=posy;
        		Info.posset[2]=posz;
        		Info.Tool_Mode="setpositiontwosecond";
        		mc.displayGuiScreen(parentScreen);
        	}
        	else if(Info.Tool_Mode=="setpositiontwosecond")
        	{
        		Info.posset[3]=posx;
        		Info.posset[4]=posy;
        		Info.posset[5]=posz;
        		Info.Tool_Mode="game";
        		mc.displayGuiScreen(parentScreen);
        	}
        }
        //主菜单/全局按钮事件响应
        else if(button==btnPosGet)
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
        //GUI_Build事件响应
        else if(button==btnEnabledType)
        {
        	tfreBlockType.setVisible(!tfreBlockType.getVisible());
        	if(!tfreBlockType.getVisible()){btnEnabledType.displayString=StatCollector.translateToLocal("btndisabledtype.button.gui.bbt");}else{btnEnabledType.displayString=StatCollector.translateToLocal("btnenabledtype.button.gui.bbt");};
        }
        else if(button==btnisKeptID)
        {
        	if(btnisKeptID.displayString==StatCollector.translateToLocal("reblockid.replace.gui.bbt"))
        	{
        		btnisKeptID.displayString=StatCollector.translateToLocal("keepblockid.replace.gui.bbt");
        		btnisKeptType.displayString=StatCollector.translateToLocal("keepblocktype.replace.gui.bbt");
        	}
        	else
        	{
        		btnisKeptID.displayString=StatCollector.translateToLocal("reblockid.replace.gui.bbt");
        		btnisKeptType.displayString=StatCollector.translateToLocal("reblocktype.replace.gui.bbt");
        	}
        }
        else if(button==btnReplacing)
        {
    		try
    		{
    			int blockb=Integer.parseInt(tfreBlockID.getText());int datab=0;
            	if(tfreBlockType.getText()!=""){datab=Integer.parseInt(tfreBlockType.getText());}
            	int blocka=Integer.parseInt(tfBlockID.getText());int dataa=Integer.parseInt(tfBlockType.getText());
            	boolean ifnodata=!tfreBlockType.getVisible(),ifsave=btnisKeptID.displayString==StatCollector.translateToLocal("keepblockid.replace.gui.bbt");
            	String[] posstr=tfPosition.getText().split(",");
        		int[] pos=new int[6];
        		for(int i=0;i<=5;i++)
        		{
        			pos[i]=Integer.parseInt(posstr[i]);
        		}
        		op.Replace(pos[0], pos[1], pos[2], pos[3], pos[4], pos[5], blockb, datab, blocka, dataa, ifnodata, ifsave);
    		}catch(Exception exc){}
        }
        //GUI_Replace事件响应
        else if(button==btnCoping)
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
        //GUI_Copy事件响应
        else if(button==btnPasting)
        {
        	op.clearpreview();
        	String bm;
        	if(btnIgnoreempyt.displayString!=StatCollector.translateToLocal("btnignoreemptydisabled.paste.gui.bbt")){bm="clearempty";}else{bm="none";}
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
        else if(button==btnClosePreview)
        {
        	op.clearpreview();
        }
        //GUI_Paste事件响应
        else if(button==btnExporting)
        {
        	try {
				File_operate.exportfile("");
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
        }
        else if(button==btnImporting)
        {
        	try {
				File_operate.importfile("", false);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
        }
    }
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}