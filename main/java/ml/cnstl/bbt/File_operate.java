package ml.cnstl.bbt;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.minecraft.client.Minecraft;

public class File_operate {

	public static void exportfile(String filename) throws Exception
	{

		if(Info.whethercopied)
		{
			JFileChooser fc=new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("BBT saves", "bbt");
			fc.setFileFilter(new FileNameExtensionFilter("BBT saves", new String[]{".bbt"}));
			fc.setSelectedFile(new File("BBT Save.bbt")); 
			fc.showSaveDialog(null);
			filename=fc.getSelectedFile().getPath();

			String mcpelevel="CE-1.7.10";
			String outstring="",bbtlevel=Info.MOD_VERSION,bllevel="";
			file_append(filename,mcpelevel+";"+bbtlevel+";"+bllevel+";"+new Date().toString()+"|"+Info.BlockType.length+";"+Info.BlockType[0].length+";"+Info.BlockType[0][0].length+"|");
			//生成文件头序列
			for(int i=0;i<Info.BlockType.length;i++)
			{
				for(int j=0;j<Info.BlockType[0].length;j++)
				{
					for(int k=0;k<Info.BlockType[0][0].length;k++)
					{
						if((i==Info.BlockType.length-1)&(j==Info.BlockType[0].length-1)&(k==Info.BlockType[0][0].length-1))
						{
							file_append(filename,outstring+Info.BlockType[i][j][k]+"|");
						}
						else
						{
							file_append(filename,outstring+Info.BlockType[i][j][k]+";");
						}
					}
				}
			}
			//生成Blocktype序列
			for(int i=0;i<Info.BlockType.length;i++)
			{
				for(int j=0;j<Info.BlockType[0].length;j++)
				{
					for(int k=0;k<Info.BlockType[0][0].length;k++)
					{
						if((i==Info.BlockType.length-1)&(j==Info.BlockType[0].length-1)&(k==Info.BlockType[0][0].length-1))
						{
							file_append(filename,outstring+Info.BlockData[i][j][k]);
						}
						else
						{
							file_append(filename,outstring+Info.BlockData[i][j][k]+";");
						}
					}
				}
			}
			//生成Blockdata序列
		}
	}
	public static void importfile(String filename,boolean whetherverification) throws IOException
	{
		JFileChooser fc=new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("BBT saves", "bbt");
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileFilter(filter);
		fc.showOpenDialog(null);
		filename=fc.getSelectedFile().getPath();

		if(fc.getSelectedFile().exists())
		{
			if(whetherverification)
			{

			}
			else
			{
				String instring=file_read(filename);
				String temp1[]=instring.split("\\|");
				String tmplen[]=temp1[1].split(";");
				int xlength=str2int(tmplen[0]),ylength=str2int(tmplen[1]),zlength=str2int(tmplen[2]);
				String Btype[]=temp1[2].split(";");
				String Bdata[]=temp1[3].split(";");
				Info.BlockType=new int[xlength][ylength][ylength];
				Info.BlockData=new int[xlength][ylength][ylength];
				//数组生成
				System.out.println(""+Info.BlockType.length);
				System.out.println(""+Info.BlockType[0].length);
				System.out.println(""+Info.BlockType[0][0].length);
				int stepc=-1;
				for(int i=0;i<Info.BlockType.length;i++)
				{
					for(int j=0;j<Info.BlockType[0].length;j++)
					{
						for(int k=0;k<Info.BlockType[0][0].length;k++)
						{
							stepc++;
							Info.BlockType[i][j][k]=str2int(Btype[stepc]);
							Info.BlockData[i][j][k]=str2int(Bdata[stepc]);
						}
					}
				}
				Info.whethercopied=true;
			}
		}
	}

	public static String file_read(String fileName) throws IOException
	{
		java.io.File f = new java.io.File(fileName);    
		java.io.InputStreamReader read = new java.io.InputStreamReader(new java.io.FileInputStream(f),"ascii");     
		java.io.BufferedReader reader=new java.io.BufferedReader(read);     
		String line,fileContent="";
		while ((line = reader.readLine()) != null) 
		{      
			fileContent += line;     
		}       
		read.close();
		return fileContent;
	}
	public static void file_write(String filename,String text) throws Exception
	{
		java.io.FileOutputStream out = new java.io.FileOutputStream(filename);
		out.write(new java.lang.String(text).getBytes());
		out.close();
	}
	public static void file_append(String filename,String text)
	{
		java.io.FileOutputStream out;
		try {
			out = new java.io.FileOutputStream(filename, true);

			// TODO 自动生成的 catch 块


			out.write(new java.lang.String(text).getBytes());

			// TODO 自动生成的 catch 块


			out.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public static int str2int(String in)
	{
		return Integer.valueOf(in);
	}
}
