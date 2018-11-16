package efe.crm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import efe.crm.bean.Affaire;
import efe.crm.bean.Contact;
import efe.crm.bean.Facture;
import efe.crm.bean.Frais;
import efe.crm.service.AffaireService;
import efe.crm.service.FraisService;

@Controller
@RequestMapping(path="/frais")
public class FraisController {


	@Autowired
	private AffaireService as;
	
	@Autowired
	private FraisService frs;
	
	@RequestMapping(path="/liste", method=RequestMethod.GET)
	public ModelAndView afficherFrais(){
		List<Affaire> liste = frs.listerAffairesAyantFrais();
		ModelAndView mav =  new ModelAndView("listeFrais", "listeA", liste);
		return mav;
	}
	
	@RequestMapping(path="/affaire", method=RequestMethod.GET)
	public ModelAndView afficherFraisAffaire(Integer index){
		List<Frais> fraisAffaire = null;
		List<Affaire> liste = frs.listerAffairesAyantFrais();
		ModelAndView mav =  new ModelAndView("listeFrais", "listeA", liste);
		if (index != null && index != 0)
			fraisAffaire = frs.listerFraisAffaire(index);
		else
			fraisAffaire = frs.listerFraisDivers();
		mav.addObject("fraisAffaire", fraisAffaire);
		mav.addObject("index", index);
		
		return mav;
	}


	@RequestMapping(path="/recherche", method=RequestMethod.GET)
	public ModelAndView rechercheFrais(String txt){
		txt = txt.trim();
		if (txt.length() == 0) {
			return afficherFrais();
		}
		else {
			List<Affaire> liste = frs.rechercheAffairesFrais(txt);
			ModelAndView mav =  new ModelAndView("listeFrais", "listeA", liste);
			mav.addObject("txt", txt);
			return mav;
		}
	}

	
	@RequestMapping(path="/ajout", method=RequestMethod.GET)
	public ModelAndView ajoutFrais(Integer aff){
		if (aff == null)
			aff = 0;
				
		Frais f = new Frais();
		Affaire affaire = as.chargerAffaire(aff);
		f.setAffaire(affaire);
		
		ModelAndView mav = new ModelAndView("ajouterFrais", "f", f);
		List<Affaire> listeA = as.listerAffairesNonAnnulesNonClotures();
		mav.addObject("listeA", listeA);
		return mav;
	}

	
	@RequestMapping(value="/ajout", method=RequestMethod.POST)
	public ModelAndView ajoutFraisValid(@Valid @ModelAttribute("f") Frais f, BindingResult br){		

		if (f.getAffaire().getId() == 0) {
			f.setAffaire(null);
		}
		if(br.hasErrors())
			return new ModelAndView("ajouterFrais", "listeA", as.listerAffairesNonAnnulesNonClotures());
		else {
			if (f.getId() == 0)
				frs.ajouterFrais(f);
			else
				frs.modifierFrais(f);

			return afficherFraisAffaire(f.getAffaire() != null ? f.getAffaire().getId() : 0);
		}
	}


	
	@RequestMapping(path="/voir", method=RequestMethod.GET)
	public ModelAndView voirFrais(int index, String message, HttpServletRequest request){
		Frais f = frs.chargerFrais(index);
		
		ModelAndView mav = new ModelAndView("voirFrais", "f", f);
		if (message != null)
			mav.addObject("message", message);
		
		if (request != null) {
			String ref = request.getHeader("Referer");
			mav.addObject("referer", ref);
		}

		return mav;
	}
	
	@RequestMapping(path="/suppr", method=RequestMethod.POST)
	public ModelAndView supprFrais(int index){
		try {
			frs.supprimerFrais(index);
			return afficherFrais();
		} catch (Exception e) {
			return voirFrais(index, "Affaire impossible à supprimer : existance de dépendances (action, frais, facture).", null);
		}
	}
	
	
	@RequestMapping(path="/modif", method=RequestMethod.GET)
	public ModelAndView modifFrais(int index){
		Frais f = frs.chargerFrais(index);
		ModelAndView mav =  new ModelAndView("ajouterFrais", "f", f);
		mav.addObject("listeA", as.listerAffairesNonAnnulesNonClotures());
		return mav;
	}


}
