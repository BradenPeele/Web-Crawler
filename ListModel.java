import javax.swing.*;


class ListModel extends DefaultListModel<Website> {
    
    // adds website then sorts emails by domain then username
    void addWebsite(int index, Website website) {
        add(index, website);
        website.sortEmailsByDomain();
        website.sortEmailsByUsername();
    }
    
    
    void clearAll() {
        clear();
    }
}
