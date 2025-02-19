import abstractions.Set;
import abstractions.SetFactory;
import benchmark.Test;

import java.util.ArrayList;
import java.util.List;

public class InsertWithScanDeleteGetBenchmark implements Test {
    private Set set;
    private int numberOfTests,percentInsert,percentDelete,percentGet;
    public InsertWithScanDeleteGetBenchmark(Set set, int percentInsert, int percentDelete,int percentGet, int numberOfTests){
        this.set = set;
        this.numberOfTests = numberOfTests;
        this.percentInsert = percentInsert;
        this.percentDelete = percentDelete;
        this.percentGet = percentGet;
    }
    @Override
    public void run() {

        int availableProcessors = Runtime.getRuntime().availableProcessors();

        int dataRange = 1000000;
        int numberOfThreads = 1;
        int numberOfScanThreads = 16;
        int testDuration=10000;
        int perAdd=percentInsert;
        int perContains=percentGet;
        int perRemove=100-(percentInsert+percentGet);


        System.out.println("Starting, "+ perAdd+"% insert, "+perRemove+"% delete "+perContains+"% get");
        System.out.print("Result Order: Add Delete Get Scan per number of threads");
        List<String> ratePerSecAddList = new ArrayList<String>();
        List<String> ratePerSecDeleteList = new ArrayList<String>();
        List<String> ratePerSecGetList = new ArrayList<String>();
        List<String> ratePerSecScanList = new ArrayList<String>();
        for (int i = 0; i < this.numberOfTests; i++) {
            double ratePerSecAdd =0.0;
            double ratePerSecDelete =0.0;
            double ratePerSecContains =0.0;
            double ratePerSecScan =0.0;
            for (int j=0;j<10;j++){
                TestSet.seed(set,dataRange,dataRange/2);
                TestResult testResult = TestSet.runTest(set, numberOfThreads+numberOfScanThreads, numberOfScanThreads ,dataRange, perContains, perAdd,1,32000,testDuration,false);
                double perSecAdd = (testResult.TotalAdds.longValue()/10);
                double perSecDelete = (testResult.TotalRemoves.longValue()/10);
                double perSecContains = (testResult.TotalContains.longValue()/10);
                double perSecScan = (testResult.numberOfScannedKeys.longValue()/10);
               /* System.out.println(testResult.TotalAdds);
                System.out.println(testResult.TotalRemoves);
                System.out.println(testResult.TotalContains);*/
                ratePerSecAdd += (perSecAdd/1000000);
                ratePerSecDelete += (perSecDelete/1000000);
                ratePerSecContains += (perSecContains/1000000);
                ratePerSecScan += (perSecScan/1000000);


                this.set = ((SetFactory)set).newInstance();

            }

            String addResult = "("+numberOfThreads +","+ ratePerSecAdd/10 +"),";
            String deleteResult = "("+numberOfThreads +","+ ratePerSecDelete/10 +"),";
            String containsResult = "("+numberOfThreads +","+ ratePerSecContains/10 +"),";
            String scanResult = "("+16 +","+ ratePerSecScan/10 +"),";

            System.out.print("ok");


            ratePerSecAddList.add(addResult);
            ratePerSecDeleteList.add(deleteResult);
            ratePerSecGetList.add(containsResult);
            ratePerSecScanList.add(scanResult);

            //numberOfThreads++;
            numberOfThreads*=2;

            //System.out.println(((SetFactory)this.set).getName());

        }
        System.out.println("Finished, the results are below");
        System.out.println("INSERT");
        for(int i=0;i<this.numberOfTests;i++){
            System.out.print(ratePerSecAddList.get(i));
        }
        System.out.println("INSERT - END ");
        System.out.println("DELETE");
        for(int i=0;i<this.numberOfTests;i++){
            System.out.print(ratePerSecDeleteList.get(i));
        }
        System.out.println("DELETE - END");
        System.out.println("GET");
        for(int i=0;i<this.numberOfTests;i++){
            System.out.print(ratePerSecGetList.get(i));
        }
        System.out.println("GET - END ");
        System.out.println("SCAN");
        for(int i=0;i<this.numberOfTests;i++){
            System.out.print(ratePerSecScanList.get(i));
        }
        System.out.println("SCAN - END");
        System.out.println("done");
    }
}
