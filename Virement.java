import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Virement extends JFrame {

	//Variables d'instance
	float argent;
	int numCompte2;
	
	//Création des étiquettes et des textes pour afficher le titre, les noms des rangées, la consigne et l'espace pour écrire
	private final JLabel labelTitre = new JLabel("Transférer de l'argent");
	private final JTextArea textConsigne = new JTextArea("Écrivez le montant d'argent et le numéro de compte auquel \n"
			+ "vous voulez le transférer");
	private final JLabel labelNumCompte = new JLabel("Numéro de Compte :");
	private final JTextField textNumCompte = new JTextField();
	private final JLabel labelArgent = new JLabel("Montant d'argent à transférer ($):");
	private final JLabel labelSousArgent = new JLabel("(Format 0.00)");
	private final JTextField textArgent = new JTextField();
	
	//Création d'un bouton Transférer pour transférer de l'argent d'un compte vers un autre comtpe
	private final JButton boutonTransferer = new JButton("Transférer");
	
    //Création des conteneurs
	private final JPanel conteneurMain = new JPanel();
	private final JPanel conteneurInfos = new JPanel();
	private final JPanel conteneurTitre = new JPanel();
	private final JPanel conteneurBouton = new JPanel();
	
	//Constructeur
	Virement(int numCompte1, ArrayList<Client> listeCompte){
		this.setTitle("Virement"); //titre
		this.setSize(410, 275); //taille
		
		//Changements apportés à l'aspect visuel des composantes
		labelTitre.setFont(new Font("Cambria", Font.BOLD, 16));
		labelTitre.setForeground(Color.decode("#505597"));
		labelSousArgent.setFont(new Font("Cambria", Font.PLAIN, 12));
		textConsigne.setEditable(false);
		
		//Placement des composantes du conteneur Infos
		textConsigne.setBounds(50, 5, 320, 40);
		labelNumCompte.setBounds(28, 55, 180, 30);
		textNumCompte.setBounds(225, 55, 140, 30);
		labelArgent.setBounds(28, 93, 190, 30);
		labelSousArgent.setBounds(28, 108, 150, 30);
		textArgent.setBounds(225, 100, 140, 30); //120
		
        //Ajout des composants dans les conteneurs appropriés
		conteneurInfos.setLayout(null);
		conteneurTitre.add(labelTitre);
		conteneurInfos.add(textNumCompte);
		conteneurInfos.add(labelNumCompte);
		conteneurInfos.add(textConsigne);
		conteneurInfos.add(labelArgent);
		conteneurInfos.add(labelSousArgent);
		conteneurInfos.add(textArgent);
		conteneurBouton.add(boutonTransferer);
		
		//Ajout des sous-conteneurs dans le conteneur principal
		conteneurMain.setLayout(new BorderLayout());
		conteneurMain.add(conteneurTitre, BorderLayout.NORTH);
		conteneurMain.add(conteneurInfos, BorderLayout.CENTER);
		conteneurMain.add(conteneurBouton, BorderLayout.SOUTH);
		
    	//Action exécutée lorsque le bouton Tranférer est appuyé par l'utilisateur
		boutonTransferer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==boutonTransferer) {
					//Variables
					boolean numCompte2Valide = false;
					boolean argentValide = false;
					String numeroCompte2 = textNumCompte.getText();
					String inputArgent = textArgent.getText();
					Client compte2 = null;
					boolean effectuer = false;
					
					//Exécution du code ci-dessous si elle répond aux exigences spécifiques
					if(numeroCompte2.matches("\\d+") && numeroCompte2.length()==4) {
						for(Client c2 : listeCompte) {
							if(c2.getNumeroCompte()==Integer.parseInt(textNumCompte.getText())) {
								numCompte2 = Integer.parseInt(textNumCompte.getText());
								numCompte2Valide = true;
								compte2 = c2;
								if(textArgent.getText().matches("\\d+(\\.\\d+)?") && (Float.parseFloat(inputArgent) > 0.0)) {
									argentValide = true;
									argent = Float.parseFloat(inputArgent);
									effectuer = true;
								}
								break;
							}
						}	
					}
					//Exécution du code ci-dessous si elle ne répond pas aux exigences spécifiques
					if (!numCompte2Valide) {
						JOptionPane.showMessageDialog(null, "Le numéro de compte que vous avez écrit est invalide. \n"
								+ "S'il-vous-plaît, veuillez essayer encore.", "Erreur de Numéro de Compte", 
								JOptionPane.ERROR_MESSAGE); //message d'erreur qui va s'apparaître
			                textNumCompte.setText("");
			                return;

					}
					else if(!argentValide){
						JOptionPane.showMessageDialog(null, "Assurez-vous d'écrire un montant d'argent valide qui est positif, "
								+ "et qui ne contient \nni de lettres et ni de virgules. Si vous voulez écrire un nombre décimal, "
								+ "veuillez \nutiliser un point, comme montré dans le format sur la fenêtre précédente.", "Erreur de Format", 
								JOptionPane.ERROR_MESSAGE); //message d'erreur qui va s'apparaître
						textArgent.setText(""); //reset le champ de texte
					}
					//Exécution du code ci-dessous si elle répond aux exigences de validation, grâce au variable effectuer qui retourne "true"
					if(effectuer) {
						for (Client c : listeCompte) {
							if(c.getNumeroCompte()==numCompte1) {
								if((c.getSolde() - argent) < c.getDecouvertMaxNegatif()){
									JOptionPane.showMessageDialog(null, "En enlevant ce montant d'argent, le solde du compte concerné serait "
											+ "inférieur \nà -2000$, qui est la limite imposée par le découvert maximal. Donc, essayez \n"
											+ "d'enlever un montant d'argent tout en respectant le découvert maximal.", "Erreur avec le Découvert Maximal", 
											JOptionPane.ERROR_MESSAGE); ///message d'erreur qui va s'apparaître
									textArgent.setText(""); //reset le champ de texte
									argent = Float.parseFloat(inputArgent);
								}
								else if(argent > c.getDebitMax()){
									JOptionPane.showMessageDialog(null, "Le montant d'argent vous voulez retirer du compte concerné "
											+ "dépasse \nla limite imposée par le débit maximal autorisé. Essayez d'enlever un montant \nd'argent "
											+ "tout en respectant le débit maximal, qui est de 1000$.", "Erreur avec le Débit Maximal", 
											JOptionPane.ERROR_MESSAGE); //message d'erreur qui va s'apparaître
									textArgent.setText(""); //reset le champ de texte
									argent = Float.parseFloat(inputArgent);
								}
								else{
									c.setSolde(c.getSolde() - argent);
									compte2.setSolde(compte2.getSolde() + argent);
									JOptionPane.showMessageDialog(null, "Le montant d'argent a été transféré au compte avec succès!", "Confirmation - Virement", 
					                                            JOptionPane.INFORMATION_MESSAGE);
									dispose(); // Fermer la fenêtre actuelle
								}
							}
						}
					}
				}
			}
		});
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		this.setContentPane(conteneurMain);  //changement du panneau de contenu de la fenêtre au conteneur principal
	}
}
