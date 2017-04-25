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
	private boolean renderEdit;
	private boolean renderCreate;
	
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
		setRenderEdit(false);
		setRenderCreate(true);
		enTask = new TaskEntity();
		return "gerenciar";
	}
	
	public String edit(){
		setRenderEdit(true);
		setRenderCreate(false);
		enTask = (TaskEntity)(listDataModel.getRowData());
		return "gerenciar";
	}
	
	
	
}
