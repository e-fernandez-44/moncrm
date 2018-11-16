package efe.crm.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Action implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;

	@Temporal(TemporalType.DATE)
	protected Date dateCreation;
	@Temporal(TemporalType.DATE)
	protected Date dateLimite;
	
	protected String tache;
	
	@Column(name="isEffectue")
	protected boolean effectue;
	
	 @Enumerated(EnumType.STRING)
	protected Association association;

	 @JoinColumn(name="societeId")
	 @ManyToOne
	protected Societe societe;
	
	@JoinColumn(name="contactId")
	 @ManyToOne
	protected Contact contact;
	
	@JoinColumn(name="affaireId")
	 @ManyToOne
	protected Affaire affaire;
	
	@JoinColumn(name="factureId")
	 @ManyToOne
	protected Facture facture;
	
	 @Enumerated(EnumType.STRING)
	protected Importance importance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateLimite() {
		return dateLimite;
	}

	public void setDateLimite(Date dateLimite) {
		this.dateLimite = dateLimite;
	}

	public String getTache() {
		return tache;
	}

	public void setTache(String tache) {
		this.tache = tache;
	}

	public boolean isEffectue() {
		return effectue;
	}

	public void setEffectue(boolean isEffectue) {
		this.effectue = isEffectue;
	}

	public Societe getSociete() {
		return societe;
	}

	public void setSociete(Societe societe) {
		this.societe = societe;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Affaire getAffaire() {
		return affaire;
	}

	public void setAffaire(Affaire affaire) {
		this.affaire = affaire;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public Importance getImportance() {
		return importance;
	}

	public void setImportance(Importance importance) {
		this.importance = importance;
	}

	
	public Association getAssociation() {
		return association;
	}

	public void setAssociation(Association association) {
		this.association = association;
	}

	public Action(int id, Date dateCreation, Date dateLimite, String tache,
			boolean isEffectue, Association association, Societe societe, Contact contact,
			Affaire affaire, Facture facture, Importance importance) {
		this.id = id;
		this.dateCreation = dateCreation;
		this.dateLimite = dateLimite;
		this.tache = tache;
		this.effectue = isEffectue;
		this.societe = societe;
		this.contact = contact;
		this.affaire = affaire;
		this.facture = facture;
		this.importance = importance;
		this.association = association;
	}
	 
	public Action(Date dateCreation, Date dateLimite, String tache,
			boolean isEffectue, Association association, Societe societe, Contact contact,
			Affaire affaire, Facture facture, Importance importance) {
		this.dateCreation = dateCreation;
		this.dateLimite = dateLimite;
		this.tache = tache;
		this.effectue = isEffectue;
		this.societe = societe;
		this.contact = contact;
		this.affaire = affaire;
		this.facture = facture;
		this.importance = importance;
		this.association = association;
	}
	
	public Action() {
		this.tache = "";
		this.effectue = false;
		this.societe = null;
		this.contact = null;
		this.affaire = null;
		this.facture = null;
		this.importance = Importance.CFaible;
		this.association = Association.Autre;
	}
	

}
