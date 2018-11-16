package efe.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import efe.crm.bean.Affaire;
import efe.crm.bean.Frais;


public interface FraisDao  extends JpaRepository<Frais, Integer>{
	
	
	List<Frais> findByAffaireIdOrderByDateDesc(int id);
	List<Frais> findByAffaireIsNullOrderByDateDesc();

	
	//Liste des affaires ayant des frais
	@Query("select distinct f.affaire from Frais f order by f.affaire.debut desc")
	List<Affaire> listeAffaireAyantFrais();
	
	@Query("select distinct f.affaire from Frais f where f.affaire.nom like ? OR f.affaire.lieu like ? or f.affaire.contact.societe.nom like ? order by f.affaire.debut desc")
	List<Affaire> rechercheAffaireAyantFrais(String s1, String s2, String s3);
	
	
	
}
