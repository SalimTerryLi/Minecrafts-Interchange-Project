package ml.cnstl.bbt;

import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class Block_operate
{
	private World world;
	public Block_operate()
	{
		try
		{
			this.world=MinecraftServer.getServer().getEntityWorld();
			if(this.world==null){this.world=Minecraft.getMinecraft().theWorld;}
		}catch(Exception err){}
	}
	private String getBlockname(int x,int y,int z)
	{
		String Blockname=world.getBlockState(new BlockPos(x,y,z)).getBlock().getUnlocalizedName();
		return Blockname;
	}
	public int getBlockdata(int x,int y,int z)
	{
		int Blockdata=world.getBlockState(new BlockPos(x,y,z)).getBlock().getMetaFromState(world.getBlockState(new BlockPos(x,y,z)));
		return Blockdata;
	}
	public int getBlockid(int x,int y,int z)
	{
		int Blockid=Block.getIdFromBlock(world.getBlockState(new BlockPos(x,y,z)).getBlock());
		return Blockid;
	}
	private void setBlockbyname(int x, int y, int z, String blockname, int blockdata)
	{
		Block block=Block.getBlockFromName(blockname);
		world.setBlockState(new BlockPos(x,y,z), block.getStateFromMeta(blockdata) , 2) ;
		//world.setBlock(x, y, z, block, blockdata, 2);
	}
	private void setBlockbyid(int x, int y, int z, int blockid, int blockdata)
	{
		Block block=Block.getBlockById(blockid);
		world.setBlockState(new BlockPos(x,y,z), block.getStateFromMeta(blockdata) , 2) ;
	}
	//基本操作封装
	public void Build(int xf,int yf,int zf,int xs,int ys,int zs,int Blocktype,int Blockdata)
	{
		int xl,yl,zl,xb,yb,zb;
		int bc=0;
		//动态变量区
		if(xf<xs){xl=xf;xb=xs;}else{xl=xs;xb=xf;}
		if(yf<ys){yl=yf;yb=ys;}else{yl=ys;yb=yf;}
		if(zf<zs){zl=zf;zb=zs;}else{zl=zs;zb=zf;}
		//大小排序
		for(int i=xl;i<=xb;i++)
		{
			for(int j=yl;j<=yb;j++)
			{
				for(int k=zl;k<=zb;k++)
				{
					bc++;
					setBlockbyid(i,j,k,Blocktype,Blockdata);
				}
			}
		}
		System.out.println("共"+bc+"个方块已被更新");
	}
	public void Replace(int xf,int yf,int zf,int xs,int ys,int zs,int blockb,int datab,int blocka,int dataa,boolean ifnodata,boolean ifsave)
	{
		int xl,yl,zl,xb,yb,zb;
		int bc=0;
		//动态变量区
		if(xf<xs){xl=xf;xb=xs;}else{xl=xs;xb=xf;}
		if(yf<ys){yl=yf;yb=ys;}else{yl=ys;yb=yf;}
		if(zf<zs){zl=zf;zb=zs;}else{zl=zs;zb=zf;}
		//大小排序
		for(int i=xl;i<=xb;i++)
		{
			for(int j=yl;j<=yb;j++)
			{
				for(int k=zl;k<=zb;k++)
				{
					if(ifsave)
					{
						if(ifnodata)
						{
							if(getBlockid(i,j,k)!=blockb)
							{
								bc++;
								setBlockbyid(i,j,k,blocka,dataa);
							}
						}
						else
						{
							if((getBlockid(i,j,k)!=blockb)&(getBlockdata(i,j,k)!=datab))
							{
								bc++;
								setBlockbyid(i,j,k,blocka,dataa);
							}
						}
					}
					else
					{
						if(ifnodata)
						{
							if(getBlockid(i,j,k)==blockb)
							{
								bc++;
								setBlockbyid(i,j,k,blocka,dataa);
							}
						}
						else
						{
							if((getBlockid(i,j,k)==blockb)&(getBlockdata(i,j,k)==datab))
							{
								bc++;
								setBlockbyid(i,j,k,blocka,dataa);
							}
						}
					}
				}
			}
		}
		System.out.println("共"+bc+"个方块已被替换");
	}
	public void Copy(int xf,int yf,int zf,int xs,int ys,int zs)
	{
		int xl,yl,zl,xb,yb,zb;
		//动态变量区
		if(xf<xs){xl=xf;xb=xs;}else{xl=xs;xb=xf;}
		if(yf<ys){yl=yf;yb=ys;}else{yl=ys;yb=yf;}
		if(zf<zs){zl=zf;zb=zs;}else{zl=zs;zb=zf;}
		//大小排序
		Info.BlockType=new int[xb-xl+1][yb-yl+1][zb-zl+1];
		Info.BlockData=new int[xb-xl+1][yb-yl+1][zb-zl+1];
		//数组生成
		for(int i=0;i<=xb-xl;i++)
		{
			for(int j=0;j<=yb-yl;j++)
			{
				for(int k=0;k<=zb-zl;k++)
				{
					Info.BlockType[i][j][k]=getBlockid(xl+i,j+yl,k+zl);
					Info.BlockData[i][j][k]=getBlockdata(xl+i,yl+j,k+zl);
				}
			}
		}
		//数组赋值
	}
	public void Paste(int x,int y,int z,String offset,String mode)
	{
		if(Info.whethercopied)
		{
			int xlength=Info.BlockType.length,ylength=Info.BlockType[0].length,zlength=Info.BlockType[0][0].length,offsetx,offsety,offsetz;
			//动态变量区
			if((offset=="none")|(offset=="diagonal_1")|(offset=="")){offsetx=0;offsety=0;offsetz=0;}
			else if(offset=="buttom_center"){offsetx=(int) (0-(Math.ceil(xlength/2)-1));offsety=0;offsetz=(int) (0-(Math.ceil(zlength/2)-1));}
			else if(offset=="diagonal_2"){offsetx=1-xlength;offsety=0;offsetz=0;}
			else if(offset=="diagonal_3"){offsetx=1-xlength;offsety=0;offsetz=1-zlength;}
			else if(offset=="diagonal_4"){offsetx=0;offsety=0;offsetz=1-zlength;}
			else if(offset=="top_center"){offsetx=(int) (0-(Math.ceil(xlength/2)-1));offsety=0-ylength;offsetz=(int) (0-(Math.ceil(zlength/2)-1));}
			else if(offset=="buttom_side_center_1"){offsetx=0;offsety=0;offsetz=(int) (0-(Math.ceil(zlength/2)-1));}
			else if(offset=="buttom_side_center_2"){offsetx=0-xlength;offsety=0;offsetz=(int) (0-(Math.ceil(zlength/2)-1));}
			else if(offset=="buttom_side_center_3"){offsetx=(int) (0-(Math.ceil(xlength/2)-1));offsety=0;offsetz=0;}
			else if(offset=="buttom_side_center_4"){offsetx=(int) (0-(Math.ceil(xlength/2)-1));offsety=0;offsetz=0-zlength;}
			else{offsetx=0;offsety=0;offsetz=0;}
			//offset
			if(mode=="preview")
			{
				int[] xlist=new int[8],ylist=new int[8],zlist=new int[8];
				xlist[0]=x+offsetx;ylist[0]=y+offsety;zlist[0]=z+offsetz;
				xlist[1]=x+offsetx;ylist[1]=y+offsety;zlist[1]=z+zlength-1+offsetz;
				xlist[2]=x+offsetx;ylist[2]=y+ylength-1+offsety;zlist[2]=z+offsetz;
				xlist[3]=x+offsetx;ylist[3]=y+ylength-1+offsety;zlist[3]=z+zlength-1+offsetz;
				xlist[4]=x+xlength-1+offsetx;ylist[4]=y+offsety;zlist[4]=z+offsetz;
				xlist[5]=x+xlength-1+offsetx;ylist[5]=y+offsety;zlist[5]=z+zlength-1+offsetz;
				xlist[6]=x+xlength-1+offsetx;ylist[6]=y+ylength-1+offsety;zlist[6]=z+offsetz;
				xlist[7]=x+xlength-1+offsetx;ylist[7]=y+ylength-1+offsety;zlist[7]=z+zlength-1+offsetz;
				String xout="",yout="",zout="";
				for(int i=0;i<8;i++)
				{
					if(getBlockid(xlist[i],ylist[i],zlist[i])==0)
					{
						//if(xout==""){xout=""+xlist[i];}else{xout=xout+";"+xlist[i];}
						//if(yout==""){yout=""+ylist[i];}else{yout=yout+";"+ylist[i];}
						//if(zout==""){zout=""+zlist[i];}else{zout=zout+";"+zlist[i];}
						Info.previewposx[i]=xlist[i];
						Info.previewposy[i]=ylist[i];
						Info.previewposz[i]=zlist[i];
						setBlockbyid(xlist[i],ylist[i],zlist[i],89,0);
					}
				}
				Info.previewstr=xout+"|"+yout+"|"+zout;
			}
			else
			{
				for(int i=0;i<xlength;i++)
				{
					for(int j=0;j<ylength;j++)
					{
						for(int k=0;k<zlength;k++)
						{
							if((mode=="")|(mode=="none"))
							{
								setBlockbyid(x+i+offsetx,y+j+offsety,z+k+offsetz,Info.BlockType[i][j][k],Info.BlockData[i][j][k]);
							}
							else if(mode=="clearempty")
							{
								if(((Info.BlockType[i][j][k]==0)&(Info.BlockData[i][j][k]==0))){}else{setBlockbyid(x+i+offsetx,y+j+offsety,z+k+offsetz,Info.BlockType[i][j][k],Info.BlockData[i][j][k]);}
							}
						}
					}
				}
			}
		}
	}
	public void clearpreview()
	{
		if(Info.previewstr!="")
		{
			//String[] inp2=Info.previewstr.split("|");
			//int[] xlist=strarr2intarr(inp2[0].split(";")),ylist=strarr2intarr(inp2[1].split(";")),zlist=strarr2intarr(inp2[2].split(";"));
			int[] xlist=Info.previewposx,ylist=Info.previewposy,zlist=Info.previewposz;
			for(int i=0;i<8;i++)
			{
				if(getBlockid(xlist[i],ylist[i],zlist[i])==89)
				{
					setBlockbyid(xlist[i],ylist[i],zlist[i],0,0);
				}
			}
			Info.previewstr="";
		}
	}
	//真正的接口
	public boolean isServerWorld()
	{
		if(MinecraftServer.getServer().getEntityWorld()==null){return false;}else{return true;}
	}
	public int[] strarr2intarr(String[] arr)
	{
		int[] out=new int[arr.length];
		for(int i=0;i<arr.length;i++)
		{
			if(arr[i].charAt(0)==45){out[i]=0-Integer.parseInt(arr[i].replace("-","0" ));}
			else{out[i]=Integer.parseInt(arr[i].replace("-","0" ));}
		}
		return out;
	}
	//辅助函数
}
