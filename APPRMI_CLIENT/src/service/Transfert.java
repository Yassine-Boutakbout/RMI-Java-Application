package service;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.Naming;
import java.util.List;

public class Transfert {

	private JFrame frame;
	private JTextField textField;
	private JLabel lblNomClientDestinataire;
	private JTextField textField_1;
	private JLabel lblSolde;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblQuantitTransferer;
	private JTextField textField_4;
	static Transfert window;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Transfert();
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
	public Transfert() {
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
		
		JLabel lblAjoutDeClients = new JLabel("Transfert d'argent");
		lblAjoutDeClients.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		lblAjoutDeClients.setForeground(new Color(0, 204, 0));
		lblAjoutDeClients.setBounds(25, 13, 277, 48);
		frame.getContentPane().add(lblAjoutDeClients);
		
		textField = new JTextField();
		textField.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		textField.setForeground(Color.BLACK);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(172, 135, 268, 64);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nom Client Source");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		lblNewLabel.setBounds(172, 74, 251, 48);
		frame.getContentPane().add(lblNewLabel);		
		
		lblNomClientDestinataire = new JLabel("Nom Client Destinataire");
		lblNomClientDestinataire.setForeground(Color.RED);
		lblNomClientDestinataire.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		lblNomClientDestinataire.setBounds(520, 74, 317, 48);
		frame.getContentPane().add(lblNomClientDestinataire);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.BLACK);
		textField_1.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.LIGHT_GRAY);
		textField_1.setBounds(520, 135, 268, 64);
		frame.getContentPane().add(textField_1);
		
		lblSolde = new JLabel("SOLDE ");
		lblSolde.setForeground(new Color(0, 128, 0));
		lblSolde.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		lblSolde.setBounds(12, 225, 105, 48);
		frame.getContentPane().add(lblSolde);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setForeground(Color.BLACK);
		textField_2.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		textField_2.setColumns(10);
		textField_2.setBackground(Color.LIGHT_GRAY);
		textField_2.setBounds(172, 218, 268, 64);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setForeground(Color.BLACK);
		textField_3.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		textField_3.setColumns(10);
		textField_3.setBackground(Color.LIGHT_GRAY);
		textField_3.setBounds(520, 218, 268, 64);
		frame.getContentPane().add(textField_3);
		
		lblQuantitTransferer = new JLabel("Quantit\u00E9 \u00E0 Transferer");
		lblQuantitTransferer.setForeground(new Color(0, 128, 0));
		lblQuantitTransferer.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		lblQuantitTransferer.setBounds(12, 302, 277, 48);
		frame.getContentPane().add(lblQuantitTransferer);
		
		textField_4 = new JTextField();
		textField_4.setForeground(Color.BLACK);
		textField_4.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		textField_4.setColumns(10);
		textField_4.setBackground(Color.LIGHT_GRAY);
		textField_4.setBounds(321, 295, 268, 64);
		frame.getContentPane().add(textField_4);
		
		JButton btnTransferer = new JButton("Transferer");
		btnTransferer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					TransactionsRMI stub = (TransactionsRMI) Naming.lookup("//Localhost:3000/Remote");
					if(textField.getText().length()==0)
					{
						JOptionPane.showMessageDialog(null, "Saisir le nom de la source");
					}
					else if(textField_1.getText().length()==0)
					{
						JOptionPane.showMessageDialog(null, "Saisir le nom du destinataire");
					}
					else if(textField_4.getText().length()==0)
					{
						JOptionPane.showMessageDialog(null, "Saisir le Montant à transferer");
					}
					else
					{
						List src,dst;
						src=(List)stub.SearchAccount(textField.getText().toString());
						dst=(List)stub.SearchAccount(textField_1.getText().toString());
						if(src.size()==0)
						{
							JOptionPane.showMessageDialog(null, "Client Source Non Trouvé");
						}
						else if(dst.size()==0)
						{
							JOptionPane.showMessageDialog(null, "Client Destinataire Non Trouvé");
						}
						else
						{
							textField_2.setText(src.get(3).toString());
							textField_3.setText(dst.get(3).toString());
							if(Integer.parseInt(src.get(3).toString())<Integer.parseInt(textField_4.getText().toString()))
							{
								JOptionPane.showMessageDialog(null, "Solde Insuffisant pour effectuer l'opération demandé");
							}
							else
							{
								stub.TransferAccount(Integer.parseInt(textField_4.getText().toString()), textField.getText().toString(), textField_1.getText().toString());
								src=(List)stub.SearchAccount(textField.getText().toString());
								dst=(List)stub.SearchAccount(textField_1.getText().toString());
								JOptionPane.showMessageDialog(null, "Opération est effectué avec succès");
								textField_2.setText(src.get(3).toString());
								textField_3.setText(dst.get(3).toString());
							}
						}
					}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		btnTransferer.setForeground(new Color(0, 0, 102));
		btnTransferer.setFont(new Font("Myriad Pro", Font.PLAIN, 30));
		btnTransferer.setBounds(331, 372, 239, 58);
		frame.getContentPane().add(btnTransferer);
		
		
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
		btnRetour.setBounds(649, 372, 221, 58);
		frame.getContentPane().add(btnRetour);
	}
}