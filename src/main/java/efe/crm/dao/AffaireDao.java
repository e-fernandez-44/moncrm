package efe.crm.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import efe.crm.bean.Affaire;

public interface AffaireDao  extends JpaRepository<Affaire, Integer> {

	List<Affaire> findByOrderByDebutDesc();
	List<Affaire> findByOrderByDebutAsc();
	
	// tri
	List<Affaire> findByOrderByNomDesc();
	List<Affaire> findByOrderByNomAsc();
	List<Affaire> findByOrderByLieuDesc();
	List<Affaire> findByOrderByLieuAsc();
	List<Affaire> findByOrderByContactNomDesc();
	List<Affaire> findByOrderByContactNomAsc();

	
	List<Affaire>  findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByDebutDesc(String s, String s2, String s3);
	List<Affaire>  findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByDebutAsc(String s, String s2, String s3);
	List<Affaire>  findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByNomDesc(String s, String s2, String s3);
	List<Affaire>  findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByNomAsc(String s, String s2, String s3);
	List<Affaire>  findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByLieuDesc(String s, String s2, String s3);
	List<Affaire>  findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByLieuAsc(String s, String s2, String s3);
	List<Affaire>  findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByContactNomDesc(String s, String s2, String s3);
	List<Affaire>  findByNomLikeOrContactNomLikeOrContactSocieteNomLikeOrderByContactNomAsc(String s, String s2, String s3);
	
	List<Affaire> findByIsAnnuleFalseAndIdNotInOrderByDebutAsc(Collection<Integer> ids);
	
	
	List<Affaire> findByIsAnnuleFalseOrderByDebutDesc();
	List<Affaire>  findByIsAnnuleFalseAndPlusDeFraisFalseOrderByDebutDesc();
}
