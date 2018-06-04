package common;
import java.sql.SQLException;
import java.util.*;
import java.io.*;


public  class User /**implements java.io.Serializable**/{
	private String name;
	private String password;
	private String role;
	
	String uploadpath="f:\\workspace\\File Management\\uploadfile\\";
	String downloadpath="f:\\workspace\\File Management\\downloadfile\\";
	
	public User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeUserInfo(String password) throws SQLException{
		//写用户信息到存储
//		if (DataProcessing.update(name, password, role)){
//			this.password=password;
//			System.out.println("修改成功");
//			return true;
//		}else
			return false;
	}
	
	public boolean downloadFile(String ID) throws SQLException,IOException{
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new IOException( "Error in accessing file" );
		byte[] buf=new byte[1024];
		Doc doc=DataProcessing.searchDoc(ID);
		if(doc==null)
			return false;
		
		File tempfile=new File(uploadpath+doc.getFilename());
		String filename=tempfile.getName();
		
		BufferedInputStream infile=new BufferedInputStream(new FileInputStream(tempfile));
		BufferedOutputStream outfile=new BufferedOutputStream(new FileOutputStream(downloadpath+filename));
		
		while(true){
			int r=infile.read(buf);
			if(r==-1)
				break;
			outfile.write(buf, 0, r);
		}
		
		infile.close();
		outfile.close();
		return true;
	}
	
	public void showFileList() throws SQLException{
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in accessing file DB" );
//		Enumeration<Doc> e=DataProcessing.getAllDocs();
//		Doc doc;
//		while(e.hasMoreElements()){
//			doc=e.nextElement();
//			System.out.println("ID:"+doc.getID()
//					+"\t Creator:"+doc.getCreator()
//					+"\t Time:"+doc.getTimestamp()
//					+"\t Description:"+doc.getDdescription()
//					);
//		}
		
	}
	
//	public abstract void showMenu();
	
	public void exitSystem(){
		System.out.println("系统退出, 谢谢使用 ! ");
		System.exit(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public static int intin(){
		Scanner scan1 = new Scanner(System.in);
		int cmd = 0;
		try{
        	cmd=scan1.nextInt();
        }
        catch(InputMismatchException e){
        	System.out.println("输入错误,请重新输入：");intin();
        }
		return cmd;
	}

}
