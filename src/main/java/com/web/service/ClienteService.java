package com.web.service;

import java.sql.Clob;
import java.util.List;
import java.util.Map;

import com.web.entity.Cliente;

public interface ClienteService {
	
	public Map<Long, Clob> findAll();
	
	public Map<Long, Clob> findByClient();
	
	public String findJson();
	
	public Boolean updateJson();

}
