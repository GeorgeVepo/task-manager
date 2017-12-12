package com.vepo.unitTests;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.vepo.TaskTestModel;
import com.vepo.task.dao.TaskDAO;
import com.vepo.task.dao.impl.TaskDAOImpl;
import com.vepo.task.entidade.TaskEntity;
import com.vepo.utils.HibernateUtil;


public class TaskDAOUnitTest {
	
	@Autowired
	private TaskDAO taskDAO;
	private TaskTestModel taskTestModel;
	
	private List <TaskEntity> list;
	private TaskEntity insertTask;
	

	
	private void openSession() {
		HibernateUtil.openSession();
		HibernateUtil.beginTransaction();
	}
	
	private void closeSession() {
		HibernateUtil.closeSession();
	}


	@Before
    public void setUp() {
		this.taskTestModel = new TaskTestModel();
		this.taskDAO = new TaskDAOImpl(true);
		this.list = new ArrayList<TaskEntity>();
		this.insertTask = taskTestModel.getValidTaskModel();
		this.list.add(insertTask);
		
	
		
    }

	@Test
	@Transactional
	public void insertValidTasktest() {
		openSession();
		long id = taskDAO.save(taskTestModel.getValidTaskModel());
		HibernateUtil.rollbackTransaction();
		closeSession();
		Assert.assertTrue(id > 0);
	}

	@Test
	@Transactional
	public void insertInvalidTasktest() {
		try{
			openSession();
			taskDAO.save(taskTestModel.getInvalidInsertTaskModel());
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			closeSession();
			Assert.assertTrue(true);
			return;
		}
		closeSession();
		Assert.assertTrue(false);
		
	}
	
	
	

}
