package org.mcare.acl.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.mcare.common.interfaces.DatabaseService;
import org.mcare.common.repository.impl.DatabaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl implements DatabaseService {
	
	@Autowired
	private DatabaseRepositoryImpl databaseRepositoryImpl;
	
	public ProviderServiceImpl() {
		
	}
	
	@Override
	public <T> long save(T t) {
		return 0;
	}
	
	@Override
	public <T> int delete(T t) {
		return 0;
	}
	
	@Override
	public <T> T findById(int id, String fieldName, Class<?> className) {
		return null;
	}
	
	@Override
	public <T> T findByKey(String value, String fieldName, Class<?> className) {
		return null;
	}
	
	@Transactional
	@Override
	public <T> List<T> findAll(String tableClass) {
		return databaseRepositoryImpl.findAll(tableClass);
	}
	
}