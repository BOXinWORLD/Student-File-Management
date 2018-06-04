package common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class DocClient {
	
	   private static ObjectOutputStream output; // output stream to server
	   private static ObjectInputStream input; // input stream from server
	   private String message = ""; // message from server
	   private static String chatServer="127.0.0.1"; // host server for this application
	   private static Socket client; // socket to communicate with server
	
	
	
	
	
	
	   public static void runClient() 
	   {
	      try // connect to server, get streams, process connection
	      {
	         connectToServer(); // create a Socket to make connection
	         getStreams(); // get the input and output streams
//	         processConnection();
	         
	      } // end try
	      catch ( EOFException eofException ) 
	      {
	         displayMessage( "\nClient terminated connection" );
	      } // end catch
	      catch ( IOException ioException ) 
	      {
	         ioException.printStackTrace();
	      } // end catch
	      
	   } // end method runClient

	   // connect to server
	   public static void connectToServer() throws IOException
	   {      
	      displayMessage( "Attempting connection\n" );

	      // create Socket to make connection to server
	      client = new Socket( InetAddress.getByName( chatServer ), 12345 );

	      // display connection information
	      displayMessage( "Connected to: " + 
	         client.getInetAddress().getHostName() );
	   } // end method connectToServer

	   // get streams to send and receive data
	   public static void getStreams() throws IOException
	   {
	      // set up output stream for objects
	      output = new ObjectOutputStream( client.getOutputStream() );      
	      output.flush(); // flush output buffer to send header information

	      // set up input stream for objects
	      input = new ObjectInputStream( client.getInputStream() );

	      displayMessage( "\nGot I/O streams\n" );
	   } // end method getStreams

	   // process connection with server
	   private void processConnection() throws IOException
	   {
	      // enable enterField so client user can send messages
	      

	      do // process messages sent from server
	      { 
	         try // read message and display it
	         {
	            message = ( String ) input.readObject(); // read new message
	            displayMessage( "\n" + message ); // display message
	         } // end try
	         catch ( ClassNotFoundException classNotFoundException ) 
	         {
	            displayMessage( "\nUnknown object type received" );
	         } // end catch
	         

	      } while ( !message.equals( "SERVER>>> TERMINATE" ) );
	      
	      
	      
	      
	      
	      
	      
	      
	      while ( message.equals( "END" ) );
          {
             closeConnection(); //  close connection
             
          } // end finally
	   } // end method processConnection

	   // close streams and socket
	   private void closeConnection() 
	   {
	      displayMessage( "\nClosing connection" );
	      

	      try 
	      {
	         output.close(); // close output stream
	         input.close(); // close input stream
	         client.close(); // close socket
	      } // end try
	      catch ( IOException ioException ) 
	      {
	         ioException.printStackTrace();
	      } // end catch
	   } // end method closeConnection

	   // send message to server
	   public static void sendData( String message )
	   {
	      try // send object to server
	      {
	         output.writeObject(message);
	         output.flush(); // flush data to output
	         displayMessage( "\nCLIENT>>> " + message );
	      } // end try
	      catch ( IOException ioException )
	      {
	    	  ioException.printStackTrace();
	         
	      } // end catch
	   } // end method sendData

	   // manipulates displayArea in the event-dispatch thread
	   private static void displayMessage(String messageToDisplay )
	   {
	      System.out.println( messageToDisplay );
	           
	   } // end method displayMessage

	   // manipulates enterField in the event-dispatch thread
	   public  static String login(String n,String p) throws ClassNotFoundException, IOException{
		   sendData("LOGIN");
		   sendData(n);
		   sendData(p);
		   String s=(String) input.readObject();
		   
		   return s;
		   
		   
	   }
	   
	   public  static String updateUser(String n,String p,String r) throws ClassNotFoundException, IOException{
		   sendData("UPDATE_USER");
		   sendData(n);
		   sendData(p);
		   sendData(r);
		   
		   
		   return (String) input.readObject();
		   
		   
	   }
	   public static Enumeration<User> getAllUser() throws SQLException{
			Hashtable<String, User> users;
			users = new Hashtable<String, User>();
			sendData("GET_ALL_USER");
			
			
			try {
				
				while(!((String) input.readObject()).equalsIgnoreCase("DONE")){
					 
				
					String username=(String) input.readObject();
					String pwd=(String) input.readObject();
					String role=(String) input.readObject();
					
					users.put(username, new User(username,pwd,role));
					
					
					
				}
				
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Enumeration<User> e=users.elements();
			
			return e;
	   
			
			
			
		} // getAllUser()
	   
	   public static boolean deleteUser(String name) throws SQLException{
			
		   sendData("DELETE_USER");
		   sendData(name);
		   String b = null;
		try {
			b = (String) input.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				if(b.equalsIgnoreCase("1")){
					return true;
				}
				else
					return false;
		}
	   
		public static boolean insertUser(String name, String password, String role) throws SQLException{
			
			sendData("INSERT_USER");
			   sendData(name);
			   sendData(password);
			   sendData(role);
			   String b = null;
			try {
				b = (String) input.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					if(b.equalsIgnoreCase("1")){
						return true;
					}
					else
						return false;
		
		}//insertUse
		
		public static  Enumeration<Doc> getAllDocs() throws SQLException{
	          
			Hashtable<String, Doc> docs;
			
			docs=new Hashtable<String,Doc>();
			
			sendData("GET_ALL_DOCS");
			
			
			try {
				
				while(!((String) input.readObject()).equalsIgnoreCase("DONE")){
					 
				
					String i=(String) input.readObject();
					String c=(String) input.readObject();
					Timestamp t=(Timestamp) input.readObject();
					String f=(String) input.readObject();
					String d=(String) input.readObject();
					
					docs.put(i, new Doc(i,c,t,d,f));
					NowUser.docCount=i;
					
					
				}
				
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    Enumeration<Doc> e=docs.elements();
		return e;
		}
	   
		public static boolean download(String n, String p){
			
			sendData("DOWNLOAD_DOC");
			sendData(n);
			File fileWrite = new File(p);

		            byte[] buf = new byte[1024];  
		            int passedlen = 0;  
		            long len = 0;
		             
		            try {
		            	Socket client2 = new Socket( InetAddress.getByName( chatServer ), 1234 );
		            	DataInputStream	inputStream = new DataInputStream(client2.getInputStream());
		            DataOutputStream fileOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileWrite)));  
		            try {
						len = Long.parseLong((String)input.readObject());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
		  
		            displayMessage("File Size()：" + len + "bytes");  
		            displayMessage("开始接收文件!" + "\n");  
		  
		            while (true) {  
		                  
		                int read = 0;  
		                if (inputStream != null) {  
		                    read = inputStream.read(buf);  
		                }  
		                passedlen += read;  
		                if (read == -1) {  
		                    break;  
		                }  
		                // 下面进度条本为图形界面的prograssBar做的，这里如果是打文件，可能会重复打印出一些相同的百分比  
		                fileOut.write(buf, 0, read); 
		                displayMessage("文件接收了" + (passedlen * 100 / len) + "%\n");  
		                 
		            }
		            fileOut.close();
		            inputStream.close();
		            client2.close();
		            return true;
		            } catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}  
		            return false;
		  
		            
			
			
		}

		public static boolean upload(String path, String text, String name, String id,String filename) {
			
			sendData("UPLOAD_DOC");
			sendData(filename);
			
    		
			try {
				Socket socket2 = new Socket( InetAddress.getByName( chatServer ), 1234 );
			
    		displayMessage("\n new file socket");
            
            
            File fileRead = new File(path);

            displayMessage("\nFile Name:" + name + "\nFile Size():" + fileRead.length() + "bytes");  

           
			
            DataInputStream	fis = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
			  
           
			
            DataOutputStream	ps = new DataOutputStream(socket2.getOutputStream());
			
            // 将文件名及长度传给客户端。 

            
            String len=String.valueOf(fileRead.length());
            sendData(len);
            

            
            byte[] buf = new byte[1024];  

            while (true) {  
                  
                int read = 0;  
                if (fis != null) {  
                    read = fis.read(buf);  
                }  

                if (read == -1) {  
                    break;  
                }  
                ps.write(buf, 0, read);  
            }  
              
            ps.flush();  
            // 注意关闭socket链接哦，不然客户端会等待server的数据过来，  
            // 直到socket超时，导致数据不完整。  
            fis.close(); 
            socket2.close();
            displayMessage("\n close file socket \n DONE");
            } catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
			sendData(text);
			sendData(id);
			sendData(name);
			String o = null;
			try {
				o = (String) input.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(o.equalsIgnoreCase("DONE")) return true;
			else return false;

         
    
			
			
			
			// TODO Auto-generated method stub
			
		}

		
	   
//	   
//	   public static void main( String args[] )
//	   {
//		  DocClient start= new DocClient(); // create server
//	      start.runClient(); // run server application
//	      
//	   } // end main


}
