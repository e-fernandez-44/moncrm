package efe.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import efe.crm.bean.Societe;

public interface SocieteDao  extends JpaRepository<Societe, Integer> {

	List<Societe> findByOrderByNomAscAdresseVilleAsc();
	List<Societe> findByOrderByNomDescAdresseVilleAsc();
	List<Societe> findByOrderByAdresseVilleAscNomAsc();
	List<Societe> findByOrderByAdresseVilleDescNomAsc();
	List<Societe> findByNomLikeOrAdresseVilleLikeOrderByNomAscAdresseVilleAsc(String s1, String s2);
	List<Societe> findByNomLikeOrAdresseVilleLikeOrderByNomDescAdresseVilleAsc(String s1, String s2);
	List<Societe> findByNomLikeOrAdresseVilleLikeOrderByAdresseVilleAscNomAsc(String s1, String s2);
	List<Societe> findByNomLikeOrAdresseVilleLikeOrderByAdresseVilleDescNomAsc(String s1, String s2);
}
