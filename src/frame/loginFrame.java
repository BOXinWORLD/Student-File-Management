package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

import javax.swing.JPasswordField;

import common.DataProcessing;
import common.DocClient;
import common.MainGUI;
import common.NowUser;
import common.User;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.KeyAdapter;

public class loginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	JLabel lblNewLabel_1 = new JLabel();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					loginFrame frame = new loginFrame();
//					frame.setVisible(true);
//					frame.setLocationRelativeTo(null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	class newpanel extends JPanel{
		public void paintComponent(Graphics g) {
			File f = new File("");//参数为空
			
			Image image=Toolkit.getDefaultToolkit().getImage( f.getAbsolutePath()+"//666.jpg"); 
		      super.paintComponent(g);
		      setBackground(Color.WHITE);
		      if (image != null) {
		         int height = image.getHeight(this);
		         int width = image.getWidth(this);
		 
		         if (height != -1 && height > getHeight())
		            height = getHeight();
		 
		         if (width != -1 && width > getWidth())
		            width = getWidth();
		 
		         int x = (int) (((double) (getWidth() - width)) / 2.0);
		         int y = (int) (((double) (getHeight() - height)) / 2.0);
		         g.drawImage(image, x, y, width, height, this);
		      }
		   }
			
		}
	
		
		
	
	public loginFrame() {
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		DocClient.runClient();
       
		

		
		setTitle("\u7CFB\u7EDF\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		
		
		contentPane = new newpanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("微软雅黑", Font.BOLD, 25));
		label.setBounds(73, 22, 121, 82);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("  \u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 25));
		label_1.setBounds(83, 90, 121, 82);
		contentPane.add(label_1);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.setFont(new Font("幼圆", Font.BOLD, 15));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
				NowUser.role = null;
						try {
							
							NowUser.role=DocClient.login(textField.getText(), String.valueOf(passwordField.getPassword()));
							
							
						} 
//						catch (SQLException ex) {
//							JOptionPane.showMessageDialog(loginFrame.this, "数据库错误" + ex.getMessage(), "错误",
//									JOptionPane.ERROR_MESSAGE); // 弹出出错信息提示框
//						}
						catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
							
						
						NowUser.name=textField.getText();
						NowUser.password=String.valueOf(passwordField.getPassword());
						if (NowUser.role.equalsIgnoreCase("administrator")||NowUser.role.equalsIgnoreCase("operator")||NowUser.role.equalsIgnoreCase("browser")) 
						{
							try {
								mainFrame frame1 = new mainFrame();
								frame1.setVisible(true);
								frame1.setLocationRelativeTo(null);
							} catch (Exception b) {
								b.printStackTrace();
							}
							setVisible(false);
						}else{
							JOptionPane.showMessageDialog(loginFrame.this, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);// 弹出出错信息提示框
				
				
				
				
						}
			}
		});
		button.setBounds(83, 185, 121, 52);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u6E05\u7A7A");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		button_1.setFont(new Font("幼圆", Font.BOLD, 15));
		button_1.setBounds(239, 185, 121, 52);
		contentPane.add(button_1);
		
		textField = new JTextField();
		textField.setBounds(216, 56, 143, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(216, 124, 143, 24);
		contentPane.add(passwordField);
		
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(184, 154, 122, 18);
		contentPane.add(lblNewLabel_1);
		

		
		
		
		
	}
}
