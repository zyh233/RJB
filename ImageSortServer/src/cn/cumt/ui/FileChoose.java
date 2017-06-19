package cn.cumt.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.ScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import cn.cumt.entity.Image;
import cn.cumt.service.ImageService;
import cn.cumt.service.ImageServiceImpl;

public class FileChoose {

	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;
	private File[] files = null;
	private List<Image> images = null;
	private ImageService service = null;

	/**
	 * Create the application.
	 */
	public FileChoose() {
		initialize();
	}

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("华文楷体", Font.PLAIN, 18));
		frame.setTitle("\u5BFC\u5165\u6570\u636E");
		frame.setBounds(100, 100, 480, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BF7\u9009\u62E9\u8981\u5BFC\u5165\u7684\u6587\u4EF6\uFF1A");
		label.setFont(new Font("华文楷体", Font.PLAIN, 18));
		label.setBounds(135, 29, 180, 38);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(51, 83, 180, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u9009\u62E9\u6587\u4EF6");
		button.setFont(new Font("华文楷体", Font.PLAIN, 18));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileDialog fileDialog=new FileDialog(frame, "选择文件");
				fileDialog.setVisible(true);
				String directory = fileDialog.getDirectory();
				files = fileDialog.getFiles();
				textField.setText(directory);
				for (File file : files) {
					String name = file.getName();
					textArea.setText(textArea.getText()+name+"\n");
				}
				
			}
		});
		button.setBounds(239, 83, 84, 29);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u5BFC\u5165\u6570\u636E");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FileInputStream fis = null;
				FileOutputStream fos = null;
				images = new ArrayList<>();
				service = new ImageServiceImpl();
				try {
					
					for (File file : files) {
						if(file.getName().endsWith(".jpg")||file.getName().endsWith(".png")){
							Image image = new Image(file.getName(), null, null);
							images.add(image);
							//./WebRoot/images
							File file2 = new File("F:/JAVA软件/Tomcat文件直接解压就好/apache-tomcat-8.5.5/webapps/ImageSortServer/images", file.getName());
							int len =0;
							byte[] buff = new byte[1024*4];
							fis = new FileInputStream(file);	
							fos = new FileOutputStream(file2);
							while ((len=fis.read(buff))!=-1) {
								fos.write(buff, 0, len);								
							}
						}
					}
					service.addImages(images);
					
				} catch (Exception e1) {
						e1.printStackTrace();
				}
				finally{
					try {
							if(fis!=null){
								fis.close();
							}
							if(fos!=null)
							{
								fos.close();
							}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null,"导入完成");
			}
		});
		button_1.setFont(new Font("华文楷体", Font.PLAIN, 18));
		button_1.setBounds(338, 83, 84, 29);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u8FD4\u56DE");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame frame1=new Index().getFrame();
				frame1.setLocation(730, 300);
				frame1.setVisible(true);
				frame.setVisible(false);
			}
		});
		button_2.setFont(new Font("华文楷体", Font.PLAIN, 18));
		button_2.setBounds(162, 319, 119, 29);
		frame.getContentPane().add(button_2);	
		textArea = new JTextArea();
		textArea.setBounds(51, 128, 371, 185);
		textArea.setFont(new Font("华文楷体", Font.PLAIN, 16));	
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(51, 128, 371, 185);
		frame.getContentPane().add(scrollPane);
		scrollPane.add(textArea);	
	}
}
