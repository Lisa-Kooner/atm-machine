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
import javax.swing.JTextField;

public class Modification extends JFrame {

	int numeroCompte; //variable d'instance
	
	//Création des étiquettes pour afficher le titre et les noms des rangées
	private JLabel labelTitre = new JLabel("Modification du Mot de Passe");
	private JLabel labelNumCompte = new JLabel("Numéro du Compte : ");
	private JLabel labelNouveauMotPasse = new JLabel("Nouveau mot de passe : ");
	private JLabel labelSousMotPasse = new JLabel("(à 4 chiffres)");
	
	//Création d'un texte pour permettre à l'utilisateur d'écrire
	private JTextField textNumCompte = new JTextField();
	private JPasswordField textNouveauMotPasse = new JPasswordField();
	
	//Création d'une case à cocher et d'un étiquette, pour permettre à l'utilisateur de voir le mot de passe
	private final JLabel labelVisible = new JLabel("(Voir le mot de passe)");
	private final JCheckBox caseVisible = new JCheckBox();

	//Création d'un bouton Modifier pour modifier les infos du compte
	private final JButton boutonModifier = new JButton("Modifier");
		
    //Création des conteneurs
	private final JPanel conteneurMain = new JPanel();
	private final JPanel conteneurTitre = new JPanel();
	private final JPanel conteneurInfos = new JPanel();
	private final JPanel conteneurBouton = new JPanel();
	
	//Constructeur
	Modification(ArrayList<Client> listeCompte){
		this.setTitle("Modification"); //titre
		this.setSize(355, 255); //taille
		
		//Changements apportés à l'aspect visuel des composantes
		labelTitre.setForeground(Color.decode("#505597"));
		labelTitre.setFont(new Font("Cambria", Font.BOLD, 16));
		labelSousMotPasse.setFont(new Font("Cambria", Font.PLAIN, 12));
		
		//Placement des composantes du conteneur Infos
		labelNumCompte.setBounds(30, 15, 140, 30);
		textNumCompte.setBounds(175, 15, 140, 30);
		labelNouveauMotPasse.setBounds(30, 49, 140, 37);
		labelSousMotPasse.setBounds(30, 72, 140, 20 );
		textNouveauMotPasse.setBounds(175, 60, 140, 30);
		labelVisible.setBounds(80, 94, 140, 30);
		caseVisible.setBounds(225, 94, 140, 30);
		
    	//Action exécutée lorsque la case Visible est cochée par l'utilisateur
		caseVisible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(caseVisible.isSelected()) {
					textNouveauMotPasse.setEchoChar((char)0);
				}else {
					textNouveauMotPasse.setEchoChar('\u2022');
				}
			}
		});
		
    	//Action exécutée lorsque le bouton Modifier est appuyé par l'utilisateur
		boutonModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==boutonModifier) {
					//Variables
					boolean numCompteValide = false;
					boolean motPasseValide = false;
					String nouveauMotPasse = textNouveauMotPasse.getText();
					String numCompte = textNumCompte.getText();
					
					//Exécution du code ci-dessous si elle ne répond pas aux exigences spécifiques
					if(numCompte.matches("\\d+") && numCompte.length()==4) {
						for(Client c : listeCompte) {
							if(c.getNumeroCompte()==Integer.parseInt(textNumCompte.getText())) {
								numeroCompte = Integer.parseInt(textNumCompte.getText());
								numCompteValide = true;
								if(nouveauMotPasse.length()==4 && (nouveauMotPasse.matches("\\d+"))) {	
									motPasseValide = true;
									c.setMotPasse(nouveauMotPasse);
									dispose();
									JOptionPane.showMessageDialog(null, "Le mot de passe a été modifié avec succès!", "Confirmation de la Modification", 
											JOptionPane.INFORMATION_MESSAGE); //affichage d'un message de confirmation
								}
								break;
							}
						}
							
					}
					//Exécution du code ci-dessous si elle ne répond pas aux exigences spécifiques
					if(!numCompteValide) {
						JOptionPane.showMessageDialog(null, "Le numéro de compte que vous avez écrit est invalide. \n"
								+ "S'il-vous-plaît, veuillez essayer encore.", "Erreur de Numéro de Compte", 
								JOptionPane.ERROR_MESSAGE); //message d'erreur qui va s'apparaître
			                textNumCompte.setText("");
			                return;
			        }
					if(!motPasseValide) {
						JOptionPane.showMessageDialog(null, "Le mot de passe que vous avez écrit ne respecte pas les critères : assurer-vous\n"
								+ "d'avoir exactement 4 chiffres. Veuillez réécrire un mot de passe s'il-vous-plaît.", "Erreur de Mot de Passe", 
								JOptionPane.ERROR_MESSAGE); //message d'erreur qui va s'apparaître
						textNouveauMotPasse.setText(""); //reset le champ de texte
					}
				}
			}
		});
		
        //Ajout des composants dans les conteneurs appropriés
		conteneurInfos.setLayout(null);
		conteneurTitre.add(labelTitre);
		conteneurInfos.add(labelNumCompte);
		conteneurInfos.add(textNumCompte);
		conteneurInfos.add(labelNouveauMotPasse);
		conteneurInfos.add(labelSousMotPasse);
		conteneurInfos.add(textNouveauMotPasse);
		conteneurInfos.add(labelVisible);
		conteneurInfos.add(caseVisible);
		conteneurBouton.add(boutonModifier);
		
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
