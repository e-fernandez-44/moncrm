package efe.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efe.crm.bean.Societe;
import efe.crm.dao.SocieteDao;

@Service
public class SocieteService {

	@Autowired
	SocieteDao sDao;
	
	public List<Societe> listerSocietes(){
		return sDao.findAll();
	}
	
	public List<Societe> listerSocietesParNomAscVilleAsc(){
		return sDao.findByOrderByNomAscAdresseVilleAsc();
	}
	public List<Societe> listerSocietesParNomDescVilleAsc(){
		return sDao.findByOrderByNomDescAdresseVilleAsc();
	}
	public List<Societe> listerSocietesParVilleAscNomAsc(){
		return sDao.findByOrderByAdresseVilleAscNomAsc();
	}
	public List<Societe> listerSocietesParVilleDescNomAsc(){
		return sDao.findByOrderByAdresseVilleDescNomAsc();
	}
	
	public List<Societe> rechercheSocietes(String txt, String tri){
		List<Societe> liste = null;
		txt = "%" + txt.trim() + "%";
		switch (tri) {
		case "nA" : liste = sDao.findByNomLikeOrAdresseVilleLikeOrderByNomAscAdresseVilleAsc(txt, txt); break;
		case "nD" : liste = sDao.findByNomLikeOrAdresseVilleLikeOrderByNomDescAdresseVilleAsc(txt, txt); break;
		case "vA" : liste = sDao.findByNomLikeOrAdresseVilleLikeOrderByAdresseVilleAscNomAsc(txt, txt); break;
		case "vD" : liste = sDao.findByNomLikeOrAdresseVilleLikeOrderByAdresseVilleDescNomAsc(txt, txt); break;
		default : liste = sDao.findByNomLikeOrAdresseVilleLikeOrderByNomAscAdresseVilleAsc(txt, txt);
		}

			
		return liste;
	}
	
	public void ajouterSociete(Societe s) {
		sDao.save(s);
	}

	public void modifierSociete(Societe s) {
		sDao.save(s);
	}
	

	public Societe chargerSociete(int id) {
		return sDao.findOne(id);
	}

	public void supprimerSociete(Societe s) throws Exception{
		sDao.delete(s.getId());
	}
	public void supprimerSociete(int id) throws Exception {
		sDao.delete(id);
	}

	public long compterSociete(){
		return sDao.count();
	}
}
