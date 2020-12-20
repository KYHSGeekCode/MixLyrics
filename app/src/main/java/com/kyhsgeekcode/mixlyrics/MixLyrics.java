package com.kyhsgeekcode.mixlyrics;

import android.util.*;
import java.io.*;
import java.nio.charset.*;

public class MixLyrics
{
	public static void Run(MainActivity mainActivity)
	{	
		String path="";
		File file=null;
		while(file==null)
		{
			try
			{
				mainActivity.print("Enter a valid file path");
				path = mainActivity.readLine();
				file=new File(path);
			}
			catch (InterruptedException e)
			{
				mainActivity.print(Log.getStackTraceString(e));
				return;
			}
		}
		try
		{
			int length=(int)file.length();
			FileInputStream fis=new FileInputStream(file);
			byte[] bytes=new byte[length];
			fis.read(bytes,0,length);
			fis.close();
			mainActivity.print("Enter a valid string encoding");
			String encoding = mainActivity.readLine();
			String total=new String(bytes,encoding);
			String sp=System.lineSeparator();
			sp+=sp;//2
			sp+=sp;//4
			String [] splitted=total.split(sp);
			mainActivity.print("langs="+splitted.length);
			String [] lines=splitted[0].split(System.lineSeparator());
			String [][] liness=new String[splitted.length][];
			for(int i=0;i<liness.length;++i)
			{
				liness[i]=splitted[i].split(System.lineSeparator());
			}
			FileOutputStream fos=new FileOutputStream(new File(file.getAbsolutePath()+"out.txt"));
			PrintWriter ps= new PrintWriter(new OutputStreamWriter( fos, StandardCharsets.UTF_8), true);
			
			for(int i=0;i<liness[0].length;++i)
			{
				for(int j=0;j<liness.length;++j)
				{
					ps.println(liness[j][i]);
				}
				ps.println("");
			}
			ps.close();
			fos.close();
		}
		catch (Exception e)
		{
			mainActivity.print(Log.getStackTraceString(e));
		}
		return ;
	}
}
