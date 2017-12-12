package com.vepo;

import com.vepo.task.entidade.TaskEntity;

public class TaskTestModel {
	
	public TaskEntity getValidTaskModel() {
		return new TaskEntity(0, "Tarefa Teste", "Pendente", "Tarefa teste descrição");
	}
	
	public TaskEntity getValidUpdatedTaskModel() {
		return new TaskEntity(1L, "Tarefa update Teste", "Pendente", "Tarefa teste descrição");
	}
	
	public TaskEntity getInvalidInsertTaskModel() {
		return new TaskEntity(0, null, null, "Tarefa teste descrição");
	}
	

	public TaskEntity getInvalidUpdatedTaskModel() {
		return new TaskEntity(1L, null, null, "Tarefa teste descrição");
	}

}
