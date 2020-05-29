package com.cdsi.backend.inve.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cdsi.backend.inve.models.entity.Arfamc;

public interface IArfamcService {
	
	List<Arfamc> getAllArfamc();
	Arfamc createArfamc(Arfamc objArfamc);
	Arfamc updateArfamct(String cia, Arfamc objArf);
	void deleteArfamc(String cia);
	//BUSCAMOS UN ARTICULO
	Arfamc findArfamc(String cia);
	//PAGINACION
	Page<Arfamc> findAll(Pageable pageable);

}
