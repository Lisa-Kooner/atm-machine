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
import javax.swing.JTextField;

public class CreerCompte  extends JFrame {
	
	float solde; //variable d'instance
	
	//Création des étiquettes pour afficher le titre et les noms des rangées
	private final JLabel labelTitre = new JLabel("Créer un nouveau compte");
	private final JLabel labelNom = new JLabel("Nom : ");
	private final JLabel labelPrenom = new JLabel("Prénom : ");
	private final JLabel labelMotPasse = new JLabel("Mot de Passe : ");
	private final JLabel labelSousMotPasse = new JLabel("(à 4 chiffres)");
	private final JLabel labelSolde = new JLabel("Solde ($) : ");
	private final JLabel labelSousSolde = new JLabel("(Format : 0.00)");

	//Création des textes pour permettre à l'utilisateur d'écrire
	private final JTextField textNom = new JTextField();
	private final JTextField textPrenom = new JTextField();
	private final JTextField textMotPasse = new JTextField();
	private final JTextField textSolde = new JTextField();
	
	//Création d'un bouton Soumettre pour soumettre les infos du compte
	private final JButton boutonSoumettre = new JButton("Soumettre");
	
    //Création des conteneurs
	private final JPanel conteneurMain = new JPanel();
	private final JPanel conteneurTitre = new JPanel();
	private final JPanel conteneurInfos = new JPanel();
	private final JPanel conteneurBouton = new JPanel();
	
	//Constructeur
	public CreerCompte(ArrayList<Client> listeCompte) {
		this.setTitle("Création d'un Compte"); //titre
		this.setSize(300, 300); //taille

		//Changements apportés à l'aspect visuel des composantes
		labelTitre.setFont(new Font("Cambria", Font.BOLD, 16));
		labelTitre.setForeground(Color.decode("#505597"));
		labelSousMotPasse.setFont(new Font("Cambria", Font.PLAIN, 12));
		labelSousSolde.setFont(new Font("Cambria", Font.PLAIN, 12));
		
		//Placement des composantes du conteneur Infos
		labelNom.setBounds(30, 15, 140, 30);
		textNom.setBounds(120, 15, 140, 30);
		labelPrenom.setBounds(30, 57, 140, 30);
		textPrenom.setBounds(120, 57, 140, 30);
		labelMotPasse.setBounds(30, 88, 140, 37);
		labelSousMotPasse.setBounds(30, 110, 140, 20 );
		textMotPasse.setBounds(120, 99, 140, 30);
		labelSolde.setBounds(30, 132, 140, 30);
		labelSousSolde.setBounds(30, 150, 140, 20 );
		textSolde.setBounds(120, 141, 140, 30);
		
		//Création d'une instance de la classe Client() et déclaration de la variable numeroCompte
		Client client = new Client();
		int numeroCompte = client.getNumeroCompte();
		
    	//Action exécutée lorsque le bouton Soumettre est appuyé par l'utilisateur
		boutonSoumettre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==boutonSoumettre) {	
					//Variables
					String nom = textNom.getText();
					String prenom = textPrenom.getText();
					String motPasse = textMotPasse.getText();
					String inputSolde = textSolde.getText();
					
					//Exécution du code ci-dessous si elle ne répond pas aux exigences spécifiques
					if(motPasse.length()!=4 || !motPasse.matches("\\d+")) {
						JOptionPane.showMessageDialog(null, "Le mot de passe que vous avez écrit ne respecte pas les critères : assurer-vous\n"
								+ "d'avoir exactement 4 chiffres. Veuillez réécrire un mot de passe s'il-vous-plaît.", "Erreur de Mot de Passe", 
								JOptionPane.ERROR_MESSAGE); //message d'erreur qui va s'apparaître
						textMotPasse.setText(""); //reset le champ de texte
					}
					//Exécution du code ci-dessous si elle répond aux exigences spécifiques
					if(textSolde.getText().matches("\\d+(\\.\\d+)?")) {
						solde = Float.parseFloat(inputSolde);
					
					}
					else if(!textSolde.getText().matches("\\d+(\\.\\d+)?")){
						JOptionPane.showMessageDialog(null, "Assurez-vous d'écrire un montant d'argent valide qui est positif, "
								+ "et qui ne contient \nni de lettres et ni de virgules. Si vous voulez écrire un nombre décimal, "
								+ "veuillez \nutiliser un point, comme montré dans le format sur la fenêtre précédente.", "Erreur de Format", 
								JOptionPane.ERROR_MESSAGE); //message d'erreur qui va s'apparaître
						textSolde.setText(""); //reset le champ de texte
					}
					//Exécution du code ci-dessous si elle répond aux exigences de validation, grâce au variable effectuer qui retourne "true"
					if(motPasse.length()==4 && motPasse.matches("\\d+") && textSolde.getText().matches("\\d+(\\.\\d+)?")) {	// && motPasse.matches("\\d+")
						Client client = new Client(nom, prenom, motPasse, solde);
						listeCompte.add(client);
						dispose();
						int numeroCompte = client.getNumeroCompte();
						ConfirmationCompte confirmationFenetre = new ConfirmationCompte(numeroCompte, motPasse);
					}
				}
			}
		});

        //Ajout des composants dans les conteneurs appropriés
		conteneurInfos.setLayout(null);
		conteneurTitre.add(labelTitre);
		conteneurInfos.add(labelNom);
		conteneurInfos.add(textNom);
		conteneurInfos.add(labelPrenom);
		conteneurInfos.add(textPrenom);
		conteneurInfos.add(labelMotPasse);
		conteneurInfos.add(labelSousMotPasse);
		conteneurInfos.add(textMotPasse);
		conteneurInfos.add(labelSolde);
		conteneurInfos.add(labelSousSolde);
		conteneurInfos.add(textSolde);
		conteneurBouton.add(boutonSoumettre);
		
		//Ajout des sous-conteneurs dans le conteneur principal
		conteneurMain.setLayout(new BorderLayout());
		conteneurMain.add(conteneurTitre, BorderLayout.NORTH);
		conteneurMain.add(conteneurInfos, BorderLayout.CENTER);
		conteneurMain.add(conteneurBouton, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		this.setContentPane(conteneurMain);  //changement du panneau de contenu de la fenêtre au conteneur principal
	}
}

