package org.mcare.schedule.monitoring.service;

import java.util.List;

public interface ScheduleMonitoringService {
	
	public List<Object[]> getData(String provider, String scheduleName);
	
}