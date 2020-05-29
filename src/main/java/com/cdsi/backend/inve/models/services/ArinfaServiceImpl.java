package com.cdsi.backend.inve.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cdsi.pven.app.model.Arinfa;
import com.cdsi.pven.app.model.IdArinfa;
import com.cdsi.pven.app.repository.IArinfaRepository;
import com.cdsi.pven.app.service.IArinfaService;

@Service
public class ArinfaServiceImpl implements IArinfaService {

	@Autowired
	private IArinfaRepository afRepo;

	@Override
	public List<Arinfa> getAllArinfas() {
		// TODO Auto-generated method stub
		List<Arinfa> objAfs = new ArrayList<Arinfa>();
		afRepo.findAll().iterator().forEachRemaining(objAfs::add);
		return objAfs;
	}

	@Override
	public Arinfa createArinfa(Arinfa objAf) {
		// TODO Auto-generated method stub
		return afRepo.save(objAf);
	}

	@Override
	public Arinfa updateArinfa(String cia, String tipo, String clase, String categoria, String codigo, Arinfa objAf) {
		// TODO Auto-generated method stub
		Arinfa objA = findArinfa(cia, tipo, clase, categoria, codigo);
		objA.setIdArfa(objAf.getIdArfa());
		objA.setDescripcion(objAf.getDescripcion());
		objA.setEstado(objAf.getEstado());
		
		return objA;
	}

	@Override
	public void deleteArinfa(String cia, String tipo, String clase, String categoria, String codigo) {
		// TODO Auto-generated method stub
		afRepo.delete(findArinfa(cia, tipo, clase, categoria, codigo));
	}

	@Override
	public Arinfa findArinfa(String cia, String tipo, String clase, String categoria, String codigo) {
		IdArinfa objAf = new IdArinfa(cia, tipo, clase, categoria, codigo);
		return  afRepo.findByIdArfa(objAf);
	}

	@Override
	public Page<Arinfa> findAll(Pageable pageable, String cia) {
		// TODO Auto-generated method stub
		return afRepo.findAll(pageable, cia);
	}
	

}
