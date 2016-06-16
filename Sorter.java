package cse214hw5;

//Jack Smith 110366081

import java.util.Comparator;

public interface Sorter<E> {
    
    void sort();
    
    void setComparator(Comparator<E> comparator);
    
}
