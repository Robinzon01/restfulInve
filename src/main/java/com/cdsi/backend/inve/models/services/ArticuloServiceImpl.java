package com.cdsi.backend.inve.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cdsi.pven.app.model.Articulo;
import com.cdsi.pven.app.model.IdArticulo;
import com.cdsi.pven.app.repository.IArticuloRepository;
import com.cdsi.pven.app.service.IArticuloService;

@Service
public class ArticuloServiceImpl implements IArticuloService {
	
	@Autowired
	private IArticuloRepository artiRepo;

	@Override
	public List<Articulo> getAllArticulos() {
		List<Articulo> objAs = new ArrayList<Articulo>();
		artiRepo.findAll().iterator().forEachRemaining(objAs::add);
		return objAs;
	}

	@Override
	public Articulo createArticulo(Articulo articulo) {
		return artiRepo.save(articulo);
	}

	@Override
	public Articulo updateArticulo(String cia,String id, Articulo articulo) {
		// TODO Auto-generated method stub
		Articulo objA = findArticulo(cia, id);
		objA.setIdArti (articulo.getIdArti());
		objA.setCatalogo (articulo.getCatalogo());
		objA.setLinea( articulo.getLinea() );
		objA.setArincat(articulo.getArincat() );
		objA.setArinfa(articulo.getArinfa());
		objA.setArtiProve(articulo.getArtiProve());
		objA.setDescripcion(articulo.getDescripcion());
		objA.setArtiNacional(articulo.getArtiNacional());
		objA.setIndLote(articulo.getIndLote());
		objA.setMedida(articulo.getMedida());
		objA.setMarca(articulo.getMarca());
		objA.setVigente(articulo.getVigente());
		objA.setOrigen(articulo.getOrigen());
		objA.setCodClasif(articulo.getCodClasif());
		objA.setCodTipArt(articulo.getCodTipArt());
		objA.setColeccion(articulo.getColeccion());
		objA.setConceptoCta(articulo.getConceptoCta());
		objA.setIndCodBarra(articulo.getIndCodBarra());
		objA.setImpVen(articulo.getImpVen());
		objA.setTipoAfectacion(articulo.getTipoAfectacion());
		
		return objA;
	}

	@Override
	public void deleteArticulo(String cia,String id) {
		// TODO Auto-generated method stub
		artiRepo.delete( findArticulo(cia, id) );
	}
	
	@Override
	public Articulo findArticulo(String cia,String id) {
		IdArticulo objIdArti = new IdArticulo(cia,id);
		return artiRepo.findByIdArti(objIdArti);
	}


	@Override
	public Page<Articulo> findAll(Pageable pageable,String cia) {
		// TODO Auto-generated method stub
		return artiRepo.findAll(pageable,cia);
	}

	@Override
	public Page<Articulo> findAllArti(Pageable pageable, String cia, String codigo) {
		// TODO Auto-generated method stub
		return artiRepo.findAllArti(pageable, cia, codigo);
	}
	
	@Override
	public List<Articulo> findCodArti(String cia, String codigo) {
		// TODO Auto-generated method stub
		return artiRepo.findCodigoArticulo(cia, codigo);
	}

	@Override
	public List<Articulo> likeDescripArti(String cia, String descrip) {
		// TODO Auto-generated method stub
		return artiRepo.findDescripcionArticulo(cia, descrip);
	}


}
