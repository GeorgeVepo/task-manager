package com.vepo.unitTests;
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

public class TaskBeanUnitTest {
	
	private TaskBean taskBean;
	private TaskTestModel taskTestModel;
	
	
	private List <TaskEntity> list;
	private TaskEntity invalidInsertTask;	
	

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
		this.invalidInsertTask = taskTestModel.getInvalidInsertTaskModel();
		
    }

	@Test
	@Transactional
	public void insertInvalidTasktest() {
		this.openSession();
		int before = (int) this.taskBean.listEnTaskDataModel().getRowCount();
		this.taskBean.setEnTask(this.invalidInsertTask);
		this.taskBean.add();
		int after = (int) this.taskBean.listEnTaskDataModel().getRowCount();
		Assert.assertEquals(before, after);
		HibernateUtil.rollbackTransaction();
		closeSession();
	}


	
	
	

}
