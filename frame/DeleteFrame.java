package frame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;

public class DeleteFrame{

	private String id;
	private String user;
	
	public DeleteFrame(String id , String user){
		
		this.id = id;
		this.user = user;
		delete();
	}

	public void delete() {
		
		if(find(user) == true){
			try {
				FileReader fread = new FileReader("/C:/Mtest/ok/"  + this.id + ".txt/");
				BufferedReader bread = new BufferedReader(fread);
				
				File file = new File("/C:/Mtest/ok/imsi.txt");
				PrintWriter pw = new PrintWriter(file);
					
				while(true){
					String data = bread.readLine();
					if(!data.equals(user)){
						BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
						bw.write(data + "\n");
						bw.close();
					}
				}
			} catch (Exception e1) {		}
			try {
				FileInputStream inputStream = new FileInputStream("/C:/Mtest/ok/imsi.txt");
				FileOutputStream outputStream = new FileOutputStream("/C:/Mtest/ok/" + id + ".txt/");
					 
				FileChannel fcin =  inputStream.getChannel();
				FileChannel fcout = outputStream.getChannel();
					 
				long size = fcin.size();
				fcin.transferTo(0, size, fcout);
					 
				fcout.close();
				fcin.close();
					 
				outputStream.close();
				inputStream.close();
					
				File f = new File("/C:/Mtest/ok/imsi.txt");
				f.delete();
			} catch (Exception e1) {		}         	
		}
	}
	
	public boolean find(String id){
	
		boolean flag = false;
		id = this.id;
		
		try {
			FileReader fread = new FileReader("/C:/Mtest/ok/User.txt");
			BufferedReader bread = new BufferedReader(fread);
			
			FileReader ffread = new FileReader("/C:/Mtest/ok/" + id + ".txt");
			BufferedReader fbread = new BufferedReader(ffread);
			
			while(true){
				String data = bread.readLine();
	
				if(data.equals(user)){
					while(true){
						if(fbread.readLine().equals(user)){
							flag = true;
							break;
						}
					}
				}
				data = bread.readLine();//Ȧ����°
			}
		}catch(Exception ex){	}
		
		return flag;
	}
}
