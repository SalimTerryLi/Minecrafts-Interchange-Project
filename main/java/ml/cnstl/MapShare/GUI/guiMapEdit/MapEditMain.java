package ml.cnstl.MapShare.GUI.guiMapEdit;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import ml.cnstl.MapShare.Data;
import ml.cnstl.MapShare.GUI.MainMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class MapEditMain extends GuiScreen
{
	private ResourceLocation texture;
	private String ToolsTip="";
	
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
	}
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		int mousex = Mouse.getX() * width / mc.displayWidth;
        int mousey = height - Mouse.getY() * height / mc.displayHeight - 1;
        
        if(isDrawDefaultBackground()){drawDefaultBackground();}
		
		drawRect((int)(width*0.1), 2, (int)(width*0.1+1), (int)(height*0.96-2), 0x80FFFFFF);//纵分界线
		{
			if((mousex>(width*0.1-30)/2)&(mousex<width*0.1)&(mousey>(height*0.96-25-180)/2)&(mousey<(height*0.96-25-180)/2+30)|(Data.lastShowMapEditGui.equals(Data.GuiBuild)))
			{
				drawRect((int)((width*0.1-30)/2), (int)((height*0.96-25-180)/2), (int)(width*0.1), (int)((height*0.96-25-180)/2+30), 0x80FFFFFF);
				if(!Data.lastShowMapEditGui.equals(Data.GuiBuild)){ToolsTip=StatCollector.translateToLocal("build.toolstip.mapeditmain.gui.ms");}
			}
			texture = new ResourceLocation(Data.MOD_ID, "textures/gui/mapeditmain/build.png"); 
			mc.renderEngine.bindTexture(texture);
			func_146110_a((int)((width*0.1-30)/2), (int)((height*0.96-25-180)/2), 0, 0, 30, 30, 30, 30);//drawModalRectWithCustomSizedTexture()
			
			if((mousex>(width*0.1-30)/2)&(mousex<width*0.1)&(mousey>(height*0.96-25-180)/2+5+30)&(mousey<(height*0.96-25-180)/2+5+30+30)|(Data.lastShowMapEditGui.equals(Data.GuiReplace)))
			{
				drawRect((int)((width*0.1-30)/2), (int)((height*0.96-25-180)/2+5+30), (int)(width*0.1), (int)((height*0.96-25-180)/2+5+30+30), 0x80FFFFFF);
				if(!Data.lastShowMapEditGui.equals(Data.GuiReplace)){ToolsTip=StatCollector.translateToLocal("replace.toolstip.mapeditmain.gui.ms");}
			}
			texture = new ResourceLocation(Data.MOD_ID, "textures/gui/mapeditmain/replace.png"); 
			mc.renderEngine.bindTexture(texture);
			func_146110_a((int)((width*0.1-30)/2), (int)((height*0.96-25-180)/2+5+30), 0, 0, 30, 30, 30, 30);//drawModalRectWithCustomSizedTexture()
			
			if((mousex>(width*0.1-30)/2)&(mousex<width*0.1)&(mousey>(height*0.96-25-180)/2+5*2+30*2)&(mousey<(height*0.96-25-180)/2+5*2+30*2+30)|(Data.lastShowMapEditGui.equals(Data.GuiCopy)))
			{
				drawRect((int)((width*0.1-30)/2), (int)((height*0.96-25-180)/2+5*2+30*2), (int)(width*0.1), (int)((height*0.96-25-180)/2+5*2+30*2+30), 0x80FFFFFF);
				if(!Data.lastShowMapEditGui.equals(Data.GuiCopy)){ToolsTip=StatCollector.translateToLocal("copy.toolstip.mapeditmain.gui.ms");}
			}
			texture = new ResourceLocation(Data.MOD_ID, "textures/gui/mapeditmain/copy.png"); 
			mc.renderEngine.bindTexture(texture);
			func_146110_a((int)((width*0.1-30)/2), (int)((height*0.96-25-180)/2+5*2+30*2), 0, 0, 30, 30, 30, 30);//drawModalRectWithCustomSizedTexture()
			
			if((mousex>(width*0.1-30)/2)&(mousex<width*0.1)&(mousey>(height*0.96-25-180)/2+5*3+30*3)&(mousey<(height*0.96-25-180)/2+5*3+30*3+30)|(Data.lastShowMapEditGui.equals(Data.GuiCut)))
			{
				drawRect((int)((width*0.1-30)/2), (int)((height*0.96-25-180)/2+5*3+30*3), (int)(width*0.1), (int)((height*0.96-25-180)/2+5*3+30*3+30), 0x80FFFFFF);
				if(!Data.lastShowMapEditGui.equals(Data.GuiCut)){ToolsTip=StatCollector.translateToLocal("cut.toolstip.mapeditmain.gui.ms");}
			}
			texture = new ResourceLocation(Data.MOD_ID, "textures/gui/mapeditmain/cut.png"); 
			mc.renderEngine.bindTexture(texture);
			func_146110_a((int)((width*0.1-30)/2), (int)((height*0.96-25-180)/2+5*3+30*3), 0, 0, 30, 30, 30, 30);//drawModalRectWithCustomSizedTexture()
			
			if((mousex>(width*0.1-30)/2)&(mousex<width*0.1)&(mousey>(height*0.96-25-180)/2+5*4+30*4)&(mousey<(height*0.96-25-180)/2+5*4+30*4+30)|(Data.lastShowMapEditGui.equals(Data.GuiPaste)))
			{
				drawRect((int)((width*0.1-30)/2), (int)((height*0.96-25-180)/2+5*4+30*4), (int)(width*0.1), (int)((height*0.96-25-180)/2+5*4+30*4+30), 0x80FFFFFF);
				if(!Data.lastShowMapEditGui.equals(Data.GuiPaste)){ToolsTip=StatCollector.translateToLocal("paste.toolstip.mapeditmain.gui.ms");}
			}
			texture = new ResourceLocation(Data.MOD_ID, "textures/gui/mapeditmain/paste.png"); 
			mc.renderEngine.bindTexture(texture);
			func_146110_a((int)((width*0.1-30)/2), (int)((height*0.96-25-180)/2+5*4+30*4), 0, 0, 30, 30, 30, 30);//drawModalRectWithCustomSizedTexture()
			
			if((mousex>(width*0.1-30)/2)&(mousex<width*0.1)&(mousey>(height*0.96-25-180)/2+5*5+30*5)&(mousey<(height*0.96-25-180)/2+5*5+30*5+30))
			{
				drawRect(0, (int)((height*0.96-25-180)/2+5*5+30*5), (int)((width*0.1-30)/2+30), (int)((height*0.96-25-180)/2+5*5+30*5+30), 0x80FFFFFF);
				ToolsTip=StatCollector.translateToLocal("back.toolstip.mapeditmain.gui.ms");
			}
			texture = new ResourceLocation(Data.MOD_ID, "textures/gui/mapeditmain/back.png"); 
			mc.renderEngine.bindTexture(texture);
			func_146110_a((int)((width*0.1-30)/2), (int)((height*0.96-25-180)/2+5*5+30*5), 0, 0, 30, 30, 30, 30);//drawModalRectWithCustomSizedTexture()
			
		}
		
		drawRect(2, (int)(height*0.96), width-2, (int) (height*0.96+1), 0x80FFFFFF);//横分界线
		drawString(fontRendererObj,ToolsTip,2, (int)(height*0.966), 0xFFFFFF);ToolsTip="";
		drawString(fontRendererObj,"MapShare "+Data.MOD_VERSION,(int)(width-fontRendererObj.getStringWidth("MapShare "+Data.MOD_VERSION)-2), (int)(height*0.966), 0xFFFFFF);
		super.drawScreen(par1, par2, par3);
	}
	
	@Override
	protected void mouseClicked(int par1, int par2, int par3)
	{
		if(par3==0)//左键单击
		{
			if((par1>(width*0.1-30)/2)&(par1<width*0.1)&(par2>(height*0.96-25-180)/2)&(par2<(height*0.96-25-180)/2+30))
			{
				mc.displayGuiScreen(Data.GuiBuild);
				Data.lastShowMapEditGui=Data.GuiBuild;
			}
			
			if((par1>(width*0.1-30)/2)&(par1<width*0.1)&(par2>(height*0.96-25-180)/2+5+30)&(par2<(height*0.96-25-180)/2+5+30+30))
			{
				mc.displayGuiScreen(Data.GuiReplace);
				Data.lastShowMapEditGui=Data.GuiReplace;
			}
			
			if((par1>(width*0.1-30)/2)&(par1<width*0.1)&(par2>(height*0.96-25-180)/2+5*2+30*2)&(par2<(height*0.96-25-180)/2+5*2+30*2+30))
			{
				mc.displayGuiScreen(Data.GuiCopy);
				Data.lastShowMapEditGui=Data.GuiCopy;
			}
			
			if((par1>(width*0.1-30)/2)&(par1<width*0.1)&(par2>(height*0.96-25-180)/2+5*3+30*3)&(par2<(height*0.96-25-180)/2+5*3+30*3+30))
			{
				mc.displayGuiScreen(Data.GuiCut);
				Data.lastShowMapEditGui=Data.GuiCut;
			}
			
			if((par1>(width*0.1-30)/2)&(par1<width*0.1)&(par2>(height*0.96-25-180)/2+5*4+30*4)&(par2<(height*0.96-25-180)/2+5*4+30*4+30))
			{
				mc.displayGuiScreen(Data.GuiPaste);
				Data.lastShowMapEditGui=Data.GuiPaste;
			}
			
			if((par1>(width*0.1-30)/2)&(par1<width*0.1)&(par2>(height*0.96-25-180)/2+5*5+30*5)&(par2<(height*0.96-25-180)/2+5*5+30*5+30))
			{
				mc.displayGuiScreen(new MainMenu());
			}
		}
		super.mouseClicked(par1, par2, par3);
	}
	
	protected boolean isDrawDefaultBackground(){return true;}
}
