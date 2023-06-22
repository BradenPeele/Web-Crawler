import java.util.*;

// class to hold all links / can check if link has already been used
class LinksVSub extends Vector<String> {

    int index;
    int numLinks;


    LinksVSub() {
        index = 0;
        numLinks = 0;
    }

    
    void addLink(String URLStr) {
        addElement(URLStr);
        numLinks++;
    }
    
    
    String getElementAtIndex() {
        return elementAt(index);
    }
    
    
    void incIndex() {
        index++;
    }
    
    
    boolean isEnd() {
        
        if(size() <= index)
            return true;
        
        return false;
    }


    void clearAll() {
        clear();
    }
}
