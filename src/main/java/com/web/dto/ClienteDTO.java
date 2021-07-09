package com.web.dto;

import java.util.List;

public class ClienteDTO {

	private Long id;
	private String first_name;
	private String last_name;
	private String email;
	private String gender;
	private Integer age;
	private List<PaisDTO> pais;	
	
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
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}
	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}
	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the pais
	 */
	public List<PaisDTO> getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(List<PaisDTO> pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "'(" + first_name + "'" +
				", '" + last_name + "'" +
				", '" + email + "'" +
				", '" + gender + "'" +
				", " + age +
				", " + pais + ")";
	}
}
