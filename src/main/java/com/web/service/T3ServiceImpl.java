package com.web.service;

import javax.annotation.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import com.web.entity.T3;
import com.web.repository.T3Repository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class T3ServiceImpl implements T3Service {

	@Resource
	private T3Repository t3Repository;
	
	@Override
	public T3 findById ( Integer id ) {
		T3 t3 = t3Repository.findById( id );
		System.out.println( t3.getC1().getC2() );
		System.out.println( t3.getC2() );
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = null;
		try {
			map = objectMapper.readValue( t3.getC1().getC2(), new TypeReference<Map<String,Object>>(){});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("\nPrint Map Stream...");
		map.entrySet().stream().forEach( e -> System.out.println( e ) );

		System.out.println("\nPrint Map Foreach...");
		map.forEach( ( k, v ) -> {
			System.out.println( k + "=" +  v );
		} );
		return t3;
	}

}
