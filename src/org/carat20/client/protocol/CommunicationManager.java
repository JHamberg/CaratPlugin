package org.carat20.client.protocol;

import android.util.Log;
import org.carat20.client.storage.DataStorage;
import java.util.ArrayList;
import java.util.List;
import org.apache.thrift.TException;
import org.carat20.client.thrift.CaratService;
import org.carat20.client.thrift.Feature;
import org.carat20.client.thrift.Reports;

public class CommunicationManager {

    private CaratService.Client instance;
    private DataStorage dataStorage;
    
    public CommunicationManager(DataStorage dataStorage){
        this.dataStorage = dataStorage;
    }
    
    public void refreshAllReports() throws TException {
        String uuid = "19db5bb46aa305ae";
        String model = "GT-I9505";
        String OS = "5.0.1";
        
        this.refreshMainReports(uuid, model, OS);
    }
    
    private void refreshMainReports(String uuid, String model, String os) throws TException{
        instance = ProtocolClient.open();
        Log.v("Carat", "Opening instance, instance is "+instance);
        Reports r = instance.getReports(uuid, getFeatures("Model", model, "OS", os));
        Log.v("Carat", "Getting reports, reports are "+r);
        dataStorage.writeReports(r);
        //Close protocol safely
        //...
    }
    
    	private List<Feature> getFeatures(String key1, String val1, String key2, String val2) {
		List<Feature> features = new ArrayList<>();
		if (key1 == null || val1 == null || key2 == null || val2 == null) {
			System.exit(1);
			return features;
		}
		Feature feature = new Feature();
                feature.setKey(key1).setValue(val1);
		features.add(feature);

		feature = new Feature().setKey(key2).setValue(val2);
		features.add(feature);
		return features;
	}
}
