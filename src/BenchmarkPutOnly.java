import benchmark.occabtree.src.OCCABTreeSet;
import benchmark.occabtreewithscan.src.OCCABTreeWithScanSet;

public class BenchmarkPutOnly {
    public static void main(String[] args) {

        System.out.println("MTA SET INSERT ONLY 2 256 - START");
        var mtasetPutOnly = new PutOnlyBenchmark(new MTASet(2, 256), 7);
        mtasetPutOnly.run();
        System.out.println("MTA SET INSERT ONLY 2 256 - END");


        System.out.println("occabtree INSERT ONLY 2 256 - START");
        var occabtree = new PutOnlyBenchmark(new OCCABTreeSet(2, 256), 7);
        occabtree.run();
        System.out.println("occabtree INSERT ONLY 2 256 - END");

        System.out.println("occabtreeWithScan INSERT ONLY 2 256 - START");
        var occabtreeWithScan = new PutOnlyBenchmark(new OCCABTreeWithScanSet(2, 256), 7);
        occabtreeWithScan.run();
        System.out.println("occabtreeWithScan INSERT ONLY 2 256 - END");





    }
}
