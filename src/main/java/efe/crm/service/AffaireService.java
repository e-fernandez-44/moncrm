package efe.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efe.crm.bean.Affaire;
import efe.crm.dao.AffaireDao;
import efe.crm.dao.FactureDao;

@Service
public class AffaireService {

	@Autowired
	AffaireDao dao;

	@Autowired
	FactureDao fDao;

	public void ajouterAffaire(Affaire a) {
		dao.save(a);
	}

	public List<Affaire> listerAffaires() {
		return dao.findAll();
	}

	public List<Affaire> listerAffairesByDateDesc() {
		return dao.findByOrderByDebutDesc();
	}

	public List<Affaire> listerAffairesByDateAsc() {
		return dao.findByOrderByDebutAsc();
	}

	public List<Affaire> listerAffairesByNomAsc() {
		return dao.findByOrderByNomAsc();
	}

	public List<Affaire> listerAffairesByNomDesc() {
		return dao.findByOrderByNomDesc();
	}

	public List<Affaire> listerAffairesByLieuAsc() {
		return dao.findByOrderByLieuAsc();
	}

	public List<Affaire> listerAffairesByLieuDesc() {
		return dao.findByOrderByLieuDesc();
	}

	public List<Affaire> listerAffairesByContactDesc() {
		return dao.findByOrderByContactNomDesc();
	}

	public List<Affaire> listerAffairesByContactAsc() {
		return dao.findByOrderByContactNomAsc();
	}

	public List<Affaire> rechercheAffaires(String txt, String tri) {
		List<Affaire> liste = null;
		txt = "%" + txt.trim() + "%";
		switch (tri) {
		case "dA":
			liste = dao.findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByDebutAsc(txt, txt, txt);
			break;
		case "dD":
			liste = dao.findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByDebutDesc(txt, txt, txt);
			break;
		case "aA":
			liste = dao.findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByNomAsc(txt, txt, txt);
			break;
		case "aD":
			liste = dao.findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByNomDesc(txt, txt, txt);
			break;
		case "vA":
			liste = dao.findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByLieuAsc(txt, txt, txt);
			break;
		case "vD":
			liste = dao.findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByLieuDesc(txt, txt, txt);
			break;
		case "cA":
			liste = dao.findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByContactNomAsc(txt, txt, txt);
			break;
		case "cD":
			liste = dao.findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByContactNomDesc(txt, txt, txt);
			break;
		default:
			liste = dao.findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByDebutDesc(txt, txt, txt);
		}

		return liste;
	}

	// public List<Affaire> listerAffairesConfirmeesByDateAsc(){
	// return entityManager.createQuery("SELECT a FROM Affaire a where
	// (a.isConfirme='1' and a.isAnnule='0' and a not in (select f.affaire from
	// Facture f)) order by a.debut ASC", Affaire.class).getResultList();
	// }
	//
	// public List<Affaire> listerAffairesConfirmeesByDateDesc(){
	// return entityManager.createQuery("SELECT a FROM Affaire a where
	// (a.isConfirme='1' and a.isAnnule='0' and a not in (select f.affaire from
	// Facture f)) order by a.debut DESC", Affaire.class).getResultList();
	// }
	//
	// public List<Affaire> listerAffairesNonAnnuleesNonFactureesByDateAsc(){
	// return entityManager.createQuery("SELECT a FROM Affaire a where
	// (a.isAnnule='0' and a not in (select f.affaire from Facture f)) order by
	// a.debut ASC", Affaire.class).getResultList();
	// }
	//
	// public List<Affaire> listerAffairesEnAttenteByDateAsc(){
	// return entityManager.createQuery("SELECT a FROM Affaire a where
	// (a.isConfirme='0' and a.isAnnule='0') order by a.debut ASC",
	// Affaire.class).getResultList();
	// }
	//
	// public List<Affaire> listerAffairesEnAttenteByDateDesc(){
	// return entityManager.createQuery("SELECT a FROM Affaire a where
	// (a.isConfirme='0' and a.isAnnule='0') order by a.debut DESC",
	// Affaire.class).getResultList();
	// }
	//
	// public List<Affaire> listerAffairesAnnuleesByDateAsc(){
	// return entityManager.createQuery("SELECT a FROM Affaire a where
	// a.isAnnule='1' order by a.debut ASC", Affaire.class).getResultList();
	// }
	//
	// public List<Affaire> listerAffairesAnnuleesByDateDesc(){
	// return entityManager.createQuery("SELECT a FROM Affaire a where
	// a.isAnnule='1' order by a.debut DESC", Affaire.class).getResultList();
	// }
	//

	public List<Affaire> listerAffairesNonAnnulesNonFactures() {

		List<Integer> listeAff = fDao.findAffaireId();

		return dao.findByIsAnnuleFalseAndIdNotInOrderByDebutAsc(listeAff);
	}
	
	public List<Affaire> listerAffairesNonAnnules() {

		return dao.findByIsAnnuleFalseOrderByDebutDesc();
	}

	public void modifierAffaire(Affaire a) {
		dao.save(a);
	}

	public void supprimerAffaire(Affaire a) {
		dao.delete(a.getId());
	}

	public void supprimerAffaire(int id) {
		dao.delete(id);
	}

	public Affaire chargerAffaire(int id) {
		return dao.findOne(id);
	}

	public List<Affaire> listerAffairesNonAnnulesNonClotures() {
		return dao.findByIsAnnuleFalseAndPlusDeFraisFalseOrderByDebutDesc();
	}

}
