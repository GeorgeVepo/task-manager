package com.vepo.task.bean;

import java.util.List;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vepo.task.dao.TaskDAO;
import com.vepo.task.entidade.TaskEntity;

@Component
@Scope("session")
public class TaskBean {
	
	

	@Autowired
	private TaskDAO taskDAO;
	private TaskEntity enTask;
	private DataModel listDataModel;
	
	public TaskDAO getTaskDAO() {
		return taskDAO;
	}
	
	public TaskEntity getEnTask() {
		return enTask;
	}



	public void setEnTask(TaskEntity enTask) {
		this.enTask = enTask;
	}



	public void setTaskDAO(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}



	public String update(){
		taskDAO.update(enTask);
		return "index";
		}

	public DataModel list(){
		List<TaskEntity> taskList = taskDAO.list();
		listDataModel = new ListDataModel(taskList);
		return listDataModel;
	}
	
	public String add(){
		taskDAO.save(enTask);
		return "index";
	}
	
	public String remove(){
		enTask = (TaskEntity)(listDataModel.getRowData());
		taskDAO.remove(enTask);
		return "index";
	}
	
	public String newTask(){
		enTask = new TaskEntity();
		return "gerenciar";
	}
	
	public String edit(){
		enTask = (TaskEntity)(listDataModel.getRowData());
		return "gerenciar";
	}
	
}
