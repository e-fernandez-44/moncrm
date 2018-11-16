package efe.crm.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Facture  implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;

	@NotBlank
	protected String numero;
	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date dateFacturation;

	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date dateEncaissementPrevu;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date dateEncaissement;
	
	protected float montant;
	protected float tva;
	protected boolean isFacture;
	protected boolean isEncaisse;
	
	@Lob
	private String moisTravailles;
	
	@JoinColumn(name="affaireId")
	@OneToOne
	protected Affaire affaire;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public Date getDateFacturation() {
		return dateFacturation;
	}


	public void setDateFacturation(Date dateFacturation) {
		this.dateFacturation = dateFacturation;
	}


	public Date getDateEncaissementPrevu() {
		return dateEncaissementPrevu;
	}


	public void setDateEncaissementPrevu(Date dateEncaissementPrevu) {
		this.dateEncaissementPrevu = dateEncaissementPrevu;
	}


	public Date getDateEncaissement() {
		return dateEncaissement;
	}


	public void setDateEncaissement(Date dateEncaissement) {
		this.dateEncaissement = dateEncaissement;
	}


	public float getMontant() {
		return montant;
	}

	public float getMontantFacture() {
		float montantTTC = montant + (tva / 100) * montant;
		return montantTTC + affaire.getFraisRemb();
	}


	public void setMontant(float montant) {
		this.montant = montant;
	}


	public float getTva() {
		return tva;
	}


	public void setTva(float tva) {
		this.tva = tva;
	}


	public boolean isFacture() {
		return isFacture;
	}


	public void setFacture(boolean isFacture) {
		this.isFacture = isFacture;
	}


	public boolean isEncaisse() {
		return isEncaisse;
	}


	public void setEncaisse(boolean isEncaisse) {
		this.isEncaisse = isEncaisse;
	}


	public Affaire getAffaire() {
		return affaire;
	}


	public void setAffaire(Affaire affaire) {
		this.affaire = affaire;
	}


	public String getMoisTravailles() {
		return moisTravailles;
	}


	public void setMoisTravailles(String moisTravailles) {
		this.moisTravailles = moisTravailles;
	}


	public Facture(int id, String numero, Date dateFacturation, Date dateEncaissementPrevu,
			Date dateEncaissement, float montant, float tva, boolean isFacture, boolean isEncaisse,
			String moisTravailles, Affaire affaire) {
		this.id = id;
		this.numero = numero;
		this.dateFacturation = dateFacturation;
		this.dateEncaissementPrevu = dateEncaissementPrevu;
		this.dateEncaissement = dateEncaissement;
		this.montant = montant;
		this.tva = tva;
		this.isFacture = isFacture;
		this.isEncaisse = isEncaisse;
		this.moisTravailles = moisTravailles;
		this.affaire = affaire;
	}

	public Facture(String numero, Date dateFacturation, Date dateEncaissementPrevu,
			Date dateEncaissement, float montant, float tva, boolean isFacture, boolean isEncaisse,
			String moisTravailles, Affaire affaire) {
		this.numero = numero;
		this.dateFacturation = dateFacturation;
		this.dateEncaissementPrevu = dateEncaissementPrevu;
		this.dateEncaissement = dateEncaissement;
		this.montant = montant;
		this.tva = tva;
		this.isFacture = isFacture;
		this.isEncaisse = isEncaisse;
		this.moisTravailles = moisTravailles;
		this.affaire = affaire;
	}

	public Facture() {
		this.numero = "";
//		this.dateFacturation = new Date();
//		this.dateEncaissementPrevu = new Date();
//		this.dateEncaissement = new Date();
		this.montant = 0.0f;
		this.tva = 20.0f;
		this.isFacture = false;
		this.isEncaisse = false;
		this.moisTravailles = "";
		this.affaire = new Affaire();
	}

	public TreeMap<String, Integer> getMapMoisTravailles(){
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		
		
		try {
			if (moisTravailles != null && moisTravailles.trim().length() != 0){
				String sep = System.getProperty("line.separator");
				
				String liste[] = moisTravailles.split(sep);
				for (int i = 0 ; i < liste.length ; i++){
					int posEgal = liste[i].indexOf("=");
					String mois = liste[i].substring(0, posEgal).trim();
					String jours = liste[i].substring(posEgal + 1).trim();
					map.put(mois, Integer.parseInt(jours));
				}
			}
		} catch (Exception e) {
			return null;
		}
		
		return map;

	}


	@Override
	public String toString() {
		return "Facture [id=" + id + ", numero=" + numero + ", dateFacturation=" + dateFacturation
				+ ", dateEncaissementPrevu=" + dateEncaissementPrevu + ", dateEncaissement=" + dateEncaissement
				+ ", montant=" + montant + ", tva=" + tva + ", isFacture=" + isFacture + ", isEncaisse=" + isEncaisse
				+ ", moisTravailles=" + moisTravailles + ", affaire=" + affaire + "]";
	}
	
}
