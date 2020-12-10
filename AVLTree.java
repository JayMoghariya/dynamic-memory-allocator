// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {

    private AVLTree left, right; // Children.
    private AVLTree parent; // Parent pointer.
    private int height; // The height of the subtree

    public AVLTree() {
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root!
        // and left child will always be null.

    }

    public AVLTree(int address, int size, int key) {
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions.
    // Some of the functions may be directly inherited from the BSTree class and
    // nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    public int getbalance(AVLTree geth) {
        if (geth.left == null && geth.right == null) {
            return 0;
        }
        if (geth.left == null) {
            return -1 - (geth.right.height);
        } else if (geth.right == null) {
            return (geth.left.height) + 1;
        } else {
            return geth.left.height - geth.right.height;
        }
    }

    public boolean checkbalance(AVLTree checkb) {
        if (Math.abs(getbalance(checkb)) <= 1) {
            return true;
        }
        return false;
    }

    public AVLTree leftrotate(AVLTree ll) {
        AVLTree temp1 = ll.right;
        AVLTree temp = ll.parent;
        int y = 0;
        if (temp.left == ll) {
            y = 1;
        }
        ll.right = temp1.left;
        if (ll.right != null){
            ll.right.parent = ll;
        }
        temp1.left = ll;
        temp1.left.parent = temp1;
        temp1.parent = temp;
        if (y == 1) {
            temp.left = temp1;
        } else {
            temp.right = temp1;
        }
        updateheight(ll);
        updateheight(temp1);
        updateheight(temp);
        return temp1;
    }

    private AVLTree rightrotate(AVLTree ll) {
        AVLTree temp1 = ll.left;
        AVLTree temp = ll.parent;
        int y = 0;
        if (temp.left == ll) {
            y = 1;
        }
        ll.left = temp1.right;
        if (ll.left != null){
            ll.left.parent = ll;
        }
        temp1.right = ll;
        temp1.right.parent = temp1;
        temp1.parent = temp;
        if (y == 1) {
            temp.left = temp1;
        } else {
            temp.right = temp1;
        }
        updateheight(ll);
        updateheight(temp1);
        updateheight(temp);
        return temp1;
    }

    private AVLTree leftrightrotate(AVLTree tt) {
        tt.left = leftrotate(tt.left);
        tt.left.parent = tt;
        return rightrotate(tt);
    }

    private AVLTree rightleftrotate(AVLTree tt) {
        tt.right = rightrotate(tt.right);
        tt.right.parent = tt;
        return leftrotate(tt);
    }

    private void updateheight(AVLTree t) {
        if (t.left == null && t.right == null) {
            t.height = 0;
        } else if (t.left == null) {
            t.height = t.right.height + 1;
        } else if (t.right == null) {
            t.height = t.left.height + 1;
        } else {
            t.height = Math.max(t.left.height, t.right.height) + 1;
        }
        // t=t.parent;
        // }

        return;
    }

    private AVLTree rebalance(AVLTree nn) {
        if (getbalance(nn) > 1) {
            if (getbalance(nn.left) >= 0) {
                nn = rightrotate(nn);
            } else {
                nn = leftrightrotate(nn);
            }
        } else if (getbalance(nn) < -1) {
            if (getbalance(nn.right) > 0) {
                nn = rightleftrotate(nn);
            } else {
                nn = leftrotate(nn);
            }
        }
        return nn;
    }

    public void checkins(AVLTree f, int what) {
        while (f.parent != null) {
            updateheight(f);
            if (checkbalance(f)) {
                f = f.parent;
            } else {
                if (what == 0) {
                    AVLTree cc = rebalance(f);
                    f = cc.parent;
                } else {
                    rebalance(f);
                    return;
                }
            }
        }
        return;
    }

    public AVLTree Insert(int address, int size, int key) {
        AVLTree newins = new AVLTree(address, size, key);
        AVLTree currins = this;
        while (currins.parent != null) {
            currins = currins.parent;
        }
        if (currins.right == null) {
            currins.right = newins;
            newins.parent = currins;
            return newins;
        }
        currins = currins.right;
        while (true) {
            if (currins.key > key) {
                if (currins.left == null) {
                    currins.left = newins;
                    newins.parent = currins;
                    checkins(newins, 1);
                    return newins;
                } else {
                    currins = currins.left;
                }
            } else if (currins.key == key) {
                if (currins.address > address) {
                    if (currins.left == null) {
                        currins.left = newins;
                        newins.parent = currins;
                        checkins(newins, 1);
                        return newins;
                    } else {
                        currins = currins.left;
                    }
                } else {
                    if (currins.right == null) {
                        currins.right = newins;
                        newins.parent = currins;
                        checkins(newins, 1);
                        return newins;
                    } else {
                        currins = currins.right;
                    }
                }
            } else {
                if (currins.right == null) {
                    currins.right = newins;
                    newins.parent = currins;
                    checkins(newins, 1);
                    return newins;
                } else {
                    currins = currins.right;
                }
            }
        }
        // return null;
    }
    // public void checkdel(AVLTree ok){
    // if(checkbalance(ok)){
    // ok=ok.parent;
    // }

    // }
    public void deletenode(AVLTree dn) {
        if (dn.right == null) {
            if (dn.left == null) {
                if (dn.parent.left == dn) {
                    AVLTree hh = dn.parent;
                    dn.parent.left = null;
                    dn.parent = null;
                    checkins(hh, 0);
                    return;
                } else {
                    dn.parent.right = null;
                    checkins(dn.parent, 0);
                    dn.parent = null;
                    return;
                }
            } else {
                if (dn.parent.left == dn) {
                    AVLTree hh = dn.parent;
                    dn.parent.left = dn.left;
                    dn.left.parent = dn.parent;
                    dn.parent = null;
                    dn.left = null;
                    checkins(hh, 0);
                    return;
                } else {
                    AVLTree hh = dn.parent;
                    dn.parent.right = dn.left;
                    dn.left.parent = dn.parent;
                    dn.parent = null;
                    dn.left = null;
                    checkins(hh, 0);
                    return;
                }
            }
        } else if (dn.left == null) {
            if (dn.parent.left == dn) {
                AVLTree hh = dn.parent;
                dn.parent.left = dn.right;
                dn.right.parent = dn.parent;
                dn.parent = null;
                dn.right = null;
                checkins(hh, 0);
                return;
            } else {
                AVLTree hh = dn.parent;
                dn.parent.right = dn.right;
                dn.right.parent = dn.parent;
                dn.parent = null;
                dn.right = null;
                checkins(hh, 0);
                return;
            }
        } else {
            AVLTree successor = dn.left;
            while (successor.right != null) {
                successor = successor.right;
            }
            dn.key = successor.key;
            dn.address = successor.address;
            dn.size = successor.size;
            deletenode(successor);
            checkins(dn, 0);
            return;
        }
    }

    public boolean Delete(Dictionary e) {
        AVLTree findtemp = this;
        while (findtemp.parent != null) {
            findtemp = findtemp.parent;
        }
        if (findtemp.right == null) {
            return false;
        }
        findtemp = findtemp.right;
        while (findtemp != null) {
            if (findtemp.key == e.key) {
                if (findtemp.address == e.address) {
                    if (findtemp.size == e.size) {
                        deletenode(findtemp);
                        return true;
                    } else {
                        return false;
                    }
                } else if (findtemp.address > e.address) {
                    findtemp = findtemp.left;
                } else {
                    findtemp = findtemp.right;
                }
            } else if (findtemp.key < e.key) {
                findtemp = findtemp.right;
            } else {
                findtemp = findtemp.left;
            }
        }
        return false;
    }

    public AVLTree Find(int key, boolean exact) {
        if (exact == true) {
            AVLTree findtemp = this;
            while (findtemp.parent != null) {
                findtemp = findtemp.parent;
            }
            if (findtemp.right == null) {
                return null;
            }
            AVLTree leastgreater = findtemp;
            findtemp = findtemp.right;
            while (true) {
                if (findtemp == null) {
                    if (leastgreater.size != -1) {
                        return leastgreater;
                    }
                    return null;
                } else if (findtemp.key == key) {
                    if (findtemp.left == null) {
                        return findtemp;
                    }
                    leastgreater = findtemp;
                    findtemp = findtemp.left;
                    // BSTree copycurr=findtemp;
                    // findtemp=findtemp.left;
                    // while(findtemp.right!=null){
                    // findtemp=findtemp.right;
                    // }
                    // if (findtemp.key==key){
                    // return findtemp;
                    // }
                    // return copycurr;
                } else if (findtemp.key < key) {
                    findtemp = findtemp.right;
                } else {
                    findtemp = findtemp.left;
                }
            }
        } else if (exact == false) {
            AVLTree findtemp = this;
            while (findtemp.parent != null) {
                // if (findtemp.key==key){return findtemp;}
                findtemp = findtemp.parent;
            }
            if (findtemp.right == null) {
                return null;
            }
            AVLTree leastgreater = findtemp;
            findtemp = findtemp.right;
            while (findtemp != null) {
                if (findtemp.key < key) {
                    if (findtemp.right == null) {
                        break;
                    }
                    findtemp = findtemp.right;
                } else {
                    leastgreater = findtemp;
                    if (findtemp.left == null) {
                        return findtemp;
                    }
                    findtemp = findtemp.left;
                }
            }
            if (leastgreater.size != -1) {
                return leastgreater;
            }
        }
        return null;
    }

    public AVLTree getFirst() {
        AVLTree tempfirst = this;
        while (tempfirst.parent != null) {
            tempfirst = tempfirst.parent;
        }
        if (tempfirst.parent == null) {
            tempfirst = tempfirst.right;
        }
        if (tempfirst == null) {
            return null;
        }
        while (tempfirst.left != null) {
            tempfirst = tempfirst.left;
        }
        return tempfirst;
        // return null;
    }

    public AVLTree getNext() {
        AVLTree nextNode = this;
        if (nextNode.parent == null) {
            return null;
        }
        if (nextNode.right != null) {
            nextNode = nextNode.right;
            while (nextNode.left != null) {
                nextNode = nextNode.left;
            }
            return nextNode;
        } else {
            while (nextNode.parent != null && nextNode.parent.left != nextNode) {
                nextNode = nextNode.parent;
            }
            if (nextNode.parent == null) {
                return null;
            } else {
                return nextNode.parent;
            }
        }
        // return null;
    }

    public boolean checksani(AVLTree t) {
        if (t == null) {
            return true;
        } else if (t.left == null) {
            if (t.right == null) {
                return true;
            } else {
                if (t.right.parent == t) {
                    return true && checksani(t.right);
                } else {
                    return false;
                }
            }
        } else if (t.right == null) {
            if (t.left == null) {
                return true;
            } else {
                if (t.left.parent == t) {
                    return true && checksani(t.left);
                } else {
                    return false;
                }
            }
        } else {
            if (t.left.parent == t && t.right.parent == t) {
                return true && checksani(t.left) && checksani(t.right);
            } else {
                return false;
            }
        }
    }
    private boolean balancesani(AVLTree hh){
        // while(hh.parent!=null){
        //     hh=hh.parent;
        // }
        if (hh==null){
            return true;
        }
        if (hh.parent==null && hh.height!=0 && hh.size!=-1 && hh.left!=null){
            return false;
        }
        if (hh.parent==null ){
            hh=hh.right;
            if (hh==null){return true;}
            
        }
        return checkbalance(hh) && balancesani(hh.left) && balancesani(hh.right);

    }
    public boolean sanity() {
        boolean sanisani=balancesani(this);
        // boolean sanisani2;
        AVLTree tempsani = this;
        while (tempsani.parent != null) {
            tempsani = tempsani.parent;
        }
        if (tempsani.parent == null && tempsani.size != -1) {
            return false;
        }
        if (tempsani.left != null) {
            return false;
        }
        return sanisani && checksani(tempsani.right);
        // return false;
    }
}

// private AVLTree balanceheight(AVLTree tt){
//     AVLTree par;
//     AVLTree grandpar;
//     if(tt.parent.parent.size==-1){
//         par=tt.parent;
//         grandpar=par.parent;
//         return null;
//     }
//     while(tt.parent.parent.size!=-1){
//         updateheight(tt);
//         updateheight(par);
//         updateheight(grandpar);
//         if(Math.abs(grandpar.left.key-grandpar.right.key) <=1){
//             tt=par;
//             par=grandpar;
//             grandpar=grandpar.parent;
//             return tt;
//         }
//         else{
//             if(grandpar.right==par){
//                 if(par.right==tt){
//                     AVLTree aa=grandpar.left;
//                     AVLTree bb=par.left;
//                     AVLTree pp=grandpar.parent;
//                     if(pp.right==grandpar){
//                         pp.right=par;
//                         par.parent=pp;
//                     }
//                     else{
//                         pp.left=par;
//                         par.parent=pp;
//                     }
//                     par.left=grandpar;
//                     par.right=tt;
//                     grandpar.right=bb;
//                     updateheight(aa);
//                     updateheight(bb);
//                     updateheight(tt);
//                     updateheight(grandpar);
//                     updateheight(pp);
//                 }
//                 else{
//                     AVLTree aa=grandpar.left;
//                     AVLTree bb=tt.right;
//                     AVLTree cc=tt.left;
//                     AVLTree pp=tt.parent;
//                     if(pp.right==grandpar){
//                         par.right=tt;
//                         tt.parent=pp;
//                     }
//                     else{
//                         pp.left=tt;
//                         tt.parent=pp;
//                     }
//                     tt.left=grandpar;
//                     tt.right=par;
//                     grandpar.right=cc;
//                     par.left=bb;
//                     updateheight(aa);
//                     updateheight(cc);
//                     updateheight(bb);
//                     updateheight(tt);
//                     updateheight(par);
//                     updateheight(tt);
//                     updateheight(par);
//                 }
//             }
//             else{
//                 if(par.left==tt){
//                     // AVLTree aa=tt.left;
//                     AVLTree bb=par.right;
//                     AVLTree pp=grandpar.parent;
//                     if(pp.right==grandpar){
//                         pp.right=par;
//                         par.parent=pp;
//                     }
//                     else{
//                         pp.left=par;
//                         par.parent=pp;
//                     }
//                     par.left=grandpar;
//                     par.right=tt;
//                     tt.left=bb;
//                     // updateheight(aa);
//                     updateheight(bb);
//                     updateheight(tt);
//                     updateheight(par);
//                     updateheight(pp);
//                 }
//                 else{
//                     AVLTree aa=grandpar.left;
//                     AVLTree bb=grandpar.right;
//                     // AVLTree cc=grandchild.left;
//                     AVLTree pp=grandpar.parent;
//                     if(pp.right==grandpar){
//                         pp.right=tt;
//                         tt.parent=pp;
//                     }
//                     else{
//                         pp.left=tt;
//                         tt.parent=pp;
//                     }
//                     tt.left=grandpar;
//                     tt.right=par;
//                     // tt.right=cc;
//                     par.left=bb;
//                     updateheight(aa);
//                     updateheight(bb);
//                     updateheight(grandpar);
//                     updateheight(par);
//                     updateheight(tt);
//                     updateheight(pp);
//                 }
//             }
//             return tt;
//         }
//     }
// }
