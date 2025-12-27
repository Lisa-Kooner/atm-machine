import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConnexionDebiter extends JFrame {

	int numeroCompte; //Variable d'instance
	
	//Création des étiquettes et d'un texte pour afficher le titre, les noms des rangées et la consigne
	private final JLabel labelTitre = new JLabel("Connexion au Compte");
	private final JTextArea textConsigne = new JTextArea("Écrivez les informations reliées au compte depuis \n"
			+ "lequel, vous voulez retirer de l'argent.");
	private final JLabel labelNumCompte = new JLabel("Numéro du Compte : ");
	private final JLabel labelMotPasse = new JLabel("Mot de passe : ");
	
	//Création des textes pour permettre à l'utilisateur d'écrire
	private final JTextField textNumCompte = new JTextField();
	private final JPasswordField textMotPasse = new JPasswordField();
	
	//Création d'une case à cocher et d'un étiquette, pour permettre à l'utilisateur de voir le mot de passe
	private final JLabel labelVisible = new JLabel("(Voir le mot de passe)");
	private final JCheckBox caseVisible = new JCheckBox();
	
	//Création d'un bouton Connexion pour connecter au compte
	private final JButton boutonConnexion = new JButton("Connexion");
	
    //Création des conteneurs
	private final JPanel conteneurMain = new JPanel();
	private final JPanel conteneurInfos = new JPanel();
	private final JPanel conteneurTitre = new JPanel();
	private final JPanel conteneurBouton = new JPanel();
	
	//Constructeur
	ConnexionDebiter(ArrayList<Client> listeCompte){
		this.setTitle("Connexion"); //titre
		this.setSize(355, 285); //taille

		//Changements apportés à l'aspect visuel des composantes
		textConsigne.setEditable(false);
		labelTitre.setFont(new Font("Cambria", Font.BOLD, 16));
		labelTitre.setForeground(Color.decode("#505597"));
		textConsigne.setBounds(40, 5, 265, 40);
		
		//Placement des composantes du conteneur Infos
		labelNumCompte.setBounds(35, 60, 140, 30);
		textNumCompte.setBounds(170, 60, 140, 30); //120
		labelMotPasse.setBounds(35, 105, 140, 30);
		textMotPasse.setBounds(170, 105, 140, 30);
		labelVisible.setBounds(80, 138, 140, 30);
		caseVisible.setBounds(225, 138, 140, 30);
		
        //Ajout des composants dans les conteneurs appropriés
		conteneurInfos.setLayout(null);
		conteneurTitre.add(labelTitre);
		conteneurInfos.add(textConsigne);
		conteneurInfos.add(labelNumCompte);
		conteneurInfos.add(textNumCompte);
		conteneurInfos.add(labelMotPasse);
		conteneurInfos.add(textMotPasse);
		conteneurInfos.add(labelVisible);
		conteneurInfos.add(caseVisible);
		conteneurBouton.add(boutonConnexion);
		
		//Ajout des sous-conteneurs dans le conteneur principal
		conteneurMain.setLayout(new BorderLayout());
		conteneurMain.add(conteneurTitre, BorderLayout.NORTH);
		conteneurMain.add(conteneurInfos, BorderLayout.CENTER);
		conteneurMain.add(conteneurBouton, BorderLayout.SOUTH);

    	//Action exécutée lorsque la case Visible est cochée par l'utilisateur
		caseVisible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(caseVisible.isSelected()) {
					textMotPasse.setEchoChar((char)0);
				}else {
					textMotPasse.setEchoChar('\u2022');
				}
			}
		});
		
    	//Action exécutée lorsque le bouton Connexion est appuyé par l'utilisateur
		boutonConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==boutonConnexion) {
					//Variables
					String numCompte = textNumCompte.getText();
					String motPasse = textMotPasse.getText();
					boolean numCompteValide = false;
					boolean motPasseValide = false;
					boolean motPasseNumCompteValides = false;
					boolean effectuer = false;
					
					//Exécution du code ci-dessous si elle répond aux exigences de validation
					if(numCompte.matches("\\d+") && numCompte.length()==4) {
						for(Client c : listeCompte) {
							if(c.getNumeroCompte()==Integer.parseInt(textNumCompte.getText())) {
								numeroCompte = Integer.parseInt(textNumCompte.getText());
								numCompteValide = true;
							}
							if(c.getMotPasse().equals(motPasse)) {
								motPasseValide = true;
							}
							if(c.getNumeroCompte()==numeroCompte && c.getMotPasse().equals(motPasse)) {
								Debiter debiterFenetre = new Debiter(numeroCompte, listeCompte);
								dispose();
								break;
							}
						}
					}
					//Actions exécutés si les données ne répondent pas aux exigences
					if (!numCompteValide) {
						JOptionPane.showMessageDialog(null, "Le numéro de compte que vous avez écrit est invalide. \n"
								+ "S'il-vous-plaît, veuillez essayer encore.", "Erreur de Numéro de Compte", 
								JOptionPane.ERROR_MESSAGE); //message qui va s'apparaître lorsque le bouton Ajouter sera appuyé par un utilisateur
			                textNumCompte.setText("");
			                return;
			        }
					if (!motPasseValide) {
						JOptionPane.showMessageDialog(null, "Le mot de passe que vous avez écrit est invalide. \n"
								+ "S'il-vous-plaît, veuillez essayer encore.", "Erreur de Mot de Passe", 
								JOptionPane.ERROR_MESSAGE); //message qui va s'apparaître lorsque le bouton Ajouter sera appuyé par un utilisateur
			                textMotPasse.setText("");
			                return;
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
