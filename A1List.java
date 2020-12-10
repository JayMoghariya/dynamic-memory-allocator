// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        A1List lastsenti=this.next;
        A1List firstNode=new A1List(address, size, key);
        this.next=firstNode;
        this.next.next=lastsenti;
        this.next.prev=this;
        firstNode.next.prev=firstNode;
        return firstNode;

        // if (this.getFirst()==null){
        //         A1List Firstsenti = this;
        //         A1List temp1=Firstsenti.next;
        //         Firstsenti.next = new A1List(address,size,key);
        //         Firstsenti.next.next = temp1;
        //         Firstsenti.next.prev =Firstsenti;
        //         Firstsenti.next.next.prev= Firstsenti.next;
        //         return Firstsenti.next;
        // }
        // A1List curr = this.getFirst();
        // while(curr.next.next != null){
        //     if (curr.prev.address <address && curr.address>address){
        //         A1List temp2=new A1List(address, size, key);
        //         curr.prev.next=temp2;
        //         temp2.next=curr;
        //         temp2.prev=curr.prev;
        //         curr.prev=temp2;
        //         curr=temp2;
        //         return temp2;
        //     }
        // }
        // if (curr.address <address){
        //     curr=curr.next;
        //     A1List temp2=new A1List(address, size, key);
        //     curr.prev.next=temp2;
        //     temp2.next=curr;
        //     temp2.prev=curr.prev;
        //     curr.prev=temp2;
        //     return temp2;
        // }
        // return null;
    }

    public boolean Delete(Dictionary d) 
    {
        A1List delcurr=this.getFirst();
        if (delcurr==null){
            return false;
        }
        while(delcurr.next!=null){
            if (delcurr.key==d.key && delcurr.address==d.address && delcurr.size==d.size){
                A1List deltemp=delcurr.next;
                delcurr.prev.next=deltemp;
                deltemp.prev=delcurr.prev;
                delcurr.next=null;
                delcurr.prev=null;
                return true;
            }
            delcurr=delcurr.next;
        }        
        return false;
    }

    public A1List Find(int k, boolean exact)
    {
        if (this.getFirst()==null){
            return null;
        }
        else if (exact==true){
            A1List findcurr = this.getFirst();
            while(findcurr.next!=null){
                if (findcurr.key==k){
                    // exact=false;
                    return findcurr;
                }
                findcurr=findcurr.next;
            }
        }
        else{
            A1List findcurr = this.getFirst();
            while(findcurr.next!=null){
                if (findcurr.key>=k){
                    // exact=true;
                    return findcurr;
                }
                findcurr=findcurr.next;
            }
        }
        // while(findcurr.getNext()!=null ||findcurr.getNext().address !=-1 ){
        //     if (findcurr.key==k){
        //         exact=true;
        //         break;
        //     }
        // }
        return null;
    }

    public A1List getFirst()
    {
        A1List fff = this;//given that this pointer is of list not its first element.
        while(fff.prev !=null){
            fff=fff.prev;
        }
        if( fff.next.address==-1){
            return null;
        }
        return fff.next;
    }
    
    public A1List getNext() 
    { 
        if (this.getFirst()==null || this.next==null|| this.next.next==null){
            return null;//even if the list is empty?
        }
        return this.next;
    }

    public boolean sanity()
    {
        //case when cycle is there.
        A1List cyclesani1 = this;
        A1List cyclesani2 = this.next;
        while (cyclesani1!=null && cyclesani2!=null){
            if (cyclesani1==cyclesani2){
                return false;
            }
            else if (cyclesani2.next==null){
                break;
            }
            cyclesani1=cyclesani1.next;
            cyclesani2=cyclesani2.next.next;
        }
        A1List currsani = this;//called over list so this gives pointer to header.
        while(currsani.prev != null) {
            currsani = currsani.prev;
            }
            if (currsani.address!=-1){
                return false;}
        while(currsani.next!=null){
            if(currsani.prev==null){
                if (currsani.address!=-1){
                    return false;
                }
            }
            if (currsani.next.prev!=currsani){
                return false;
            }
            currsani=currsani.next;
        }
        if(currsani.address!=-1 && currsani.next==null){
            return false;
        }
        return true;
    }

}


