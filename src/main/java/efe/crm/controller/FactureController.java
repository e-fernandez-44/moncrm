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
import efe.crm.bean.Facture;
import efe.crm.service.AffaireService;
import efe.crm.service.FactureService;
import efe.crm.service.FraisService;
import efe.crm.service.SocieteService;

@Controller
@RequestMapping(path = "/factures")
public class FactureController {

	@Autowired
	private AffaireService as;

	@Autowired
	private SocieteService ss;

	@Autowired
	private FactureService fs;

	@Autowired
	private FraisService frs;

	@RequestMapping(path = "/liste", method = RequestMethod.GET)
	public ModelAndView afficherFactures() {
		List<Facture> liste = fs.listerFacturesByNumeroDesc();
		ModelAndView mav = new ModelAndView("listeFactures", "listeF", liste);
		return mav;
	}

	
	@RequestMapping(path="/tri", method=RequestMethod.GET)
	public ModelAndView afficherFacturesTries(String par){
		List<Facture> liste = null;
		switch (par) {
		case "nA" : liste = fs.listerFacturesByNumeroDesc(); break;
		case "nD" : liste = fs.listerFacturesByNumeroAsc(); break;
		case "aA" : liste = fs.listerFacturesByNomAsc(); break;
		case "aD" : liste = fs.listerFacturesByNomDesc(); break;
		default : liste = fs.listerFacturesByNumeroDesc();
		}
		return new ModelAndView("listeFactures", "listeF", liste);
	}
	
	@RequestMapping(path = "/recherche", method = RequestMethod.GET)
	public ModelAndView rechercheFactures(String txt, String tri) {
		txt = txt.trim();
		if (txt.length() == 0) {
			return afficherFacturesTries(tri);
		} else {
			List<Facture> liste = fs.rechercheFactures(txt, tri);
			ModelAndView mav = new ModelAndView("listeFactures", "listeF", liste);
			mav.addObject("txt", txt);
			return mav;

		}
	}

	
	@RequestMapping(path="/voir", method=RequestMethod.GET)
	public ModelAndView voirFacture(int index, String message, HttpServletRequest request){
		Facture f = fs.chargerFacture(index);
		ModelAndView mav = new ModelAndView("voirFacture", "f", f);
		if (message != null)
			mav.addObject("message", message);
		
		if (request != null) {
			String ref = request.getHeader("Referer");
			mav.addObject("referer", ref);
		}

		mav.addObject("fraisReel", frs.totalFraisByAffaire(f.getAffaire().getId()));
		
		return mav;
	}
	
	
	
	@RequestMapping(path="/ajout", method=RequestMethod.GET)
	public ModelAndView ajoutFacture(){
		Facture f = new Facture();
		ModelAndView mav = new ModelAndView("ajouterFacture", "f", f);
		List<Affaire> listeA = as.listerAffairesNonAnnulesNonFactures();
		mav.addObject("listeA", listeA);
		return mav;
	}
	
	
	
	@RequestMapping(value="/ajout", method=RequestMethod.POST)
	public ModelAndView ajoutFactureValid(@Valid @ModelAttribute("f") Facture f, BindingResult br){		

		System.out.println(f);
		
	
		if(br.hasErrors())
			return new ModelAndView("ajouterFacture", "listeA", as.listerAffairesNonAnnulesNonFactures());
		else {
			if (f.getId() == 0)
				fs.ajouterFacture(f);
			else
				fs.modifierFacture(f);
			return afficherFactures();
		}
	}

	
	
	@RequestMapping(path="/suppr", method=RequestMethod.POST)
	public ModelAndView supprFacture(int index){
		try {
			fs.supprimerFacture(index);
			return afficherFactures();
		} catch (Exception e) {
			return voirFacture(index, "Affaire impossible à supprimer : existance de dépendances (action, frais, facture).", null);
		}
	}
	
	
	@RequestMapping(path="/modif", method=RequestMethod.GET)
	public ModelAndView modifFacture(int index){
		Facture f = fs.chargerFacture(index);
		ModelAndView mav =  new ModelAndView("ajouterFacture", "f", f);
		mav.addObject("listeA", as.listerAffairesNonAnnulesNonFactures());
		return mav;
	}

}
