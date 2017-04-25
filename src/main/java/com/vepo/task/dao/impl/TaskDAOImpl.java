package com.vepo.task.dao.impl;


import java.util.List;

import com.vepo.task.dao.TaskDAO;
import com.vepo.task.entidade.TaskEntity;
import com.vepo.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TaskDAOImpl implements TaskDAO{

	@Override
	public void save(TaskEntity enTask) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(enTask);
		t.commit();				
	}

	@Override
	public TaskEntity getTask(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return (TaskEntity) session.load(TaskEntity.class, id);
	}

	@Override
	public List<TaskEntity> list() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from TaskEntity").list();
		t.commit();
		return lista;
	}

	@Override
	public void remove(TaskEntity enTask) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(enTask);
		t.commit();
	}

	@Override
	public void update(TaskEntity enTask) {		
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction t = session.beginTransaction();
	session.update(enTask);
	t.commit();
	}



}
