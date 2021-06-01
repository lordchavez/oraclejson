package com.web.service;

import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.entity.Cliente;
import com.web.repository.ClienteRepository;

@Service
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
		Clob myClob = clienteRepository.findJson();
		String str = null;
		try {
			str = myClob.getSubString( 1, (int) myClob.length() );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("findJson=" + str );
		return str;
	}

	@Override
	public Boolean updateJson() {
		System.out.println("updateJosn Started");
		clienteRepository.updateJson();
		System.out.println("updateJosn Finished");
		return false;
	}
	
	

}
