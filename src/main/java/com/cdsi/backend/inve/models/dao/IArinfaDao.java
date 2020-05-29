package com.cdsi.backend.inve.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdsi.backend.inve.models.entity.Arinfa;
import com.cdsi.backend.inve.models.entity.IdArinfa;

@Repository
public interface IArinfaDao extends PagingAndSortingRepository<Arinfa,IdArinfa> {
	
	//VAMOS A TRAER TODAS LAS SUB LINEAS DE UN ARTICULO
	@Query("SELECT a FROM Arinfa a WHERE a.idArfa.cia = :cia")
	Page<Arinfa> findAll(Pageable pageable,@Param("cia") String cia);

	//VAMOS A BUSCAR UNA FAMILIA DE ARTICULOS
	Arinfa findByIdArfa(IdArinfa idArfa);
	
}
