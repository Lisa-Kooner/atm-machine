import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.io.FileOutputStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Accueil extends JFrame {

	//Création d'une liste pour contenir les données de bases des comptes des clients
	private static ArrayList<Client> listeCompte = new ArrayList<>();
	
	//Création d'un étiquette et d'un texte pour afficher le titre et la consigne générale
	private final JLabel labelTitre = new JLabel("Page d'Accueil");
	private final JTextArea textAreaConsigne = new JTextArea("Bienvenue au page d'accueil! Ici, vous avez la possibilité de créer, "
			+ "modifier et voir toutes vos comptes. À cet égard, le numéro de compte sera choisi de manière alétoire  par la machine."
			+ "Vous pouvez aussi effectuez des transactions financières. Par contre, il est important que savoir que le débit maximal "
			+ "autorisé est de 1000$, et lorsque vous retirez de l'argent du compte, vous ne pouvez pas dépasser un solde de -2000$.");
	
	//Création des boutons de compte, qui vont être afficher dans la section des informations des comptes
    private final JButton boutonCreer = new JButton("Créer un compte");
    private final JButton boutonChanger = new JButton("Changer le mot de passe");
    private final JButton boutonAfficher = new JButton("Afficher les comptes");
    
   //Création des boutons de transactions, qui vont être afficher dans la section des transactions financières
    private final JButton boutonCrediter = new JButton("Créditer");
    private final JButton boutonDebiter = new JButton("Débiter");
    private final JButton boutonVirement = new JButton("Virement");
    
    //Création d'un bouton Quitter pour fermer le programme, qui va s'afficher vers le bas du fenêtre
    private final JButton boutonQuitter = new JButton("Quitter");
    
    //Création des conteneurs
    private final JPanel conteneurMain = new JPanel();
    private final JPanel conteneurDebut = new JPanel();
    private final JPanel conteneurConsigne = new JPanel();
    private final JPanel conteneurInfoCompte = new JPanel();
    private final JPanel conteneurTransaction = new JPanel();
    private final JPanel conteneurFin = new JPanel();
    
	//Constructeur de la classe Accueil
    public Accueil(){
    	super("Guichet Bancaire"); //titre de la fenêtre
    	this.setSize(650, 435); //taille de la fenêtre
    	
    	//appel de la méthode de la désérialisation
    	recupererComptes();
    	
    	//Changement apporté aux espaces de texte réservés à l'affichage du consigne
    	textAreaConsigne.setEditable(false); //rendre l'espace du consigne inéditable pour l'utilisateur
    	textAreaConsigne.setWrapStyleWord(true); //assurer que les longs mots qui ne rentrent pas dans une ligne, saute à la prochaine ligne pour que ses lettres soient ensembles
    	textAreaConsigne.setLineWrap(true); //donner la forme d'un cadre rectangulaire fixe pour l'esapce où l'information sera affichée
    	textAreaConsigne.setSize(575, 220); //donner une taille précis à l'esapace pour afficher la consigne
    	textAreaConsigne.setBorder(BorderFactory.createEmptyBorder(0, 2, 2, 2)); //espacement autour du texte
    	
    	//Emplacement des composants dessous-conteneurs dans le conteneur principal
    	conteneurMain.setLayout(new GridLayout(5, 1, 5, 5)); //6 lignes et 3 colonnes, avec un espacement de 5 pixels de manière horizontale et verticale
    	conteneurMain.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10)); //espacement autour du conteneur principal
    			
    	//Création d'une variable concernant la couleur des frontières des conteneurs
    	LineBorder lineBorder = new LineBorder(Color.BLACK);
    	labelTitre.setFont(new Font("Cambria", Font.BOLD, 21)); //couleur pour le titre
    			
    	//Donner des titres et des couleurs aux sous-conteneurs
    	TitledBorder titreInfoCompte = BorderFactory.createTitledBorder("Informations des Comptes"); //titre du conteneur Info-Compte
        conteneurInfoCompte.setBorder(titreInfoCompte);
        titreInfoCompte.setBorder(lineBorder);  //couleur noire pour les frontières du conteneur
        titreInfoCompte.setTitleColor(Color.decode("#962316")); //couleur rouge foncée pour le titre
    		    
        //Donner des titres et des couleurs au conteneur Menu et à ses sous-conteneurs
    	TitledBorder titreTransaction = BorderFactory.createTitledBorder("Transactions Financières"); //titre du conteneur Transactions
        conteneurTransaction.setBorder(titreTransaction);
        titreTransaction.setBorder(lineBorder);  //couleur noire pour les frontières du conteneur
        titreTransaction.setTitleColor(Color.decode("#962316")); //couleur rouge foncée pour le titre
    		    	
//    	conteneurDebut.setBackground(Color.WHITE);
//    	conteneurConsigne.setBackground(Color.WHITE);
//    	conteneurInfoCompte.setBackground(Color.WHITE);
//    	conteneurTransaction.setBackground(Color.WHITE);
//    	conteneurFin.setBackground(Color.WHITE);
//    	conteneurMain.setBackground(Color.WHITE);
        
        //Ajout des composants dans les conteneurs appropriés
    	conteneurDebut.add(labelTitre);
    	conteneurConsigne.add(textAreaConsigne);
    	conteneurInfoCompte.add(boutonCreer);
    	conteneurInfoCompte.add(boutonChanger);
    	conteneurInfoCompte.add(boutonAfficher);
    	conteneurTransaction.add(boutonCrediter);
    	conteneurTransaction.add(boutonDebiter);
    	conteneurTransaction.add(boutonVirement);
    	conteneurFin.add(boutonQuitter);
    	
    	//Ajout des sous-conteneurs dans le conteneur principal
    	conteneurMain.add(conteneurDebut);
    	conteneurMain.add(conteneurConsigne);
    	conteneurMain.add(conteneurInfoCompte);
    	conteneurMain.add(conteneurTransaction);
    	conteneurMain.add(conteneurFin);
    	
    	this.setContentPane(conteneurMain); //changement du panneau de contenu de la fenêtre au conteneur principal
    
    	//Action exécutée lorsque le bouton Créer est appuyé par l'utilisateur
    	boutonCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreerCompte creerCompte = new CreerCompte(listeCompte);
			}
		});
    	
    	//Action exécutée lorsque le bouton Changer est appuyé par l'utilisateur
    	boutonChanger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modification modification = new Modification(listeCompte);
			}
		});
    	
    	//Action exécutée lorsque le bouton Afficher est appuyé par l'utilisateur
    	boutonAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AfficherCompte afficherCompte = new AfficherCompte(listeCompte);
			}
		});
   	
    	//Action exécutée lorsque le bouton Créditer est appuyé par l'utilisateur
    	boutonCrediter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnexionCrediter connexionCrediter = new ConnexionCrediter(listeCompte);
			}
		});
    	
    	//Action exécutée lorsque le bouton Débiter est appuyé par l'utilisateur
    	boutonDebiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnexionDebiter connexionDebiter = new ConnexionDebiter(listeCompte);
			}
		});
    	
    	//Action exécutée lorsque le bouton Virement est appuyé par l'utilisateur
    	boutonVirement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnexionVirement connexionVirement = new ConnexionVirement(listeCompte);
			}
		});
    	
    	//Action exécutée lorsque le bouton Quitter est appuyé par l'utilisateur
    	boutonQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enregistrerComptes(); //appel de la méthode de la désérialisation
				dispose();
				
			}
		});
    } //fin du constructeur
    
	//Méthode de type void, qui permet de récupérer les objets contenus dans le fichier texte (Désérialisation)
	public static void recupererComptes() {
		ObjectInputStream input = null;  //initialisation du variable
		//Ouverture du fichier
		try {
			FileInputStream fis = new FileInputStream("comptesBancaires.txt");
			input = new ObjectInputStream(fis);
			listeCompte = (ArrayList<Client>) input.readObject();
		} 
		//Capturer les erreurs lors de la manipulation du fichier texte
		catch (EOFException e) {
			System.out.println("Bienvenue au programme des comptes bancaires !");
		} 
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		//Fermeture du fichier
		finally {
			if(input != null) {
				try {
					input.close();
				} 
				catch (IOException e) {
					System.err.println("Erreur à fermer le fichier.");
				}
			}
		}
	}

	//Méthode de type void, qui permet de sauvegarder les objets dans le fichier texte (Sérialisation)
	public static void enregistrerComptes() {
		ObjectOutputStream output = null;	//initialisation du variable
		//Ouverture du fichier
		try {
			FileOutputStream fos = new FileOutputStream("comptesBancaires.txt");
			output = new ObjectOutputStream(fos);
			output.writeObject(listeCompte);
		} 
		//Capturer les erreurs lors de la manipulation du fichier texte
		catch (IOException e) {
			e.printStackTrace();
		} 
		//Fermeture du fichier
		finally {
			if (output != null) {
				try {
					output.close();
				} 
				catch (IOException e) {
					System.err.println("Erreur à fermer le fichier. Le programme se termine.");
				}
			}
		}
	}
    
	//Méthode main de la classe Accueil
	public static void main(String[] args) {
		Accueil pageAccueil = new Accueil();
		pageAccueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pageAccueil.setResizable(true);
		pageAccueil.setLocationRelativeTo(null);
		pageAccueil.setVisible(true);
	}
}
