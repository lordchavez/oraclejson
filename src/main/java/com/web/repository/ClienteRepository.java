package com.web.repository;

import java.io.Reader;
import java.io.StringReader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.LobCreator;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Query( value = "UPDATE KIO.CLIENTE c SET c.ITEM = json_mergepatch ( c.ITEM, json_object( 'first_name' VALUE 'Terminator' ) ) WHERE c.ITEM.id = 1", nativeQuery = true )
	default void updateJson() {
		System.out.println("ClienteRepository.updateJson");
	}
	
	@Query( value = "INSERT INTO KIO.CLIENTE (ID, ITEM) VALUES (125,"
			+ "('{\"id\":3,\"first_name\":\"Philip\",\"last_name\":\"McLenaghan\",\"email\":\"pmclenaghan1@sohu.com\",\"gender\":\"Genderqueer\",\"age\":72}') )", nativeQuery = true )
	void insertJson();
	
}
