package efe.crm.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efe.crm.bean.Action;
import efe.crm.bean.Association;
import efe.crm.dao.ActionDao;


@Service
public class ActionService {
	
	
	@Autowired
	ActionDao dao;
	

	public List<Action> listerAction(){
		return dao.findAll();
	}

	public List<Action> listerActionNonEffectuesTriParDateLimiteDesc(){
		return dao.findByEffectueFalseOrderByDateLimiteDesc();
	}

	

	public void ajouterAction(Action a) {
		dao.save(a);
	}
	
	
	public void modifierAction(Action a) {
		dao.save(a);
	}

	public void supprimerAction(Action a) {
		dao.delete(a.getId());
	}
	public void supprimerAction(int id) {
		dao.delete(id);
	}
	public Action chargerAction(int id) {
		return dao.findOne(id);
	}

/*
	public void modifierActionIsEffectue(int id){
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Action a = entityManager.find(Action.class, id);
		a.setEffectue(!a.isEffectue());
		entityManager.merge(a);
		transaction.commit();
	}
	public List<Action> listerActionById(){
		return entityManager.createQuery("SELECT a FROM Action a order by a.isEffectue, a.id DESC", Action.class).getResultList();
	}

	public List<Action> listerActionNonEffById(){
		return entityManager.createQuery("SELECT a FROM Action a where a.isEffectue = '0' order by a.id DESC", Action.class).getResultList();
	}

	public List<Action> listerActionByDateLimiteAsc(){
		return entityManager.createQuery("SELECT a FROM Action a order by a.isEffectue, a.dateLimite ASC", Action.class).getResultList();
	}

	public List<Action> listerActionByDateLimiteDesc(){
		return entityManager.createQuery("SELECT a FROM Action a order by a.isEffectue, a.dateLimite DESC", Action.class).getResultList();
	}

	public List<Action> listerActionNonEffByDateLimiteAsc(){
		return entityManager.createQuery("SELECT a FROM Action a where a.isEffectue = '0' order by a.dateLimite ASC", Action.class).getResultList();
	}

	public List<Action> listerActionNonEffByDateLimiteDesc(){
		return entityManager.createQuery("SELECT a FROM Action a where a.isEffectue = '0' order by a.dateLimite DESC", Action.class).getResultList();
	}

	public List<Action> listerActionByImportanceAsc(){
		return entityManager.createQuery("SELECT a FROM Action a order by a.isEffectue, a.importance ASC, a.dateLimite DESC", Action.class).getResultList();
	}

	public List<Action> listerActionNonEffByImportanceAsc(){
		return entityManager.createQuery("SELECT a FROM Action a where a.isEffectue = '0' order by a.importance ASC, a.dateLimite DESC", Action.class).getResultList();
	}

	public List<Action> listerActionByContact(int id) {
		return entityManager.createQuery("SELECT a FROM Action a where a.contact.id = '"+id+"' order by a.id DESC", Action.class).getResultList();
	}
	

	public List<Action> listerActionByContactAssocie(int id) {
		List<Action> liste1 = listerActionById();
		List<Action> liste = new ArrayList<Action>();
		for (Action a : liste1){
			if (a.getAssociation().equals(Association.Facture) && a.getFacture().getAffaire().getContact().getId() == id)
				liste.add(a);
			else
			if (a.getAssociation().equals(Association.Affaire) && a.getAffaire().getContact().getId() == id)
				liste.add(a);
		}
		return liste;
	}

	public List<Action> listerActionByAffaire(int id) {
		return entityManager.createQuery("SELECT a FROM Action a where a.affaire.id = '"+id+"' order by a.id DESC", Action.class).getResultList();
	}

	public List<Action> listerActionByAffaireAssocie(int id) {
		return entityManager.createQuery("SELECT a FROM Action a where a.facture.affaire.id = '"+id+"'", Action.class).getResultList();
	}

	public List<Action> listerActionBySociete(int id) {
		return entityManager.createQuery("SELECT a FROM Action a where a.societe.id = '"+id+"' order by a.id DESC", Action.class).getResultList();
	}

	public List<Action> listerActionBySocieteAssocie(int id) {
		List<Action> liste1 = listerActionById();
		List<Action> liste = new ArrayList<Action>();
		for (Action a : liste1){
			if (a.getAssociation().equals(Association.Facture) && a.getFacture().getAffaire().getContact().getSociete().getId() == id)
				liste.add(a);
			else
			if (a.getAssociation().equals(Association.Affaire) && a.getAffaire().getContact().getSociete().getId() == id)
				liste.add(a);
			else
			if (a.getAssociation().equals(Association.Contact) && a.getContact().getSociete().getId() == id)
				liste.add(a);
		}
		return liste;
	}

	public List<Action> listerActionByFacture(int id) {
		return entityManager.createQuery("SELECT a FROM Action a where a.facture.id = '"+id+"'", Action.class).getResultList();
	}

	*/
	

}
