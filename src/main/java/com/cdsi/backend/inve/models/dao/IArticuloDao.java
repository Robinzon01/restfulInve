package com.cdsi.backend.inve.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdsi.backend.inve.models.entity.Articulo;
import com.cdsi.backend.inve.models.entity.IdArticulo;

@Repository
public interface IArticuloDao extends PagingAndSortingRepository<Articulo,IdArticulo> {
	
	//METODO QUE NOS PERMITE TRAER TODOS LOS DATOS PARA LA TABLA
	@Query("SELECT a FROM Articulo a WHERE a.idArti.cia = :cia and a.idArti.noArti = :codigo")
	Page<Articulo> findAllArti(Pageable pageable,@Param("cia") String cia,@Param("codigo") String codigo);
	
	//METODO QUE NOS PERMITE TRAER TODOS LOS DATOS PARA LA TABLA
	@Query("SELECT a FROM Articulo a WHERE a.idArti.cia = :cia")
	Page<Articulo> findAll(Pageable pageable,@Param("cia") String cia);
	
	//VAMOS A TRAER UN ARTICULO
	Articulo findByIdArti(IdArticulo idArti);
	
	//VAMOS A BUSCAR O TRAER TODOS LOS CODIGOS DEL ARTICULO SEGUN COMO ESCRIBE EL CODIGO
	@Query("SELECT a FROM Articulo a WHERE a.idArti.cia = :cia AND a.idArti.noArti LIKE '%:cod%'")
	List<Articulo> findCodigoArticulo(@Param("cia") String cia,@Param("cod") String cod);
	
   //VAMOS A TRAER TODOS LOS ARTICULOS SEGUN LA DESCRIPCION DEL ARTICULO
	@Query("SELECT a FROM Articulo a WHERE a.idArti.cia = :cia AND a.descripcion LIKE %:dscri%")
	List<Articulo> findDescripcionArticulo(@Param("cia") String cia,@Param("dscri") String dscri);
	
}
