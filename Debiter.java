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

public class Debiter  extends JFrame {
	
	float argent; //Variable d'instance

	//Création des étiquettes et des textes pour afficher le titre, les noms des rangées, la consigne et l'espace pour écrire
	private final JLabel labelTitre = new JLabel("Débiter de l'argent");
	private final JTextArea textConsigne = new JTextArea("Écrivez le montant d'argent que vous voulez retirer depuis \n"
			+ "le compte dont vous avez été connecté précédemment.");
	private final JLabel labelArgent = new JLabel("Montant d'argent à retirer ($):");
	private final JLabel labelSousArgent = new JLabel("(Format 0.00)");
	private final JTextField textArgent = new JTextField();
	
	//Création d'un bouton Débiter pour retirer de l'argent du compte
	private final JButton boutonDebiter = new JButton("Débiter");
	
    //Création des conteneurs
	private final JPanel conteneurMain = new JPanel();
	private final JPanel conteneurInfos = new JPanel();
	private final JPanel conteneurTitre = new JPanel();
	private final JPanel conteneurBouton = new JPanel();
	
	//Constructeur
	Debiter(int numeroCompte, ArrayList<Client> listeCompte){
		this.setTitle("Débiter"); //titre
		this.setSize(410, 200); //taille

		//Changements apportés à l'aspect visuel des composantes
		labelTitre.setFont(new Font("Cambria", Font.BOLD, 16));
		labelTitre.setForeground(Color.decode("#505597"));
		labelSousArgent.setFont(new Font("Cambria", Font.PLAIN, 12));
		textConsigne.setEditable(false);
		
		//Placement des composantes du conteneur Infos
		textConsigne.setBounds(50, 5, 300, 40);
		labelArgent.setBounds(40, 46, 180, 30);
		labelSousArgent.setBounds(40, 61, 150, 30);
		textArgent.setBounds(225, 55, 140, 30);
		
        //Ajout des composants dans les conteneurs appropriés
		conteneurInfos.setLayout(null);
		conteneurTitre.add(labelTitre);
		conteneurInfos.add(textConsigne);
		conteneurInfos.add(labelArgent);
		conteneurInfos.add(labelSousArgent);
		conteneurInfos.add(textArgent);
		conteneurBouton.add(boutonDebiter);
		
		//Ajout des sous-conteneurs dans le conteneur principal
		conteneurMain.setLayout(new BorderLayout());
		conteneurMain.add(conteneurTitre, BorderLayout.NORTH);
		conteneurMain.add(conteneurInfos, BorderLayout.CENTER);
		conteneurMain.add(conteneurBouton, BorderLayout.SOUTH);
		
		
    	//Action exécutée lorsque le bouton Débiter est appuyé par l'utilisateur
		boutonDebiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==boutonDebiter) {
					//Variables
					String inputArgent = textArgent.getText();
					Client compte = null;
					boolean effectuer = false;
					//Exécution du code ci-dessous si elle répond aux exigences spécifiques
					if(textArgent.getText().matches("\\d+(\\.\\d+)?") && (Float.parseFloat(inputArgent) > 0.0)) { //\\d+(\\.\\d+)? 
						argent = Float.parseFloat(inputArgent);
						effectuer = true;
					
					}
					//Exécution du code ci-dessous si elle ne répond pas aux exigences spécifiques
					else if((!textArgent.getText().matches("\\d+(\\.\\d+)?"))){
						JOptionPane.showMessageDialog(null, "Assurez-vous d'écrire un montant d'argent valide qui est positif, "
								+ "et qui ne contient \nni de lettres et ni de virgules. Si vous voulez écrire un nombre décimal, "
								+ "veuillez \nutiliser un point, comme montré dans le format sur la fenêtre précédente.", "Erreur de Format", 
								JOptionPane.ERROR_MESSAGE); //message d'erreur qui va s'apparaître
						textArgent.setText(""); //reset le champ de texte
						
					}
					//Exécution du code ci-dessous si elle répond aux exigences de validation, grâce au variable effectuer qui retourne "true"
					if(effectuer) {
						for (Client c : listeCompte) {
							if(c.getNumeroCompte()==numeroCompte) {
								if(argent > 0 && argent <= c.getDebitMax() && (c.getSolde() - argent)>= c.getDecouvertMaxNegatif()) {
									c.setSolde(c.getSolde() - argent); //calcul effectué
									dispose();
									JOptionPane.showMessageDialog(null, "Le montant d'argent a été retiré du compte avec succès!", "Confirmation - Débiter", 
										JOptionPane.INFORMATION_MESSAGE); //affichage d'un message de confirmation
									break;
								}
								//autre cas possible avec les erreurs reliés au découvert maximal et le débit maximal
								else if((c.getSolde() - argent) < c.getDecouvertMaxNegatif()){
									JOptionPane.showMessageDialog(null, "En enlevant ce montant d'argent, le solde du compte concerné serait "
											+ "inférieur \nà -2000$, qui est la limite imposée par le découvert maximal. Donc, essayez \n"
											+ "d'enlever un montant d'argent tout en respectant le découvert maximal.", "Erreur avec le Découvert Maximal", 
											JOptionPane.ERROR_MESSAGE); //message d'erreur qui va s'apparaître
									textArgent.setText(""); //reset le champ de texte
									argent = Float.parseFloat(inputArgent);
									break;
								}
								else if(argent > c.getDebitMax()){
									JOptionPane.showMessageDialog(null, "Le montant d'argent que vous voulez retirer du compte concerné "
											+ "dépasse \nla limite imposée par le débit maximal autorisé. Essayez d'enlever un montant \nd'argent "
											+ "tout en respectant le débit maximal, qui est de 1000$.", "Erreur avec le Débit Maximal", 
											JOptionPane.ERROR_MESSAGE); //message d'erreur qui va s'apparaître
									textArgent.setText(""); //reset le champ de texte
									argent = Float.parseFloat(inputArgent);
									break;
								}
								else {
									JOptionPane.showMessageDialog(null, "Assurez-vous d'écrire un montant d'argent valide qui est positif, "
											+ "et qui ne contient \nni de lettres et ni de virgules. Si vous voulez écrire un nombre décimal, "
											+ "veuillez \nutiliser un point, comme montré dans le format sur la fenêtre précédente.", "Erreur de Format", 
											JOptionPane.ERROR_MESSAGE); //message d'erreur qui va s'apparaître
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
