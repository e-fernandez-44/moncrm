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

import efe.crm.bean.Contact;
import efe.crm.bean.Societe;
import efe.crm.service.ContactService;
import efe.crm.service.SocieteService;

@Controller
@RequestMapping(path="/contacts")
public class ContactController {

	@Autowired
	private ContactService cs;

	@Autowired
	private SocieteService ss;

	
	@RequestMapping(path="/liste", method=RequestMethod.GET)
	public ModelAndView afficherContacts(){
		List<Contact> liste = cs.listerContactsOrderByNomPrenomAsc();
		return new ModelAndView("listeContacts", "listeC", liste);
	}

	
	@RequestMapping(path="/tri", method=RequestMethod.GET)
	public ModelAndView afficherContactsTries(String par){
		List<Contact> liste = null;
		switch (par) {
		case "nA" : liste = cs.listerContactsOrderByNomPrenomAsc(); break;
		case "nD" : liste = cs.listerContactsOrderByNomPrenomDesc(); break;
		case "sA" : liste = cs.listerContactsOrderBySocieteAsc(); break;
		case "sD" : liste = cs.listerContactsOrderBySocieteDesc(); break;
		default : liste = cs.listerContactsOrderByNomPrenomAsc();
		}
		return new ModelAndView("listeContacts", "listeC", liste);
	}

	
	@RequestMapping(path="/recherche", method=RequestMethod.GET)
	public ModelAndView rechercheSocietes(String txt, String tri){
		txt = txt.trim();
		if (txt.length() == 0) {
			return afficherContactsTries(tri);
		}
		else {
			List<Contact> liste = cs.rechercheContacts(txt, tri);
			ModelAndView mav =  new ModelAndView("listeContacts", "listeC", liste);
			mav.addObject("txt", txt);
			return mav;
		}
	}

	
	@RequestMapping(path="/ajout", method=RequestMethod.GET)
	public ModelAndView ajoutContact(){
		Contact c = new Contact();
		ModelAndView mav = new ModelAndView("ajouterContact", "contact", c);
		List<Societe> listeS = ss.listerSocietesParNomAscVilleAsc();
		mav.addObject("listeSocietes", listeS);
		return mav;
	}
	
	
	@RequestMapping(value="/ajout", method=RequestMethod.POST)
	public ModelAndView ajoutContactValid(@Valid @ModelAttribute("contact") Contact contact, BindingResult br){		

		System.out.println(contact);
		if(br.hasErrors())
			return new ModelAndView("ajouterContact", "listeSocietes", ss.listerSocietesParNomAscVilleAsc());
		else {
			if (contact.getId() == 0)
				cs.ajouterContact(contact);
			else
				cs.modifierContact(contact);
			return afficherContacts();
		}
	}
	
	
	@RequestMapping(path="/modif", method=RequestMethod.GET)
	public ModelAndView modifContact(int index){
		Contact c = cs.chargerContact(index);
		ModelAndView mav =  new ModelAndView("ajouterContact", "contact", c);
		List<Societe> listeS = ss.listerSocietesParNomAscVilleAsc();
		mav.addObject("listeSocietes", listeS);
		return mav;
	}
	
	@RequestMapping(path="/suppr", method=RequestMethod.POST)
	public ModelAndView supprContact(int index){
		try {
			cs.supprimerContact(index);
			return afficherContacts();
		} catch (Exception e) {
			return voirContact(index, "Contact impossible à supprimer (existance d'affaires ou d'actions associées).", null);
		}
		
	}
	
	
	@RequestMapping(path="/voir", method=RequestMethod.GET)
	public ModelAndView voirContact(int index, String message, HttpServletRequest request){
		Contact c = cs.chargerContact(index);
		ModelAndView mav = new ModelAndView("voirContact", "contact", c);
		if (message != null)
			mav.addObject("message", message);
		
		if (request != null) {
			String ref = request.getHeader("Referer");
			System.out.println("    Referer : " + ref);
			mav.addObject("referer", ref);
		}

		return mav;
	}

}
