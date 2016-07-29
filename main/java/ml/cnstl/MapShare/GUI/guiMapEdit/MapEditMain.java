package ml.cnstl.MapShare.GUI.guiMapEdit;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import ml.cnstl.MapShare.Data;
import ml.cnstl.MapShare.GUI.MainMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.common.MinecraftForge;

public class MapEditMain extends GuiScreen
{
	private ResourceLocation texture;
	private String ToolsTip="";
	
	public void initGui()
	{
		this.intxStartPos=(int) (super.width*0.1+1);
		this.height=(int) (super.height*0.96);this.width=(int) (super.width-(super.width*0.1+1));
		Keyboard.enableRepeatEvents(true);
		textList.clear();
		initSubGui();
	}
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3)
	{
		int mousex = Mouse.getX() * super.width / mc.displayWidth;
        int mousey = super.height - Mouse.getY() * super.height / mc.displayHeight - 1;
        
        if(isDrawDefaultBackground()){drawDefaultBackground();}
		
		drawRect_((int)(super.width*0.1), 2, (int)(super.width*0.1+1), (int)(super.height*0.96-2), 0x80FFFFFF);//纵分界线
		{
			if((mousex>(super.width*0.1-30)/2)&(mousex<super.width*0.1)&(mousey>(super.height*0.96-25-180)/2)&(mousey<(super.height*0.96-25-180)/2+30)|(Data.lastShowMapEditGui.equals(Data.GuiBuild)))
			{
				drawRect_((int)((super.width*0.1-30)/2), (int)((super.height*0.96-25-180)/2), (int)(super.width*0.1), (int)((super.height*0.96-25-180)/2+30), 0x80FFFFFF);
				if(!Data.lastShowMapEditGui.equals(Data.GuiBuild)){ToolsTip=StatCollector.translateToLocal("build.toolstip.mapeditmain.gui.ms");}
			}
			texture = new ResourceLocation(Data.MOD_ID, "textures/gui/mapeditmain/build.png"); 
			mc.renderEngine.bindTexture(texture);
			func_146110_a_((int)((super.width*0.1-30)/2), (int)((super.height*0.96-25-180)/2), 0, 0, 30, 30, 30, 30);//drawModalRectWithCustomSizedTexture()
			
			if((mousex>(super.width*0.1-30)/2)&(mousex<super.width*0.1)&(mousey>(super.height*0.96-25-180)/2+5+30)&(mousey<(super.height*0.96-25-180)/2+5+30+30)|(Data.lastShowMapEditGui.equals(Data.GuiReplace)))
			{
				drawRect_((int)((super.width*0.1-30)/2), (int)((super.height*0.96-25-180)/2+5+30), (int)(super.width*0.1), (int)((super.height*0.96-25-180)/2+5+30+30), 0x80FFFFFF);
				if(!Data.lastShowMapEditGui.equals(Data.GuiReplace)){ToolsTip=StatCollector.translateToLocal("replace.toolstip.mapeditmain.gui.ms");}
			}
			texture = new ResourceLocation(Data.MOD_ID, "textures/gui/mapeditmain/replace.png"); 
			mc.renderEngine.bindTexture(texture);
			func_146110_a_((int)((super.width*0.1-30)/2), (int)((super.height*0.96-25-180)/2+5+30), 0, 0, 30, 30, 30, 30);//drawModalRectWithCustomSizedTexture()
			
			if((mousex>(super.width*0.1-30)/2)&(mousex<super.width*0.1)&(mousey>(super.height*0.96-25-180)/2+5*2+30*2)&(mousey<(super.height*0.96-25-180)/2+5*2+30*2+30)|(Data.lastShowMapEditGui.equals(Data.GuiCopy)))
			{
				drawRect_((int)((super.width*0.1-30)/2), (int)((super.height*0.96-25-180)/2+5*2+30*2), (int)(super.width*0.1), (int)((super.height*0.96-25-180)/2+5*2+30*2+30), 0x80FFFFFF);
				if(!Data.lastShowMapEditGui.equals(Data.GuiCopy)){ToolsTip=StatCollector.translateToLocal("copy.toolstip.mapeditmain.gui.ms");}
			}
			texture = new ResourceLocation(Data.MOD_ID, "textures/gui/mapeditmain/copy.png"); 
			mc.renderEngine.bindTexture(texture);
			func_146110_a_((int)((super.width*0.1-30)/2), (int)((super.height*0.96-25-180)/2+5*2+30*2), 0, 0, 30, 30, 30, 30);//drawModalRectWithCustomSizedTexture()
			
			if((mousex>(super.width*0.1-30)/2)&(mousex<super.width*0.1)&(mousey>(super.height*0.96-25-180)/2+5*3+30*3)&(mousey<(super.height*0.96-25-180)/2+5*3+30*3+30)|(Data.lastShowMapEditGui.equals(Data.GuiCut)))
			{
				drawRect_((int)((super.width*0.1-30)/2), (int)((super.height*0.96-25-180)/2+5*3+30*3), (int)(super.width*0.1), (int)((super.height*0.96-25-180)/2+5*3+30*3+30), 0x80FFFFFF);
				if(!Data.lastShowMapEditGui.equals(Data.GuiCut)){ToolsTip=StatCollector.translateToLocal("cut.toolstip.mapeditmain.gui.ms");}
			}
			texture = new ResourceLocation(Data.MOD_ID, "textures/gui/mapeditmain/cut.png"); 
			mc.renderEngine.bindTexture(texture);
			func_146110_a_((int)((super.width*0.1-30)/2), (int)((super.height*0.96-25-180)/2+5*3+30*3), 0, 0, 30, 30, 30, 30);//drawModalRectWithCustomSizedTexture()
			
			if((mousex>(super.width*0.1-30)/2)&(mousex<super.width*0.1)&(mousey>(super.height*0.96-25-180)/2+5*4+30*4)&(mousey<(super.height*0.96-25-180)/2+5*4+30*4+30)|(Data.lastShowMapEditGui.equals(Data.GuiPaste)))
			{
				drawRect_((int)((super.width*0.1-30)/2), (int)((super.height*0.96-25-180)/2+5*4+30*4), (int)(super.width*0.1), (int)((super.height*0.96-25-180)/2+5*4+30*4+30), 0x80FFFFFF);
				if(!Data.lastShowMapEditGui.equals(Data.GuiPaste)){ToolsTip=StatCollector.translateToLocal("paste.toolstip.mapeditmain.gui.ms");}
			}
			texture = new ResourceLocation(Data.MOD_ID, "textures/gui/mapeditmain/paste.png"); 
			mc.renderEngine.bindTexture(texture);
			func_146110_a_((int)((super.width*0.1-30)/2), (int)((super.height*0.96-25-180)/2+5*4+30*4), 0, 0, 30, 30, 30, 30);//drawModalRectWithCustomSizedTexture()
			
			if((mousex>(super.width*0.1-30)/2)&(mousex<super.width*0.1)&(mousey>(super.height*0.96-25-180)/2+5*5+30*5)&(mousey<(super.height*0.96-25-180)/2+5*5+30*5+30))
			{
				drawRect_(0, (int)((super.height*0.96-25-180)/2+5*5+30*5), (int)((super.width*0.1-30)/2+30), (int)((super.height*0.96-25-180)/2+5*5+30*5+30), 0x80FFFFFF);
				ToolsTip=StatCollector.translateToLocal("back.toolstip.mapeditmain.gui.ms");
			}
			texture = new ResourceLocation(Data.MOD_ID, "textures/gui/mapeditmain/back.png"); 
			mc.renderEngine.bindTexture(texture);
			func_146110_a_((int)((super.width*0.1-30)/2), (int)((super.height*0.96-25-180)/2+5*5+30*5), 0, 0, 30, 30, 30, 30);//drawModalRectWithCustomSizedTexture()
			
		}
		drawSubScreen(par1,par2,par3);
		
		drawRect_(2, (int)(super.height*0.96), super.width-2, (int) (super.height*0.96+1), 0x80FFFFFF);//横分界线
		drawString_(fontRendererObj,ToolsTip,2, (int)(super.height*0.966), 0xFFFFFF);ToolsTip="";
		drawString_(fontRendererObj,"MapShare "+Data.MOD_VERSION,(int)(super.width-fontRendererObj.getStringWidth("MapShare "+Data.MOD_VERSION)-2), (int)(super.height*0.966), 0xFFFFFF);
		super.drawScreen(par1, par2, par3);
	}
	
	@Override
	protected void mouseClicked(int par1, int par2, int par3)
	{
		if(par3==0)//左键单击
		{
			if((par1>(super.width*0.1-30)/2)&(par1<super.width*0.1)&(par2>(super.height*0.96-25-180)/2)&(par2<(super.height*0.96-25-180)/2+30))
			{
				mc.displayGuiScreen(Data.GuiBuild);
				Data.lastShowMapEditGui=Data.GuiBuild;
			}
			
			if((par1>(super.width*0.1-30)/2)&(par1<super.width*0.1)&(par2>(super.height*0.96-25-180)/2+5+30)&(par2<(super.height*0.96-25-180)/2+5+30+30))
			{
				mc.displayGuiScreen(Data.GuiReplace);
				Data.lastShowMapEditGui=Data.GuiReplace;
			}
			
			if((par1>(super.width*0.1-30)/2)&(par1<super.width*0.1)&(par2>(super.height*0.96-25-180)/2+5*2+30*2)&(par2<(super.height*0.96-25-180)/2+5*2+30*2+30))
			{
				mc.displayGuiScreen(Data.GuiCopy);
				Data.lastShowMapEditGui=Data.GuiCopy;
			}
			
			if((par1>(super.width*0.1-30)/2)&(par1<super.width*0.1)&(par2>(super.height*0.96-25-180)/2+5*3+30*3)&(par2<(super.height*0.96-25-180)/2+5*3+30*3+30))
			{
				mc.displayGuiScreen(Data.GuiCut);
				Data.lastShowMapEditGui=Data.GuiCut;
			}
			
			if((par1>(super.width*0.1-30)/2)&(par1<super.width*0.1)&(par2>(super.height*0.96-25-180)/2+5*4+30*4)&(par2<(super.height*0.96-25-180)/2+5*4+30*4+30))
			{
				mc.displayGuiScreen(Data.GuiPaste);
				Data.lastShowMapEditGui=Data.GuiPaste;
			}
			
			if((par1>(super.width*0.1-30)/2)&(par1<super.width*0.1)&(par2>(super.height*0.96-25-180)/2+5*5+30*5)&(par2<(super.height*0.96-25-180)/2+5*5+30*5+30))
			{
				mc.displayGuiScreen(new MainMenu());
			}
		}
		SubmouseClicked(par1,par2,par3);
		super.mouseClicked(par1, par2, par3);
	}
	@Override
    protected void keyTyped(char par1, int par2)
	{
		SubkeyTyped(par1, par2);
		super.keyTyped(par1, par2);
	}
	//以上主体
	public static void drawRect_(int p_73734_0_, int p_73734_1_, int p_73734_2_, int p_73734_3_, int p_73734_4_)
	{
		net.minecraft.client.gui.Gui.drawRect( p_73734_0_,  p_73734_1_,  p_73734_2_,  p_73734_3_,  p_73734_4_);
	}
	public static void func_146110_a_(int p_146110_0_, int p_146110_1_, float p_146110_2_, float p_146110_3_, int p_146110_4_, int p_146110_5_, float p_146110_6_, float p_146110_7_)
	{
		net.minecraft.client.gui.Gui.func_146110_a( p_146110_0_,  p_146110_1_,  p_146110_2_,  p_146110_3_,  p_146110_4_,  p_146110_5_,  p_146110_6_,  p_146110_7_);
	}
	public void drawString_(FontRenderer p_73731_1_, String p_73731_2_, int p_73731_3_, int p_73731_4_, int p_73731_5_)
	{
		super.drawString(p_73731_1_, p_73731_2_, p_73731_3_, p_73731_4_, p_73731_5_);
	}
	public void drawCenteredString_(FontRenderer p_73732_1_, String p_73732_2_, int p_73732_3_, int p_73732_4_, int p_73732_5_)
	{
		super.drawCenteredString(p_73732_1_, p_73732_2_, p_73732_5_, p_73732_5_, p_73732_5_);
	}
	protected List textList = new ArrayList();
	protected List textListtext=new ArrayList();
	//以上封装
	public int height=(int) (super.height*0.96),width=(int) (super.width-(super.width*0.1+1));
	private int intxStartPos=(int) (super.width*0.1+1),intyStartPos=0;
	protected boolean isDrawDefaultBackground(){return true;}
	public void initSubGui(){}
	public void drawSubRect(int p_73734_0_, int p_73734_1_, int p_73734_2_, int p_73734_3_, int p_73734_4_)
	{
		drawRect_( p_73734_0_+intxStartPos,  p_73734_1_,  p_73734_2_+intxStartPos,  p_73734_3_,  p_73734_4_);
	}
	public void func_146110_aSub(int p_146110_0_, int p_146110_1_, float p_146110_2_, float p_146110_3_, int p_146110_4_, int p_146110_5_, float p_146110_6_, float p_146110_7_)
	{
		func_146110_a_( p_146110_0_+intxStartPos,  p_146110_1_,  p_146110_2_,  p_146110_3_,  p_146110_4_,  p_146110_5_,  p_146110_6_,  p_146110_7_);
	}
	public void drawSubString(FontRenderer p_73731_1_, String p_73731_2_, int p_73731_3_, int p_73731_4_, int p_73731_5_)
	{
		drawString(p_73731_1_, p_73731_2_, p_73731_3_+intxStartPos, p_73731_4_, p_73731_5_);
	}
	public void drawSubCenteredString(FontRenderer p_73732_1_, String p_73732_2_, int p_73732_3_, int p_73732_4_, int p_73732_5_)
	{
		drawCenteredString(p_73732_1_, p_73732_2_, p_73732_3_+intxStartPos, p_73732_4_, p_73732_5_);
	}
	public GuiButton addGuiButton(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_, String p_i1021_6_)
	{
		GuiButton newGuiButton;
		newGuiButton=new GuiButton(p_i1021_1_, p_i1021_2_+intxStartPos, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
		return newGuiButton;
	}
	public GuiTextField addGuiTextField(FontRenderer p_i1032_1_, int p_i1032_2_, int p_i1032_3_, int p_i1032_4_, int p_i1032_5_)
	{
		GuiTextField newGuiTextField = new GuiTextField(p_i1032_1_,  p_i1032_2_+intxStartPos,  p_i1032_3_,  p_i1032_4_,  p_i1032_5_);
		textList.add(newGuiTextField);
		textListtext.add("");
		return newGuiTextField;
	}
	public void drawSubScreen(int par1, int par2, float par3)
	{
		int k;

        for (k = 0; k < this.textList.size(); ++k)
        {
        	((GuiTextField)this.textList.get(k)).setText(((String)(textListtext.get(k))));
            ((GuiTextField)this.textList.get(k)).drawTextBox();
        }
	}
	//以上子类调用
	protected void SubmouseClicked(int par1, int par2, int par3)
	{
		int k;

        for (k = 0; k < this.textList.size(); ++k)
        {
            ((GuiTextField)this.textList.get(k)).mouseClicked(par1, par2, par3);
        }
	}
	protected void SubkeyTyped(char par1, int par2)
	{
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
	}
	//以上子类优化
	
	
}
