package br.edu.ifsp.postgresql.view;

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

import br.edu.ifsp.dao.DAOManager;
import br.edu.ifsp.helper.ImageHelper;
import br.edu.ifsp.model.MyImage;

public class JFMainViewPostgre extends JFrame {

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
					JFMainViewPostgre frame = new JFMainViewPostgre();
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
	public JFMainViewPostgre() {
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

	public JScrollPane getjScrollPane() {
		return jScrollPane;
	}

	public void setjScrollPane(JScrollPane jScrollPane) {
		this.jScrollPane = jScrollPane;
	}

	
	

	private void formWindowOpened(java.awt.event.WindowEvent evt) {
		this.jLabelImage = new JLabel();

		try {

			MyImage myImage = DAOManager.myImageDAOPostgre().search(14);
			
			BufferedImage bufferedImage = ImageHelper.byteArrayToBufferedImage(myImage.getImageBytes());			

			ImageIcon imageIcon = new ImageIcon(ImageHelper.resizeBufferedImage(bufferedImage, 1024, 768));

			jLabelImage.setIcon(imageIcon);
			jLabelImage.setHorizontalAlignment(JLabel.CENTER);
			jScrollPane.getViewport().add(jLabelImage);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}
