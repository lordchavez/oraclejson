package com.web.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t3")
public class T3 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2380010337421686895L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "i_d")
	private Integer id;

	@JoinColumn( name = "c1" )
	@OneToOne
	private T1 c1;
	
	@Column( name = "c2" )
	private String c2;

	public T3() {

	}

	public T3(Integer id, T1 c1, String c2) {
		this.id = id;
		this.c1 = c1;
		this.c2 = c2;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the c1
	 */
	public T1 getC1() {
		return c1;
	}

	/**
	 * @param c1 the c1 to set
	 */
	public void setC1(T1 c1) {
		this.c1 = c1;
	}

	public String getC2() {
		return c2;
	}

	public void setC2(String c2) {
		this.c2 = c2;
	}
}
