import abstractions.Set;
import abstractions.SetFactory;
import benchmark.Test;

public class Get90Insert9Delete1 implements Test {
    Set set;
    int insertPercent, deletePercent, maxNumberOfScanThreads,getPercent;

    public Get90Insert9Delete1(Set set){
        this.set = set;
        this.insertPercent = 9;
        this.deletePercent = 1;
        this.getPercent = 90;
        this.maxNumberOfScanThreads = maxNumberOfScanThreads;
    }

    public void run(){
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int dataRange = 1000000;
        int numberOfThreads = 1;
        int numberOfScanThreads = 0;//Integer.parseInt(args[0]);
        int numberOfTests = 7;//maxNumberOfScanThreads;
        int testDuration=10000;
        int perAdd=this.insertPercent;
        int perContains=0;
        int perRemove=this.deletePercent;
        boolean scanOnly=false;
        /// int perRange=100-perAdd-perContains-perRemove;

            /*System.out.println("Number of available processors: "+availableProcessors);
            System.out.println("Number of tests: "+numberOfTests);
            System.out.println("Dataset Size: "+dataRange+" keys");
            System.out.println("Single test duration: "+testDuration+ " ms");
            System.out.println("insert: "+perAdd+"%");
            System.out.println("remove: "+perRemove+"%");
            System.out.println("contains: "+perContains+"%");
            System.out.println("name: "+((SetFactory)set).getName());
            System.out.println("Scan32KWithInsertsAndDeletesBenchmark, Starting....");
            System.out.println("Results are number of keys per sec");
            */

        for (int i = 0; i < numberOfTests; i++) {
            double ratePerSec =0.0;
            for (int j=0;j<10;j++){
                TestSet.seed(set,dataRange,dataRange/2);
                TestResult testResult = TestSet.runTest(set, numberOfThreads, numberOfScanThreads ,dataRange, this.getPercent, perAdd,1,32000,testDuration,scanOnly);
                double perSec = (testResult.TotalContains.longValue()/10);
                ratePerSec += (perSec/1000000);
                this.set = ((SetFactory)set).newInstance();

            }
            System.out.print("("+numberOfThreads +","+ ratePerSec/10 +") ");
            //numberOfThreads++;
            numberOfThreads*=2;

            //System.out.println(((SetFactory)this.set).getName());

        }
        System.out.println("done");
    }

}
