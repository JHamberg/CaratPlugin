package org.carat20.client.storage;
import android.util.Log;
import org.carat20.client.thrift.Reports;

public class DataStorage {
    Reports r;

    public DataStorage() {
        r = null;
    }
    
    public Reports getReports(){
        return r;
    }

    public void writeReports(Reports r) {
        Log.v("Carat", "Writing reports, reports are "+r);
        this.r = r;
    }
    
}
