package cn.cumt.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Index {

	private JFrame frame;
	

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Index() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u540E\u53F0\u7BA1\u7406\u7CFB\u7EDF");
		frame.setBounds(100, 100, 480, 420);
		frame.setLocation(730, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button = new JButton("\u6DFB\u52A0\u56FE\u7247");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileChoose choose = new FileChoose();
				choose.getFrame().setVisible(true);
				choose.getFrame().setLocation(730, 300);
				frame.setVisible(false);
				
			}
		});
		button.setBounds(133, 65, 210, 40);
		frame.getContentPane().add(button);
	}
}
