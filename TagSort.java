import java.util.*;

class TagSort extends Vector<Integer> {
    
    
    void sort(Vector<Website> unsorted, ListModel listModel) {

        Website website = unsorted.get(unsorted.size()-1);
        addElement(unsorted.size()-1);
        int n = unsorted.size()-1;
        for (int i=0; i<n-1; i++) {
            for (int j=i+1; j<n; j++) {
                if (unsorted.get(get(i)).URLStr.compareToIgnoreCase(unsorted.get(get(j)).URLStr) > 0) {
                    Collections.swap(this, i, j);
                }
            }
        }
        // adds to listModel which updates the jlist in View
        listModel.addWebsite(indexOf(unsorted.size()-1), website);
    }
}
