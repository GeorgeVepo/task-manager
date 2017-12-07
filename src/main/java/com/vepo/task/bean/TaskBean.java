package com.vepo.task.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
	private DataModel enTaskDataModel;
	private boolean renderEdit;
	private boolean renderCreate;
	
	@PostConstruct
	public void TaskBean() {
		this.newTask();
		this.listEnTaskDataModel();
	}

	public String update(){
		taskDAO.update(enTask);
		return "index";
	}

	public DataModel listEnTaskDataModel(){
		List<TaskEntity> taskList = taskDAO.list();
		enTaskDataModel = new ListDataModel(taskList);
		return enTaskDataModel;
		
	}
	
	public String add(){
		taskDAO.save(enTask);
		return "index";
	}
	
	public String remove(){
		enTask = (TaskEntity)(enTaskDataModel.getRowData());
		taskDAO.remove(enTask);
		return "index";
	}
	
	public String newTask(){
		setRenderEdit(false);
		setRenderCreate(true);
		enTask = new TaskEntity();
		return "gerenciar";
	}
	
	public String edit(){
		setRenderEdit(true);
		setRenderCreate(false);
		enTask = (TaskEntity)(enTaskDataModel.getRowData());
		return "gerenciar";
	}
	
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

	public boolean isRenderEdit() {
		return renderEdit;
	}

	public void setRenderEdit(boolean renderEdit) {
		this.renderEdit = renderEdit;
	}

	public boolean isRenderCreate() {
		return renderCreate;
	}

	public void setRenderCreate(boolean renderCreate) {
		this.renderCreate = renderCreate;
	}

	public DataModel getEnTaskDataModel() {
		return enTaskDataModel;
	}

	public void setEnTaskDataModel(DataModel enTaskDataModel) {
		this.enTaskDataModel = enTaskDataModel;
	}	
	
}
