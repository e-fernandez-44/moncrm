package efe.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Affaire implements Serializable {
	private static final long serialVersionUID = 1L;

		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;

	@NotBlank
	private String nom;
	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date debut;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date fin;
	private int nbJours;
	private String lieu;
	private String clientFinal;
	private float fraisRemb;
	private boolean isConfirme;
	private boolean isAnnule;
	private float tarifJour;
	private boolean isHorsFrais;

	@Column(name="plusDeFrais")
	private boolean plusDeFrais;
	
	@Lob
	private String commentaire;

	@JoinColumn(name = "contactId")
	@ManyToOne
	private Contact contact;

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

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public int getNbJours() {
		return nbJours;
	}

	public void setNbJours(int nbJours) {
		this.nbJours = nbJours;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getClientFinal() {
		return clientFinal;
	}

	public void setClientFinal(String clientFinal) {
		this.clientFinal = clientFinal;
	}

	public float getFraisRemb() {
		return fraisRemb;
	}

	
	public void setFraisRemb(float fraisRemb) {
		this.fraisRemb = fraisRemb;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public boolean isConfirme() {
		return isConfirme;
	}

	public void setConfirme(boolean isConfirme) {
		this.isConfirme = isConfirme;
	}

	public boolean isAnnule() {
		return isAnnule;
	}

	public void setAnnule(boolean isAnnule) {
		this.isAnnule = isAnnule;
	}

	public float getTarifJour() {
		return tarifJour;
	}

	public void setTarifJour(float tarifJour) {
		this.tarifJour = tarifJour;
	}

	public boolean isHorsFrais() {
		return isHorsFrais;
	}

	public void setHorsFrais(boolean isHorsFrais) {
		this.isHorsFrais = isHorsFrais;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	
	
	public boolean isPlusDeFrais() {
		return plusDeFrais;
	}

	public void setPlusDeFrais(boolean plusDeFrais) {
		this.plusDeFrais = plusDeFrais;
	}

	public Affaire(int id, String nom, Date debut, Date fin, int nbJours, String lieu, Contact contact,
			String clientFinal, float tarifJour, boolean isHorsFrais, float fraisRemb, boolean isConfirme,
			boolean isAnnule, String comm, boolean plusDeFrais) {
		this.id = id;
		this.nom = nom;
		this.debut = debut;
		this.fin = fin;
		this.nbJours = nbJours;
		this.lieu = lieu;
		this.contact = contact;
		this.clientFinal = clientFinal;
		this.tarifJour = tarifJour;
		this.isHorsFrais = isHorsFrais;
		this.fraisRemb = fraisRemb;
		this.isConfirme = isConfirme;
		this.isAnnule = isAnnule;
		this.commentaire = comm;
		this.plusDeFrais = plusDeFrais;
	}

	public Affaire(String nom, Date debut, Date fin, int nbJours, String lieu, Contact contact, String clientFinal,
			float tarifJour, boolean isHorsFrais, float fraisRemb, boolean isConfirme, boolean isAnnule, String comm, boolean plusDeFrais) {
		this.nom = nom;
		this.debut = debut;
		this.fin = fin;
		this.nbJours = nbJours;
		this.lieu = lieu;
		this.contact = contact;
		this.clientFinal = clientFinal;
		this.tarifJour = tarifJour;
		this.isHorsFrais = isHorsFrais;
		this.fraisRemb = fraisRemb;
		this.isConfirme = isConfirme;
		this.isAnnule = isAnnule;
		this.commentaire = comm;
		this.plusDeFrais = plusDeFrais;
}

	public Affaire() {
		id = 0;
		this.nom = "";
		// this.debut = new Date();
		// this.fin = new Date();
		this.nbJours = 0;
		this.lieu = "";
		this.clientFinal = "";
		this.tarifJour = 0.0f;
		this.isHorsFrais = false;
		this.fraisRemb = 0.0f;
		this.isConfirme = false;
		this.isAnnule = false;
		contact = null;
		this.commentaire = "";
	}

	@Override
	public String toString() {
		return "Affaire [id=" + id + ", nom=" + nom + ", debut=" + debut + ", fin=" + fin + ", nbJours=" + nbJours
				+ ", lieu=" + lieu + ", clientFinal=" + clientFinal + ", fraisRemb=" + fraisRemb + ", isConfirme="
				+ isConfirme + ", isAnnule=" + isAnnule + ", tarifJour=" + tarifJour + ", isHorsFrais=" + isHorsFrais
				+ ", plusDeFrais=" + plusDeFrais + ", commentaire=" + commentaire + ", contact=" + contact + "]";
	}


}
