package efe.crm.controller;

import java.util.Enumeration;
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

import efe.crm.bean.Contact;
import efe.crm.bean.Societe;
import efe.crm.service.ContactService;
import efe.crm.service.SocieteService;

@Controller
@RequestMapping(path="/societes")
public class SocieteController {

	@Autowired
	private SocieteService ss;
	
	@Autowired
	private ContactService cs;
	
	@RequestMapping(path="/liste", method=RequestMethod.GET)
	public ModelAndView afficherSocietes(){
		List<Societe> listeS = ss.listerSocietesParNomAscVilleAsc();
		return new ModelAndView("listeSocietes", "listeS", listeS);
	}
	
	@RequestMapping(path="/tri", method=RequestMethod.GET)
	public ModelAndView afficherSocietesTries(String par){
		List<Societe> listeS = null;
		switch (par) {
		case "nA" : listeS = ss.listerSocietesParNomAscVilleAsc(); break;
		case "nD" : listeS = ss.listerSocietesParNomDescVilleAsc(); break;
		case "vA" : listeS = ss.listerSocietesParVilleAscNomAsc(); break;
		case "vD" : listeS = ss.listerSocietesParVilleDescNomAsc(); break;
		default : listeS = ss.listerSocietesParNomAscVilleAsc();
		}
		return new ModelAndView("listeSocietes", "listeS", listeS);
	}
	
	
	
	@RequestMapping(path="/voir", method=RequestMethod.GET)
	public ModelAndView voirSociete(int index, String message, HttpServletRequest request){
		
		
		Societe s = ss.chargerSociete(index);
		ModelAndView mav = new ModelAndView("voirSociete", "soc", s);
		List<Contact> listeC = cs.listerContactsBySocieteOrderByNom(index);
		mav.addObject("listeC", listeC);
		if (message != null)
			mav.addObject("message", message);
		
		if (request != null) {
			String ref = request.getHeader("Referer");
			System.out.println("    Referer : " + ref);
			mav.addObject("referer", ref);
		}

		return mav;
	}
	
	
	@RequestMapping(path="/recherche", method=RequestMethod.GET)
	public ModelAndView rechercheSocietes(String txt, String tri){
		txt = txt.trim();
		if (txt.length() == 0) {
			return afficherSocietesTries(tri);
		}
		else {
			List<Societe> listeS = ss.rechercheSocietes(txt, tri);
			ModelAndView mav =  new ModelAndView("listeSocietes", "listeS", listeS);
			mav.addObject("txt", txt);
			return mav;
			
		}
	}
	
	@RequestMapping(path="/ajout", method=RequestMethod.GET)
	public ModelAndView ajoutSociete(){
		Societe s = new Societe();
		ModelAndView mav = new ModelAndView("ajouterSociete", "soc", s);
		return mav;
	}
	
	@RequestMapping(path="/modif", method=RequestMethod.GET)
	public ModelAndView modifSociete(int index){
		Societe s = ss.chargerSociete(index);
		ModelAndView mav = new ModelAndView("ajouterSociete", "soc", s);
		
		return mav;
	}
	
	@RequestMapping(path="/suppr", method=RequestMethod.POST)
	public ModelAndView supprSociete(int index){
		try {
			ss.supprimerSociete(index);
			return afficherSocietes();
		} catch (Exception e) {
			return voirSociete(index, "Société impossible à supprimer (existance de contacts ou d'actions associées).", null);
		}
		
	}
	
	@RequestMapping(value="/ajout", method=RequestMethod.POST)
	public ModelAndView ajoutSocieteValid(@Valid @ModelAttribute("soc") Societe soc, BindingResult br){		

		System.out.println(soc);
		if(br.hasErrors())
			return new ModelAndView("ajouterSociete");
		else {
			if (soc.getId() == 0)
				ss.ajouterSociete(soc);
			else
				ss.modifierSociete(soc);
			return afficherSocietes();
		}
	}

}
