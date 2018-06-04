package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import common.DataProcessing;
import common.DocClient;
import common.NowUser;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class selfFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtA_1;
	private JTextField txtA;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					selfFrame frame = new selfFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public selfFrame() {
		this.setResizable(false);
		setTitle("\u4E2A\u4EBA\u4FE1\u606F\u4FEE\u6539");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 450);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setBounds(66, 41, 92, 31);
		label.setFont(new Font("微软雅黑", Font.BOLD, 23));
		panel.add(label);
		
		txtA_1 = new JTextField();
		txtA_1.setEditable(false);
		txtA_1.setText(NowUser.password);
		txtA_1.setBounds(215, 93, 86, 24);
		txtA_1.setColumns(10);
		panel.add(txtA_1);
		
		txtA = new JTextField();
		txtA.setText(NowUser.name);
		txtA.setBounds(215, 49, 86, 24);
		txtA.setColumns(10);
		panel.add(txtA);
		
		JLabel label_1 = new JLabel("\u539F\u5BC6\u7801\uFF1A");
		label_1.setBounds(66, 85, 92, 31);
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 23));
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		label_2.setBounds(66, 129, 92, 31);
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 23));
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u786E\u8BA4\u65B0\u5BC6\u7801\uFF1A");
		label_3.setBounds(66, 173, 138, 31);
		label_3.setFont(new Font("微软雅黑", Font.BOLD, 23));
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("\u89D2\u8272\uFF1A");
		label_4.setBounds(66, 217, 69, 31);
		label_4.setFont(new Font("微软雅黑", Font.BOLD, 23));
		panel.add(label_4);
		
		JButton button = new JButton("\u4FEE\u6539");
		
		button.setBounds(66, 284, 98, 46);
		button.setFont(new Font("新宋体", Font.BOLD, 20));
		panel.add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		
		button_1.setBounds(203, 284, 98, 46);
		button_1.setFont(new Font("新宋体", Font.BOLD, 20));
		panel.add(button_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(215, 137, 86, 24);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		
		passwordField_1.setBounds(215, 181, 86, 24);
		panel.add(passwordField_1);
		
		final JComboBox comboBox = new JComboBox();
		
		final DefaultComboBoxModel modelb;
		comboBox.setModel(modelb=new DefaultComboBoxModel(new String[]{"administrator","browser","operator"}));
		modelb.setSelectedItem(NowUser.role);
		comboBox.setBounds(168, 225, 133, 24);
		panel.add(comboBox);
		
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				txtA.getDocument().addDocumentListener(new DocumentListener(){
//					public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
//					      NowUser.name = txtA.getText().trim();//trim()方法用于去掉你可能误输入的空格号
//					      
//					       }
//
//					@Override
//					public void insertUpdate(DocumentEvent e) {
//						// TODO Auto-generated method stub
//						
//					}
//
//					@Override
//					public void removeUpdate(DocumentEvent e) {
//						// TODO Auto-generated method stub
//						
//					}
//					
//				});
				 NowUser.name = txtA.getText().trim();
				 NowUser.role = comboBox.getSelectedItem().toString();
//				comboBox.addItemListener(new ItemListener() {
//					public void itemStateChanged(ItemEvent e) {
//						NowUser.role = comboBox.getSelectedItem().toString();
//					}
//				});
				
				
			if(String.valueOf(passwordField_1.getPassword()).equals("")&&String.valueOf(passwordField.getPassword()).equals(""))
			{
				
				String b;
				try {
					b = DocClient.updateUser(NowUser.name,NowUser.password,NowUser.role);
					if ( b.equals("true")) {
					//user.showFrame(LoginFrame.this);//打开用户方法
					JOptionPane.showMessageDialog(getComponent(0), "修改成功");
					
				} else {
					JOptionPane.showMessageDialog(selfFrame.this, "修改用户失败", "错误", JOptionPane.ERROR_MESSAGE);// 弹出出错信息提示框
				}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			 
//				catch (SQLException ex) {
//				JOptionPane.showMessageDialog(selfFrame.this, "数据库错误" + ex.getMessage(), "错误",
//						JOptionPane.ERROR_MESSAGE); // 弹出出错信息提示框
//			}
				
			
			}
			else if(String.valueOf(passwordField.getPassword()).equals(String.valueOf(passwordField_1.getPassword())))
						{
					NowUser.password=String.valueOf(passwordField_1.getPassword());
					
						String b;
						try {
							b = DocClient.updateUser(NowUser.name,NowUser.password,NowUser.role);
							if ( b.equals("true")) {
							//user.showFrame(LoginFrame.this);//打开用户方法
							JOptionPane.showMessageDialog(getComponent(0), "修改成功");
							
						} else {
							JOptionPane.showMessageDialog(selfFrame.this, "修改用户失败", "错误", JOptionPane.ERROR_MESSAGE);// 弹出出错信息提示框
						}
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					
//					catch (SQLException ex) {
//						JOptionPane.showMessageDialog(selfFrame.this, "数据库错误" + ex.getMessage(), "错误",
//								JOptionPane.ERROR_MESSAGE); // 弹出出错信息提示框
//					}
							}
			else{
				JOptionPane.showMessageDialog(selfFrame.this, "两次输入不同", "错误", JOptionPane.ERROR_MESSAGE);
				
			}
				
			      
						
						
				
					
		
				
				
			}
		});
		
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelb.setSelectedItem(NowUser.role);
				txtA.setText(NowUser.name);
				txtA_1.setText(NowUser.password);
				
			}
		});
		
		
		
		
		
		
	}
}
