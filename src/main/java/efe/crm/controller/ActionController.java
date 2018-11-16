package efe.crm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import efe.crm.bean.Action;
import efe.crm.service.ActionService;

@Controller
@RequestMapping(path="/actions")
public class ActionController {

	
	@Autowired
	ActionService as;
	
	@RequestMapping(path="/liste", method=RequestMethod.GET)
	public ModelAndView afficherActions(){
		List<Action> liste = as.listerActionNonEffectuesTriParDateLimiteDesc();
		ModelAndView mav =  new ModelAndView("listeActions", "listeA", liste);
		return mav;
	}

	
	@RequestMapping(path="/voir", method=RequestMethod.GET)
	public ModelAndView voirAction(int index, String message, HttpServletRequest request){
		Action a = as.chargerAction(index);
		ModelAndView mav = new ModelAndView("voirAction", "action", a);
		if (message != null)
			mav.addObject("message", message);
		if (request != null) {
			String ref = request.getHeader("Referer");
			mav.addObject("referer", ref);
		}
		return mav;
	}

}
