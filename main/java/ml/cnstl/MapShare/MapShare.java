package ml.cnstl.MapShare;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=Data.MOD_ID,name=Data.MOD_NAME,version=Data.MOD_VERSION)

public class MapShare
{
	@SidedProxy(modId=Data.MOD_ID,serverSide="ml.cnstl.MapShare.CommonProxy",clientSide="ml.cnstl.MapShare.CommonProxy$ClientProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preLoad(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(proxy);
		proxy.init();
		FMLCommonHandler.instance().bus().register(proxy);
		//Proxy register
	}
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		
	}
	@EventHandler
	public void postLoad(FMLPostInitializationEvent event)
	{
		
	}
	//Mod loading events
}


//Minecraft mc = Minecraft.getMinecraft();mc.setIngameFocus();用于返回游戏界面
