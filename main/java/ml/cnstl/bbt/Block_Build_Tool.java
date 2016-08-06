package ml.cnstl.bbt;

import ml.cnstl.bbt.item.*;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;


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
		Info.PosSelector.setRegistryName("Block_Build_Tool:PosSelector");
		Info.PosSelector.setMaxStackSize(1);
		Info.PosSelector.setCreativeTab(CreativeTabs.tabMaterials);
		GameRegistry.registerItem(Info.PosSelector, "PositionSelector");
		String name = GameData.getItemRegistry().getNameForObject(Info.PosSelector).toString();
		ModelLoader.setCustomModelResourceLocation(Info.PosSelector, 0, new ModelResourceLocation(name, "inventory"));
		//����ѡ�񹤾�
	}
	 
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		
	}
	 
	@EventHandler
	public void postLoad(FMLPostInitializationEvent event)
	{
		
	}
	@SidedProxy(modId=Info.MOD_ID, //�˴�Ϊ���modid
	        serverSide="ml.cnstl.bbt.CommonProxy",
	        clientSide="ml.cnstl.bbt.CommonProxy$ClientProxy")
	public static CommonProxy proxy;
	//���̳�ʼ��
	
	//�Զ����ʼ���Ӻ���
}