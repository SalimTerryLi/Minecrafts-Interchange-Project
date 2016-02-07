package ml.cnstl.bbt;

import net.minecraft.block.Block;

public class Block_info
{
	public Block name2block(String Blockname)
	{
		Block block=Block.getBlockFromName(Blockname);
		return block;
	}
	public int name2id(String Blockname)
	{
		int Blockid=Block.getIdFromBlock(Block.getBlockFromName(Blockname));
		return Blockid;
	}
	public int id2name(int Blockid)
	{
		String Blockname=Block.getBlockById(Blockid).getUnlocalizedName();
		return Blockid;
	}
}
