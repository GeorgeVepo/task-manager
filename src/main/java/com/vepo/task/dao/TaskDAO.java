package com.vepo.task.dao;

import java.util.List;

import com.vepo.task.entidade.TaskEntity;

public interface TaskDAO{

	public long save(TaskEntity enTask);
	public TaskEntity getTask(long id);
	public List<TaskEntity> list();
	public TaskEntity remove(TaskEntity enTask);
	public TaskEntity update(TaskEntity enTask);

}