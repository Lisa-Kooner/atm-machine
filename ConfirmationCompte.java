import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmationCompte extends JFrame{

	//Création d'un étiquette et d'un texte pour afficher le titre et la consigne générale
	private JLabel labelTitre = new JLabel("Informations de votre Compte");
	private JTextArea textConfirmation = new JTextArea("Votre compte a été créé avec succès! Voici \n"
			+ "les informations importantes que vous aurez \nbesoin lors de la connexion au comtpe.");
	private JLabel labelNumeroCompte;
	private JLabel labelMotPasse;
	
    //Création des conteneurs
	private JPanel conteneurTitre = new JPanel();
	private JPanel conteneurInfos = new JPanel();
	private JPanel conteneurBouton = new JPanel();
	private JPanel conteneurMain = new JPanel();
	
    //Création d'un bouton Fermer pour fermer le programme, qui va s'afficher vers le bas du fenêtre
	private JButton boutonFermer = new JButton("Fermer");
	
	//Constructeur de la classe
	ConfirmationCompte(int numeroCompte, String motPasse){
		this.setTitle("Confirmation"); //titre de cette fenêtre
		this.setSize(280,240); //taille de cette fenêtre
		
		//Assigner les mots que les étiquettes doivent montrer sur la fenêtre
		labelNumeroCompte = new JLabel("Numéro du Compte : " + numeroCompte);
		labelMotPasse = new JLabel("Mot de Passe : " + motPasse);
		
		//Changements apportés à l'espace de texte réservé à l'affichage des détails des comptes
		textConfirmation.setEditable(false); //rendre l'espace des détails des comptes inéditable pour l'utilisateur
		textConfirmation.setSize(240, 80); //donner une taille précis à l'esapace pour afficher le message de confirmation
		textConfirmation.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4)); //espacement autour du texte
		labelNumeroCompte.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); //espacement autour de l'étiquette
		
		//Changements apportés aux couleurs du titre et la police de caractère
		labelTitre.setFont(new Font("Cambria", Font.BOLD, 16));
		labelTitre.setForeground(Color.decode("#505597")); //5D62AB
		textConfirmation.setFont(new Font("Cambria", Font.PLAIN, 12));
				
        //Ajout des composants dans les conteneurs appropriés
		conteneurTitre.add(labelTitre);
		conteneurInfos.add(textConfirmation, new FlowLayout());
		conteneurInfos.add(labelNumeroCompte, new FlowLayout());
		conteneurInfos.add(labelMotPasse, new FlowLayout());
		conteneurBouton.add(boutonFermer);
		
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
