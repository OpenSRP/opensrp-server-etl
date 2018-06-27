package org.opensrp.common.repository.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.opensrp.common.interfaces.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseRepositoryImpl implements DatabaseRepository {
	
	private static final Logger logger = Logger.getLogger(DatabaseRepositoryImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public DatabaseRepositoryImpl() {
		
	}
	
	@Override
	public <T> long save(T t) throws Exception {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		long returnValue = -1;
		try {
			tx = session.beginTransaction();
			session.save(t);
			logger.info("saved successfully: " + t.getClass().getName());
			returnValue = 1;
			if (!tx.wasCommitted())
				tx.commit();
		}
		catch (HibernateException e) {
			returnValue = -1;
			tx.rollback();
			throw new Exception(e.getMessage());
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
			logger.info("updated successfully");
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
			logger.info("deleting: " + t.getClass().getName());
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
	
	public <T> List<T> findAllByKeys(Map<String, String> fielaValues, Class<?> className) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(className);
		for (Map.Entry<String, String> entry : fielaValues.entrySet()) {
			criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
		}
		
		@SuppressWarnings("unchecked")
		List<T> result = criteria.list();
		session.close();
		return (List<T>) (result.size() > 0 ? (List<T>) result : null);
	}
	
	@SuppressWarnings("unchecked")
	public boolean findByUserName(String value, String fieldName, Class<?> className) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(className);
		criteria.add(Restrictions.eq(fieldName, value));
		List<Object> result = criteria.list();
		session.close();
		return (result.size() > 0 ? true : false);
	}
	
	public <T> T findByCaseIdAndToday(String relationalId, Date today, Class<?> className) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(className);
		criteria.add(Restrictions.eq("relationalId", relationalId));
		criteria.add(Restrictions.eq("today", today));
		@SuppressWarnings("unchecked")
		List<T> result = criteria.list();
		session.close();
		return (T) (result.size() > 0 ? (T) result.get(0) : null);
	}
	
	public <T> T findByCaseIdAndBNFDate(String relationalId, Date bnfdate, Class<?> className) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(className);
		criteria.add(Restrictions.eq("relationalId", relationalId));
		criteria.add(Restrictions.eq("FWBNFDATE", bnfdate));
		@SuppressWarnings("unchecked")
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
			logger.error("error:" + e.getMessage());
		}
		finally {
			session.close();
		}
		
		return (List<T>) result;
	}
	
	public <T> List<T> findAllByKey(String value, String fieldName, Class<?> className) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(className);
		criteria.add(Restrictions.eq(fieldName, value));
		@SuppressWarnings("unchecked")
		List<T> result = criteria.list();
		session.close();
		return (List<T>) (result.size() > 0 ? (List<T>) result : null);
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
	/*
	public int count() {
		return sessionFactory.openSession().createCriteria(HouseholdEntity.class).list().size();
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> search(SearchBuilder searchBuilder, int result, int offsetreal, Class<?> entityClassName) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(entityClassName);
		
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
			criteria.add(Restrictions.ilike("firstName", searchBuilder.getName().toUpperCase(), MatchMode.ANYWHERE));
		}
		criteria.setFirstResult(offsetreal);
		criteria.setMaxResults(result);
		criteria.addOrder(Order.desc("created"));
		
		List<T> products = null;
		try {
			products = (List<T>) criteria.list();
			session.close();
		}
		catch (Exception e) {
			logger.error(e);
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
	*/
	
	
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
	public List<Object[]> executeSelectQuery(String provider, String caseId, String scheduleName, String userType,
	                                         String sqlQuery) {
		
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sqlQuery);
		
		if (!provider.isEmpty()) {
			query.setParameter("provider", provider);
		}
		if (!caseId.isEmpty()) {
			query.setParameter("case_id", caseId);
		}
		if (!scheduleName.isEmpty()) {
			query.setParameter("scheduleName", scheduleName);
		}
		if (!userType.isEmpty()) {
			query.setParameter("userType", userType);
		}
		List<Object[]> results = query.list();
		
		return results;
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getDataFromSQLFunction(String procedureName, String params) {
		Session session = sessionFactory.openSession();
		List<T> aggregatedData = null;
		try {
			String hql = "select * from " + procedureName + "(" + params + ")";
			Query query = session.createSQLQuery(hql);
			aggregatedData = query.list();
			logger.info("data fetched successfully from " + procedureName + ", aggregated data size: "
			        + aggregatedData.size());
			session.close();
		}
		catch (Exception e) {
			logger.error("Data fetch from " + procedureName + " error:" + e.getMessage());
		}
		return aggregatedData;
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Object[]> executeRawQuery(SearchBuilder searchBuilder, String sqlQuery) {
		System.err.println("sqlQuery:" + sqlQuery);
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sqlQuery);
		
		if (searchBuilder.getDivision() != null && !searchBuilder.getDivision().isEmpty()) {
			
			query.setParameter("division", searchBuilder.getDivision().toUpperCase());
		}
		if (searchBuilder.getDistrict() != null && !searchBuilder.getDistrict().isEmpty()) {
			
			query.setParameter("district", searchBuilder.getDistrict().toUpperCase());
		}
		if (searchBuilder.getUpazila() != null && !searchBuilder.getUpazila().isEmpty()) {
			
			query.setParameter("upazila", searchBuilder.getUpazila());
		}
		if (searchBuilder.getUnion() != null && !searchBuilder.getUnion().isEmpty()) {
			
			query.setParameter("unions", searchBuilder.getUnion());
		}
		if (searchBuilder.getWard() != null && !searchBuilder.getWard().isEmpty()) {
			query.setParameter("ward", searchBuilder.getWard());
		}
		if (searchBuilder.getMauzapara() != null && !searchBuilder.getMauzapara().isEmpty()) {
			query.setParameter("mauza_para", searchBuilder.getMauzapara());
		}
		if (searchBuilder.getSubunit() != null && !searchBuilder.getSubunit().isEmpty()) {
			query.setParameter("subunit", searchBuilder.getSubunit());
		}
		if (searchBuilder.getProvider() != null && !searchBuilder.getProvider().isEmpty()) {
			query.setParameter("provider", searchBuilder.getProvider());
		}
		//query.setParameter("years", searchBuilder.getYear());
		
		List<Object[]> results = query.list();
		
		return results;
	}*/
}