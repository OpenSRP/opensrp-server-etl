package org.mpower.etl.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mcare.etl.entity.HouseholdEntity;
import org.mcare.etl.service.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:couchdb-connection-context.xml", 
	"classpath:etl-bean-context.xml", 
	"classpath:hibernate-hbm.xml", 
	"classpath:listener-context.xml", 
	"classpath:mvc-context.xml" })
public class HouseholdServiceTest {
	
	@Autowired
	private HouseholdService householdService;
	
@Ignore
	@Test
	public void saveTest() throws ParseException {
		String caseId = "e599f590-4e10-408d-b3f3-bedf4d3b69c4";
/*		HouseholdEntity he = new HouseholdEntity();
		he = householdService.findByCaseId(caseId);
		System.out.println(he.toString());
		householdService.update(he);*/
		HouseholdEntity he = new HouseholdEntity();
		he.setCaseId(caseId);
		try {
			householdService.save(he);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
@Ignore 
@Test
public void dateConverStingToDate () {
	
	 final Date currentTime = new Date();

	    final SimpleDateFormat sdf =
	            new SimpleDateFormat("E, dd MMM yyyy hh:mm:ss z");

	    // Give it to me in GMT time.
	    sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
	    System.out.println("GMT time: " + sdf.format(currentTime));
	    
	    
	//SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd yyyy HH:mm:ss z");
	  //formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
	SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
    String dateInString = "Sat, 09 Sep 2017 00:00:00 GMT"; //
    
  

    try {

        Date date = sdf.parse(dateInString);
        System.out.println(date);
        System.out.println(sdf.format(date));
       System.out.println(formatter1.format(date));

    } catch (ParseException e) {
        e.printStackTrace();
    }
}
	
}