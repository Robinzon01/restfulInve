package com.cdsi.backend.inve.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cdsi.backend.inve.models.entity.Articulo;
import com.cdsi.backend.inve.models.services.IArticuloService;

@CrossOrigin(origins = {"*"}, methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/api/arti")
public class ArticuloController {
	
	@Autowired
	private IArticuloService artiServi;
	
	//METODO QUE ENVIA UNA PAGINACION DE RGTACDE
  	@GetMapping("/list/page/{cia}/{page}")
  	@Secured({"ROLE_ADMIN","ROLE_VENDEDOR","ROLE_USER"})
  	public Page<Articulo> pagiArticulos(@PathVariable("cia") String cia, @PathVariable("page") Integer page ){
  		Pageable pageable = PageRequest.of(page, 15);
  		return artiServi.findAll(pageable, cia);
  	}

}
