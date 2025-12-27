import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class AfficherCompte extends JFrame {
	
	//Création des composantes pour la fenêtre Affichage
	private final JLabel labelTitre = new JLabel("Affichage des comptes bancaires créés"); //étiquette pour le titre
	private final JTextArea textAreaCompte = new JTextArea();	//texte pour montrer les détails des comptes
	private final JButton boutonFermer = new JButton("Fermer"); //bouton Fermer pour fermer la fenêtre
	
    //Création des conteneurs
	private JPanel conteneurTitre = new JPanel();
	private JPanel conteneurInfos = new JPanel();
	private JPanel conteneurBouton = new JPanel();
	private final JPanel conteneurMain = new JPanel();
	
	//Constructeur de la classe AfficherCompte
	public AfficherCompte(ArrayList<Client> listeCompte) {
		this.setTitle("Affichage des Comptes"); //titre de la fenêtre
		this.setSize(570, 300); //taille de la fenêtre
		
		//Changements apportés à l'espace de texte réservé à l'affichage des détails des comptes
		textAreaCompte.setEditable(false); //rendre l'espace des détails des comptes inéditable pour l'utilisateur
		textAreaCompte.setWrapStyleWord(true); //assurer que les longs mots qui ne rentrent pas dans une ligne, saute à la prochaine ligne pour que ses lettres soient ensembles
		textAreaCompte.setLineWrap(true); //donner la forme d'un cadre rectangulaire fixe pour l'esapce où l'information sera affichée
		textAreaCompte.setSize(520, 200); //donner une taille précis à l'esapace pour afficher les détails des comptes
		
		//Changement de couleurs et de la police de caractère du titre et du texte
		textAreaCompte.setFont(new Font("Cambria", Font.ITALIC, 13));;
		labelTitre.setFont(new Font("Cambria", Font.BOLD, 16));
		labelTitre.setForeground(Color.decode("#505597"));
		
		//Création d'une variable pour ajuster la partie décimal pour le solde
		DecimalFormat df = new DecimalFormat("0.00");
		
		//Boucle for pour afficher tous les détails des comptes créés
		for(Client e : listeCompte) {
			String solde = df.format(e.getSolde()); //ajustement au format du solde
			//Ajout des informations dans le texte
			textAreaCompte.append("Compte #" + e.getNumeroCompte() +":     Nom : " + e.getNom() + ",     Prénom : " + e.getPrenom() + ",     Mot de Passe : " + e.getMotPasse() + ",     Solde : " + solde +"$ "); //"\n"
			textAreaCompte.append("\n"); //passer à la prochaine ligne
		}
		
        //Ajout des composants dans les conteneurs appropriés
		conteneurTitre.add(labelTitre);
		conteneurInfos.add(textAreaCompte);
		conteneurBouton.add(boutonFermer, BorderLayout.SOUTH);
    	
		//Ajout des sous-conteneurs dans le conteneur principal
		conteneurMain.setLayout(new BorderLayout()); 
		conteneurMain.add(conteneurTitre, BorderLayout.NORTH);
		conteneurMain.add(conteneurInfos, BorderLayout.CENTER);
		conteneurMain.add(conteneurBouton, BorderLayout.SOUTH);
		
    	//Action exécutée lorsque le bouton Fermer est appuyé par l'utilisateur
		boutonFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		this.setContentPane(conteneurMain);  //changement du panneau de contenu de la fenêtre au conteneur principal
	}
}
