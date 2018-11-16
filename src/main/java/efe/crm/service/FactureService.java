package efe.crm.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efe.crm.bean.Facture;
import efe.crm.bean.Affaire;
import efe.crm.bean.Contact;
import efe.crm.bean.Societe;
import efe.crm.dao.FactureDao;

@Service
public class FactureService {

	
	@Autowired
	FactureDao dao;
	
	public void ajouterFacture(Facture f) {
		dao.save(f);
	}
	
	public List<Facture> listerFactures(){
		return dao.findAll();
	}

	public List<Facture> listerFacturesByNumeroDesc(){
		List<Facture> liste = dao.findByOrderByNumeroDesc();
		List<Facture> listeBis = new ArrayList<>(); 
		for (Facture facture : liste) {
			if (facture.getNumero().startsWith("2014"))
				listeBis.add(facture);
		}
		for (Facture facture : listeBis) {
			liste.remove(facture);
			liste.add(facture);
		}
		
		return liste;
	}
	public List<Facture> listerFacturesByNumeroAsc(){
		List<Facture> liste = dao.findByOrderByNumeroAsc();
		List<Facture> listeBis = new ArrayList<>(); 
		for (Facture facture : liste) {
			if (facture.getNumero().startsWith("2014"))
				listeBis.add(facture);
		}
		for (int i = 0 ; i< listeBis.size() ; i++){
			Facture facture = listeBis.get(i);
			liste.remove(facture);
			liste.add(i, facture);
		}
		
		return liste;
	}
	
	public List<Facture> listerFacturesByNomAsc(){
		return dao.findByOrderByAffaireNomAscAffaireDebutAsc();
	}
	public List<Facture> listerFacturesByNomDesc(){
		return dao.findByOrderByAffaireNomDescAffaireDebutDesc();
	}
	
	public List<Facture> rechercheFactures(String txt, String tri){
		List<Facture> liste = null;
		txt = "%" + txt.trim() + "%";
		switch (tri) {
		case "nA" : {
					liste = dao.findByNumeroLikeOrAffaireNomLikeOrAffaireContactNomLikeOrAffaireContactPrenomLikeOrAffaireContactSocieteNomLikeOrderByNumeroAsc(txt, txt, txt, txt, txt); 
					List<Facture> listeBis = new ArrayList<>(); 
					for (Facture facture : liste) {
						if (facture.getNumero().startsWith("2014"))
							listeBis.add(facture);
					}
					for (int i = 0 ; i< listeBis.size() ; i++){
						Facture facture = listeBis.get(i);
						liste.remove(facture);
						liste.add(i, facture);
					}
					break;
					}
		case "nD" : {
					liste = dao.findByNumeroLikeOrAffaireNomLikeOrAffaireContactNomLikeOrAffaireContactPrenomLikeOrAffaireContactSocieteNomLikeOrderByNumeroDesc(txt, txt, txt, txt, txt); 
					List<Facture> listeBis = new ArrayList<>(); 
					for (Facture facture : liste) {
						if (facture.getNumero().startsWith("2014"))
							listeBis.add(facture);
					}
					for (Facture facture : listeBis) {
						liste.remove(facture);
						liste.add(facture);
					}
					break;
					}
		case "aA" : liste = dao.findByNumeroLikeOrAffaireNomLikeOrAffaireContactNomLikeOrAffaireContactPrenomLikeOrAffaireContactSocieteNomLikeOrderByAffaireNomAscAffaireDebutAsc(txt, txt, txt, txt, txt); 
					System.out.println("Liste : " + liste);
		break;
		case "aD" : liste = dao.findByNumeroLikeOrAffaireNomLikeOrAffaireContactNomLikeOrAffaireContactPrenomLikeOrAffaireContactSocieteNomLikeOrderByAffaireNomDescAffaireDebutDesc(txt, txt, txt, txt, txt); break;
		default : {
					liste = dao.findByNumeroLikeOrAffaireNomLikeOrAffaireContactNomLikeOrAffaireContactPrenomLikeOrAffaireContactSocieteNomLikeOrderByNumeroDesc(txt, txt, txt, txt, txt); 
					List<Facture> listeBis = new ArrayList<>(); 
					for (Facture facture : liste) {
						if (facture.getNumero().startsWith("2014"))
							listeBis.add(facture);
					}
					for (Facture facture : listeBis) {
						liste.remove(facture);
						liste.add(facture);
					}
					break;
					}		
		}

			
		return liste;
	}

	
/*
	public List<Facture> listerFacturesByDateFactDesc(){
		return entityManager.createQuery("SELECT f FROM Facture f order by f.dateFacturation DESC", Facture.class).getResultList();
	}

	public List<Facture> listerFacturesByDateFactAsc(){
		return entityManager.createQuery("SELECT f FROM Facture f order by f.dateFacturation ASC", Facture.class).getResultList();
	}

	public List<Facture> listerFacturesByDateEncDesc(){
		return entityManager.createQuery("SELECT f FROM Facture f order by f.dateEncaissement DESC", Facture.class).getResultList();
	}

	public List<Facture> listerFacturesByDateEncAsc(){
		return entityManager.createQuery("SELECT f FROM Facture f order by f.dateEncaissement ASC", Facture.class).getResultList();
	}

	public List<Facture> listerFacturesEncByDateEncDesc(){
		return entityManager.createQuery("SELECT f FROM Facture f where f.isEncaisse='1' order by f.dateEncaissement DESC", Facture.class).getResultList();
	}

	public List<Facture> listerFacturesEncByDateEncAsc(){
		return entityManager.createQuery("SELECT f FROM Facture f where f.isEncaisse='1' order by f.dateEncaissement ASC", Facture.class).getResultList();
	}

	public List<Facture> listerFacturesNonEncByDateEncDesc(){
		return entityManager.createQuery("SELECT f FROM Facture f where f.isEncaisse='0' order by f.dateEncaissement DESC", Facture.class).getResultList();
	}

	public List<Facture> listerFacturesNonEncByDateEncAsc(){
		return entityManager.createQuery("SELECT f FROM Facture f where f.isEncaisse='0' order by f.dateEncaissement ASC", Facture.class).getResultList();
	}
*/
	public void modifierFacture(Facture f) {
		dao.save(f);
	}

	public void supprimerFacture(Facture f) {
		dao.delete(f.getId());
	}
	public void supprimerFacture(int id) {
		dao.delete(id);
	}

	public Facture chargerFacture(int id) {
		return dao.findOne(id);
	}

	
}
