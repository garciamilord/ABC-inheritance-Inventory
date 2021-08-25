/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abc;

import java.util.ArrayList;

/**
 *
 * @author garci
 */
public class databaseClass {

    private ArrayList<productClass> list = new ArrayList();
    private productClass info;
    private int index;
    private boolean found;

    public void search(String key) {
        found = false;
        int i = 0;
        while (!found && i < list.size()) {
            productClass b = list.get(i);
            if (b.getProduct().equalsIgnoreCase(key)) {
                info = b;
                found = true;
                index = i;
            } else {
                i++;
            }
        }
    }

    public void add(productClass newProduct) {
        list.add(newProduct);
    }

    public productClass delete(int i) {
        return list.remove(i);
    }

    public int getIndex() {
        return index;
    }

    public boolean inList() {
        return found;
    }
public productClass getProduct()
   {
       return info;
   }
   public int size()
   {
       return list.size();
   }

public boolean isEmpty()
   {
       return list.isEmpty();
   }
public ArrayList<productClass> getList()
   {
       return list;
   }
}


