package org.unicef.etl.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unicef.etl.entity.EventEntity;
import org.unicef.etl.interfaces.RegisterService;
import org.unicef.etl.repository.CommonDatabaseRepository;

@Service
public class EventService implements RegisterService<EventEntity> {
	
	@Autowired
	private CommonDatabaseRepository commonDatabaseRepository;
	
	public EventService() {
		// TODO Auto-generated constructor stub
	}
	
	@Transactional
	@Override
	public void save(EventEntity eventEntity) {
		if (findBybaseEntityId(eventEntity.getBaseEntityId()) == null) {
			commonDatabaseRepository.save(eventEntity);
		} else {
			System.out.println("event already exists in database!!!");
		}
		
	}
	
	@Override
	public boolean delete(EventEntity t) {
		return true;
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(EventEntity t) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public EventEntity findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	@Override
	public EventEntity findBybaseEntityId(String baseEntityId) {
		return commonDatabaseRepository.findByKey(baseEntityId, "baseEntityId", EventEntity.class);
	}
	
}