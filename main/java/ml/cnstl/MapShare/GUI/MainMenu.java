package ml.cnstl.MapShare.GUI;

import java.util.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import ml.cnstl.MapShare.Data;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import ml.cnstl.MapShare.GUI.guiMapEdit.*;

public class MainMenu extends GuiScreen
{
	private ResourceLocation texture;
	private String ToolsTip="";
	
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
	}
	protected void keyTyped(char par1, int par2)
	{
		if(Data.kbShowMainMenu.getKeyCode()==par2)
		{
			Minecraft mc = Minecraft.getMinecraft();mc.setIngameFocus();
		}
		//Back to Game Screen
		
		super.keyTyped(par1, par2);
	}
	@Override
	protected void mouseClicked(int par1, int par2, int par3)
	{
		if(par3==0)//左键单击
		{
			if((par1<((width-3*80)/4+80))&(par1>((width-3*80)/4))&(par2<height*0.2+80+20)&(par2>height*0.2)){mc.displayGuiScreen(Data.lastShowMapEditGui);}//区域判定*这里作为测试代码
			else if((par1<((width-3*80)/2+80+80))&(par1>((width-3*80)/2+80))&(par2<height*0.2+80+20)&(par2>height*0.2)){System.out.println("2");}
			else if((par1<((width-3*80)/4*3+2*80+80))&(par1>((width-3*80)/4*3+2*80))&(par2<height*0.2+80+20)&(par2>height*0.2)){System.out.println("3");}
			else{Minecraft mc = Minecraft.getMinecraft();mc.setIngameFocus();}
		}
		super.mouseClicked(par1, par2, par3);
	}
	public void drawScreen(int par1, int par2, float par3)
	{
		drawDefaultBackground();
		
		int mousex = Mouse.getX() * width / mc.displayWidth;
        int mousey = height - Mouse.getY() * height / mc.displayHeight - 1;
        
        int color1=0xFFFFFF,color2=0xFFFFFF,color3=0xFFFFFF;
    	if((mousex<((width-3*80)/4+80))&(mousex>((width-3*80)/4))&(mousey<height*0.2+80+20)&(mousey>height*0.2)){color1=0x44CEF6;}//区域判定
    	else if((mousex<((width-3*80)/2+80+80))&(mousex>((width-3*80)/2+80))&(mousey<height*0.2+80+20)&(mousey>height*0.2)){color2=0x44CEF6;}
    	else if((mousex<((width-3*80)/4*3+2*80+80))&(mousex>((width-3*80)/4*3+2*80))&(mousey<height*0.2+80+20)&(mousey>height*0.2)){color3=0x44CEF6;}
    	else{}
		
    	drawCenteredString(fontRendererObj, "", 0, 0, color1);//用来制造颜色干扰
		texture = new ResourceLocation(Data.MOD_ID, "textures/gui/mainmenu/world_operate.png"); 
		mc.renderEngine.bindTexture(texture);
		func_146110_a((int) ((width-3*80)/4), (int) (height*0.2), 0, 0, 80, 80, 80, 80);//drawModalRectWithCustomSizedTexture()
		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("world-operate.mainmenu.gui.ms"), (int) ((width-3*80)/4+40), (int)(height*0.2+80+10), color1);
		drawCenteredString(fontRendererObj, "", 0, 0, 0xFFFFFF);//用来排除颜色干扰
		drawCenteredString(fontRendererObj, "", 0, 0, color2);//用来制造颜色干扰
		texture = new ResourceLocation(Data.MOD_ID, "textures/gui/mainmenu/map_share.png"); 
		mc.renderEngine.bindTexture(texture);
		func_146110_a((int) ((width-3*80)/2+80), (int) (height*0.2), 0, 0, 80, 80, 80, 80);//drawModalRectWithCustomSizedTexture()
		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("mapshare.mainmenu.gui.ms"), (int) ((width-3*80)/2+80+40), (int)(height*0.2+80+10), color2);
		drawCenteredString(fontRendererObj, "", 0, 0, 0xFFFFFF);//用来排除颜色干扰
		drawCenteredString(fontRendererObj, "", 0, 0, color3);//用来制造颜色干扰
		texture = new ResourceLocation(Data.MOD_ID, "textures/gui/mainmenu/misc.png"); 
		mc.renderEngine.bindTexture(texture);
		func_146110_a((int) ((width-3*80)/4*3+2*80), (int) (height*0.2), 0, 0, 80, 80, 80, 80);//drawModalRectWithCustomSizedTexture()
		drawCenteredString(fontRendererObj, StatCollector.translateToLocal("misc.mainmenu.gui.ms"), (int) ((width-3*80)/4*3+2*80+40), (int)(height*0.2+80+10), color3);
		drawCenteredString(fontRendererObj, "", 0, 0, 0xFFFFFF);//用来排除颜色干扰
		
    	if((mousex<((width-3*80)/4+80))&(mousex>((width-3*80)/4))&(mousey<height*0.2+80+20)&(mousey>height*0.2)){drawString(fontRendererObj,StatCollector.translateToLocal("me.toolstip.mainmenu.gui.ms"),2, (int)(height*0.966), 0xFFFFFF);}//区域判定
    	else if((mousex<((width-3*80)/2+80+80))&(mousex>((width-3*80)/2+80))&(mousey<height*0.2+80+20)&(mousey>height*0.2)){drawString(fontRendererObj,StatCollector.translateToLocal("s.toolstip.mainmenu.gui.ms"),2, (int)(height*0.966), 0xFFFFFF);}
    	else if((mousex<((width-3*80)/4*3+2*80+80))&(mousex>((width-3*80)/4*3+2*80))&(mousey<height*0.2+80+20)&(mousey>height*0.2)){drawString(fontRendererObj,StatCollector.translateToLocal("misc.toolstip.mainmenu.gui.ms"),2, (int)(height*0.966), 0xFFFFFF);}
    	else{drawString(fontRendererObj,StatCollector.translateToLocal("none.toolstip.mainmenu.gui.ms"),2, (int)(height*0.966), 0xFFFFFF);}
    	
    	drawRect(2, (int)(height*0.96), width-2, (int) (height*0.96+1), 0x80FFFFFF);
		drawString(fontRendererObj,"MapShare "+Data.MOD_VERSION,(int)(width-fontRendererObj.getStringWidth("MapShare "+Data.MOD_VERSION)-2), (int)(height*0.966), 0xFFFFFF);
		
	}
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}
	@Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
