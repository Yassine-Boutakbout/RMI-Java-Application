package service;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.Naming;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Chercher {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	static Chercher window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Chercher();
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
	public Chercher() {
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
		
		JLabel lblAjoutDeClients = new JLabel("Recherche de clients");
		lblAjoutDeClients.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		lblAjoutDeClients.setForeground(new Color(0, 204, 0));
		lblAjoutDeClients.setBounds(25, 13, 277, 48);
		frame.getContentPane().add(lblAjoutDeClients);
		
		textField = new JTextField();
		textField.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		textField.setForeground(Color.BLACK);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(264, 67, 527, 64);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nom Client");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		lblNewLabel.setBounds(35, 74, 162, 48);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSolde = new JLabel("Date Creation");
		lblSolde.setForeground(Color.RED);
		lblSolde.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		lblSolde.setBounds(35, 231, 186, 48);
		frame.getContentPane().add(lblSolde);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(264, 144, 527, 64);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("Chercher Client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					TransactionsRMI stub = (TransactionsRMI) Naming.lookup("//Localhost:3000/Remote");
					if(textField.getText().length()==0)
					{
						JOptionPane.showMessageDialog(null, "Saisir un nom");
					}
					else
					{
						List c;
						c=(List)stub.SearchAccount(textField.getText().toString());
						if(c.size()==0)
						{
							JOptionPane.showMessageDialog(null, "Client Non Trouvé");
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Client Trouvé");
							textField_1.setText(c.get(3).toString()+"  $");
							textField_2.setText(c.get(2).toString());
							textField_3.setText(c.get(4).toString());
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 102));
		btnNewButton.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		btnNewButton.setBounds(12, 382, 239, 58);
		frame.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("Solde");
		label.setForeground(Color.RED);
		label.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		label.setBounds(35, 151, 92, 48);
		frame.getContentPane().add(label);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setForeground(Color.BLACK);
		textField_2.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		textField_2.setColumns(10);
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setBounds(264, 224, 527, 64);
		frame.getContentPane().add(textField_2);
		
		JLabel lblDateDernierModif = new JLabel("Date Dernier modif");
		lblDateDernierModif.setForeground(Color.RED);
		lblDateDernierModif.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		lblDateDernierModif.setBounds(12, 308, 246, 48);
		frame.getContentPane().add(lblDateDernierModif);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setForeground(Color.BLACK);
		textField_3.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		textField_3.setColumns(10);
		textField_3.setBackground(Color.LIGHT_GRAY);
		textField_3.setBounds(264, 301, 527, 64);
		frame.getContentPane().add(textField_3);
		
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
		btnRetour.setBounds(607, 382, 221, 58);
		frame.getContentPane().add(btnRetour);
	}
}
