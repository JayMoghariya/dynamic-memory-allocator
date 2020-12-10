// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        // Dictionary findele1 = freeBlk.Find(blockSize, true);
        // if (findele1!=null){
        //     allocBlk.Insert(findele1.address,blockSize, findele1.address);
        //     // freeBlk.Insert(findele.address+findele.size, findele.size-blockSize, findele.size-blockSize);//do we have to add +1 here or not?
        //     freeBlk.Delete(findele1);
        //     return findele1.address;
        // }
        if (blockSize <= 0) return -1;
        Dictionary findele = freeBlk.Find(blockSize, false);
        if (findele==null){
            return -1;
        }
        else {
            allocBlk.Insert(findele.address,blockSize, findele.address);
            if (findele.size != blockSize) {
                freeBlk.Insert(findele.address+blockSize, findele.size-blockSize, findele.size-blockSize);
        }
            freeBlk.Delete(findele);
            return findele.address;
        }
        // return -1;
    } 
    
    public int Free(int startAddr) {
        if (startAddr<0){
            return -1;
        }
        Dictionary freeele= allocBlk.Find(startAddr,true);
        if (freeele!=null){
            freeBlk.Insert(freeele.address, freeele.size,freeele.size);
            allocBlk.Delete(freeele);
            return 0;
        }
        return -1;
    }
}