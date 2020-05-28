package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.backend.apirest.models.dao.IRoleDao;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Role;

@Service
public class RoleServImpl implements IRolService {
	@Autowired
	private IRoleDao rolDoa;
	@Override
	public List<Role> rolesUnicos() {
		List<Role> roles = new ArrayList<>();
		rolDoa.findRolesUnicos().iterator().forEachRemaining( roles :: add );
		return roles;
	}
	@Override
	public Role getRol(Long id) {
		return rolDoa.findById(id).orElse(null);
	}

}
