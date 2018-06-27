package org.opensrp.acl.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.opensrp.common.repository.impl.DatabaseRepositoryImpl;
import org.opensrp.acl.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private DatabaseRepositoryImpl databaseRepositoryImpl;

	@Autowired
	private SessionFactory sessionFactory;

	public LocationServiceImpl() {

	}

	@Transactional
	@Override
	public List<Object[]> getLocationByTagId(int tagId) {
		// TODO Auto-generated method stub
		String sqlQuery = "SELECT location.name,location.location_id FROM location left join location_tag_map  on location.location_id =location_tag_map.location_id "
				+ "WHERE location_tag_id=:location_tag_id";
		return databaseRepositoryImpl.executeSelectQuery(sqlQuery, "location_tag_id", tagId);
	}

	@Transactional
	@Override
	public List<Object[]> getChildData(int parentId) {
		String sqlQuery = "SELECT location.name,location.location_id from location where parent_location=:parentId";
		return databaseRepositoryImpl.executeSelectQuery(sqlQuery, "parentId", parentId);
	}

	@SuppressWarnings("unchecked")
	public <T> boolean isExist(String className) {
		Session session = sessionFactory.openSession();
		List<T> result = null;
		try {
			SQLQuery query = session.createSQLQuery("SELECT * from " + className);
			if(query!= null && !query.list().isEmpty()) {
				result = query.list();
			}
			session.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if(result != null && !result.isEmpty() &&result.size() > 0) {
			return true;
		}
		return false;
	}
}