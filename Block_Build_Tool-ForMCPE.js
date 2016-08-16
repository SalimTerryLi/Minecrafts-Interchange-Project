var block_build_tool_level="Beta2e2";
//静态脚本信息
var positions=new Array(6),fpoint=false,pselected=false;
var paste2position=new Array(3),pastepselected=false,pastemode="buttom_center",previewstr="";

var tool_mode="outgame"
//静态变量区
gui_show();
//基础启动部分
function newLevel()
{
	tool_mode="game";
}
function leaveGame()
{
	tool_mode="outgame";
	pselected=false;
	pastepselected=false;
}
function procCmd(cmd)
{
	var Data = cmd.split(" ");
	
}
function useItem(x,y,z,hid,bid,side,itemDamage,blockDamage )
{
if(tool_mode!="game"){preventDefault();}
if((tool_mode=="setposition")|(tool_mode=="setpositionc")|(tool_mode=="setpositionr"))
{
	if(!fpoint)
	{
		positions[0]=x;
		positions[1]=y;
		positions[2]=z;
		fpoint=true;
		print("首坐标已确定");
	}
	else
	{
		positions[3]=x;
		positions[4]=y;
		positions[5]=z;
		fpoint=false;
		print("次坐标已确定，请打开工具菜单");
		pselected=true;
		if(tool_mode=="setposition"){tool_mode="build";}else if(tool_mode=="setpositionc"){tool_mode="copy";}else if(tool_mode=="setpositionr"){tool_mode="replace";}
	}
}
else if(tool_mode=="setpastep")
{
	paste2position[0]=x;
	paste2position[1]=y;
	paste2position[2]=z;
	pastepselected=true;
	paste(x,y,z,pastemode,"preview");
	tool_mode="paste";
	print("坐标已确定，请打开工具菜单");
}
else if(tool_mode=="printinfo")
{
	clientMessage("Tool" + hid + "Type" + itemDamage + "Block" + bid + "Type" + blockDamage + "@" + x + "," + y + "," + z + " Player@" + Math.round(Player.getX()*100)/100 +","+ Math.round(Player.getY()*100)/100 +","+Math.round(Player.getZ()*100)/100);
}
}
function destroyBlock(x,y,z,side)
{
	if(tool_mode!="game")
	{
		preventDefault();
	}
}
//bl事件响应
var Blocktype,Blockdata,whethercopied=false;
//静态变量区
function build(xf,yf,zf,xs,ys,zs,block,data)
{
try{
	var xl,yl,zl,xb,yb,zb;
	var bc=0;
	//动态变量区
	if(xf<xs){xl=xf;xb=xs;}else{xl=xs;xb=xf;}
	if(yf<ys){yl=yf;yb=ys;}else{yl=ys;yb=yf;}
	if(zf<zs){zl=zf;zb=zs;}else{zl=zs;zb=zf;}
	//大小排序
	for(var i=xl;i<=xb;i++)
	{
		for(var j=yl;j<=yb;j++)
		{
			for(var k=zl;k<=zb;k++)
			{
				bc++;
				setTile(i,j,k,block,data);
			}
		}
	}
	var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get() 
	ctx.runOnUiThread(new java.lang.Runnable({ 
	run: function(){
	mainbutton.setText("~");
	}}))
	print("共"+bc.toString()+"个方块已被建造");
}catch(err){print(err);}
}
function replace(xf,yf,zf,xs,ys,zs,blockb,datab,blocka,dataa,ifnodata,ifsave)
{
try{
	var xl,yl,zl,xb,yb,zb;
	var bc=0;
	//动态变量区
	if(xf<xs){xl=xf;xb=xs;}else{xl=xs;xb=xf;}
	if(yf<ys){yl=yf;yb=ys;}else{yl=ys;yb=yf;}
	if(zf<zs){zl=zf;zb=zs;}else{zl=zs;zb=zf;}
	//大小排序
	for(var i=xl;i<=xb;i++)
	{
		for(var j=yl;j<=yb;j++)
		{
			for(var k=zl;k<=zb;k++)
			{
				if(ifsave)
				{
					if(ifnodata)
					{
						if(Level.getTile(i,j,k)!=blockb)
						{
							bc++;
							setTile(i,j,k,blocka,dataa);
						}
					}
					else
					{
						if((Level.getTile(i,j,k)!=blockb)&(Level.getData(i,j,k)!=datab))
						{
							bc++;
							setTile(i,j,k,blocka,dataa);
						}
					}
				}
				else
				{
					if(ifnodata)
					{
						if(Level.getTile(i,j,k)==blockb)
						{
							bc++;
							setTile(i,j,k,blocka,dataa);
						}
					}
					else
					{
						if((Level.getTile(i,j,k)==blockb)&(Level.getData(i,j,k)==datab))
						{
							bc++;
							setTile(i,j,k,blocka,dataa);
						}
					}
				}
			}
		}
	}
	var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get() 
	ctx.runOnUiThread(new java.lang.Runnable({ 
	run: function(){
	mainbutton.setText("~");
	}}))
	print("共"+bc.toString()+"个方块已被替换");
}catch(err){print(err);}
}
function copy(xf,yf,zf,xs,ys,zs)//方块坐标由小由小至大生成
{
	var xl,yl,zl,xb,yb,zb;
	//动态变量区
	if(xf<xs){xl=xf;xb=xs;}else{xl=xs;xb=xf;}
	if(yf<ys){yl=yf;yb=ys;}else{yl=ys;yb=yf;}
	if(zf<zs){zl=zf;zb=zs;}else{zl=zs;zb=zf;}
	//大小排序
	Blocktype=Array(xb-xl+1);
	for(var i=0;i<=xb-xl;i++)
	{
		Blocktype[i]=Array(yb-yl+1);
		for(var j=0;j<=yb-yl;j++)
		{
			Blocktype[i][j]=new Array(zb-zl+1);
		}
	}
	Blockdata=Array(xb-xl+1);
	for(var i=0;i<=xb-xl;i++)
	{
		Blockdata[i]=Array(yb-yl+1);
		for(var j=0;j<=yb-yl;j++)
		{
			Blockdata[i][j]=new Array(zb-zl+1);
		}
	}
	//数组生成
	for(var i=0;i<=xb-xl;i++)
	{
		for(var j=0;j<=yb-yl;j++)
		{
			for(var k=0;k<=zb-zl;k++)
			{
				Blocktype[i][j][k]=getTile(xl+i,j+yl,k+zl);
				Blockdata[i][j][k]=Level.getData(xl+i,yl+j,k+zl);
			}
		}
	}
	//数组赋值
	whethercopied=true;
	var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get() 
	ctx.runOnUiThread(new java.lang.Runnable({ 
	run: function(){
	mainbutton.setText("~");
	}}))
	
}
function clearcopy()
{
	Blocktype=0;
	Blockdata=0;
	whethercopied=false;
}
function clearpreview()
{
try
{
		var inp2=previewstr.split("|");
		var xlist=inp2[0].split(";"),ylist=inp2[1].split(";"),zlist=inp2[2].split(";");
		for(var i=0;i<8;i++)
		{
			if(getTile(xlist[i],ylist[i],zlist[i])==89)
			{
				setTile(xlist[i],ylist[i],zlist[i],0,0);
			}
		}
		previewstr="";
}
catch(err){}
}
function paste(x,y,z,offset,mode)
{
	if(whethercopied)
	{
		var xlength=Blocktype.length,ylength=Blocktype[0].length,zlength=Blocktype[0][0].length,offsetx,offsety,offsetz;
		//动态变量区
		if((offset=="none")|(offset=="diagonal_1")|(offset=="")){offsetx=0;offsety=0;offsetz=0;}
		else if(offset=="buttom_center"){offsetx=0-(Math.ceil(xlength/2)-1);offsety=0;offsetz=0-(Math.ceil(zlength/2)-1);}
		else if(offset=="diagonal_2"){offsetx=1-xlength;offsety=0;offsetz=0;}
		else if(offset=="diagonal_3"){offsetx=1-xlength;offsety=0;offsetz=1-zlength;}
		else if(offset=="diagonal_4"){offsetx=0;offsety=0;offsetz=1-zlength;}
		else if(offset=="top_center"){offsetx=0-(Math.ceil(xlength/2)-1);offsety=0-ylength;offsetz=0-(Math.ceil(zlength/2)-1);}
		else if(offset=="buttom_side_center_1"){offsetx=0;offsety=0;offsetz=0-(Math.ceil(zlength/2)-1);}
		else if(offset=="buttom_side_center_2"){offsetx=0-xlength;offsety=0;offsetz=0-(Math.ceil(zlength/2)-1);}
		else if(offset=="buttom_side_center_3"){offsetx=0-(Math.ceil(xlength/2)-1);offsety=0;offsetz=0;}
		else if(offset=="buttom_side_center_4"){offsetx=0-(Math.ceil(xlength/2)-1);offsety=0;offsetz=0-zlength;}
		else{offsetx=0;offsety=0;offsetz=0;}
		//offset
		if(mode=="preview")
		{
			var xlist=new Array(8),ylist=new Array(8),zlist=new Array(8);
			xlist[0]=x+offsetx,ylist[0]=y+offsety,zlist[0]=z+offsetz;
			xlist[1]=x+offsetx,ylist[1]=y+offsety,zlist[1]=z+zlength-1+offsetz;
			xlist[2]=x+offsetx,ylist[2]=y+ylength-1+offsety,zlist[2]=z+offsetz;
			xlist[3]=x+offsetx,ylist[3]=y+ylength-1+offsety,zlist[3]=z+zlength-1+offsetz;
			xlist[4]=x+xlength-1+offsetx,ylist[4]=y+offsety,zlist[4]=z+offsetz;
			xlist[5]=x+xlength-1+offsetx,ylist[5]=y+offsety,zlist[5]=z+zlength-1+offsetz;
			xlist[6]=x+xlength-1+offsetx,ylist[6]=y+ylength-1+offsety,zlist[6]=z+offsetz;
			xlist[7]=x+xlength-1+offsetx,ylist[7]=y+ylength-1+offsety,zlist[7]=z+zlength-1+offsetz;
			var xout="",yout="",zout="";
			for(var i=0;i<8;i++)
			{
				if(getTile(xlist[i],ylist[i],zlist[i])==0)
				{
					if(xout==""){xout=xlist[i]}else{xout=xout+";"+xlist[i];}
					if(yout==""){yout=ylist[i]}else{yout=yout+";"+ylist[i];}
					if(zout==""){zout=zlist[i]}else{zout=zout+";"+zlist[i];}
					setTile(xlist[i],ylist[i],zlist[i],89,0);
				}
			}
			previewstr=xout+"|"+yout+"|"+zout;
		}
		else
		{
			for(var i=0;i<xlength;i++)
			{
				for(var j=0;j<ylength;j++)
			{
				for(var k=0;k<zlength;k++)
					{
						if((mode=="")|(mode=="none"))
						{
							setTile(x+i+offsetx,y+j+offsety,z+k+offsetz,Blocktype[i][j][k],Blockdata[i][j][k]);
						}
						else if(mode=="clearempty")
						{
							if(((Blocktype[i][j][k]==0)&(Blockdata[i][j][k]==0))){}else{setTile(x+i+offsetx,y+j+offsety,z+k+offsetz,Blocktype[i][j][k],Blockdata[i][j][k]);}
						}
					}
				}
			}
		
		}
	}
}
function exportfile(filename)
{
	if(whethercopied)
	{
		//ZipOutputStream out = new ZipOutputStream(new FileOutputStream(filename));
		//BufferedOutputStream bo = new BufferedOutputStream(out);
		
		//bo.close();
		//out.close();
		var mcpelevel="unknown";
		try{mcpelevel=ModPE.getMinecraftVersion();}catch(err){print("您的Blocklauncher版本过低");}
		file_write(filename,"");
		var outstring="",bbtlevel=block_build_tool_level;
		file_append(filename,mcpelevel+";"+bbtlevel+";"+Date()+"|"+Blocktype.length+";"+Blocktype[0].length+";"+Blocktype[0][0].length+"|");
		//生成文件头序列
		for(var i=0;i<Blocktype.length;i++)
		{
			for(var j=0;j<Blocktype[0].length;j++)
			{
				for(var k=0;k<Blocktype[0][0].length;k++)
				{
					if((i==Blocktype.length-1)&(j==Blocktype[0].length-1)&(k==Blocktype[0][0].length-1))
					{
						file_append(filename,outstring+Blocktype[i][j][k]+"|");
					}
					else
					{
						file_append(filename,outstring+Blocktype[i][j][k]+";");
					}
				}
			}
		}
	//生成Blocktype序列
	for(var i=0;i<Blocktype.length;i++)
		{
			for(var j=0;j<Blocktype[0].length;j++)
			{
				for(var k=0;k<Blocktype[0][0].length;k++)
				{
					if((i==Blocktype.length-1)&(j==Blocktype[0].length-1)&(k==Blocktype[0][0].length-1))
					{
						file_append(filename,outstring+Blockdata[i][j][k]);
					}
					else
					{
						file_append(filename,outstring+Blockdata[i][j][k]+";");
					}
				}
			}
		}
	//生成Blockdata序列
	var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get() 
	ctx.runOnUiThread(new java.lang.Runnable({ 
	run: function(){
	mainbutton.setText("~");
	}}))
	print("文件 "+filename+" 导出成功");
	}
}
function importfile(filename,whetherverification)
{
	if(whetherverification)
	{
		
	}
	else
	{
		//ZipInputStream Zin=new ZipInputStream(new FileInputStream(filename));//输入源zip路径
		//BufferedInputStream Bin=new BufferedInputStream(Zin);
		
		//Bin.close();
		//Zin.close();
		var instring=file_read(filename);
		var temp1=instring.split("|");
		var tmplen=temp1[1].split(";");
		var xlength=tmplen[0],ylength=tmplen[1],zlength=tmplen[2];
		var Btype=temp1[2].split(";");
		var Bdata=temp1[3].split(";");
		Blocktype=Array(xlength);
		for(var i=0;i<xlength;i++)
		{
			Blocktype[i]=Array(ylength);
			for(var j=0;j<ylength;j++)
			{
				Blocktype[i][j]=new Array(zlength);
			}
		}
		Blockdata=Array(xlength);
		for(var i=0;i<xlength;i++)
		{
			Blockdata[i]=Array(ylength);
			for(var j=0;j<ylength;j++)
			{
				Blockdata[i][j]=new Array(zlength);
			}
		}
		//数组生成
		var stepc=-1;
		for(var i=0;i<xlength;i++)
		{
			for(var j=0;j<ylength;j++)
			{
				for(var k=0;k<zlength;k++)
				{
					stepc++;
					Blocktype[i][j][k]=Btype[stepc];
					Blockdata[i][j][k]=Bdata[stepc];
				}
			}
		}
		whethercopied=true;
		var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get() 
		ctx.runOnUiThread(new java.lang.Runnable({ 
		run: function(){
			mainbutton.setText("~");
		}}))
		print("导入成功!");
	}
}
function file_append(filename, text)
{
	var out = new java.io.FileOutputStream(filename, true);
	out.write(new java.lang.String(text).getBytes());
	out.close();
}
function file_read(fileName)
{
	var f = new java.io.File(fileName);    
	var read = new java.io.InputStreamReader(new java.io.FileInputStream(f),"ascii");     
	var reader=new java.io.BufferedReader(read);     
	var line,fileContent="";
	while ((line = reader.readLine()) != null) 
	{      
		fileContent += line;     
	}       
	read.close();
		return fileContent;
}
function file_write(filename, text)
{
	var out = new java.io.FileOutputStream(filename);
	out.write(new java.lang.String(text).getBytes());
	out.close();
}
function folderview(dirpath)
{
	var dirinc=shell("ls -a "+dirpath).split(String.fromCharCode(10));
	var dircount=0;
	for(var i=0;i<dirinc.length;i++)
	{
		if(whetherdir(dirpath+dirinc[i])==true){dircount++;}
	}
	var folders=new Array(dircount);
	var ls1=-1;
	for(var i=0;i<dirinc.length;i++)
	{
		if(whetherdir(dirpath+dirinc[i])==true){ls1++;folders[ls1]=dirinc[i]+"/";}
	}
	
	var filecount=0;
	for(var i=0;i<dirinc.length;i++)
	{
		if(whetherdir(dirpath+dirinc[i])==false){filecount++;}
	}
	var files=new Array(filecount);
	var ls1=-1;
	for(var i=0;i<dirinc.length;i++)
	{
		if(whetherdir(dirpath+dirinc[i])==false){ls1++;files[ls1]=dirinc[i];}
	}
	cb=new Array(2);
	cb[0]=folders;
	cb[1]=files;
	return cb
	
}
function whetherdir(dirpath)
{
	var file=new java.io.File(dirpath);
	return file.isDirectory();
}
function shell(command){
var proc = java.lang.Runtime.getRuntime().exec("sh");
var dos = new java.io.DataOutputStream(proc.getOutputStream());
dos.write(new java.lang.String(command).getBytes());
dos.writeBytes("\n");
dos.flush();
dos.writeBytes("exit\n");
dos.flush();
var status = proc.waitFor();
var errorMsg = new java.lang.StringBuilder();
var results = new java.lang.StringBuilder();
var successReader = new java.io.BufferedReader(new java.io.InputStreamReader(proc.getInputStream()));
var errorReader = new java.io.BufferedReader(new java.io.InputStreamReader(proc.getErrorStream()));
var lineStr;
while ((lineStr = successReader.readLine()) != null)
{
	results.append(lineStr + "\n");
}
while ((lineStr = errorReader.readLine()) != null)
{
	errorMsg.append(lineStr + "\n");
}
if (dos != null)dos.close();
if (successReader != null)successReader.close();
if (errorReader !=null)errorReader.close();
if (proc != null)proc.destroy();
return results.toString();
}
function strarr2intarr(arr)
{
try{
	for(var i=0;i<arr.length;i++)
	{
		if(arr[i].charCodeAt(0)=="-"){arr[i]=0-Number(arr[i])}else{arr[i]=Number(arr[i]);}
	}
	return arr;
}catch(err){print(err);}
}
//核心代码
var win,fwin,mainbutton;
//静态变量部分
function gui_show()
{
	var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get() 
	ctx.runOnUiThread(new java.lang.Runnable({ 
	run: function(){ 
	try{
	var layout = new android.widget.LinearLayout(ctx);
	mainbutton = new android.widget.Button(ctx);
	mainbutton.setText(String.fromCharCode(126));
	mainbutton.setOnClickListener(new android.view.View.OnClickListener() { 
	onClick: function(v){ 
		openMenu();
	}}); 
	win=new android.widget.PopupWindow(ctx); 
	win.setContentView(mainbutton); 
	win.setWidth(64); 
	win.setHeight(64); 
	win.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.argb(0,0,0,0)))
	win.showAtLocation(ctx.getWindow().getDecorView(),android.view.Gravity.LEFT | android.view.Gravity.TOP,7,60);
	}catch(err){print(err)}
	}}))
}
function gui_hide()
{
	var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get() 
	ctx.runOnUiThread(new java.lang.Runnable({ 
	run: function(){
	if(win!=null){win.dismiss();}
	win=null;
	}}))
}
function dip2px(ctx, dips){ 
	return Math.ceil(dips * ctx.getResources().getDisplayMetrics().density);
}
function toolmodetostring(mode)
{
	if(mode=="game"){return "正常游戏";}
	if(mode=="outgame"){return "未开始游戏";}
	if(mode=="build"){return "快速建造";}
	if(mode=="copy"){return "方块复制";}
	if(mode=="paste"){return "方块粘贴";}
	if(mode=="setposition"){return "坐标拾取";}
	if(mode=="setpositionc"){return "坐标拾取";}
	if(mode=="setpositionr"){return "坐标拾取";}
	if(mode=="setpastep"){return "坐标拾取";}
	if(mode=="printinfo"){return "信息获取";}
	if(mode=="replace"){return "方块置换";}
}
function openMenu(){
 var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get()
  try{
   var layout=new android.widget.LinearLayout(ctx)
   var menu=new android.widget.PopupWindow(layout)
   menu.setFocusable(true)
   mainMenu=menu
   layout.setOrientation(1)

   var textParams=new android.widget.LinearLayout.LayoutParams(android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT, android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT)
   textParams.setMargins(dip2px(ctx, 5), 0, 0, 0)
   var title=new android.widget.TextView(ctx);
   title.setTextSize(25)
   title.setText("建筑系 V "+block_build_tool_level)
   title.setLayoutParams(textParams)
   layout.addView(title)
   var stitle=new android.widget.TextView(ctx);
   stitle.setTextSize(20)
   stitle.setText(toolmodetostring(tool_mode));
   stitle.setLayoutParams(textParams)
   layout.addView(stitle)
 if((tool_mode=="game")|(tool_mode=="paste")|(tool_mode=="build")|(tool_mode=="copy")|(tool_mode=="replace"))
 {
if(tool_mode=="game"){
 var button=new android.widget.Button(ctx)
 button.setText("信息获取")
 button.setOnClickListener(new android.view.View.OnClickListener({
  onClick:function(viewarg)
  {
	  tool_mode="printinfo";
	  menu.dismiss();
  }}))
layout.addView(button);}
if((tool_mode=="game")|(tool_mode=="build")){
 var button=new android.widget.Button(ctx)
 button.setText("快速建造")
 button.setOnClickListener(new android.view.View.OnClickListener({
  onClick:function(viewarg)
  { 
	var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get()
  try{
   var flayout=new android.widget.LinearLayout(ctx)
   var fmenu=new android.widget.PopupWindow(flayout)
   fmenu.setFocusable(true)
   fmainMenu=fmenu
   flayout.setOrientation(1);
   if(pselected){
   var stitle=new android.widget.TextView(ctx);
   stitle.setTextSize(20)
   stitle.setText("输入方块ID");
   stitle.setLayoutParams(textParams)
   flayout.addView(stitle)
   var textid=new android.widget.EditText(ctx)
	textid.setText("0")
	flayout.addView(textid);
	var stitle=new android.widget.TextView(ctx);
   stitle.setTextSize(20)
   stitle.setText("输入方块DATA");
   stitle.setLayoutParams(textParams)
   flayout.addView(stitle)
   var textdata=new android.widget.EditText(ctx)
	textdata.setText("0")
	flayout.addView(textdata);
	}
	var button=new android.widget.Button(ctx)
	button.setText("拾取坐标")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{
		print("请点击方块以确定坐标");
		tool_mode="setposition";
		pastepselected=false;
		fmenu.dismiss();
		menu.dismiss();
	}}))
	flayout.addView(button);
	if(pselected){
	var textpos=new android.widget.EditText(ctx)
	textpos.setText(positions[0]+","+positions[1]+","+positions[2]+","+positions[3]+","+positions[4]+","+positions[5])
	flayout.addView(textpos);
   var button=new android.widget.Button(ctx)
	button.setText("建造")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{
		mainbutton.setText("!");
		var newthread=new java.lang.Thread(new java.lang.Runnable({run: 
		function(){
			try{
			var inppositions=(textpos.getText()+"").split(",");
			inppositions=strarr2intarr(inppositions);
			if(inppositions.length==6){build(inppositions[0],inppositions[1],inppositions[2],inppositions[3],inppositions[4],inppositions[5],Number(textid.getText()+""),Number(textdata.getText()+""));}else{
			build(positions[0],positions[1],positions[2],positions[3],positions[4],positions[5],Number(textid.getText()+""),Number(textdata.getText()+""));}
			}catch(err){clientMessage(err);}
		}}));
		newthread.start();
		pselected=false;
		tool_mode="game";
		fmenu.dismiss();
		menu.dismiss();
	}}))
	flayout.addView(button);}
	var button=new android.widget.Button(ctx)
	button.setText("取消")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{
		tool_mode="game";
		pselected=false;
		fmenu.dismiss();
		menu.dismiss();
	}}))
	flayout.addView(button);
	var mlayout=makeMenu(ctx,fmenu,flayout)
   fmenu.setContentView(mlayout)
   fmenu.setWidth(ctx.getWindowManager().getDefaultDisplay().getWidth()/3)
   fmenu.setHeight(ctx.getWindowManager().getDefaultDisplay().getHeight())
   fmenu.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT))
   fmenu.showAtLocation(ctx.getWindow().getDecorView(),android.view.Gravity.LEFT | android.view.Gravity.TOP,94+ctx.getWindowManager().getDefaultDisplay().getWidth()/3,0)
	 }catch(err){
    	print("错误: "+err+".")
 	}
 }}))
  layout.addView(button) }
  
  if((tool_mode=="game")|(tool_mode=="replace")){
 var button=new android.widget.Button(ctx)
 button.setText("方块置换")
 button.setOnClickListener(new android.view.View.OnClickListener({
  onClick:function(viewarg)
  { 
	var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get()
  try{
   var flayout=new android.widget.LinearLayout(ctx)
   var fmenu=new android.widget.PopupWindow(flayout)
   fmenu.setFocusable(true)
   fmainMenu=fmenu
   flayout.setOrientation(1);
   if(pselected){
   var stitle=new android.widget.TextView(ctx);
   stitle.setTextSize(20)
   stitle.setText("被替换的方块ID");
   stitle.setLayoutParams(textParams)
   flayout.addView(stitle)
   var textidb=new android.widget.EditText(ctx)
	textidb.setText("0")
	flayout.addView(textidb);
	var stitle=new android.widget.TextView(ctx);
   stitle.setTextSize(20)
   stitle.setText("被替换的方块DATA");
   stitle.setLayoutParams(textParams)
   flayout.addView(stitle)
   var textdatab=new android.widget.EditText(ctx)
	textdatab.setText("0")
	flayout.addView(textdatab);
	var stitle=new android.widget.TextView(ctx);
   stitle.setTextSize(20)
   stitle.setText("替换为方块的ID");
   stitle.setLayoutParams(textParams)
   flayout.addView(stitle)
   var textida=new android.widget.EditText(ctx)
	textida.setText("0")
	flayout.addView(textida);
	var stitle=new android.widget.TextView(ctx);
   stitle.setTextSize(20)
   stitle.setText("替换为方块的DATA");
   stitle.setLayoutParams(textParams)
   flayout.addView(stitle)
   var textdataa=new android.widget.EditText(ctx)
	textdataa.setText("0")
	flayout.addView(textdataa);
	var checkboxd=new android.widget.CheckBox(ctx);
	checkboxd.setText("忽略被替换方块的Data值");
	flayout.addView(checkboxd);
	var checkboxs=new android.widget.CheckBox(ctx);
	checkboxs.setText("方块保留模式");
	flayout.addView(checkboxs);
	}
	var button=new android.widget.Button(ctx)
	button.setText("拾取坐标")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{
		print("请点击方块以确定坐标");
		tool_mode="setpositionr";
		pastepselected=false;
		fmenu.dismiss();
		menu.dismiss();
	}}))
	flayout.addView(button);
	if(pselected){
	var textpos=new android.widget.EditText(ctx)
	textpos.setText(positions[0]+","+positions[1]+","+positions[2]+","+positions[3]+","+positions[4]+","+positions[5])
	flayout.addView(textpos);
   var button=new android.widget.Button(ctx)
	button.setText("替换")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{
		mainbutton.setText("!");
		var newthread=new java.lang.Thread(new java.lang.Runnable({run: 
		function(){
			try{
			var inppositions=(textpos.getText()+"").split(",");
			inppositions=strarr2intarr(inppositions);
			if(inppositions.length==6){replace(inppositions[0],inppositions[1],inppositions[2],inppositions[3],inppositions[4],inppositions[5],Number(textidb.getText()+""),Number(textdatab.getText()+""),Number(textida.getText()+""),Number(textdataa.getText()+""),checkboxd.isChecked(),checkboxs.isChecked());}else{
			replace(positions[0],positions[1],positions[2],positions[3],positions[4],positions[5],Number(textidb.getText()+""),Number(textdatab.getText()+""),Number(textida.getText()+""),Number(textdataa.getText()+""),checkboxd.isChecked(),checkboxs.isChecked());}
			}catch(err){clientMessage(err);}
		}}));
		newthread.start();
		pselected=false;
		tool_mode="game";
		fmenu.dismiss();
		menu.dismiss();
	}}))
	flayout.addView(button);}
	var button=new android.widget.Button(ctx)
	button.setText("取消")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{
		tool_mode="game";
		pselected=false;
		fmenu.dismiss();
		menu.dismiss();
	}}))
	flayout.addView(button);
	var mlayout=makeMenu(ctx,fmenu,flayout)
   fmenu.setContentView(mlayout)
   fmenu.setWidth(ctx.getWindowManager().getDefaultDisplay().getWidth()/3)
   fmenu.setHeight(ctx.getWindowManager().getDefaultDisplay().getHeight())
   fmenu.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT))
   fmenu.showAtLocation(ctx.getWindow().getDecorView(),android.view.Gravity.LEFT | android.view.Gravity.TOP,94+ctx.getWindowManager().getDefaultDisplay().getWidth()/3,0)
	 }catch(err){
    	print("错误: "+err+".")
 	}
 }}))
  layout.addView(button) }
  
 if((tool_mode=="game")|(tool_mode=="copy")){
  var button=new android.widget.Button(ctx)
 button.setText("方块复制")
 button.setOnClickListener(new android.view.View.OnClickListener({
  onClick:function(viewarg)
  {
	
	  var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get()
  try{
   var flayout=new android.widget.LinearLayout(ctx)
   var fmenu=new android.widget.PopupWindow(flayout)
   fmenu.setFocusable(true)
   fmainMenu=fmenu
   flayout.setOrientation(1);
	var button=new android.widget.Button(ctx)
	button.setText("拾取坐标")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{
		print("请点击方块以确定坐标");
		tool_mode="setpositionc";
		pastepselected=false;
		fmenu.dismiss();
		menu.dismiss();
	}}))
	flayout.addView(button);
	if(pselected){
	var textpos=new android.widget.EditText(ctx)
	textpos.setText(positions[0]+","+positions[1]+","+positions[2]+","+positions[3]+","+positions[4]+","+positions[5])
	flayout.addView(textpos);
   var button=new android.widget.Button(ctx)
	button.setText("复制")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{
		var inppositions=(textpos.getText()+"").split(",");
		inppositions=strarr2intarr(inppositions);
		if(inppositions.length==6){copy(inppositions[0],inppositions[1],inppositions[2],inppositions[3],inppositions[4],inppositions[5]);}else{
		copy(positions[0],positions[1],positions[2],positions[3],positions[4],positions[5]);}
		
		pselected=false;
		tool_mode="game";
		print("复制完毕");
		fmenu.dismiss();
		menu.dismiss();
	}}))
	flayout.addView(button);}
	var button=new android.widget.Button(ctx)
	button.setText("取消")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{
		tool_mode="game";
		pselected=false;
		fmenu.dismiss();
		menu.dismiss();
	}}))
	flayout.addView(button);
	var mlayout=makeMenu(ctx,fmenu,flayout)
   fmenu.setContentView(mlayout)
   fmenu.setWidth(ctx.getWindowManager().getDefaultDisplay().getWidth()/3)
   fmenu.setHeight(ctx.getWindowManager().getDefaultDisplay().getHeight())
   fmenu.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT))
   fmenu.showAtLocation(ctx.getWindow().getDecorView(),android.view.Gravity.LEFT | android.view.Gravity.TOP,94+ctx.getWindowManager().getDefaultDisplay().getWidth()/3,0)
	 }catch(err){
    	print("错误: "+err+".")
 	}
	  
 }}))
 layout.addView(button);}
 if(whethercopied&((tool_mode=="game")|(tool_mode=="setpastep")|(tool_mode=="paste"))){
  var button=new android.widget.Button(ctx)
 button.setText("方块粘贴")
 button.setOnClickListener(new android.view.View.OnClickListener({
  onClick:function(viewarg)
  { 
	var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get()
  try{
   var flayout=new android.widget.LinearLayout(ctx)
   var fmenu=new android.widget.PopupWindow(flayout)
   fmenu.setFocusable(true)
   fmainMenu=fmenu
   flayout.setOrientation(1);
   
   var button=new android.widget.Button(ctx)
	button.setText("拾取坐标")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{
		print("请点击方块以确定坐标");
		clearpreview();
		tool_mode="setpastep";
		fmenu.dismiss();
		menu.dismiss();
	}}))
	flayout.addView(button);
	if(pastepselected){
	var button=new android.widget.Button(ctx)
	button.setText("切换预览")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{
		if(pastemode=="diagonal_1"){pastemode="diagonal_2";}
		else if(pastemode=="diagonal_2"){pastemode="diagonal_3";}
		else if(pastemode=="diagonal_3"){pastemode="diagonal_4";}
		else if(pastemode=="diagonal_4"){pastemode="top_center";}
		else if(pastemode=="top_center"){pastemode="buttom_center";}
		else if(pastemode=="buttom_center"){pastemode="buttom_side_center_1";}
		else if(pastemode=="buttom_side_center_1"){pastemode="buttom_side_center_2";}
		else if(pastemode=="buttom_side_center_2"){pastemode="buttom_side_center_3";}
		else if(pastemode=="buttom_side_center_3"){pastemode="buttom_side_center_4";}
		else if(pastemode=="buttom_side_center_4"){pastemode="diagonal_1";}
		
		clearpreview();
		print(pastemode);
		paste(paste2position[0],paste2position[1],paste2position[2],pastemode,"preview")
	}}))
	flayout.addView(button);
	var checkbox=new android.widget.CheckBox(ctx);
	checkbox.setText("忽略空方块");
	flayout.addView(checkbox);
	var checkboxp=new android.widget.CheckBox(ctx);
	checkboxp.setText("保护附着性方块");
	checkboxp.setChecked(true);
	flayout.addView(checkboxp);
	var button=new android.widget.Button(ctx)
	button.setText("粘贴")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{
		if(checkbox.isChecked()){var bm="clearempty";}else{var bm="none";}
		clearpreview();
		mainbutton.setText("!");
		var twicepaste=checkboxp.isChecked();
		var newthread=new java.lang.Thread(new java.lang.Runnable({run: function(){
		if(twicepaste){paste(paste2position[0],paste2position[1],paste2position[2],pastemode,bm);}
		paste(paste2position[0],paste2position[1],paste2position[2],pastemode,bm);
		pastepselected=false;
		var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get() 
	ctx.runOnUiThread(new java.lang.Runnable({ 
	run: function(){
	mainbutton.setText("~");
	}}))
	print("粘贴成功");
		}}));
		tool_mode="game";
		newthread.start();
		var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get() 
		ctx.runOnUiThread(new java.lang.Runnable({ 
		run: function(){
			fmenu.dismiss();
			menu.dismiss();
		}}))
	}}))
	flayout.addView(button);
	}
	
   var button=new android.widget.Button(ctx)
	button.setText("取消")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{
		pastepselected=false;
		tool_mode="game";
		clearpreview();
		fmenu.dismiss();
		menu.dismiss();
	}}))
	flayout.addView(button);
   var mlayout=makeMenu(ctx,fmenu,flayout)
   fmenu.setContentView(mlayout)
   fmenu.setWidth(ctx.getWindowManager().getDefaultDisplay().getWidth()/3)
   fmenu.setHeight(ctx.getWindowManager().getDefaultDisplay().getHeight())
   fmenu.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT))
   fmenu.showAtLocation(ctx.getWindow().getDecorView(),android.view.Gravity.LEFT | android.view.Gravity.TOP,94+ctx.getWindowManager().getDefaultDisplay().getWidth()/3,0)
	 }catch(err){
    	print("错误: "+err+".")
 	}
 }}))
 layout.addView(button);}
}
if((tool_mode=="game")|(tool_mode=="outgame")){
   var button=new android.widget.Button(ctx)
 button.setText("导入方块")
 button.setOnClickListener(new android.view.View.OnClickListener({
  onClick:function(viewarg)
  { 
  var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get() 
var ad=android.app.AlertDialog.Builder
ctx.runOnUiThread(new java.lang.Runnable({ 
run: function(){ 
try{
var d=new ad(ctx)
d.setItems(folderview("/sdcard/games/salimterryli/blockbuildtool/")[1],new android.content.DialogInterface.OnClickListener(){
onClick: function(dia,w){
	files=folderview("/sdcard/games/salimterryli/blockbuildtool/")[1];
	mainbutton.setText("!");
	print("开始操作，请不要退出");
	var newthread=new java.lang.Thread(new java.lang.Runnable({run: function(){importfile("/sdcard/games/salimterryli/blockbuildtool/"+files[w]);}}));
	newthread.start();
	menu.dismiss();
}})
d.setNegativeButton("取消",new android.content.DialogInterface.OnClickListener(){
onClick: function(dia,w){
}})
d.setTitle("选择文件")
d.show()
}catch(e){print(e)}
}}))
  
 }}))
  layout.addView(button) }
if(whethercopied)
{
   var button=new android.widget.Button(ctx)
 button.setText("导出方块")
 button.setOnClickListener(new android.view.View.OnClickListener({
  onClick:function(viewarg)
  { 
	var ctx=com.mojang.minecraftpe.MainActivity.currentMainActivity.get()
  try{
   var flayout=new android.widget.LinearLayout(ctx)
   var fmenu=new android.widget.PopupWindow(flayout)
   fmenu.setFocusable(true)
   fmainMenu=fmenu
   flayout.setOrientation(1);
   var stitle=new android.widget.TextView(ctx);
   stitle.setTextSize(20)
   stitle.setText("命名");
   stitle.setLayoutParams(textParams)
   flayout.addView(stitle)
   var text=new android.widget.EditText(ctx)
	text.setText("new_block_array")
	flayout.addView(text);
   var button=new android.widget.Button(ctx)
	button.setText("确定")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{
		shell("mkdir -p /sdcard/games/salimterryli/blockbuildtool");
		if((shell("cd /sdcard/games/salimterryli/blockbuildtool")==""))
		{
			mainbutton.setText("!");
			print("开始操作，请不要退出");
			var newthread=new java.lang.Thread(new java.lang.Runnable({run: function(){exportfile("/sdcard/games/salimterryli/blockbuildtool/"+text.getText()+".bbt");}}));
			newthread.start();
		}
		fmenu.dismiss();
	}}))
	flayout.addView(button);
	var button=new android.widget.Button(ctx)
	button.setText("取消")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{ 
		fmenu.dismiss();
	}}))
	flayout.addView(button);
	var mlayout=makeMenu(ctx,fmenu,flayout)
   fmenu.setContentView(mlayout)
   fmenu.setWidth(ctx.getWindowManager().getDefaultDisplay().getWidth()/3)
   fmenu.setHeight(ctx.getWindowManager().getDefaultDisplay().getHeight())
   fmenu.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT))
   fmenu.showAtLocation(ctx.getWindow().getDecorView(),android.view.Gravity.LEFT | android.view.Gravity.TOP,94+ctx.getWindowManager().getDefaultDisplay().getWidth()/3,0)
	 }catch(err){
    	print("错误: "+err+".")
 	}
 }}))
 layout.addView(button)
 }
 if((tool_mode!="game")&(tool_mode!="outgame"))
 {
	if((tool_mode=="setposition")|(tool_mode=="setpositionc")|(tool_mode=="setpositionr")|(tool_mode=="setpastep"))
	{
		var button=new android.widget.Button(ctx)
		button.setText("获取脚下坐标")
		button.setOnClickListener(new android.view.View.OnClickListener({
		onClick:function(viewarg)
		{
			if((tool_mode=="setposition")|(tool_mode=="setpositionc")|(tool_mode=="setpositionr"))
			{
				if(!fpoint)
				{
					positions[0]=Math.floor(Player.getX());
					positions[1]=Math.floor(Player.getY())-2;
					positions[2]=Math.floor(Player.getZ());
					fpoint=true;
					print("首坐标已确定");
				}
				else
				{
					positions[3]=Math.floor(Player.getX());
					positions[4]=Math.floor(Player.getY())-2;
					positions[5]=Math.floor(Player.getZ());
					fpoint=false;
					print("次坐标已确定，请打开工具菜单");
					pselected=true;
					if(tool_mode=="setposition"){tool_mode="build";}else if(tool_mode=="setpositionc"){tool_mode="copy";}else if(tool_mode=="setpositionr"){tool_mode="replace";}
					menu.dismiss();
				}
			}
			else if(tool_mode=="setpastep")
			{
				paste2position[0]=Math.floor(Player.getX());
				paste2position[1]=Math.floor(Player.getY())-2;
				paste2position[2]=Math.floor(Player.getZ());
				pastepselected=true;
				paste(x,y,z,pastemode,"preview");
				tool_mode="paste";
				print("坐标已确定，请打开工具菜单");
				menu.dismiss();
			}
		}}))
		layout.addView(button);
	}
	
	var button=new android.widget.Button(ctx)
	button.setText("返回游戏")
	button.setOnClickListener(new android.view.View.OnClickListener({
	onClick:function(viewarg)
 	{
		fpoint=false;pselected=false;
		pastepselected=false;
		clearpreview();
		
		tool_mode="game";
		menu.dismiss();
	}}))
	layout.addView(button);
 }
   var mlayout=makeMenu(ctx,menu,layout)
   menu.setContentView(mlayout)
   menu.setWidth(ctx.getWindowManager().getDefaultDisplay().getWidth()/3)
   menu.setHeight(ctx.getWindowManager().getDefaultDisplay().getHeight())
   menu.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT))
   menu.showAtLocation(ctx.getWindow().getDecorView(),android.view.Gravity.LEFT | android.view.Gravity.TOP,94,0)
 }catch(err){
    print("错误: "+err+".")
 }
}
function makeMenu(ctx,menu,layout){
  var mlayout=new android.widget.RelativeLayout(ctx)
 var svParams=new android.widget.RelativeLayout.LayoutParams(android.widget.RelativeLayout.LayoutParams.FILL_PARENT,android.widget.RelativeLayout.LayoutParams.FILL_PARENT)
var scrollview=new android.widget.ScrollView(ctx)
 var pad = dip2px(ctx,5)
 scrollview.setPadding(pad,pad,pad,pad)
 scrollview.setLayoutParams(svParams)
 scrollview.addView(layout)
 mlayout.addView(scrollview)
 return mlayout
}
//GUI
