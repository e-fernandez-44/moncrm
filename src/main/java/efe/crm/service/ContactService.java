package efe.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efe.crm.bean.Contact;
import efe.crm.bean.Societe;
import efe.crm.dao.ContactDao;

@Service
public class ContactService {
	
	
	@Autowired
	ContactDao dao;
	
	
	public void ajouterContact(Contact c) {
		dao.save(c);
	}
	
	public List<Contact> listerContacts(){
		return dao.findAll();
	}

	public List<Contact> listerContactsOrderByNomPrenomAsc(){
		return dao.findByOrderByNomAscPrenomAsc();
	}

	public List<Contact> listerContactsOrderByNomPrenomDesc(){
		return dao.findByOrderByNomDescPrenomDesc();
	}
	public List<Contact> listerContactsOrderBySocieteAsc(){
		return dao.findByOrderBySocieteNomAscSocieteAdresseVilleAscNomAsc();
	}
	
	public List<Contact> listerContactsOrderBySocieteDesc(){
		return dao.findByOrderBySocieteNomDescSocieteAdresseVilleDescNomAsc();
	}

	public List<Contact> listerContactsBySocieteOrderByNom(int societeId){
		return dao.findBySocieteIdOrderByNom(societeId);
	}
	/*
	public List<Contact> listerContactsOrderBySociete(){
		return dao.findByOrderBySocieteNom();
	}
*/
	public void modifierContact(Contact c) {
		dao.save(c);
	}

	public void supprimerContact(Contact c) {
		dao.delete(c.getId());;
	}
	public void supprimerContact(int id) {
		dao.delete(id);
	}

	public Contact chargerContact(int id) {
		return dao.findOne(id);
	}

	
	public List<Contact> rechercheContacts(String txt, String tri){
		List<Contact> liste = null;
		txt = "%" + txt.trim() + "%";
		switch (tri) {
		case "nA" : liste = dao.findByNomLikeOrPrenomLikeOrSocieteNomLikeOrSocieteAdresseVilleLikeOrderByNomAscPrenomAsc(txt, txt, txt, txt); break;
		case "nD" : liste = dao.findByNomLikeOrPrenomLikeOrSocieteNomLikeOrSocieteAdresseVilleLikeOrderByNomDescPrenomDesc(txt, txt, txt, txt); break;
		case "sA" : liste = dao.findByNomLikeOrPrenomLikeOrSocieteNomLikeOrSocieteAdresseVilleLikeOrderBySocieteNomAscSocieteAdresseVilleAscNomAsc(txt, txt, txt, txt); break;
		case "sD" : liste = dao.findByNomLikeOrPrenomLikeOrSocieteNomLikeOrSocieteAdresseVilleLikeOrderBySocieteNomDescSocieteAdresseVilleDescNomAsc(txt, txt, txt, txt); break;
		default : liste = dao.findByNomLikeOrPrenomLikeOrSocieteNomLikeOrSocieteAdresseVilleLikeOrderByNomAscPrenomAsc(txt, txt, txt, txt);
		}

			
		return liste;
	}

	
}
