package efe.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efe.crm.bean.Affaire;
import efe.crm.bean.Frais;
import efe.crm.dao.FraisDao;


@Service
public class FraisService {
	

	
	@Autowired
	FraisDao dao;

	
	public Frais chargerFrais(int id) {
		return dao.findOne(id);
	}

	public List<Affaire> listerAffairesAyantFrais(){
		return dao.listeAffaireAyantFrais();
	}



	public List<Affaire> rechercheAffairesFrais(String txt) {
		List<Affaire> liste = null;
		txt = "%" + txt.trim() + "%";
		liste = dao.rechercheAffaireAyantFrais(txt, txt, txt); 
		
		return liste;
	}



	public List<Frais> listerFraisAffaire(int index) {
		
		return dao.findByAffaireIdOrderByDateDesc(index);
	}



	public List<Frais> listerFraisDivers() {
		
		List<Frais> liste = dao.findByAffaireIsNullOrderByDateDesc();
		
		return liste;
	}
	
	public void modifierFrais(Frais f) {
		dao.save(f);
	}

	public void supprimerFrais(Frais f) {
		dao.delete(f.getId());
	}
	public void supprimerFrais(int id) {
		dao.delete(id);
	}

	public void ajouterFrais(Frais f) {
		dao.save(f);
	}

	
	public double totalFraisByAffaire(int id){
		double res = 0.0f;
		
		List<Frais> liste = listerFraisAffaire(id);
		for (Frais f : liste){
			res += f.getMontantTTC();
		}
		return res;
	}
/*	
	@PersistenceContext(unitName = "JPA_PROJECT")
	private EntityManager entityManager = Persistence
			.createEntityManagerFactory("JPA_PROJECT").createEntityManager();


	public void creerFrais(Frais f) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(f);
		transaction.commit();
	}
	
	public List<Frais> listerFrais(){
		return entityManager.createQuery("SELECT f FROM Frais f", Frais.class).getResultList();
	}

	public List<Frais> listerFraisByDateDesc(){
		return entityManager.createQuery("SELECT f FROM Frais f order by f.date DESC", Frais.class).getResultList();
	}

	public List<Frais> listerFraisDiversByDateDesc(){
		return entityManager.createQuery("SELECT f FROM Frais f where f.affaire IS NULL order by f.date DESC", Frais.class).getResultList();
	}

	public List<Frais> listerFraisAffairesByDateDesc(){
		return entityManager.createQuery("SELECT f FROM Frais f where f.affaire IS NOT NULL order by f.date DESC", Frais.class).getResultList();
	}

	public void modifierFrais(Frais f) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(f);
		transaction.commit();
	}

	public void supprimerFrais(Frais f) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		f = entityManager.merge(f);
		entityManager.remove(f);
		transaction.commit();
	}
	public void supprimerFrais(int id) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Frais f = entityManager.find(Frais.class, id);
		entityManager.remove(f);
		transaction.commit();
	}

	public Frais chargerFrais(int id) {
		return entityManager.find(Frais.class, id);
	}


	*/
}
