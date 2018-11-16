package efe.crm.bean;

import java.io.Serializable;

public class SimulFrais  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	protected float trainAvionEuros = 0.0f;
	protected int voitureKm = 0;
	protected int nbJours = 0;
	protected boolean isDepartMatin = true;
	protected boolean isRetourSoir = true;
	protected float hotelEuros = 0.0f;
	protected float repasMidiEuros = 0.0f;
	protected float repasSoirEuros = 0.0f;
	protected float taxiEuros = 0.0f;
	protected float locationVoitureEuros = 0.0f;
	protected float autresFraisEuros = 0.0f;
	protected float peageEuros = 0.0f;
	
	public float getTrainAvionEuros() {
		return trainAvionEuros;
	}
	public void setTrainAvionEuros(float trainAvionEuros) {
		this.trainAvionEuros = trainAvionEuros;
	}
	public int getVoitureKm() {
		return voitureKm;
	}
	public void setVoitureKm(int voitureKm) {
		this.voitureKm = voitureKm;
	}
	public int getNbJours() {
		return nbJours;
	}
	public void setNbJours(int nbJours) {
		this.nbJours = nbJours;
	}
	public boolean isDepartMatin() {
		return isDepartMatin;
	}
	public void setDepartMatin(boolean isDepartMatin) {
		this.isDepartMatin = isDepartMatin;
	}
	public boolean isRetourSoir() {
		return isRetourSoir;
	}
	public void setRetourSoir(boolean isRetourSoir) {
		this.isRetourSoir = isRetourSoir;
	}
	public float getHotelEuros() {
		return hotelEuros;
	}
	public void setHotelEuros(float hotelEuros) {
		this.hotelEuros = hotelEuros;
	}
	public float getRepasMidiEuros() {
		return repasMidiEuros;
	}
	public void setRepasMidiEuros(float repasMidiEuros) {
		this.repasMidiEuros = repasMidiEuros;
	}
	public float getRepasSoirEuros() {
		return repasSoirEuros;
	}
	public void setRepasSoirEuros(float repasSoirEuros) {
		this.repasSoirEuros = repasSoirEuros;
	}
	public float getTaxiEuros() {
		return taxiEuros;
	}
	public void setTaxiEuros(float taxiEuros) {
		this.taxiEuros = taxiEuros;
	}
	public float getLocationVoitureEuros() {
		return locationVoitureEuros;
	}
	public void setLocationVoitureEuros(float locationVoitureEuros) {
		this.locationVoitureEuros = locationVoitureEuros;
	}
	public float getAutresFraisEuros() {
		return autresFraisEuros;
	}
	public void setAutresFraisEuros(float autresFraisEuros) {
		this.autresFraisEuros = autresFraisEuros;
	}
	public float getPeageEuros() {
		return peageEuros;
	}
	public void setPeageEuros(float peageEuros) {
		this.peageEuros = peageEuros;
	}
	
	
	
}	
