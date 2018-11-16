package efe.crm.bean;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AffaireOptions implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean annule;
	private boolean effectue;
	private boolean enAttente;
	private boolean confirme;
	private boolean enCours;
	
	public AffaireOptions() {
		enAttente = true;
		confirme = true;
		enCours = true;
	}

	public void copy(AffaireOptions ao) {
		this.annule = ao.annule;
		this.effectue = ao.effectue;
		this.enAttente = ao.enAttente;
		this.confirme = ao.confirme;
		this.enCours = ao.enCours;
	}
	
	public boolean isAnnule() {
		return annule;
	}

	public void setAnnule(boolean annule) {
		this.annule = annule;
	}

	public boolean isEffectue() {
		return effectue;
	}

	public void setEffectue(boolean effectue) {
		this.effectue = effectue;
	}

	public boolean isEnAttente() {
		return enAttente;
	}

	public void setEnAttente(boolean enAttente) {
		this.enAttente = enAttente;
	}

	public boolean isConfirme() {
		return confirme;
	}

	public void setConfirme(boolean confirme) {
		this.confirme = confirme;
	}

	public boolean isEnCours() {
		return enCours;
	}

	public void setEnCours(boolean enCours) {
		this.enCours = enCours;
	}
	
	
}
