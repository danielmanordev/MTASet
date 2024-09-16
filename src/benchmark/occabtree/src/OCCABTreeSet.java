package benchmark.occabtree.src;

import abstractions.Set;
import abstractions.SetFactory;
import benchmark.occabtreewithscan.src.OCCABTreeWithScan;

public class OCCABTreeSet implements Set, SetFactory {

    private OCCABTree occABTree;
    int a,b;
    public OCCABTreeSet(int a, int b){
        this.occABTree = new OCCABTree(a,b);
        this.a =a;
        this.b =b;
    }


    @Override
    public int add(int key, int value) {
        return this.occABTree.add(key,value);
    }

    @Override
    public int contains(int key) {
        return this.occABTree.find(key).getValue();
    }

    @Override
    public int remove(int key) {
        return this.occABTree.tryDelete(key).getValue();
    }

    @Override
    public int getRange(int[] result, int low, int high) {
       return 0;
    }

    @Override
    public Set newInstance() {
        return new OCCABTreeSet(a,b);
    }

    @Override
    public String getName() {
        return "OCCABTreeWithScanSet";
    }
}
