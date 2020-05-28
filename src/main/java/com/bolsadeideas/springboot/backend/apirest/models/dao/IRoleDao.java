package com.bolsadeideas.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Role;

@Repository
public interface IRoleDao extends CrudRepository<Role, Long>{

	@Query("SELECT r FROM Role r")
	List<Role> findRolesUnicos();
}
