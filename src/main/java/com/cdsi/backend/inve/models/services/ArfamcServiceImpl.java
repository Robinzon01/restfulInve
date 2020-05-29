package com.cdsi.backend.inve.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cdsi.pven.app.model.Arfamc;
import com.cdsi.pven.app.repository.IArfamcRepository;
import com.cdsi.pven.app.service.IArfamcService;

@Service
public class ArfamcServiceImpl implements IArfamcService {
	
	@Autowired
	private IArfamcRepository arfamcRepo;

	@Override
	public List<Arfamc> getAllArfamc() {
		List<Arfamc> objArs = new ArrayList<Arfamc>();
		arfamcRepo.findAll().iterator().forEachRemaining(objArs::add);
		return objArs;
	}

	@Override
	public Arfamc createArfamc(Arfamc objArfamc) {
		// TODO Auto-generated method stub
		return arfamcRepo.save(objArfamc);
	}

	@Override
	public void deleteArfamc(String cia) {
		// TODO Auto-generated method stub
		arfamcRepo.delete(findArfamc(cia));
	}

	@Override
	public Arfamc findArfamc(String cia) {
		return arfamcRepo.findByCia(cia);
	}

	@Override
	public Page<Arfamc> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return arfamcRepo.findAll(pageable);
	}

	@Override
	public Arfamc updateArfamct(String cia, String nombre, String nombreAno, String ruc, String razonSocial,
			String cuentaSol, String cuentaDol) {
		Arfamc objA = findArfamc(cia);
		objA.setCia(cia);
		objA.setCuentaDol(cuentaDol);
		objA.setCuentaSol(cuentaDol);
		objA.setNombre(nombre);
		objA.setNombreAno(nombreAno);
		objA.setRazonSocial(razonSocial);
		objA.setRuc(ruc);
		
		arfamcRepo.save(objA);
		return objA;
	}

}
