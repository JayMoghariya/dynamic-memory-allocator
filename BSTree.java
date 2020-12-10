

// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    {
        // if(this.sanity()==false){
        //     return null;
        // }
        BSTree newins = new BSTree(address, size, key);
        BSTree currins = this;
        while(currins.parent!=null){
            currins=currins.parent;}
        if (currins.right==null){
            currins.right=newins;
            newins.parent=currins;
            return newins;
        }
        currins=currins.right;
        while(true){
            if (currins.key>key){
                if(currins.left==null){
                    currins.left=newins;
                    newins.parent=currins;
                    return newins;
                }
                else{
                    currins=currins.left;
                }
            }
            else if(currins.key==key){
                if (currins.address>address){
                    if(currins.left==null){
                        currins.left=newins;
                        newins.parent=currins;
                        return newins;
                    }
                    else{
                        currins=currins.left;
                    }
                }
                else{
                    if(currins.right==null){
                        currins.right=newins;
                        newins.parent=currins;
                        return newins;
                    }
                    else{
                        currins=currins.right;
                    }
                }
            }
            else{
                if(currins.right==null){
                    currins.right=newins;
                    newins.parent=currins;
                    return newins;
                }
                else{
                    currins=currins.right;
                }
            }
        }
        // return null;
    }
    public void deletenode(BSTree dn){
        if (dn.right==null){
            if(dn.left==null){
                if(dn.parent.left==dn){
                    dn.parent.left=null;
                    dn.parent=null;
                    return;
                }
                else{
                    dn.parent.right=null;
                    dn.parent=null;
                    return;
                }
            }
            else{
                if(dn.parent.left==dn){
                    dn.parent.left=dn.left;
                    dn.left.parent=dn.parent;
                    dn.parent=null;
                    dn.left=null;
                    return;
                }
                else{
                    dn.parent.right=dn.left;
                    dn.left.parent=dn.parent;
                    dn.parent=null;
                    dn.left=null;
                    return;
                }
            }
        }
        else if(dn.left==null){
            if(dn.parent.left==dn){
                dn.parent.left=dn.right;
                dn.right.parent=dn.parent;
                dn.parent=null;
                dn.right=null;
                return;
            }
            else{
                dn.parent.right=dn.right;
                dn.right.parent=dn.parent;
                dn.parent=null;
                dn.right=null;
                return;
            }
        }
        else{
            BSTree successor=dn.left;
            while(successor.right!=null){
                successor=successor.right;
            }
            dn.key=successor.key;
            dn.address=successor.address;
            dn.size=successor.size;
            deletenode(successor);
            return;
        }
    }

    public boolean Delete(Dictionary e)
    {
        BSTree findtemp= this;
        while(findtemp.parent!=null){
            findtemp=findtemp.parent;
        }
        if (findtemp.right==null){
            return false;
        }
        findtemp=findtemp.right;
        while (findtemp!=null){
            if(findtemp.key==e.key){
                if(findtemp.address==e.address){
                    if(findtemp.size==e.size){
                        deletenode(findtemp);
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                else if (findtemp.address>e.address){
                    findtemp=findtemp.left;
                }
                else{
                    findtemp=findtemp.right;
                }
            }
            else if(findtemp.key<e.key){
                findtemp=findtemp.right;
            }
            else{
                findtemp=findtemp.left;
            }
        }
        return false;
    }
        
    public BSTree Find(int key, boolean exact)
    { 
        // if(this.sanity()==false){
        //     return null;
        // }
        if (exact==true){
            BSTree findtemp= this;
            while(findtemp.parent!=null){
                findtemp=findtemp.parent;
            }
            if(findtemp.right==null){
                return null;}
            BSTree leastgreater = findtemp;
            findtemp=findtemp.right;
            while(true){
                if (findtemp==null){
                    if ( leastgreater.size!=-1){
                        return leastgreater;
                    }
                    return null;
                }
                else if(findtemp.key==key){
                    if(findtemp.left==null){
                        return findtemp;
                    }
                    leastgreater=findtemp;
                    findtemp=findtemp.left;
                    // BSTree copycurr=findtemp;
                    // findtemp=findtemp.left;
                    // while(findtemp.right!=null){
                    //     findtemp=findtemp.right;
                    // }
                    // if (findtemp.key==key){
                    //     return findtemp;
                    // }
                    // return copycurr;
                }
                else if(findtemp.key<key){
                    findtemp=findtemp.right;}
                else{
                    findtemp=findtemp.left;
                }                
            }
        }
        else if (exact==false){
            BSTree findtemp= this;
            while(findtemp.parent!=null){
                // if (findtemp.key==key){return findtemp;}
                findtemp=findtemp.parent;
            }
            if(findtemp.right==null){
                return null;}
            BSTree leastgreater = findtemp;
            findtemp=findtemp.right;
            while(findtemp!=null){
                if(findtemp.key==key){
                    if(findtemp.left==null){
                        return findtemp;
                    }
                    BSTree copycurr=findtemp;
                    findtemp=findtemp.left;
                    while(findtemp.right!=null){
                        findtemp=findtemp.right;
                    }
                    if (findtemp.key==key){
                        return findtemp;
                    }
                    return copycurr;}
                else if(findtemp.key<key){
                    if (findtemp.right==null){
                        break;
                    }
                    findtemp=findtemp.right;
                }
                else {
                    leastgreater=findtemp;
                    if(findtemp.left==null){
                        return findtemp;
                    }
                    findtemp=findtemp.left;
                }
            }
            if(leastgreater.size!=-1){
                return leastgreater;
            }     
        }
        return null;
    }

    public BSTree getFirst()
    {
        BSTree tempfirst =this;
        while(tempfirst.parent!=null){
            tempfirst=tempfirst.parent;
        }
        if (tempfirst.parent==null){
            tempfirst=tempfirst.right;
        }
        if (tempfirst==null){
            return null;
        }
        while (tempfirst.left!=null){
            tempfirst=tempfirst.left;
        }
        return tempfirst;
    }

    public BSTree getNext()
    { 
        BSTree nextNode=this;
        if (nextNode.parent==null){
            return null;
        }
        if (nextNode.right!=null){
            nextNode=nextNode.right;
            while(nextNode.left!=null){
                nextNode=nextNode.left;
            }
            return nextNode;
        }
        else{
            while( nextNode.parent!=null && nextNode.parent.left!=nextNode){
                nextNode=nextNode.parent;
            }
            if (nextNode.parent==null){
                return null;
            }
            else{
                return nextNode.parent;
            }
        }
        //return null;
    }

    public boolean checksani(BSTree t){
        if(t==null){
            return true;
        }
        else if(t.left==null){
            if(t.right==null){return true;}
            else{
                if(t.right.parent==t){
                    return true && checksani(t.right);
                }
                else{return false;}
            }
        }
        else if(t.right==null){
            if(t.left==null){return true;}
            else{
                if(t.left.parent==t){
                    return true && checksani(t.left);
                }
                else{return false;}
            }
        }
        else{
            if(t.left.parent==t && t.right.parent==t){
                return true && checksani(t.left) && checksani(t.right);
            }
            else{return false;}
        }
    }
    public boolean sanity()
    {
        BSTree tempsani=this;
        while(tempsani.parent!=null){
            tempsani=tempsani.parent;
        }
        if(tempsani.parent==null && tempsani.size!=-1 ){
            return false;
        }
        if (tempsani.left!=null){
            return false;
        }
        return checksani(tempsani.right);
        // return false;
    }
    
}


 


