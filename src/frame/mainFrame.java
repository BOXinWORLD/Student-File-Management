package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.NowUser;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Color;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class mainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	JMenu mnNewMenu = new JMenu("\u7528\u6237\u7BA1\u7406");
	JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u6863\u6848\u4E0B\u8F7D");
	
	
	
	class newpanel extends JPanel{
		public void paintComponent(Graphics g) {
			File f = new File("");//参数为空
			
			Image image=Toolkit.getDefaultToolkit().getImage( f.getAbsolutePath()+"//667.jpg"); 
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


	/**
	 * Create the frame.
	 */
	public mainFrame() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 800);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		setJMenuBar(menuBar);
		
	
		
		mnNewMenu.setBackground(Color.LIGHT_GRAY);
		mnNewMenu.setFont(new Font("微软雅黑", Font.BOLD, 30));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u65B0\u589E\u7528\u6237");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					userFrame frame = new userFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setindex(1);
				} catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
		mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("\u5220\u9664\u7528\u6237");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					userFrame frame = new userFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setindex(2);
				} catch (Exception a) {
					a.printStackTrace();
				}
				
			}
		});
		mntmNewMenuItem_5.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		mnNewMenu.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u4FEE\u6539\u7528\u6237");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					userFrame frame = new userFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					;
				} catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("\u6863\u6848\u7BA1\u7406");
		mnNewMenu_1.setBackground(Color.LIGHT_GRAY);
		mnNewMenu_1.setFont(new Font("微软雅黑", Font.BOLD, 30));
		menuBar.add(mnNewMenu_1);
		
		
		
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fileFrame frame = new fileFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setindex(1);
				} catch (Exception a) {
					a.printStackTrace();
				}
				
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u6863\u6848\u4E0A\u4F20");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fileFrame frame = new fileFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					
				} catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
		mntmNewMenuItem_3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		mnNewMenu_2.setBackground(Color.LIGHT_GRAY);
		mnNewMenu_2.setFont(new Font("微软雅黑", Font.BOLD, 30));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("\u4FE1\u606F\u4FEE\u6539");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selfFrame frame = new selfFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception a) {
					a.printStackTrace();
				}
			}
		});
		mntmNewMenuItem_4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		mnNewMenu_2.add(mntmNewMenuItem_4);
		contentPane = new newpanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		if(NowUser.role.equalsIgnoreCase("administrator"))
			setTitle("档案管理员界面");
		if(NowUser.role.equalsIgnoreCase("browser")){
			setTitle("档案录入员界面");
		mnNewMenu.setEnabled(false);
		mntmNewMenuItem_2.setEnabled(false);
		}
		if(NowUser.role.equalsIgnoreCase("operator")){
			setTitle("档案浏览员界面");
		mnNewMenu.setEnabled(false);
		}
	}

}
