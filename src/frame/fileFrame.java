package frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import common.*;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class fileFrame extends JFrame {

	private JPanel contentPane;
	private JTable table_1;
	private JTextField textField;
	private File file;
	private File upfile;
	JTabbedPane tabbedPane = new JTabbedPane();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fileFrame frame = new fileFrame();
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
	public fileFrame() {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		tabbedPane.setFont(new Font("微软雅黑", Font.BOLD, 18));
		contentPane.add(tabbedPane);
		
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("\u6587\u4EF6\u4E0A\u4F20", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton = new JButton("\u4E0A\u4F20");
		
		panel_2.add(btnNewButton);
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		JButton btnNewButton_1 = new JButton("\u53D6\u6D88");
		panel_2.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(null);
		
		JLabel label = new JLabel("\u6587\u4EF6\uFF1A");
		label.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label.setBounds(133, 33, 104, 47);
		panel_4.add(label);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(233, 47, 143, 24);
		panel_4.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u63CF\u8FF0\uFF1A");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		label_1.setBounds(133, 93, 104, 47);
		panel_4.add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(233, 114, 193, 84);
		panel_4.add(scrollPane);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setRows(9);
		scrollPane.setViewportView(textArea);
		textArea.setBackground(Color.WHITE);
		
		JButton btnNewButton_2 = new JButton("...");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(DataProcessing.download);
				
				FileNameExtensionFilter filter1 = new FileNameExtensionFilter("JPG & GIF images", "jpg","gif");
				FileNameExtensionFilter filter2 = new FileNameExtensionFilter("JAVA & CLASS", "java","class");
				FileNameExtensionFilter filter3 = new FileNameExtensionFilter("TXT & DOC", "txt","doc");
				
				chooser.setFileFilter(filter1);
				chooser.setFileFilter(filter3);
				chooser.setFileFilter(filter2);
				chooser.setMultiSelectionEnabled(false);//设置不可多选
				
//				chooser.showSaveDialog(new JFrame());//以新的对话框的形式打开
				chooser.setDialogTitle("上传文件");     //自定义选择框标题
				
				int result = chooser.showOpenDialog(chooser);
				if(result == JFileChooser.APPROVE_OPTION){
					
					upfile = chooser.getSelectedFile();
					textField.setText(upfile.getName());
					
				

				}
				
				
				
				
				
			}
		});
		btnNewButton_2.setBounds(382, 46, 44, 27);
		panel_4.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\u6587\u4EF6\u4E0B\u8F7D", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		
		scrollPane_1.setViewportView(table_1);
		final DefaultTableModel model;
		table_1.setModel(model=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u6863\u6848\u53F7", "\u521B\u5EFA\u8005", "\u65F6\u95F4", "\u6587\u4EF6\u540D", "\u63CF\u8FF0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setPreferredWidth(55);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(65);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(85);
		
		Enumeration<Doc> e;
		Doc doc;
		try {
			e = DocClient.getAllDocs();
			while(e.hasMoreElements())
		{
			doc=e.nextElement();
			model.addRow(new Object[]{doc.getID(),doc.getCreator(),doc.getTimestamp(),doc.getFilename(),doc.getDdescription()});			
		}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i=0;i<15;i++)
			model.addRow(new Object[]{null, null, null, null, null});
		
		
		
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.SOUTH);
		
		final JButton button = new JButton("\u4E0B\u8F7D");
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFileChooser chooser = new JFileChooser(DataProcessing.download);
				
				FileNameExtensionFilter filter1 = new FileNameExtensionFilter("JPG & GIF images", "jpg","gif");
				FileNameExtensionFilter filter2 = new FileNameExtensionFilter("JAVA & CLASS", "java","class");
				FileNameExtensionFilter filter3 = new FileNameExtensionFilter("TXT & DOC", "txt","doc");
				
				chooser.setFileFilter(filter1);
				chooser.setFileFilter(filter3);
				chooser.setFileFilter(filter2);
				chooser.setMultiSelectionEnabled(false);//设置不可多选
				
//				chooser.showSaveDialog(new JFrame());//以新的对话框的形式打开
				chooser.setDialogTitle("保存文件");     //自定义选择框标题
				
				
				String Filename=(String) table_1.getValueAt(table_1.getSelectedRow(), 3);
				
				
				chooser.setSelectedFile(new File(Filename)); //设置默认文件名
				int result = chooser.showOpenDialog(chooser);
				if(result == JFileChooser.APPROVE_OPTION){
					
					file = chooser.getSelectedFile();
					boolean b=DocClient.download(Filename,file.getPath());
					if(b==true)JOptionPane.showMessageDialog(getComponent(0), "文件下载成功");
					else JOptionPane.showMessageDialog(fileFrame.this, "文件下载失败", "错误", JOptionPane.ERROR_MESSAGE);// 弹出出错信息提示框
					
				

				}
				
				
				
			}
		});
			}
		});
		
		panel_3.add(button);
		button.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		JButton button_1 = new JButton("\u5237\u65B0");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model.setRowCount(0);
	            table_1.setModel(model);
				
				Enumeration<Doc> e1;
				Doc doc;
				try {
					e1 = DocClient.getAllDocs();
					while(e1.hasMoreElements())
				{
					doc=e1.nextElement();
					model.addRow(new Object[]{doc.getID(),doc.getCreator(),doc.getTimestamp(),doc.getFilename(),doc.getDdescription()});			
				}
				} catch (SQLException e11) {
					// TODO Auto-generated catch block
					e11.printStackTrace();
				}
				for(int i=0;i<15;i++)
					model.addRow(new Object[]{null, null, null, null, null});
			}
		});
		panel_3.add(button_1);
		button_1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a=Integer.parseInt(NowUser.docCount);
				a++;
				String id=String.valueOf(a);
//				JOptionPane.showMessageDialog(getComponent(0), id);
				
//				String id=NowUser.docCount+"1";
				
				boolean b=DocClient.upload(upfile.getPath(),textArea.getText(),NowUser.name,id,upfile.getName());
				if(b==true)JOptionPane.showMessageDialog(getComponent(0), "文件上传成功");
				else JOptionPane.showMessageDialog(fileFrame.this, "文件上传失败", "错误", JOptionPane.ERROR_MESSAGE);// 弹出出错信息提示框
				
			}
		});
		
	
		
		
	}
	public void setindex(int i){
		tabbedPane.setSelectedIndex(i);
	}
}
