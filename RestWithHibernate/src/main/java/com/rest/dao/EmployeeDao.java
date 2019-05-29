package com.rest.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.rest.beans.Employee;

@Component
public class EmployeeDao {

	@SuppressWarnings("unchecked")
	public List<Employee> readAll() {
		System.out.println("readAll():: call");
		List<Employee> list = null;

		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "From Employee";
		Query query = session.createQuery(hql);
		list = query.list();
		tx.commit();
		session.close();
		return list;

	}

	@SuppressWarnings("unchecked")
	public Employee readById(Integer id) {
		System.out.println("readAll():: call");
		List<Employee> list = null;
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		String hql = "FROM EMPLOYEE WHERE ID=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		list = query.list();
		tx.commit();
		session.close();

		for (Employee e : list) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		return null;
	}
	public static void getSession() {
		
	}
	public Employee insert(Employee e) {
		System.out.println("insert():: method call");
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("transaction begin");
		session.save(e);
		System.out.println("object save successfully");
		tx.commit();
		session.close();
		return e;

	}

	public Employee update(Integer id, Employee e) {
		System.out.println("update() :: method call");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(e);
		tx.commit();
		session.close();
		return e;
	}

	public Integer delete(Integer id) {

		System.out.println("delete() :: method call");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Employee employee = new Employee();
		employee.setId(id);
		session.delete(employee);
		tx.commit();
		session.close();
		return id;
	}
}
