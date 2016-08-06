package ml.cnstl.bbt;

import ml.cnstl.bbt.item.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.registry.GameRegistry;


@Mod(modid=Info.MOD_ID,name=Info.MOD_NAME,version=Info.MOD_VERSION)

public class Block_Build_Tool
{		
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(proxy);
		proxy.init();
		FMLCommonHandler.instance().bus().register(proxy);
		
		Info.PosSelector=new PosSelector();
		Info.PosSelector.setUnlocalizedName("PositionSelector");
		Info.PosSelector.setTextureName("Block_Build_Tool:PosSelector");
		Info.PosSelector.setMaxStackSize(1);
		Info.PosSelector.setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerItem(Info.PosSelector, "PositionSelector");
		//坐标选择工具
	}
	 
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		
	}
	 
	@EventHandler
	public void postLoad(FMLPostInitializationEvent event)
	{
		
	}
	@SidedProxy(modId=Info.MOD_ID, //此处为你的modid
	        serverSide="ml.cnstl.bbt.CommonProxy",
	        clientSide="ml.cnstl.bbt.CommonProxy$ClientProxy")
	public static CommonProxy proxy;
	//过程初始化
	
	//自定义初始化子函数
}