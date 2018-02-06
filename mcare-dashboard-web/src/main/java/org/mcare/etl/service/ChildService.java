package org.mcare.etl.service;

import javax.transaction.Transactional;

import org.mcare.acl.repository.DatabaseRepositoryImpl;
import org.mcare.etl.entity.ChildEntity;
import org.mcare.etl.entity.HouseholdEntity;
import org.mcare.etl.interfaces.RegisterService;
import org.mcare.etl.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ChildService implements RegisterService<ChildEntity> {
	
	@Autowired
	private DatabaseRepositoryImpl databaseRepositoryImpl;
	
	public ChildService() {
		
	}
	
	@Transactional
	@Override
	public void save(ChildEntity childEntity) {
		ChildEntity existingCHildEntity = findByCaseId(childEntity.caseId);
		if (existingCHildEntity == null) {
			databaseRepositoryImpl.save(childEntity);
		} else {
			if (delete(existingCHildEntity))
				databaseRepositoryImpl.save(childEntity);
			
		}
	}
	
	@Transactional
	@Override
	public boolean delete(ChildEntity childEntity) {
		return databaseRepositoryImpl.delete(childEntity);
		
	}
	
	@Transactional
	@Override
	public void update(ChildEntity childEntity) {
		databaseRepositoryImpl.delete(childEntity);
		
	}
	
	@Override
	public ChildEntity findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	@Override
	public ChildEntity findByCaseId(String caseId) {
		return databaseRepositoryImpl.findByKey(caseId,"caseId", ChildEntity.class);
	}
	
}