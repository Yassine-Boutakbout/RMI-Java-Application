package service;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ajout {

	private JFrame frame;
	static Ajout window;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Ajout();
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
	public Ajout() {
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
		
		JLabel lblAjoutDeClients = new JLabel("Ajout de clients");
		lblAjoutDeClients.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		lblAjoutDeClients.setForeground(new Color(0, 204, 0));
		lblAjoutDeClients.setBounds(25, 13, 210, 48);
		frame.getContentPane().add(lblAjoutDeClients);
		
		textField = new JTextField();
		textField.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		textField.setForeground(Color.BLACK);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(264, 92, 348, 64);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nom Client");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		lblNewLabel.setBounds(32, 97, 162, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSolde = new JLabel("Solde");
		lblSolde.setForeground(Color.RED);
		lblSolde.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		lblSolde.setBounds(32, 217, 162, 48);
		frame.getContentPane().add(lblSolde);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(264, 205, 348, 64);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("Ajouter Client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					TransactionsRMI stub = (TransactionsRMI) Naming.lookup("//Localhost:3000/Remote");
					if(textField.getText().length()==0)
					{
						JOptionPane.showMessageDialog(null, "Saisir un nom");
					}
					else if(textField_1.getText().length()==0)
					{
						JOptionPane.showMessageDialog(null, "Saisir le Solde");
					}
					else
					{
						stub.CreateAccount(1, textField.getText().toString(), new Date(), Integer.parseInt(textField_1.getText().toString()), new Date());
						JOptionPane.showMessageDialog(null, "Client Ajouté avec succès");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 102));
		btnNewButton.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		btnNewButton.setBounds(29, 333, 221, 58);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				window.frame.setVisible(false);
				GUI.main(null);
			}
		});
		btnRetour.setForeground(new Color(0, 0, 102));
		btnRetour.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		btnRetour.setBounds(599, 333, 221, 58);
		frame.getContentPane().add(btnRetour);
	}
}
