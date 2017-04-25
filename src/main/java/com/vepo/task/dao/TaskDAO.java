package com.vepo.task.dao;

import java.util.List;

import com.vepo.task.entidade.TaskEntity;

public interface TaskDAO{

	public void save(TaskEntity enTask);
	public TaskEntity getTask(long id);
	public List<TaskEntity> list();
	public void remove(TaskEntity enTask);
	public void update(TaskEntity enTask);

}