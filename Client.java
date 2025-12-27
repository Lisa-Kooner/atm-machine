import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Client implements Serializable {

	private String nom;
	private String prenom;
	private String motPasse;
	private int numeroCompte;
	private float solde = 0;
	private static float debitMax = 1000;
	private static float decouvertMaxNegatif = -2000;
	private ArrayList<Client> listeCompte = new ArrayList<>();
	private int randomNombre;
	
	//Constructeur vide par défaut, pour que les objets soient sérialisables
	public Client(){}
	
	//Constructeur vide par défaut, pour que les objets soient sérialisables
	public Client(String nom, String prenom, String motPasse, float solde){  //, String prenom, String motPasse, float solde
		this.nom = nom;
		this.prenom = prenom;
		this.motPasse = motPasse;
		this.solde = solde;
		Random r = new Random();
		randomNombre = 1000 + r.nextInt(9000); //choix d'un nombre au hasard pour le numéro du compte
				
		//Vérifier si le numéro de compte existe déjà
		for(Client c : listeCompte) {
			if(c.getNumeroCompte()== randomNombre) {
				randomNombre = 1000 + r.nextInt(9000); //si elle existe déjà, un autre nombre sera choisi au hasard
				this.numeroCompte = randomNombre;
			}	
		}
		this.numeroCompte = randomNombre;
	}

	//Méthodes getters et setters
	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getMotPasse() {
		return motPasse;
	}

	public int getNumeroCompte() {
		return numeroCompte;
	}

	public float getSolde() {
		return solde;
	}

	public float getDebitMax() {
		return debitMax;
	}

	public float getDecouvertMaxNegatif() {
		return decouvertMaxNegatif;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public void setNumeroCompte(int numeroCompte) {
		this.numeroCompte = numeroCompte;
	}

	public void setSolde(float solde) {
		this.solde = solde;
	}

	public void setDebitMax(float debitMax) {
		this.debitMax = debitMax;
	}

	public void setDecouvertMaxNegatif(float decouvertMaxNegatif) {
		this.decouvertMaxNegatif = decouvertMaxNegatif;
	}
}
