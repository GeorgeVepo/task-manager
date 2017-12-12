package com.vepo.task.dao.impl;


import java.util.List;

import com.vepo.task.dao.TaskDAO;
import com.vepo.task.entidade.TaskEntity;
import com.vepo.utils.HibernateUtil;

public class TaskDAOImpl implements TaskDAO{
	
	private Boolean isTest = false;
	
	public TaskDAOImpl() {
		this.isTest = false;
	}
	
	public TaskDAOImpl(Boolean isTest) {
		this.isTest = isTest;
	}
	
	private void openSession() {
		if(!this.isTest) {
			HibernateUtil.openSession();
			HibernateUtil.beginTransaction();
		}
		
	}
	
	private void closeSession() {
		if(!isTest) {
			HibernateUtil.commitTransaction();
			HibernateUtil.closeSession();
		}
	}

	@Override
	public long save(TaskEntity enTask) {
		openSession();
		long id = (Long) HibernateUtil.getSession().save(enTask);
		closeSession();
		return id;
	}

	@Override
	public TaskEntity getTask(long id) {
		openSession();
		TaskEntity taskEntity;
		try{
			taskEntity = (TaskEntity) HibernateUtil.getSession().load(TaskEntity.class, id);
		} catch (org.hibernate.ObjectNotFoundException e) {
			closeSession();
			return null;
		}
		closeSession();
		return taskEntity;
	}

	@Override
	public List<TaskEntity> list() {
		openSession();
		List lista = HibernateUtil.getSession().createQuery("from TaskEntity").list();
		closeSession();
		return lista;
	}

	@Override
	public TaskEntity remove(TaskEntity enTask) {
		openSession();
		HibernateUtil.getSession().delete(enTask);
		closeSession();
		return enTask;
	}

	@Override
	public TaskEntity update(TaskEntity enTask) {		
		openSession();
		HibernateUtil.getSession().update(enTask);
		closeSession();
		return enTask;
	}



}
