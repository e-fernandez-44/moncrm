package efe.crm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import efe.crm.bean.Contact;

public interface ContactDao  extends JpaRepository<Contact, Integer> {

	List<Contact> findByOrderByNomAscPrenomAsc();
	List<Contact> findByOrderByNomDescPrenomDesc();
	List<Contact> findByOrderBySocieteNomAscSocieteAdresseVilleAscNomAsc();
	List<Contact> findByOrderBySocieteNomDescSocieteAdresseVilleDescNomAsc();
	
	List<Contact> findBySocieteIdOrderByNom(int id);
	
	
	List<Contact> findByNomLikeOrPrenomLikeOrSocieteNomLikeOrSocieteAdresseVilleLikeOrderByNomAscPrenomAsc(String s1, String s2, String s3, String s4);
	List<Contact> findByNomLikeOrPrenomLikeOrSocieteNomLikeOrSocieteAdresseVilleLikeOrderByNomDescPrenomDesc(String s1, String s2, String s3, String s4);
	List<Contact> findByNomLikeOrPrenomLikeOrSocieteNomLikeOrSocieteAdresseVilleLikeOrderBySocieteNomAscSocieteAdresseVilleAscNomAsc(String s1, String s2, String s3, String s4);
	List<Contact> findByNomLikeOrPrenomLikeOrSocieteNomLikeOrSocieteAdresseVilleLikeOrderBySocieteNomDescSocieteAdresseVilleDescNomAsc(String s1, String s2, String s3, String s4);

}
