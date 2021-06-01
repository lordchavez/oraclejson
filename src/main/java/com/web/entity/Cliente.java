package com.web.entity;

import java.io.Serializable;
import java.sql.Clob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")

public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1228757290851743172L;
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "ITEM")
	@Lob
	private Clob item;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Clob getItem() {
		return item;
	}

	public void setItem(Clob item) {
		this.item = item;
	}
	
}
