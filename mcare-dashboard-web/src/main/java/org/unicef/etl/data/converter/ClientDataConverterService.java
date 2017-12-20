package org.unicef.etl.data.converter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unicef.etl.entity.ClientEntity;
import org.unicef.etl.interfaces.DataConverterService;
import org.unicef.etl.service.ClientService;
import org.unicef.etl.service.ExceptionService;

@Service
public class ClientDataConverterService implements DataConverterService {
	
	@Autowired
	private ClientEntity clientEntity;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private DataConverter dataConverter;
	
	@Autowired
	private ExceptionService exceptionService;
	
	public ClientDataConverterService() {
		
	}
	
	@Override
	public void convertToEntityAndSave(JSONObject doc) throws JSONException {
		
		try {
			Class<ClientEntity> className = ClientEntity.class;
			Object object = clientEntity;
			clientEntity = (ClientEntity) dataConverter.convert(doc, className, object);
			System.out.println("clientEntity: " + clientEntity.toString());
			
			JSONObject identifiers = new JSONObject(doc.getString("identifiers"));
			if (doc.has("identifiers") && doc.isNull("identifiers") || doc.getJSONObject("identifiers").length() == 0) {
				System.out.println("identifiers key does not exists!!");
			} else {
				System.out.println("identifiers key exists!!");
				clientEntity = (ClientEntity) dataConverter.convert(identifiers, className, object);
			}
			
			JSONObject attributes = new JSONObject(doc.getString("attributes"));
			if (doc.has("attributes") && doc.isNull("attributes") || doc.getJSONObject("attributes").length() == 0) {
				System.out.println("attributes key does not exists!!");
			} else {
				System.out.println("attributes key exists!!");
				clientEntity = (ClientEntity) dataConverter.convert(attributes, className, object);
			}
			
			JSONArray addresses = doc.getJSONArray("addresses");
			JSONObject addressJson = new JSONObject();
			
			System.out.println("addresses" + addresses.toString());
			for (int i = 0; i < addresses.length(); i++) {
				JSONObject addressesObj = addresses.getJSONObject(i);
				String addressType = addressesObj.getString("addressType");
				addressJson.put("addressType", addressType);
				JSONObject addressFields = new JSONObject(addressesObj.getString("addressFields"));
				String addressFieldsDiv = addressFields.getString("stateProvince");
				addressJson.put("division", addressFieldsDiv);
				String addressFieldsDist = addressFields.getString("countyDistrict");
				addressJson.put("district", addressFieldsDist);
				String addressFieldUpazila = addressFields.getString("cityVillage");
				addressJson.put("upazila", addressFieldUpazila);
				String addressFieldsUnions = addressFields.getString("address1");
				addressJson.put("union", addressFieldsUnions);
				String addressFieldsWard = addressFields.getString("address2");
				addressJson.put("ward", addressFieldsWard);
				String addressFieldsSubunit = addressFields.getString("address3");
				addressJson.put("subunit", addressFieldsSubunit);
				String addressFieldsMauzaPara = addressFields.getString("address4");
				addressJson.put("mauzapara", addressFieldsMauzaPara);
				String addressFieldsGoBHHID = addressFields.getString("address5");
				addressJson.put("gobhhid", addressFieldsGoBHHID);
				String addressFieldscountry = addressFields.getString("country");
				addressJson.put("country", addressFieldscountry);
				
			}
			System.out.println("addressJson" + addressJson.toString());
			clientEntity = (ClientEntity) dataConverter.convert(addressJson, className, object);
			System.out.println("clientEntity: " + clientEntity.toString());
			clientService.save(clientEntity);
		}
		catch (JSONException e) {
			System.out.println("client data converter services: " + e.fillInStackTrace().toString());
			//exceptionService.generatedEntityAndSaveForAction(doc, e.fillInStackTrace().toString(), "client");
		}
		
	}
	
}
