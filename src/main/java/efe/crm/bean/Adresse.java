package efe.crm.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;


@Entity
public class Adresse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String adresse;
	private String adresse2;
	
	@Column(name="cp")
	private String codePostal;
	
	@NotBlank
	private String ville;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getAdresse2() {
		return adresse2;
	}
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public Adresse(int id, String adresse, String adresse2, String codePostal,
			String ville) {
		this.id = id;
		this.adresse = adresse;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public Adresse(String adresse, String adresse2, String codePostal,
			String ville) {
		this.adresse = adresse;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public Adresse() {
	}
	
	@Override
	public String toString() {
		return "Adresse [id=" + id + ", adresse=" + adresse + ", adresse2=" + adresse2 + ", codePostal=" + codePostal
				+ ", ville=" + ville + "]";
	}

	
	
}
