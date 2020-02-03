package service;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;

public class GUI {

	private JFrame frame;
	static GUI window ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAjouterClient = new JButton("Ajouter Client");
		btnAjouterClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*Ajout window = new Ajout();
				window.main(null);*/
				Ajout.main(null);
				window.frame.setVisible(false);

			}
		});
		btnAjouterClient.setBounds(91, 196, 153, 42);
		frame.getContentPane().add(btnAjouterClient);
		
		JButton btnNewButton = new JButton("Chercher Client");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Chercher.main(null);
				window.frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(339, 196, 142, 42);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Transferer");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Transfert.main(null);
				window.frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(563, 196, 129, 42);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblApplicationRmi = new JLabel("APPLICATION CLIENT");
		lblApplicationRmi.setForeground(new Color(0, 204, 0));
		lblApplicationRmi.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		lblApplicationRmi.setBounds(12, 13, 290, 48);
		frame.getContentPane().add(lblApplicationRmi);
	}
}
