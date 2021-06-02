package com.web.service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.ClobProxy;
import org.hibernate.engine.jdbc.LobCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.web.entity.Cliente;
import com.web.entity.ClienteVO;
import com.web.repository.ClienteRepository;

@Repository
public class ClienteServiceImpl implements ClienteService {

	@Resource
	private ClienteRepository clienteRepository;

	@Transactional
	public Map<Long, Clob> findAll() {

		List<Cliente> list = clienteRepository.findAll();
		System.out.println("size=" + list.size());
		Map<Long, Clob> map = list.stream().collect( Collectors.toMap( Cliente::getId, Cliente::getItem) );
		return map;
	}

	@Override
	public Map<Long, Clob> findByClient() {
		List<Cliente> list = clienteRepository.findByClient();
		System.out.println("size=" + list.size());
		Map<Long, Clob> map = list.stream().collect( Collectors.toMap( Cliente::getId, Cliente::getItem) );
		return map;
	}

	@Override
	public String findJson() {
		Clob clob = clienteRepository.findJson();
		String str = null;
		try {
			str = clob.getSubString( 1, (int) clob.length() );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("findJson=" + str );
		return str;
	}
	
	
	
	@Transactional
	@Override
	public Boolean updateJson() {
		
		System.out.println("updateJosn Started");
		Cliente cliente = clienteRepository.findOne( 123L );
		Cliente updateClient = new Cliente();
		
		ObjectMapper objectMapper = new ObjectMapper();
		ClienteVO cvo = new ClienteVO();
		try {
			// JSON to Object
			String clobStr = cliente.getItem().getSubString( 1, ( int ) cliente.getItem().length() );
			cvo = objectMapper.readValue( clobStr, ClienteVO.class );
			System.out.println( "CVO.getFirst_Name=" + cvo.getFirst_name() );
			cvo.setFirst_name("Terminator");
			System.out.println( "CVO.getFirst_Name=" + cvo.getFirst_name() );
			
			// JSON to Map
			Map<String, Object> map = objectMapper.readValue( clobStr, new TypeReference<Map<String,Object>>(){});
			map.entrySet().stream().forEach( e -> System.out.println( e ) );
			
			// POJO to JSON
			byte[] writeValueAsBytes = objectMapper.writeValueAsBytes( cvo );
			String jsonStr = new String( writeValueAsBytes, StandardCharsets.UTF_8 );
			System.out.println( jsonStr );
			
			updateClient.setId( cliente.getId() );
			updateClient.setItem( ClobProxy.generateProxy( jsonStr ) );
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} 
		
		clienteRepository.saveAndFlush( updateClient );
		System.out.println("updateJosn Finished");
		return false;
	}
	
	@Transactional
	@Override
	public Boolean insertJson() {
		System.out.println("insertJson Started");
		clienteRepository.insertJson();
		System.out.println("insertJson Finished");
		return false;
	}
	
	

}
