package org.mcare.acl.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.mcare.common.interfaces.DatabaseRepository;
import org.mcare.etl.entity.ActionEntity;
import org.mcare.etl.entity.HouseholdEntity;
import org.mcare.params.builder.SearchBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseRepositoryImpl implements DatabaseRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public DatabaseRepositoryImpl() {
		
	}
	
	public void test() {
		System.err.println("sessionFactory:" + sessionFactory);
	}
	
	@Override
	public <T> long save(T t) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		long returnValue = -1;
		try {
			tx = session.beginTransaction();
			session.save(t);
			returnValue = 1;
			if (!tx.wasCommitted())
				tx.commit();
		}
		catch (HibernateException e) {
			returnValue = -1;
			tx.rollback();
		}
		finally {
			session.close();
		}
		return returnValue;
	}
	
	@Override
	public <T> int update(T t) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int returnValue = -1;
		try {
			tx = session.beginTransaction();
			session.update(t);
			if (!tx.wasCommitted())
				tx.commit();
			returnValue = 1;
		}
		catch (HibernateException e) {
			returnValue = -1;
			tx.rollback();
		}
		finally {
			session.close();
		}
		return returnValue;
	}
	
	@Override
	public <T> boolean delete(T t) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		boolean returnValue = false;
		try {
			tx = session.beginTransaction();
			session.delete(t);
			if (!tx.wasCommitted())
				tx.commit();
			returnValue = true;
		}
		catch (HibernateException e) {
			returnValue = false;
			tx.rollback();
		}
		finally {
			session.close();
			
		}
		return returnValue;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T findById(int id, String fieldName, Class<?> className) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(className);
		criteria.add(Restrictions.eq(fieldName, id));
		List<T> result = criteria.list();
		session.close();
		return (T) (result.size() > 0 ? (T) result.get(0) : null);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(String tableClass) {
		Session session = sessionFactory.openSession();
		List<T> result = null;
		try {
			Query query = session.createQuery("from " + tableClass + " t order by t.id desc");
			//query.setFirstResult(0);
			//query.setMaxResults(10);
			result = (List<T>) query.list();
		}
		catch (Exception e) {
			System.out.println("error:" + e.getMessage());
		}
		finally {
			session.close();
		}
		
		return (List<T>) result;
	}
	
	@Override
	public <T> T findByKey(String value, String fieldName, Class<?> className) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(className);
		criteria.add(Restrictions.eq(fieldName, value));
		@SuppressWarnings("unchecked")
		List<T> result = criteria.list();
		session.close();
		return (T) (result.size() > 0 ? (T) result.get(0) : null);
	}
	
	public <T> T findByCaseIdAndToday(String relationalId, Date today, Class<?> className) {
		System.out.println("finding caseId and today relationalId:" + relationalId);
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(className);
		criteria.add(Restrictions.eq("relationalId", relationalId));
		criteria.add(Restrictions.eq("today", today));
		@SuppressWarnings("unchecked")
		List<T> result = criteria.list();
		session.close();
		System.out.println("finding result:" + result.toString());
		return (T) (result.size() > 0 ? (T) result.get(0) : null);
	}
	
	public ActionEntity getAction(String caseId, String visitCode, String alertStatus, Date startDate) {
		Session session = sessionFactory.openSession();
		
		ActionEntity actions = null;
		try {
			String hql = "select A.caseId from " + "ActionEntity A " + "where A.caseId = :case_id "
			        + "and A.visitCode = :visit_code " + "and A.alertStatus = :alert_status "
			        + " and A.startDate = :start_date";
			Query query = session.createQuery(hql);
			query.setParameter("case_id", caseId);
			query.setParameter("visit_code", visitCode);
			query.setParameter("alert_status", alertStatus);
			query.setParameter("start_date", startDate);
			actions = (ActionEntity) query.list().get(0);
			session.close();
			
		}
		catch (Exception e) {
			System.out.println("From getAction: " + e.getMessage());
		} // TODO Auto-generated method stub
		
		return actions;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> list(int result, int offsetreal, Class<?> entityClassName) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(entityClassName);
		criteria.setFirstResult(offsetreal);
		criteria.setMaxResults(result);
		List<T> products = null;
		try {
			products = (List<T>) criteria.list();
			session.close();
		}
		catch (Exception e) {
			
		}
		
		return products;
	}
	
	public int count() {
		return sessionFactory.openSession().createCriteria(HouseholdEntity.class).list().size();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> executeSelectQuery(String sqlQuery, String paramName, int paramValue) {
		Session session = sessionFactory.openSession();
		List<Object[]> results = null;
		try {
			SQLQuery query = session.createSQLQuery(sqlQuery);
			query.setParameter(paramName, paramValue);
			results = query.list();
			session.close();
		}
		catch (Exception e) {
			session.close();
		}
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> search(SearchBuilder searchBuilder, int result, int offsetreal, Class<?> entityClassName) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(entityClassName);
		
		if (searchBuilder.getDivision() != null && !searchBuilder.getDivision().isEmpty()) {
			System.err.println("searchBuilder.getDivision():" + searchBuilder.getDivision());
			criteria.add(Restrictions.eq("division", searchBuilder.getDivision().toUpperCase()));
		}
		if (searchBuilder.getDistrict() != null && !searchBuilder.getDistrict().isEmpty()) {
			
			criteria.add(Restrictions.eq("district", searchBuilder.getDistrict().toUpperCase()));
		}
		if (searchBuilder.getUpazila() != null && !searchBuilder.getUpazila().isEmpty()) {
			
			criteria.add(Restrictions.eq("upazila", searchBuilder.getUpazila()));
		}
		if (searchBuilder.getUnion() != null && !searchBuilder.getUnion().isEmpty()) {
			criteria.add(Restrictions.eq("union", searchBuilder.getUnion()));
		}
		if (searchBuilder.getWard() != null && !searchBuilder.getWard().isEmpty()) {
			criteria.add(Restrictions.eq("ward", searchBuilder.getWard()));
		}
		if (searchBuilder.getMauzapara() != null && !searchBuilder.getMauzapara().isEmpty()) {
			criteria.add(Restrictions.eq("mauzaPara", searchBuilder.getMauzapara()));
		}
		if (searchBuilder.getSubunit() != null && !searchBuilder.getSubunit().isEmpty()) {
			criteria.add(Restrictions.eq("subunit", searchBuilder.getSubunit()));
		}
		
		if (searchBuilder.getProvider() != null && !searchBuilder.getProvider().isEmpty()) {
			System.err.println("searchBuilder.getDivision():" + searchBuilder.getProvider());
			criteria.add(Restrictions.eq("provider", searchBuilder.getProvider()));
		}
		if (searchBuilder.getName() != null && !searchBuilder.getName().isEmpty()) {
			
			criteria.add(Restrictions.ilike("firstName", searchBuilder.getName().toUpperCase(), MatchMode.ANYWHERE));
		}
		criteria.setFirstResult(offsetreal);
		criteria.setMaxResults(result);
		
		List<T> products = null;
		try {
			products = (List<T>) criteria.list();
			session.close();
		}
		catch (Exception e) {
			
		}
		
		return products;
	}
	
	public int countBySearch(SearchBuilder searchBuilder, Class<?> entityClassName) {
		Session session = sessionFactory.openSession();
		int count = 0;
		Criteria criteria = session.createCriteria(entityClassName);
		try {
			
			if (searchBuilder.getDivision() != null && !searchBuilder.getDivision().isEmpty()) {
				
				criteria.add(Restrictions.eq("division", searchBuilder.getDivision().toUpperCase()));
			}
			if (searchBuilder.getDistrict() != null && !searchBuilder.getDistrict().isEmpty()) {
				
				criteria.add(Restrictions.eq("district", searchBuilder.getDistrict().toUpperCase()));
			}
			if (searchBuilder.getUpazila() != null && !searchBuilder.getUpazila().isEmpty()) {
				
				criteria.add(Restrictions.eq("upazila", searchBuilder.getUpazila()));
			}
			if (searchBuilder.getUnion() != null && !searchBuilder.getUnion().isEmpty()) {
				criteria.add(Restrictions.eq("union", searchBuilder.getUnion()));
			}
			if (searchBuilder.getWard() != null && !searchBuilder.getWard().isEmpty()) {
				criteria.add(Restrictions.eq("ward", searchBuilder.getWard()));
			}
			if (searchBuilder.getMauzapara() != null && !searchBuilder.getMauzapara().isEmpty()) {
				criteria.add(Restrictions.eq("mauzaPara", searchBuilder.getMauzapara()));
			}
			if (searchBuilder.getSubunit() != null && !searchBuilder.getSubunit().isEmpty()) {
				criteria.add(Restrictions.eq("subunit", searchBuilder.getSubunit()));
			}
			if (searchBuilder.getProvider() != null && !searchBuilder.getProvider().isEmpty()) {
				criteria.add(Restrictions.eq("provider", searchBuilder.getProvider()));
			}
			if (searchBuilder.getName() != null && !searchBuilder.getName().isEmpty()) {
				criteria.add(Restrictions.ilike("firstName", searchBuilder.getName(), MatchMode.ANYWHERE));
			}
			count = criteria.list().size();
			session.close();
		}
		catch (Exception e) {
			session.close();
		}
		
		return count;
	}
}