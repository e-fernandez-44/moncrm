package efe.crm.controller.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import efe.crm.bean.Frais;
import efe.crm.dao.FraisDao;


@RestController
@RequestMapping("fraisrs")
public class FraisWS {

	@Autowired
	FraisDao fDao;
	
	@RequestMapping("{id}/{valid}")
	public void modifyFrais(@PathVariable("id")int id, @PathVariable("valid")boolean valid){
		
		Frais f = fDao.findOne(id);
		f.setValidate(valid);
		fDao.save(f);
	}
}
