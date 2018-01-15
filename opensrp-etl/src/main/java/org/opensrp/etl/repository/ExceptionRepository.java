package org.opensrp.etl.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.opensrp.etl.entity.ExceptionEntity;
import org.opensrp.etl.interfaces.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ExceptionRepository implements RegisterRepository<ExceptionEntity> {

    @Autowired
	private SessionFactory sessionFactory;
	
	public ExceptionRepository() {
		// TODO Auto-generated constructor stub
	}
	
	private Session getSession() {
		Session session = this.sessionFactory.getCurrentSession();
		return session;
	}
	
	@Override
	public void save(ExceptionEntity exceptionEntity) {
		try {
            getSession().save(exceptionEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	@Override
	public boolean delete(ExceptionEntity exceptionEntity) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete ExceptionEntity where id = :ID");
		query.setParameter("ID", exceptionEntity.getId());
		int result = query.executeUpdate();
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void update(ExceptionEntity t) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public ExceptionEntity findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ExceptionEntity findByCaseId(String caseID) {
		Criteria listChildCr = getSession().createCriteria(ExceptionEntity.class);
		listChildCr.add(Restrictions.eq("caseId", caseID));
		List<ExceptionEntity> listException = listChildCr.list();
		return listException.size() > 0 ? (ExceptionEntity) listException.get(0) : null;
	}
}