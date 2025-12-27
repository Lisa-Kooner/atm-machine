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

public class Crediter extends JFrame{

	float argent; //Variable d'instance
	
	//Création des étiquettes et des textes pour afficher le titre, les noms des rangées, la consigne et l'espace pour écrire
	private final JLabel labelTitre = new JLabel("Créditer de l'argent");
	private final JTextArea textConsigne = new JTextArea("Écrivez le montant d'argent que vous voulez ajouté au \n"
			+ "compte dont vous avez été connecté précédemment.");
	private final JLabel labelArgent = new JLabel("Montant d'argent à ajouter ($):");
	private final JLabel labelSousArgent = new JLabel("(Format 0.00)");
	private final JTextField textArgent = new JTextField();
	
	//Création d'un bouton Créditer pour ajouter l'argent au compte
	private final JButton boutonCrediter = new JButton("Créditer");
	
    //Création des conteneurs
	private final JPanel conteneurMain = new JPanel();
	private final JPanel conteneurInfos = new JPanel();
	private final JPanel conteneurTitre = new JPanel();
	private final JPanel conteneurBouton = new JPanel();
	
	//Contructeur
	Crediter(int numeroCompte, ArrayList<Client> listeCompte){
		this.setTitle("Créditer"); //titre
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
		conteneurBouton.add(boutonCrediter);
		
		//Ajout des sous-conteneurs dans le conteneur principal
		conteneurMain.setLayout(new BorderLayout());
		conteneurMain.add(conteneurTitre, BorderLayout.NORTH);
		conteneurMain.add(conteneurInfos, BorderLayout.CENTER);
		conteneurMain.add(conteneurBouton, BorderLayout.SOUTH);
		
    	//Action exécutée lorsque le bouton Crediter est appuyé par l'utilisateur
		boutonCrediter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==boutonCrediter) {
					//Variables
					String inputArgent = textArgent.getText();
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
								c.setSolde(c.getSolde() + argent); //calcul effectué
								dispose();
								JOptionPane.showMessageDialog(null, "Le montant d'argent a été ajouté au compte avec succès!", "Confirmation - Créditer", 
										JOptionPane.INFORMATION_MESSAGE); //message d'erreur qui va s'apparaître
								break;
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