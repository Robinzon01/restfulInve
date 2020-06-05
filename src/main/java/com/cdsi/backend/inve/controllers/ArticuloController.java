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
  	public Page<Object> pagiArticulos(@PathVariable("cia") String cia, @PathVariable("page") Integer page ){
  		Pageable pageable = PageRequest.of(page, 8);
  		// return artiServi.findAll(pageable, cia,"1");
  		return artiServi.pagArtiPrecStock(pageable, cia,"1");
  	}
    //METODO QUE NOS DEVUELVE EL PRECIO DEL ARTICULO
  	@GetMapping("/precio/{cia}/{arti}")
  	@Secured({"ROLE_ADMIN","ROLE_VENDEDOR","ROLE_USER"})
  	public String precioArticulo(@PathVariable("cia") String cia, @PathVariable("arti") String arti ){
  		return artiServi.precioArticulo(cia, arti);
  	}
    //METODO QUE NOS DEVUELVE EL STOCK DEL ARTICULO
  	@GetMapping("/stkarti/{cia}/{arti}")
  	@Secured({"ROLE_ADMIN","ROLE_VENDEDOR","ROLE_USER"})
  	public String saldoArticulo(@PathVariable("cia") String cia, @PathVariable("arti") String arti ){
  		return artiServi.saldoArticulo(cia, arti);
  	}
    //METODO QUE NOS DEVUELVE EL STOCK COMPROMETIDO DEL ARTICULO
  	@GetMapping("/comparti/{cia}/{arti}")
  	@Secured({"ROLE_ADMIN","ROLE_VENDEDOR","ROLE_USER"})
  	public String articuloComprometido(@PathVariable("cia") String cia, @PathVariable("arti") String arti ){
  		return artiServi.saldoComprometido(cia, arti);
  	}

}
