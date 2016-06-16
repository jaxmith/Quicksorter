package cse214hw5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//Jack Smith 110366081

public class Quicksorter<E> implements Sorter<E> {
    
    private Comparator<E> comparator;
    private ArrayList<E> list;
    
    public Quicksorter(Comparator<E> c, ArrayList<E> list){
        comparator = c;
        this.list = list;
    }

    @Override
    public void sort() {
       sort(list, 0, list.size()-1); 
    }
    
    public void sort(ArrayList<E> list, int first, int last){
        int left, right;
        if(first >= last)
            return;
        left = first; right = last;
        E pivot = list.get((first + last)/2);
        do{
            while(comparator.compare(pivot, list.get(left)) > 0){ 
                left++;
            }
            while(comparator.compare(pivot, list.get(right)) < 0){
                right--;
            }
            
            if(left <= right)
                Collections.swap(list, left++, right--);
            
        }while(left <= right);
        sort(list, first, right);
        sort(list, left, last);
    }

    @Override
    public void setComparator(Comparator<E> comparator) {
        this.comparator = comparator;
    }
    
}
