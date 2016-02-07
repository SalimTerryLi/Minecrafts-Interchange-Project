package ml.cnstl.bbt.operatewindows.screen;

import org.lwjgl.input.Keyboard;

import ml.cnstl.bbt.GUI;
import ml.cnstl.bbt.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ScreenMain extends GuiScreen{

	private GuiScreen parentScreen;
	private GuiButton btnClose,btnBuild,btnCopy,btnPaste,btnImport,btnExport,btnReplace,btnCancelSel,btngetBBTtool;//���˵�/ȫ�ְ�ť

	public ScreenMain(GuiScreen parent) {
		this.parentScreen = parent;
		// TODO �Զ����ɵĹ��캯�����
	}

	public void initGui()
	{
		//ÿ�����汻��ʱ����
		Keyboard.enableRepeatEvents(true); //�򿪼�����������
		if(Info.Tool_Mode=="game")
		{
			buttonList.add(btnBuild = new GuiButton(0, (int)(width*0.2), (int)(height*0.02+20), (int)(width-width*0.4), 20, StatCollector.translateToLocal("btnBuild.button.gui.bbt")));
			buttonList.add(btnReplace = new GuiButton(0, (int)(width*0.2), (int)(height*0.03+40), (int)(width-width*0.4), 20, StatCollector.translateToLocal("btnReplace.button.gui.bbt")));
			buttonList.add(btnCopy = new GuiButton(0, (int)(width*0.2), (int)(height*0.04+60), (int)(width-width*0.4), 20, StatCollector.translateToLocal("btnCopy.button.gui.bbt")));
			buttonList.add(btnPaste = new GuiButton(0, (int)(width*0.2), (int)(height*0.05+80), (int)(width-width*0.4), 20, StatCollector.translateToLocal("btnPaste.button.gui.bbt")));
			buttonList.add(btnImport = new GuiButton(0, (int)(width*0.2), (int)(height*0.06+100), (int)(width-width*0.4), 20, StatCollector.translateToLocal("btnImport.button.gui.bbt")));
			buttonList.add(btnExport = new GuiButton(0, (int)(width*0.2), (int)(height*0.07+120), (int)(width-width*0.4), 20, StatCollector.translateToLocal("btnExport.button.gui.bbt")));
			buttonList.add(btngetBBTtool = new GuiButton(0, (int)(width*0.01), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btngetbbttool.button.gui.bbt")));
			buttonList.add(btnClose = new GuiButton(0, (int)(width-width*0.01-80), (int)(height-height*0.1), 80, 20, StatCollector.translateToLocal("btnClose.button.gui.bbt")));
		}
	}

	public void drawScreen(int par1, int par2, float par3)
	{
		//drawDefaultBackground();
		//����������ı�������ȷǿؼ�����,������ƵĶ����ᱻ�ؼ�(������)��ס.
		super.drawScreen(par1,par2,par3);
			drawCenteredString(fontRendererObj, StatCollector.translateToLocal("title.gui.bbt"), width/2, (int)(2), 0xFFFF00);
	}
	
	@Override
    protected void actionPerformed(GuiButton button) {
        if(button==btnBuild)
        {
        	mc.displayGuiScreen(new ScreenBuild(mc.currentScreen));
        }
        else if(button==btnCopy)
        {
            mc.displayGuiScreen(new ScreenCopy(mc.currentScreen));
        }
        else if(button==btnPaste)
        {
            mc.displayGuiScreen(new ScreenPaste(mc.currentScreen));
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
            mc.displayGuiScreen(new ScreenReplace(mc.currentScreen));
        }
        else if(button == btnClose)
        {
        	mc.displayGuiScreen(parentScreen);
    	}
        else if(button==btngetBBTtool)
        {
        	EntityClientPlayerMP player=Minecraft.getMinecraft().thePlayer;
        	player.inventory.addItemStackToInventory(new ItemStack(Info.PosSelector));
        }
        //���˵�/ȫ�ְ�ť�¼���Ӧ
    }
	
	@Override
	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false); //�رռ�����������
	}
	
	@Override
    public boolean doesGuiPauseGame() {
        return false;
    }
	
}
