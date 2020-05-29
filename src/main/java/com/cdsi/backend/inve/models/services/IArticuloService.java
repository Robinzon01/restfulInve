package com.cdsi.backend.inve.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cdsi.backend.inve.models.entity.Articulo;
import com.cdsi.backend.inve.models.entity.IdArticulo;

public interface IArticuloService {

	List<Articulo> getAllArticulos();

	Articulo createArticulo(Articulo articulo);

	Articulo updateArticulo(IdArticulo objIdAr, Articulo objA);

	void deleteArticulo(IdArticulo objIdAr);
	
	//BUSCAR UN ARTICULO
	Articulo findArticulo(IdArticulo objIdAr);
	
	//Pagination
    Page<Articulo> findAll(Pageable pageable,String cia);
    
    //Pagination
    Page<Articulo> findAllArti(Pageable pageable,String cia,String codigo);
    
    //VAMOS A BUSCAR UNOS ARTICULOS POR SU CODIGO
    List<Articulo> findCodArti(String cia,String codigo);
    
    //VAMOS A BUSCAR UN ARTICULOS POR SU DESCRIPCION
    List<Articulo> likeDescripArti(String cia,String descrip);
}
