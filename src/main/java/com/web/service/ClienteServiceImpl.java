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

import com.web.dto.ClienteDTO;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.engine.jdbc.ClobProxy;
import org.hibernate.engine.jdbc.LobCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.web.entity.ClientePO;
import com.web.entity.PaisPO;
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
	public String findById( Long id ) {
		Cliente cliente = clienteRepository.findOne( id );
		String clobStr = "";
		try {
			clobStr = cliente.getItem().getSubString( 1, ( int )cliente.getItem().length() );
			
			ObjectMapper objectMapper = new ObjectMapper();
			ClientePO cpo = new ClientePO();
			cpo = objectMapper.readValue( clobStr, ClientePO.class );
			
			List<PaisPO> list = cpo.getPais();
			Stream<PaisPO> stream = list.stream();
	        stream.forEach( p -> System.out.println( p ) );
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return clobStr;
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
//		Cliente updateClient = new Cliente();
		
		ObjectMapper objectMapper = new ObjectMapper();
		ClientePO cpo = new ClientePO();
		try {
			// JSON to Object
			String clobStr = cliente.getItem().getSubString( 1, ( int ) cliente.getItem().length() );
			System.out.println("Print String Json...");
			System.out.println( clobStr );
			cpo = objectMapper.readValue( clobStr, ClientePO.class );
			System.out.println( "CVO.getFirst_Name=" + cpo.getFirst_name() );
			cpo.setFirst_name("Terminator");
			System.out.println( "CVO.getFirst_Name=" + cpo.getFirst_name() );
			
			// JSON to Map
			Map<String, Object> map = objectMapper.readValue( clobStr, new TypeReference<Map<String, Object>>(){} );
			System.out.println("Print Stream Map...");
			map.entrySet().stream().forEach( e -> System.out.println( e ) );
			System.out.println("Print By Key...");
			System.out.println( "first_name=" + map.get("first_name").toString() );
			System.out.println( "email=" + map.get("email").toString() );
			
			// POJO to JSON
			byte[] writeValueAsBytes = objectMapper.writeValueAsBytes( cpo );
			String jsonStr = new String( writeValueAsBytes, StandardCharsets.UTF_8 );
			System.out.println("Print Pojo to Json...");
			System.out.println( jsonStr );
			
//			updateClient.setId( cliente.getId() );
//			updateClient.setItem( ClobProxy.generateProxy( jsonStr ) );

			// Set json string to object
			cliente.setItem( ClobProxy.generateProxy( jsonStr ) );
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} 
		
		clienteRepository.saveAndFlush( cliente );
		System.out.println("updateJosn Finished");
		return false;
	}
	
	@Transactional
	@Override
	public Boolean insertJson() {
		System.out.println("insertJson Started");
		
		ClientePO cpo = new ClientePO();
		cpo.setId( 4L );
		cpo.setFirst_name( "Robocop" );
		cpo.setLast_name( "Murphy" );
		cpo.setEmail( "robo@cop.com" );
		cpo.setGender( "Male" );
		cpo.setAge( 33 );
		List<PaisPO> list = new ArrayList<PaisPO>();
		PaisPO ppo = new PaisPO();
		ppo.setId(2L);
		ppo.setNombre( "Canada" );;
		list.add( ppo );
		cpo.setPais( list );
		
		Cliente cliente = new Cliente();
		cliente.setId( 126L );
		cliente.setItem( ClobProxy.generateProxy( pojoToJson( cpo ) ) );
		
		clienteRepository.saveAndFlush( cliente );
		System.out.println("insertJson Finished");
		return true;
	}


	@Override
	public Boolean insert() {

		ObjectMapper objectMapper = new ObjectMapper();
		List<Cliente> list = clienteRepository.findAll();
		if ( list != null && !list.isEmpty() ) {
			try {
				for (Iterator<Cliente> iterator = list.iterator(); iterator.hasNext();) {
					Cliente cliente = (Cliente) iterator.next();
					String clobStr = cliente.getItem().getSubString( 1, ( int ) cliente.getItem().length() );
					ClienteDTO dto = objectMapper.readValue( clobStr, ClienteDTO.class );
					System.out.println( dto.toString() );
				}
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
	}


	/**
	 * @param obj
	 * @return
	 */
	private String pojoToJson( Object obj ) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		byte[] writeValueAsBytes = null;
		try {
			writeValueAsBytes = objectMapper.writeValueAsBytes( obj );
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		String jsonStr = new String( writeValueAsBytes, StandardCharsets.UTF_8 );
		System.out.println( jsonStr );
		return jsonStr;
	}
	

}
