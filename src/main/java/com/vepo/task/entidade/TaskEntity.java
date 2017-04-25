package com.vepo.task.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




@Entity
public class TaskEntity {

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String titulo;
	
	@Column
	private String status;
	
	public TaskEntity() {
	}
	
	public TaskEntity(long id, String titulo, String status) {
		this.id = id;
		this.titulo = titulo;
		this.status = status;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
