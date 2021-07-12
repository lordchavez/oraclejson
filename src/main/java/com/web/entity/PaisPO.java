package com.web.entity;

import java.io.Serializable;

public class PaisPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4821875270109739136L;
	
	private Long id;
	
	private String nombre;

	public PaisPO() {
		super();
	}
	
	public PaisPO(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "PaisPO [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
