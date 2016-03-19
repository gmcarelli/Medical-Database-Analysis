package br.com.a1402072.projetoic.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import br.com.a1402072.projetoic.control.SystemControl;
import br.com.a1402072.projetoic.model.MyImage;

public class JFrameTest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel jLabelImage;
	private JScrollPane jScrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameTest frame = new JFrameTest();
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
	public JFrameTest() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		jScrollPane = new JScrollPane();
		contentPane.add(jScrollPane, BorderLayout.NORTH);
		formWindowOpened(new WindowEvent(this, 0));
	}

	private void formWindowOpened(java.awt.event.WindowEvent evt) {
		this.jLabelImage = new JLabel();

		try {

			MyImage myImage = SystemControl.myImageControl().readMyImageFromDB(14);
			
			BufferedImage bufferedImage = MyImage.byteArrayToBufferedImage(myImage.getImageBytes());			

			ImageIcon imageIcon = new ImageIcon(MyImage.resizeBufferedImage(bufferedImage, 1024, 768));

			jLabelImage.setIcon(imageIcon);
			jLabelImage.setHorizontalAlignment(JLabel.CENTER);
			jScrollPane.getViewport().add(jLabelImage);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	
}
