package efe.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import efe.crm.bean.Facture;

public interface FactureDao  extends JpaRepository<Facture, Integer> {

	List<Facture> findByNumeroNotContainingOrderByNumeroDesc(String num);
	List<Facture> findByNumeroStartingWithOrderByNumeroDesc(String num);
	
	
	List<Facture> findByOrderByNumeroDesc();
	List<Facture> findByOrderByNumeroAsc();
	List<Facture> findByOrderByAffaireNomAscAffaireDebutAsc();
	List<Facture> findByOrderByAffaireNomDescAffaireDebutDesc();
	
	List<Facture> findByNumeroLikeOrAffaireNomLikeOrAffaireContactNomLikeOrAffaireContactPrenomLikeOrAffaireContactSocieteNomLikeOrderByNumeroDesc(String s1,String s2,String s3,String s4,String s5);
	List<Facture> findByNumeroLikeOrAffaireNomLikeOrAffaireContactNomLikeOrAffaireContactPrenomLikeOrAffaireContactSocieteNomLikeOrderByNumeroAsc(String s1,String s2,String s3,String s4,String s5);
	List<Facture> findByNumeroLikeOrAffaireNomLikeOrAffaireContactNomLikeOrAffaireContactPrenomLikeOrAffaireContactSocieteNomLikeOrderByAffaireNomAscAffaireDebutAsc(String s1,String s2,String s3,String s4,String s5);
	List<Facture> findByNumeroLikeOrAffaireNomLikeOrAffaireContactNomLikeOrAffaireContactPrenomLikeOrAffaireContactSocieteNomLikeOrderByAffaireNomDescAffaireDebutDesc(String s1,String s2,String s3,String s4,String s5);

	
	//Liste des id d'affaires facturées
	@Query("select f.affaire.id from Facture f")
	List<Integer> findAffaireId();
}
