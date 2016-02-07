package ml.cnstl.bbt;

import org.lwjgl.input.Keyboard;

import ml.cnstl.bbt.item.PosSelector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.client.event.GuiScreenEvent.InitGuiEvent;
import net.minecraftforge.client.event.RenderWorldEvent.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class CommonProxy {
	 
    public void init() 
    {
    	
    }
 
    public static class ClientProxy extends CommonProxy {
 
        @Override
        public void init() {
            //通常这里还会有super.init() 不过这里我们在服务器代理器中没有操作,因此就省去了
        	FMLCommonHandler.instance().bus().register(this);
        	ClientRegistry.registerKeyBinding(kbShowNewGui);
        	MinecraftForge.EVENT_BUS.register(new CommonProxy());
    		FMLCommonHandler.instance().bus().register(new CommonProxy());
        }
        @SubscribeEvent
        public void keyListener(KeyInputEvent event) {
            if (kbShowNewGui.isPressed())
            {
            	/*
            	String test1="CE-1.7.10;dev1;;Fri Dec 11 23:02:44 CST 2015|2;4;2|3;3;2;2;31;0;0;0;3;3;2;0;31;0;0;0|0;0;0;0;1;0;0;0;0;0;0;0;1;0;0;0";
            	String[] test2=test1.split("\\|");
            	for(int i=0;i<test2.length;i++)
            	{
            		System.out.println(test2[i]);
            	}
            	*/
                Minecraft mc = Minecraft.getMinecraft();
                mc.displayGuiScreen(new GUI(mc.currentScreen,"GUI_main"));
                //mc.displayGuiScreen(new GUI(mc.currentScreen,"GUI_main"));
            }
        }
        public static final KeyBinding kbShowNewGui = new KeyBinding("title.hot-key.option.mc.bbt", Keyboard.KEY_B, "title.option.mc.bbt");
        @SubscribeEvent
        public void WorldLoad(WorldEvent.Load event)
        {
        	Info.Tool_Mode="game";
        }
        @SubscribeEvent
        public void WorldUnload(WorldEvent.Unload event)
        {
        	Info.Tool_Mode="outgame";
        	new Block_operate().clearpreview();
        }
        @SubscribeEvent
    	public void onDropped(ItemTossEvent event) {
    		if (event.entityItem.getEntityItem().getItem() instanceof PosSelector) {
    			event.setCanceled(true);
    			ItemStack[] inv=event.player.inventory.mainInventory;
    			for(int i=0;i<inv.length;i++)
    			{
    				if(inv[i]==event.entityItem.getEntityItem()){event.player.inventory.mainInventory[i] = null;}
    			}
    		}
        }
    }
}