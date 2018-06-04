package common;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import common.DataProcessing;
import common.User;


import java.awt.Font;

public class DocServer  
{
   private JTextArea displayArea; // display information to user
   private ObjectOutputStream output; // output stream to client
   private ObjectInputStream input; // input stream from client
   private ServerSocket server; // server socket
   private Socket connection; // connection to client
   private int counter = 1; // counter of number of connections
   
	
	
	
   
   
 


   // set up GUI
   public DocServer()
   {
//      super( "Server" );
//      this.setLocationRelativeTo(null);
//
//      displayArea = new JTextArea(); // create displayArea
//      displayArea.setFont(new Font("Courier New", Font.PLAIN, 18));
//      displayArea.setEditable(false);
//      getContentPane().add( new JScrollPane( displayArea ), BorderLayout.CENTER );
//
//      setSize( 302, 444 ); // set size of window
//      setVisible( true ); // show window
   } // end Server constructor

   // set up and run server 
   public void runServer(Socket s)
   {
	  
	   String driverName="com.mysql.jdbc.Driver";               // 加载数据库驱动类
	  	String url="jdbc:mysql://localhost:3306/document";       // 声明数据库的URL
	  	String user="root";                                      // 数据库用户
	  	String password="A1936580";
	      try {
			DataProcessing.connectToDatabase(driverName, url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      try // set up server to receive connections; process connections
      {
//         server = SocketServer; // create ServerSocket

         while ( true ) 
         {
            try 
            {
               waitForConnection(s); // wait for a connection
               getStreams(); // get input & output streams
               processConnection(); // process connection
            } // end try
            catch ( EOFException eofException ) 
            {
               displayMessage( "\nServer terminated connection" );
            } // end catch
            finally 
            {
               closeConnection(); //  close connection
               counter++;
            } // end finally
         } // end while
      } // end try
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } // end catch
   } // end method runServer

   // wait for connection to arrive, then display connection info
   private void waitForConnection(Socket s) throws IOException
   {
      displayMessage( "Waiting for connection\n" );
      connection = s; // allow server to accept connection            
      displayMessage( "Connection " + counter + " received from: " +
         connection.getInetAddress().getHostName() );
   } // end method waitForConnection

   // get streams to send and receive data
   private void getStreams() throws IOException
   {
      // set up output stream for objects
      output = new ObjectOutputStream( connection.getOutputStream() );
      output.flush(); // flush output buffer to send header information

      // set up input stream for objects
      input = new ObjectInputStream( connection.getInputStream() );

      displayMessage( "\nGot I/O streams\n" );
   } // end method getStreams

   // process connection with client
   private void processConnection() throws IOException
   {
      String message = "Connection successful";
//      sendData( message ); // send connection successful message

      // enable enterField so server user can send messages
//      setTextFieldEditable( true );

      do // process messages sent from client
      { 
         try // read message and display it
         {
            message = ( String ) input.readObject(); // read new message
            displayMessage( "\n" + message ); // display message
            
            
            
            
            
            
            if( message.equals( "LOGIN" ) )
            { 
  	        	
  			String name=(String) input.readObject();
  			displayMessage(" "+ name );
  			String pass=(String) input.readObject();
  			displayMessage( " "+ pass );
  			User temp = null;
  			try {
				temp=DataProcessing.searchUser(name, pass);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
  			if(temp!=null)
  			sendData(temp.getRole());
  			else sendData("NULL");
  			
            }
            
            
            if( message.equals( "UPDATE_USER" ) )
            { 
  	        	
  			String name=(String) input.readObject();
  			displayMessage(" "+ name );
  			String pass=(String) input.readObject();
  			displayMessage( " "+ pass );
  			String role=(String) input.readObject();
  			displayMessage( " "+ role );
  			boolean b=false;
  			try {
				b=DataProcessing.updateUser(name, pass,role);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			sendData(String.valueOf(b));
			
  			
            }
            
            
            
            if( message.equals( "GET_ALL_USER" ) )
            { 
            	Enumeration<User> e;
            	try {
					e = DataProcessing.getAllUser();
					User user;
					while(e.hasMoreElements())
		    		{
		    			user=e.nextElement();

		    			output.writeObject(" ");
		    			output.flush();
		    			output.writeObject(user.getName());
		    			output.flush();	
		    			output.writeObject(user.getPassword());
		    			output.flush();	
		    			output.writeObject(user.getRole());
		    			output.flush();	
		    		}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	sendData("DONE");
            }
            
            
            if( message.equals( "GET_ALL_DOCS" ) )
            { 
            	Enumeration<Doc> e;
            	try {
					e = DataProcessing.getAllDocs();
					Doc doc;
					while(e.hasMoreElements())
		    		{
		    			doc=e.nextElement();

		    			output.writeObject(" ");
		    			output.flush();
		    			output.writeObject(doc.getID());
		    			output.flush();	
		    			output.writeObject(doc.getCreator());
		    			output.flush();	
		    			output.writeObject(doc.getTimestamp());
		    			output.flush();	
		    			output.writeObject(doc.getFilename());
		    			output.flush();
		    			output.writeObject(doc.getDdescription());
		    			output.flush();
		    		}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	sendData("DONE");
            }
            
            
            
            if( message.equals( "DELETE_USER" ) )
            { 
  	        	
            	try {
					boolean b=DataProcessing.deleteUser((String) input.readObject());
					if(b==true){
						sendData("1");
					}
					else sendData("0");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  			
            }
            
            
            if( message.equals( "INSERT_USER" ) )
            { 
  	        	
            	try {
					boolean b=DataProcessing.insertUser((String) input.readObject(),(String) input.readObject(),(String) input.readObject());
					if(b==true){
						sendData("1");
					}
					else sendData("0");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  			
            }
            
            
            
            if(message.equals( "DOWNLOAD_DOC" ))
            {
            	 
                    // 选择进行传输的文件  
            		ServerSocket server2 = new ServerSocket( 1234, 100 );
            		Socket socket2 = server2.accept();
            		displayMessage("\n new file socket");
                    String name=(String) input.readObject();
                    
                    File fileRead = new File(DataProcessing.upload+name);
      
                    displayMessage("\nFile Name:" + name + "\nFile Size():" + fileRead.length() + "bytes");  

                    DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(DataProcessing.upload+name)));  
                      
                    DataOutputStream ps = new DataOutputStream(socket2.getOutputStream());  
                      
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
                    server2.close();
                    displayMessage("\n close file socket \n DONE");
  
                 
            }
  			
  			
         } // end try
         catch ( ClassNotFoundException classNotFoundException ) 
         {
            displayMessage( "\nUnknown object type received" );
         } // end catch
         
         
         if(message.equals( "UPLOAD_DOC" ))
         {
        	 
	             
	            try {
	            	ServerSocket server2 = new ServerSocket( 1234, 100 );
            		
	            	Socket client2 =server2.accept();
	            	String name=(String) input.readObject();
		        	 File fileWrite = new File(DataProcessing.upload+name);
	            	DataInputStream	inputStream = new DataInputStream(client2.getInputStream());
	            DataOutputStream fileOut = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileWrite)));  
	            
	            

		            byte[] buf = new byte[1024];  
		            int passedlen = 0;  
		            long len = 0;
	            
	            
	            try {
					len = Long.parseLong((String)input.readObject());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
	  
	            displayMessage("\n File Size()：" + len + "bytes");  
	            
	  
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
	                displayMessage("\n DO" + (passedlen * 100 / len) + "%\n");  
	                 
	            }
	            fileOut.close();
	            inputStream.close();
	            client2.close();
	            server2.close();
	            
	            
	            Timestamp t = new Timestamp(System.currentTimeMillis());
	            String d=(String)input.readObject();
	            String i=(String)input.readObject();
	            String n=(String)input.readObject();
	            
	            boolean o=DataProcessing.insertDoc(i, n, t, d, name);
	            if(o==true)sendData("DONE");
	            else sendData("WRONG");
	           
	            } catch (IOException | ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
	            
	            
	           
	            
	            
        	 
         }
         
         
         
         
         
         
         
         
         
         

      } while ( !message.equals( "CLIENT>>> TERMINATE" ) );
   } // end method processConnection

   // close streams and socket
   private void closeConnection() 
   {
      displayMessage( "\nTerminating connection\n" );
//      setTextFieldEditable( false ); // disable enterField

      try 
      {
         output.close(); // close output stream
         input.close(); // close input stream
         connection.close(); // close socket
      } // end try
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } // end catch
   } // end method closeConnection

   // send message to client
   private void sendData( String message )
   {
      try // send object to client
      {
         output.writeObject(message);
         output.flush(); // flush output to client
         displayMessage( "\nSERVER>>> " + message );
      } // end try
      catch ( IOException ioException ) 
      {
         displayArea.append( "\nError writing object" );
      } // end catch
   } // end method sendData

   // manipulates displayArea in the event-dispatch thread
   private void displayMessage( final String messageToDisplay )
   {
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            public void run() // updates displayArea
            {
               System.out.print( messageToDisplay ); // append message
            } // end method run
         } // end anonymous inner class
      ); // end call to SwingUtilities.invokeLater
   } // end method displayMessage

   // manipulates enterField in the event-dispatch thread
//   private void setTextFieldEditable( final boolean editable )
//   {
//      SwingUtilities.invokeLater(
//         new Runnable()
//         {
//            public void run() // sets enterField's editability
//            {
//               enterField.setEditable( editable );
//            } // end method run
//         }  // end inner class
//      ); // end call to SwingUtilities.invokeLater
//   } // end method setTextFieldEditable
} // end class Server



