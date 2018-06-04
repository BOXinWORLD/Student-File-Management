package common;

import java.awt.EventQueue;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import com.sun.awt.*;



import frame.loginFrame;

public class MainGUI {
	static ObjectOutputStream output; // output stream to server
	 static ObjectInputStream input; // input stream from server
	public static class Main {
		public static void main(String[] args)  {
			
			EventQueue.invokeLater(new Runnable(){
				public void run(){

					
					
					try {
						
//					    JFrame.setDefaultLookAndFeelDecorated(true);  
					    JFrame loginframe=new loginFrame();
					    
				        
				        /** …Ë÷√‘≤Ω« */  
//					    loginframe.setUndecorated(true);
//
//					    loginframe.addComponentListener(new ComponentAdapter()
//					    {
//					     
//					        public void componentResized(ComponentEvent e)
//					        {
//					        	loginframe.setShape(new RoundRectangle2D.Double(0d, 0d, loginframe.getWidth(), loginframe.getHeight(), 25d, 25d));
//					        }
//					    });
					    
					      
						loginframe.setVisible(true);
						loginframe.setLocationRelativeTo(null);
						
						
										
										
										
										
									}
									catch(Exception e){
										e.printStackTrace();
									}
					            
				
					
				}
			}
			);
			
			
	}

}
}
	
