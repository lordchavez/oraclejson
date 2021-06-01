package com.web.repository;

import java.sql.Clob;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.web.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query( "SELECT c FROM Cliente c" )
	List<Cliente> findByClient();
	
	@Query( value = "SELECT c.id, c.item FROM cliente c", nativeQuery = true )
	List<Cliente> findByClientNative();
	
	@Query( value = "SELECT c.ITEM FROM KIO.CLIENTE c WHERE json_textcontains ( c.ITEM, '$.id', '1' )", nativeQuery = true )
	Clob findJson();

	@Transactional
	@Query( value = "UPDATE KIO.CLIENTE c SET c.ITEM = json_mergepatch ( c.ITEM, '{\"first_name\":\"Terminator\"}' ) WHERE c.ID = 123",
			nativeQuery = true )
	void updateJson();
	
}
