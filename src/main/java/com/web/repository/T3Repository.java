package com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.entity.T3;

public interface T3Repository extends JpaRepository<T3, Integer> {
	
	T3 findById( Integer id );

}
