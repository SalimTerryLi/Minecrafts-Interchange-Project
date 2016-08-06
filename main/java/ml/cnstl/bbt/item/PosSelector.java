package ml.cnstl.bbt.item;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ml.cnstl.bbt.Block_operate;
import ml.cnstl.bbt.Info;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.item.ItemTossEvent;

public class PosSelector extends Item {
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		Block_operate op=new Block_operate();
		if(player==Minecraft.getMinecraft().thePlayer)
		{
			player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("1.blockclick.chat.bbt")+x+","+y+","+z+StatCollector.translateToLocal("2.blockclick.chat.bbt")+op.getBlockid(x, y, z)+StatCollector.translateToLocal("3.blockclick.chat.bbt")+op.getBlockdata(x, y, z)+")"));
			if(Info.Tool_Mode=="setpositiononlyone")
			{
				Info.posset[0]=x;
				Info.posset[1]=y;
				Info.posset[2]=z;
				Info.Tool_Mode="game";
			}
			else if(Info.Tool_Mode=="setpositiontwo")
			{
				Info.posset[0]=x;
				Info.posset[1]=y;
				Info.posset[2]=z;
				Info.Tool_Mode="setpositiontwosecond";
			}
			else if(Info.Tool_Mode=="setpositiontwosecond")
			{
				Info.posset[3]=x;
				Info.posset[4]=y;
				Info.posset[5]=z;
				Info.Tool_Mode="game";
			}
		}
		return true;
	}
	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
	{
		ItemStack[] inv=player.inventory.mainInventory;
		for(int i=0;i<inv.length;i++)
		{
			if(inv[i]==item){player.inventory.mainInventory[i] = null;}
		}
		return false;
	}
	
}