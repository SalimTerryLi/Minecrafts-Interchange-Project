package ml.cnstl.bbt.operatewindows.screen;

import org.lwjgl.input.Keyboard;

import ml.cnstl.bbt.Block_operate;
import ml.cnstl.bbt.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.item.ItemStack;

public class Screen extends GuiScreen{
	
	protected GuiScreen parentScreen;
	protected GuiButton btnClose,btnCancelSel,btngetBBTtool;//���˵�/ȫ�ְ�ť
	protected GuiButton btnPosGet;
	protected GuiTextField tfBlockID,tfBlockType,tfreBlockID,tfreBlockType,tfPosition;
	protected Block_operate op=new Block_operate();
	
	public Screen(GuiScreen parent)
    {
         parentScreen = parent; //�������ĸ����������,�Ա��Ժ󷵻��Ǹ�����
        //�������ʼ��������޹ص�����,������ֻ���ʼ��һ�ε�����.
    }
	
	@Override
    protected void actionPerformed(GuiButton button) {
        if(button == btnClose)
        {
        	mc.displayGuiScreen(parentScreen);
    	}
        else if(button==btngetBBTtool)
        {
        	EntityClientPlayerMP player=Minecraft.getMinecraft().thePlayer;
        	player.inventory.addItemStackToInventory(new ItemStack(Info.PosSelector));
        }
        else if(button==btnPosGet)
        {
        	Info.Tool_Mode="setpositiontwo";
        	mc.displayGuiScreen(parentScreen);
        }
        else if(button==btnCancelSel)
        {
        	Info.Tool_Mode="game";
        	mc.displayGuiScreen(parentScreen);
        }
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
