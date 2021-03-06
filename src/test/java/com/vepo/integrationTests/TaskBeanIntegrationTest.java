package com.vepo.integrationTests;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.vepo.TaskTestModel;
import com.vepo.task.bean.TaskBean;
import com.vepo.task.dao.impl.TaskDAOImpl;
import com.vepo.task.entidade.TaskEntity;
import com.vepo.utils.HibernateUtil;


public class TaskBeanIntegrationTest {

	private TaskBean taskBean;
	private TaskTestModel taskTestModel;


	private List<TaskEntity> list;
	private TaskEntity insertTask;
	private TaskEntity updatedTask;
	private TaskEntity invalidUpdateTask;

	private void openSession() {
		HibernateUtil.openSession();
		HibernateUtil.beginTransaction();
	}

	private void  closeSession(){
		HibernateUtil.closeSession();
	}


	@Before
	public void setUp() {
		this.taskBean = new TaskBean();
		this.taskBean.setTaskDAO(new TaskDAOImpl(true));
		this.taskBean.setEnTaskDataModel( new ListDataModel(new ArrayList<TaskEntity>()));
		this.taskTestModel = new TaskTestModel();
		this.insertTask = taskTestModel.getValidTaskModel();

		this.updatedTask = taskTestModel.getValidUpdatedTaskModel();
		this.invalidUpdateTask = taskTestModel.getInvalidUpdatedTaskModel();

	}

	@Test
	@Transactional
	public void insertValidTasktest() {
		this.openSession();
		int before = (int) this.taskBean.listEnTaskDataModel().getRowCount();
		this.taskBean.setEnTask(this.insertTask);
		this.taskBean.add();
		int after = (int) this.taskBean.listEnTaskDataModel().getRowCount();
		Assert.assertEquals(before + 1, after);
		HibernateUtil.rollbackTransaction();
		this.closeSession();
	}

	@Test
	@Transactional
	public void deleteValidTasktest() {
		this.openSession();
		this.taskBean.setEnTask(this.insertTask);
		long id = this.taskBean.add();

		TaskEntity enTask = this.taskBean.getTaskDAO().getTask(id);
		this.taskBean.getTaskDAO().remove(enTask);

		enTask = this.taskBean.getTaskDAO().getTask(id);
		Assert.assertEquals(null, enTask);
		HibernateUtil.rollbackTransaction();
		this.closeSession();

	}
	
	@Test
	@Transactional
	public void updateValidTasktest() {
		this.openSession();
		this.taskBean.setEnTask(this.insertTask);
		long id = this.taskBean.add();
		
		HibernateUtil.commitTransaction();
		this.closeSession();
		
		openSession();
		this.updatedTask.setId(id);
		this.taskBean.setEnTask(this.updatedTask);
		this.taskBean.update();
		
		TaskEntity taskEntity = this.taskBean.getTaskDAO().getTask(this.updatedTask.getId());
		Assert.assertSame(this.updatedTask.getTitulo(), taskEntity.getTitulo());
		
		TaskEntity enTask = this.taskBean.getTaskDAO().getTask(id);
		this.taskBean.getTaskDAO().remove(enTask);
		
		enTask = this.taskBean.getTaskDAO().getTask(id);
		Assert.assertEquals(null, enTask);

		HibernateUtil.commitTransaction();
		this.closeSession();
	}
	

	@Test
	@Transactional
	public void updateInvalidTasktest() {
		this.openSession();
		this.taskBean.setEnTask(this.insertTask);
		long id = this.taskBean.add();
		this.invalidUpdateTask.setId(id);
		this.taskBean.setEnTask(this.invalidUpdateTask);
		this.taskBean.update();
		TaskEntity taskEntity = this.taskBean.getTaskDAO().getTask(id);
		Assert.assertSame(this.insertTask.getTitulo(), taskEntity.getTitulo());
		HibernateUtil.rollbackTransaction();
		this.closeSession();
	}



}
