import abstractions.Set;
import abstractions.SetFactory;
import benchmark.JavaConcurrentSkipList;
import benchmark.Test;

import java.util.ArrayList;

public class ScanOnlyBenchmark implements Test {
    Set set;
    int numberOfTests;

    public ScanOnlyBenchmark(Set set, int numberOfTests){
        this.set = set;
        this.numberOfTests = numberOfTests;
    }

     public void run(){
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int dataRange = 1000000;
        int numberOfThreads = 1;
        int numberOfScanThreads = 1;//Integer.parseInt(args[0]);
        int numberOfTests = this.numberOfTests;
        int testDuration=10000;

        System.out.println("Number of available processors: "+availableProcessors);
        System.out.println("Number of tests: "+numberOfTests);
        System.out.println("Dataset Size: "+dataRange+" keys");
        System.out.println("Single test duration: "+testDuration+ " ms");
        System.out.println("name: "+((SetFactory)set).getName());
        System.out.println("Scan Only, Starting....");


        for (int i = 0; i < numberOfTests; i++) {

            TestSet.fill(set,64000);
            TestResult testResult = TestSet.runTest(set, numberOfThreads, numberOfScanThreads ,dataRange, 0, 0,1,32000,testDuration,true);
            System.out.print("("+numberOfScanThreads +","+ testResult.numberOfScannedKeys.longValue()+") ");
            numberOfThreads*=2;
            numberOfScanThreads*=2;
            this.set = ((SetFactory)set).newInstance();
        }


        }

}
