package org.mcare.common.interfaces;

import org.mcare.acl.entity.ProviderEntity;
import org.mcare.params.builder.SearchBuilder;

import java.util.List;

public interface DatabaseRepository {
	
	public <T> long save(T t) throws Exception;
	
	public <T> int update(T t);
	
	public <T> boolean delete(T t);
	
	public <T> T findById(int id, String fieldName, Class<?> className);
	
	public <T> T findByKey(String value, String fieldName, Class<?> className);
	
	public <T> List<T> findAll(String tableClass);

	public <T> List<T> getProviderByFPI(SearchBuilder searchBuilder);
}
