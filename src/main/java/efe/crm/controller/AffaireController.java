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
import efe.crm.bean.AffaireOptions;
import efe.crm.bean.Contact;
import efe.crm.service.AffaireService;
import efe.crm.service.ContactService;
import efe.crm.service.FraisService;

@Controller
@RequestMapping(path="/affaires")
public class AffaireController {


	@Autowired
	private ContactService cs;
	
	@Autowired
	private FraisService frs;


	@Autowired
	AffaireOptions affOptions;
	
	@Autowired
	private AffaireService as;
	
	@RequestMapping(path="/liste", method=RequestMethod.GET)
	public ModelAndView afficherAffaires(){
		List<Affaire> liste = as.listerAffairesByDateDesc();
		ModelAndView mav =  new ModelAndView("listeAffaires", "listeA", liste);
		mav.addObject("affOptions", affOptions);
		return mav;
	}

	
	@RequestMapping(path="/validOptions", method=RequestMethod.POST)
	public ModelAndView validOptions(@ModelAttribute("affOptions") AffaireOptions affO){
		
		affOptions.copy(affO);

		return afficherAffaires();
	}

	
	@RequestMapping(path="/tri", method=RequestMethod.GET)
	public ModelAndView afficherAffairesTries(String par){
		List<Affaire> liste = null;
		switch (par) {
		case "dA" : liste = as.listerAffairesByDateAsc(); break;
		case "dD" : liste = as.listerAffairesByDateDesc(); break;
		case "aA" : liste = as.listerAffairesByNomAsc(); break;
		case "aD" : liste = as.listerAffairesByNomDesc(); break;
		case "vA" : liste = as.listerAffairesByLieuAsc(); break;
		case "vD" : liste = as.listerAffairesByLieuDesc(); break;
		case "cA" : liste = as.listerAffairesByContactAsc(); break;
		case "cD" : liste = as.listerAffairesByContactDesc(); break;
		default : liste = as.listerAffairesByDateDesc();
		}
		ModelAndView mav =  new ModelAndView("listeAffaires", "listeA", liste);
		mav.addObject("affOptions", affOptions);
		return mav;
	}

	@RequestMapping(path="/recherche", method=RequestMethod.GET)
	public ModelAndView rechercheAffaires(String txt, String tri){
		txt = txt.trim();
		if (txt.length() == 0) {
			return afficherAffairesTries(tri);
		}
		else {
			List<Affaire> liste = as.rechercheAffaires(txt, tri);
			ModelAndView mav =  new ModelAndView("listeAffaires", "listeA", liste);
			mav.addObject("affOptions", affOptions);
			mav.addObject("txt", txt);
			return mav;
		}
	}

	
	@RequestMapping(path="/ajout", method=RequestMethod.GET)
	public ModelAndView ajoutAffaire(){
		Affaire a = new Affaire();
		ModelAndView mav = new ModelAndView("ajouterAffaire", "aff", a);
		List<Contact> listeC = cs.listerContactsOrderByNomPrenomAsc();
		mav.addObject("listeContacts", listeC);
		return mav;
	}

	
	@RequestMapping(value="/ajout", method=RequestMethod.POST)
	public ModelAndView ajoutAffaireValid(@Valid @ModelAttribute("aff") Affaire aff, BindingResult br){		

		System.out.println(aff);
		
		if (aff.getDebut() != null && aff.getFin() != null && aff.getDebut().after(aff.getFin())) {
			System.out.println("Probleme de date !!!");
			br.rejectValue("fin", "error.aff", "Voir date de début");
		}
		
		if(br.hasErrors())
			return new ModelAndView("ajouterAffaire", "listeContacts", cs.listerContactsOrderByNomPrenomAsc());
		else {
			if (aff.getId() == 0)
				as.ajouterAffaire(aff);
			else
				as.modifierAffaire(aff);
			return afficherAffaires();
		}
	}

	
	
	@RequestMapping(path="/voir", method=RequestMethod.GET)
	public ModelAndView voirAffaire(int index, String message, HttpServletRequest request){
		Affaire a = as.chargerAffaire(index);
		ModelAndView mav = new ModelAndView("voirAffaire", "aff", a);
		if (message != null)
			mav.addObject("message", message);
		if (request != null) {
			String ref = request.getHeader("Referer");
			mav.addObject("referer", ref);
		}
		mav.addObject("fraisReel", frs.totalFraisByAffaire(index));
		return mav;
	}
	
	
	
	
	@RequestMapping(path="/suppr", method=RequestMethod.POST)
	public ModelAndView supprAffaire(int index){
		try {
			as.supprimerAffaire(index);
			return afficherAffaires();
		} catch (Exception e) {
			return voirAffaire(index, "Affaire impossible à supprimer : existance de dépendances (action, frais, facture).", null);
		}
		
	}
	
	
	
	@RequestMapping(path="/modif", method=RequestMethod.GET)
	public ModelAndView modifAffaire(int index){
		Affaire a = as.chargerAffaire(index);
		ModelAndView mav =  new ModelAndView("ajouterAffaire", "aff", a);
		mav.addObject("listeContacts", cs.listerContactsOrderByNomPrenomAsc());
		return mav;
	}
	
	
}
