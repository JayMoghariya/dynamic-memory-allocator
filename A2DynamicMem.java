
// import java.util.Dictionary;

// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
    //Your BST (and AVL tree) implementations should obey the property that keys in the left subtree <= root.key < keys in the right subtree. How is this total order between blocks defined? It shouldn't be a problem when using key=address since those are unique (this is an important invariant for the entire assignment123 module). When using key=size, use address to break ties i.e. if there are multiple blocks of the same size, order them by address. Now think outside the scope of the allocation problem and think of handling tiebreaking in blocks, in case key is neither of the two. 
    @Override public int Allocate(int blockSize) {
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
            int tempo=findele.address;
            freeBlk.Delete(findele);
            return tempo;
        }
        // return -1;
    } 
    @Override public void Defragment() {
        Dictionary dbst;
        if(type!=2 && type!=3){
            return;
        }
        else if (type==2){
            dbst=new BSTree();
        }
        else{
            dbst =new AVLTree();
        }
        Dictionary tempdef=freeBlk.getFirst();
            while(tempdef!=null){
                dbst.Insert(tempdef.address, tempdef.size,tempdef.address);
                tempdef=tempdef.getNext();
            }
            Dictionary tempdef1=dbst.getFirst();
            while(tempdef1!=null){
                Dictionary tempdef2=tempdef1.getNext();
                if(tempdef2==null){
                    break;
                }
                else if (tempdef2.address==(tempdef1.address+tempdef1.size)){
                    Dictionary copy1=new BSTree(tempdef1.address, tempdef1.size, tempdef1.key);
                    Dictionary copy2=new BSTree(tempdef2.address, tempdef2.size, tempdef2.key);
                    dbst.Delete(tempdef1);
                    dbst.Delete(tempdef2);
                    tempdef1=dbst.Insert(copy1.address,copy1.size+copy2.size,copy1.address);
                    copy1.key=copy1.size;
                    copy2.key=copy2.size;
                    freeBlk.Delete(copy1);
                    freeBlk.Delete(copy2);
                    freeBlk.Insert(copy1.address,copy1.size+copy2.size,copy1.size+copy2.size);
                }
                else{
                    tempdef1=tempdef1.getNext();
                }
            }   
        return ;
    }
}