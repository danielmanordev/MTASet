import benchmark.JavaConcurrentSkipList;
import benchmark.occabtree.src.OCCABTreeSet;
import benchmark.occabtreewithscan.src.OCCABTreeWithScanSet;

public class BenchmarkInsertDelete5050 {

    public static void main(String[] args) {
        System.out.println("MTASET 5050 START");
        var mtaset5050 = new InsertDeleteGetBenchmark(new MTASet(2,256),50,50,0,7);
        mtaset5050.run();
        System.out.println("MTASET 5050 END");

        System.out.println("LINKED LIST 5050 - START");
        var cls5050 = new InsertDeleteGetBenchmark(new JavaConcurrentSkipList(),50,50,0,7);
        cls5050.run();
        System.out.println("LINKED LIST 5050 - END");



        System.out.println("OCCABTREE 5050 START");
        var occabtree5050 = new InsertDeleteGetBenchmark(new OCCABTreeSet(2,256),50,50,0,7);
        occabtree5050.run();
        System.out.println("OCCABTREE 5050 END");

        System.out.println("OCCABTREEAB WITH SCAN 5050 START");
        var occabtreescanset5050 = new InsertDeleteGetBenchmark(new OCCABTreeWithScanSet(2,256),50,50,0,7);
        occabtreescanset5050.run();
        System.out.println("OCCABTREEAB WITH SCAN 5050 END");

    }

}
