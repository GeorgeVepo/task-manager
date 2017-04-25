package com.vepo.task.entidade;

import java.util.Date;

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

	@Column
	private String descricao;

	public TaskEntity() {
	}


	public TaskEntity(long id, String titulo, String status, String descricao) {
		this.id = id;
		this.titulo = titulo;
		this.status = status;
		this.descricao = descricao;
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


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	

}
