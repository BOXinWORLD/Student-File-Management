package common;
import java.util.*;
import java.sql.*;


//public  class DataProcessing {
//	private static boolean connectToDB=false;
//	
//	static Hashtable<String, User> users;
	
//	static {
//		users = new Hashtable<String, User>();
//		users.put("jack", new Operator("jack","123","operator"));
//		users.put("rose", new Browser("rose","123","browser"));
//		users.put("kate", new Administrator("kate","123","administrator"));
//		Init();
//			
//		
//	
//	
//	}
//	
//	public static  void Init(){
//		// connect to database
//		
//		// update database connection status
//		if (Math.random()>0.2)
//			connectToDB = true;
//		else
//			connectToDB = false;
//		
//	}	
//	
//	public static Doc searchDoc(String ID) throws SQLException{
//		if(docs.containsKey(ID)){
//			Doc temp=docs.get(ID);
//			return temp;
//		}
//		return null;
//	}
//	public static Enumeration<Doc> getAllDocs() throws SQLException{
//		Enumeration<Doc> e=docs.elements();
//		return e;
//	}
//	public static boolean insertDoc(String ID,String creator,Timestamp timestamp,String description,String filename){
//		Doc doc;
//		if(docs.containsKey(ID))
//			return false;
//		else{
//			doc=new Doc(ID,creator,timestamp,description,filename);
//			docs.put(ID, doc);
//			return true;
//			
//		}
//	}
//	
//	public static User search(String name, String password) throws SQLException {
////		if ( !connectToDB ) 
////	        throw new SQLException( "Not Connected to Database" );
////		double ranValue=Math.random();
////		if (ranValue>0.5)
////			throw new SQLException( "Error in excecuting Query" );
//		
//		if (users.containsKey(name)) {
//			User temp =users.get(name);
//			if ((temp.getPassword()).equals(password))
//				return temp;
//		}
//		return null;
//	}
//	
//	public static Enumeration<User> getAllUser() throws SQLException{
// //		if ( !connectToDB ) 
// //	        throw new SQLException( "Not Connected to Database" );
// 		
// //		double ranValue=Math.random();
// //		if (ranValue>0.5)
// //			throw new SQLException( "Error in excecuting Query" );
//		
//		Enumeration<User> e  = users.elements();
//		return e;
//	}
//	
//	
//	
//	public static boolean update(String name, String password, String role) throws SQLException{
//		User user;
////		if ( !connectToDB ) 
////	        throw new SQLException( "Not Connected to Database" );
//		
////		double ranValue=Math.random();
////		if (ranValue>0.5)
////			throw new SQLException( "Error in excecuting Update" );
//		
//		if (users.containsKey(name)) {
//			if (role.equalsIgnoreCase("administrator"))
//				user = new Administrator(name,password, role);
//			else if (role.equalsIgnoreCase("operator"))
//				user = new Operator(name,password, role);
//			else
//				user = new Browser(name,password, role);
//			users.put(name, user);
//			return true;
//		}else
//			return false;
//	}
//	
//	public static boolean insert(String name, String password, String role) throws SQLException{
//		User user;
//		
////		if ( !connectToDB ) 
////	        throw new SQLException( "Not Connected to Database" );
//		
////		double ranValue=Math.random();
////		if (ranValue>0.5)
////			throw new SQLException( "Error in excecuting Insert" );
//		
//		if (users.containsKey(name))
//			return false;
//		else{
//			if (role.equalsIgnoreCase("administrator"))
//				user = new Administrator(name,password, role);
//			else if (role.equalsIgnoreCase("operator"))
//				user = new Operator(name,password, role);
//			else
//				user = new Browser(name,password, role);
//			users.put(name, user);
//			return true;
//		}
//	}
//	
//	public static boolean delete(String name) throws SQLException{
////		if ( !connectToDB ) 
////	        throw new SQLException( "Not Connected to Database" );
//		
////		double ranValue=Math.random();
////		if (ranValue>0.5)
////			throw new SQLException( "Error in excecuting Delete" );
//		
//		if (users.containsKey(name)){
//			users.remove(name);
//			return true;
//		}else
//			return false;
//		
//	}	
//            
//	public void disconnectFromDB() {
//		if ( connectToDB ){
//			// close Statement and Connection            
//			try{
////			    if (Math.random()>0.5)
////					throw new SQLException( "Error in disconnecting DB" );
////			}catch ( SQLException sqlException ){                                            
////			    sqlException.printStackTrace();           
//			}finally{                                            
//				connectToDB = false;              
//			}                             
//		} 
//   }           
//
//	
//	
//	
//}




public class DataProcessing {
	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement preparedstatement;
	private static ResultSet resultSet;
	private static boolean connectedToDatabase = false;
	public static String download = "F:\\Workspaces\\File Management\\downLoadfile\\";
	public static String upload = "F:\\Workspaces\\File Management\\upLoadfile\\";
	
	public static void connectToDatabase(String driverName,String url,String user,String password) throws SQLException, ClassNotFoundException{
		Class.forName(driverName);
		connection=DriverManager.getConnection(url, user, password);
		connectedToDatabase= true;
	}
	
	public static void disconnectFromDatabase(){
		if(connectedToDatabase){
			try{
				resultSet.close();
				statement.close();
				connection.close();
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				connectedToDatabase =true;
			}
		}
	}
	
	public static Doc searchDoc(String DocID)throws SQLException{
		Doc temp=null;
		if(!connectedToDatabase)
			throw new SQLException("Not Connected to DataBase");
		statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String sql="select * from doc_info where Id='"+DocID+"'";
		resultSet =statement.executeQuery(sql);
		
		if(resultSet.next()){
			String ID=resultSet.getString("Id");
			String creator=resultSet.getString("creator");
			Timestamp timestamp=resultSet.getTimestamp("timestamp");
			String filename=resultSet.getString("filename");
			String description=resultSet.getString("description");
			
			
			temp=new Doc(ID,creator,timestamp,description,filename);
		}
		return temp;
	}
	
	public static User searchUser(String username, String Userpassword) throws SQLException{

		User temp=null;
		
		statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String sql="select * from user_info where username='"+username+"' and password='"+Userpassword+"'";
//		String sql="select * from user_info";
		resultSet =statement.executeQuery(sql);
		
		if(resultSet.next())
		{
//			if(username.equalsIgnoreCase(resultSet.getString("username"))&&password.equalsIgnoreCase(resultSet.getString("password")))
//			{
			String Username=resultSet.getString("username");
			String Password=resultSet.getString("password");
			String Role=resultSet.getString("role");
			
			temp=new User(Username,Password,Role);
//			}
		}
		return temp;
	}
	

	
	

	public static  Enumeration<Doc> getAllDocs() throws SQLException{
          
		Hashtable<String, Doc> docs;
		
		docs=new Hashtable<String,Doc>();

		
		
		if(!connectedToDatabase)
			throw new SQLException("NCTD");
		statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String sql ="select * from doc_info";
		resultSet =statement.executeQuery(sql);
	    
	    while(resultSet.next()){
			String ID=resultSet.getString("Id");
			String creator=resultSet.getString("creator");
			Timestamp timestamp=resultSet.getTimestamp("timestamp");
			String filename=resultSet.getString("filename");
			String description=resultSet.getString("description");
			
			docs.put(ID, new Doc(ID,creator,timestamp,description,filename));
			
		}
	    Enumeration<Doc> e=docs.elements();
	return e;
	}
	
	public static Enumeration<User> getAllUser() throws SQLException{
		Hashtable<String, User> users;
		users = new Hashtable<String, User>();
		if ( !connectedToDatabase ) 
	        throw new SQLException( "Not Connected to Database" );
		String sql="select * from user_info";
		resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()){
			String username=resultSet.getString("username");
			String pwd=resultSet.getString("password");
			String role=resultSet.getString("role");
			
			users.put(username, new User(username,pwd,role));
				
		}	
		Enumeration<User> e=users.elements();

		return e;
	} // getAllUser()
	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{
		
		if ( !connectedToDatabase ) throw new SQLException( "Not Connected to Database" );
		
		String sql = "INSERT INTO doc_info(Id,creator,timestamp,description,filename) VALUES(?,?,?,?,?)";
		preparedstatement=connection.prepareStatement(sql);
		  preparedstatement.setString(1,ID);
		  preparedstatement.setString(2,creator);
		  preparedstatement.setTimestamp(3,timestamp);
		  preparedstatement.setString(4,description);
		  preparedstatement.setString(5,filename);
		  int temp = preparedstatement.executeUpdate();
			if(temp!=0){
				return true;
			}
			else
				return false;
			
	} //insertDoc
	
	public static boolean insertUser(String name, String password, String role) throws SQLException{
		if (!connectedToDatabase ) 
	        throw new SQLException( "Not Connected to Database" );
		
		String sql="INSERT INTO user_info(username,password,role) VALUES(?,?,?)";		
		preparedstatement=connection.prepareStatement(sql);
		  preparedstatement.setString(1,name);
		  preparedstatement.setString(2,password);
		  preparedstatement.setString(3,role);
		  
		  int temp = preparedstatement.executeUpdate();
		if(temp!=0){
			return true;
		}
		else
			return false;
	
	}//insertUse
	
	public static boolean deleteUser(String name) throws SQLException{
		if ( !connectedToDatabase ) 
	        throw new SQLException( "Not Connected to Database" );
		String sql="DELETE FROM user_info where username=?";
		
		preparedstatement=connection.prepareStatement(sql);
		  preparedstatement.setString(1, name);
		  int temp = preparedstatement.executeUpdate();
			if(temp!=0){
				return true;
			}
			else
				return false;
	}
	
	public static boolean updateUser(String name, String password, String role) throws SQLException{
		
		if ( !connectedToDatabase ) 
	        throw new SQLException( "Not Connected to Database" );
		String sql = "UPDATE user_info SET password=?,role=? where username=?";
		  preparedstatement=connection.prepareStatement(sql);
		  preparedstatement.setString(1,password);
		 preparedstatement.setString(2,role);
		 preparedstatement.setString(3,name);
		 int temp = preparedstatement.executeUpdate();
			if(temp!=0){
				return true;
			}
			else
				return false;
	}//updateUser

	

}
