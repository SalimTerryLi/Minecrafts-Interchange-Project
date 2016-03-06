package ml.cnstl.MapShare.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Player
{
	Minecraft mc;
	EntityClientPlayerMP player;
	World world;
	
	public Player(Minecraft mc)
	{
		this.mc=mc;
		this.player=mc.thePlayer;
		this.world=mc.theWorld;
	}
	
	public int[] getFootPosition()
	{
		int[] back=new int[3];
    	back[0]=(int) player.posX;
    	back[1]=(int) player.posY-2;
    	back[2]=(int) player.posZ;
		return back;
	}
	public int[] getHeadPosition()
	{
		int[] back=new int[3];
    	back[0]=(int) player.posX;
    	back[1]=(int) player.posY;
    	back[2]=(int) player.posZ;
		return back;
	}
	public int[] getFacetoPosition()
	{
		int[] back=new int[3];
		final int  EXPLORELENGTH=8;
		MovingObjectPosition objPosition=world.func_147447_a(Vec3.createVectorHelper(player.posX, player.posY, player.posZ), Vec3.createVectorHelper(player.posX+EXPLORELENGTH*player.getLookVec().xCoord, player.posY+EXPLORELENGTH*player.getLookVec().yCoord, player.posZ+EXPLORELENGTH*player.getLookVec().zCoord), true, false, false);
		if(objPosition==null)
		{
			back[0]=0;
			back[1]=0;
			back[2]=0;
		}
		else
		{
			back[0]=(int)objPosition.blockX;
	    	back[1]=(int)objPosition.blockY;
	    	back[2]=(int)objPosition.blockZ;
		}
    	
		return back;
	}
}
