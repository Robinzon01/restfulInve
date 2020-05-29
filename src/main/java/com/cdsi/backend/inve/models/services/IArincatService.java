package com.cdsi.backend.inve.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cdsi.backend.inve.models.entity.Arincat;
import com.cdsi.backend.inve.models.entity.IdArincat;

public interface IArincatService {

	List<Arincat> getAllArincats();
	Arincat createArincat(Arincat objAri);
	Arincat updateArincat(IdArincat objIdArin, Arincat objArin);
	void deleteArincat(IdArincat objIdArin);
	//BUSCAMOS UN ARTICULO
	Arincat findArincat(IdArincat objIdArin);
	//PAGINACION
	Page<Arincat> findAll(Pageable pageable,String cia);
}
