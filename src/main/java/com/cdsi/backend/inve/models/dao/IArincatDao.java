package com.cdsi.backend.inve.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdsi.backend.inve.models.entity.Arincat;
import com.cdsi.backend.inve.models.entity.IdArincat;

@Repository
public interface IArincatDao extends PagingAndSortingRepository<Arincat,IdArincat> {

	//VAMOS A TRAER TODAS LAS SUB LINEAS DE UN ARTICULO
	@Query("SELECT a FROM Arincat a WHERE a.idArinc.cia = :cia")
	Page<Arincat> findAll(Pageable pageable,@Param("cia") String cia);
	
	//VAMOS A TRAER UNA SUB LINEA
	Arincat findByIdArinc(IdArincat idArinc);
	
}
