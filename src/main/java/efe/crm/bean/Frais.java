package efe.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Frais implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;

	private float montantHT;
	private float tva;
	private float montantTTC;
	
	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@NotBlank
	private String lieu;
	
	@NotBlank
	private String intitule;
	@JoinColumn(name="affaireId")
	@ManyToOne
	private Affaire affaire;
	
	private boolean validate;
	
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getTva() {
		return tva;
	}
	public void setTva(float tva) {
		this.tva = tva;
		this.montantHT = montantTTC - tva;
	}
	public float getMontantTTC() {
		return montantTTC;
	}
	public void setMontantTTC(float montantTTC) {
		this.montantTTC = montantTTC;
		this.montantHT = montantTTC - tva;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public float getMontantHT() {
		return montantHT;
	}
	
	
	public Affaire getAffaire() {
		return affaire;
	}
	public void setAffaire(Affaire affaire) {
		this.affaire = affaire;
	}
	public Frais(int id, float montantTTC, float tva, Date date, String lieu,
			String intitule, Affaire affaire, boolean validate) {
		this.id = id;
		this.montantTTC = montantTTC;
		this.tva = tva;
		this.date = date;
		this.lieu = lieu;
		this.intitule = intitule;
		this.montantHT = montantTTC - tva;
		this.affaire = affaire;
		this.validate = validate;
	}
	
	public Frais(float montantTTC, float tva, Date date, String lieu,
			String intitule, Affaire affaire, boolean validate) {
		this.montantTTC = montantTTC;
		this.tva = tva;
		this.date = date;
		this.lieu = lieu;
		this.intitule = intitule;
		this.montantHT = montantTTC - tva;
		this.affaire = affaire;
		this.validate = validate;
	}
	
	public Frais() {
		this.montantTTC = 0.0f;
		this.tva = 0.0f;
		this.lieu = "";
		this.intitule = "";
		this.montantHT = 0.0f;
		affaire = new Affaire();
		this.validate = false;
	}
	@Override
	public String toString() {
		return "Frais [id=" + id + ", montantHT=" + montantHT + ", tva=" + tva + ", montantTTC=" + montantTTC
				+ ", date=" + date + ", lieu=" + lieu + ", intitule=" + intitule + ", affaire=" + affaire
				+ ", validate=" + validate + "]";
	}
	
	
	
}
