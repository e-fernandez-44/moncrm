package efe.crm.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Contact implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String nom;
	private String prenom;
	private String telFixe;
	private String telMobile;
	private String fonction;
	private String email;
	
	
	@ManyToOne(targetEntity=Societe.class)
	@JoinColumn(name="societeId")
	private Societe societe;
	
	
	
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTelFixe() {
		return telFixe;
	}
	public void setTelFixe(String telFixe) {
		this.telFixe = telFixe;
	}
	public String getTelMobile() {
		return telMobile;
	}
	public void setTelMobile(String telMobile) {
		this.telMobile = telMobile;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public Societe getSociete() {
		return societe;
	}
	public void setSociete(Societe societe) {
		this.societe = societe;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Contact(int id, String nom, String prenom, String telFixe,
			String telMobile, String fonction, String email, Societe societe) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.telFixe = telFixe;
		this.telMobile = telMobile;
		this.fonction = fonction;
		this.email = email;
		this.societe = societe;
	}
	
	public Contact(String nom, String prenom, String telFixe,
			String telMobile, String fonction, String email, Societe societe) {
		this.nom = nom;
		this.prenom = prenom;
		this.telFixe = telFixe;
		this.telMobile = telMobile;
		this.fonction = fonction;
		this.email = email;
		this.societe = societe;
	}
	
	public Contact() {

	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", telFixe=" + telFixe + ", telMobile="
				+ telMobile + ", fonction=" + fonction + ", email=" + email + ", societe=" + societe + "]";
	}
	
	
}
