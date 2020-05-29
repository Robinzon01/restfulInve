package com.cdsi.backend.inve.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cdsi.pven.app.model.Arincat;
import com.cdsi.pven.app.model.IdArincat;
import com.cdsi.pven.app.repository.IArincatRepository;
import com.cdsi.pven.app.service.IArincatService;

@Service
public class ArincatServiceImpl implements IArincatService {
	
	@Autowired
	private IArincatRepository arinRepo;

	@Override
	public List<Arincat> getAllArincats() {
		List<Arincat> objAs = new ArrayList<Arincat>();
		arinRepo.findAll().iterator().forEachRemaining(objAs::add);
		return objAs;
	}

	@Override
	public Arincat createArincat(Arincat objAri) {
		// TODO Auto-generated method stub
		return arinRepo.save(objAri);
	}

	@Override
	public Arincat updateArincat(String cia, String tipo, String clase, String codigo, Arincat objArin) {
		Arincat objA = findArincat(cia, tipo, clase, codigo);
		objA.setIdArinc(objArin.getIdArinc());
		objA.setDescripcion(objArin.getDescripcion());
		objA.setEstado(objArin.getEstado());
		
		arinRepo.save(objA);
		return objA;
	}

	@Override
	public void deleteArincat(String cia, String tipo, String clase, String codigo) {
		arinRepo.delete(findArincat(cia, tipo, clase, codigo));
	}

	@Override
	public Arincat findArincat(String cia, String tipo, String clase, String codigo) {
		IdArincat objIdArin = new IdArincat(cia, tipo, clase, codigo);
		return arinRepo.findByIdArinc(objIdArin);
	}

	@Override
	public Page<Arincat> findAll(Pageable pageable, String cia) {
		// TODO Auto-generated method stub
		return arinRepo.findAll(pageable, cia);
	}

}
