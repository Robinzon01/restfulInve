package com.cdsi.backend.inve.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cdsi.pven.app.model.Catalogo;
import com.cdsi.pven.app.model.IdCatalogo;
import com.cdsi.pven.app.repository.ICatalogoRepository;
import com.cdsi.pven.app.service.ICatalogoService;

@Service
public class CatalogoServiceImpl implements ICatalogoService {
	
	@Autowired
	private ICatalogoRepository cataRepo;

	@Override
	public List<Catalogo> getAllCatalogos() {
		List<Catalogo> objAs = new ArrayList<Catalogo>();
		cataRepo.findAll().iterator().forEachRemaining(objAs::add);
		return objAs;
	}

	@Override
	public Catalogo addCatalogo(Catalogo objC) {
		// TODO Auto-generated method stub
		return cataRepo.save(objC);
	}

	@Override
	public Catalogo updateCatalogo(String cia, String id, Catalogo objC) {
		Catalogo objCata = findCatalogo(cia, id);
		objCata.setIdCata(objC.getIdCata());
		objCata.setDescripcion(objC.getDescripcion());
		objCata.setEstado(objC.getEstado());
		return objCata;
	}

	@Override
	public void deleteCatalogo(String cia, String id) {
		// TODO Auto-generated method stub
		cataRepo.delete(findCatalogo(cia, id));
	}

	@Override
	public Catalogo findCatalogo(String cia, String id) {
		IdCatalogo objIdCata = new IdCatalogo(cia,id);
		return cataRepo.findByIdCata(objIdCata);
	}

	@Override
	public Page<Catalogo> findAll(Pageable pageable, String cia) {
		// TODO Auto-generated method stub
		return cataRepo.findAll(pageable,cia);
	}

}
