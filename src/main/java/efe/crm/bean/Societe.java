 package efe.crm.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Societe implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String nom;
	
	@Valid
	@OneToOne(targetEntity=Adresse.class, cascade = CascadeType.ALL)
	@JoinColumn(name="adresseId")
	protected Adresse adresse;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public Societe(int id, String nom, Adresse adresse) {
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
	}
	public Societe(String nom, Adresse adresse) {
		this.nom = nom;
		this.adresse = adresse;
	}
	public Societe() {
	}
	@Override
	public String toString() {
		return "Societe [id=" + id + ", nom=" + nom + ", adresse=" + adresse + "]";
	}
	
	
}
