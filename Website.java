import java.util.*;

// class to hold the extracted data
class Website {
    
    String URLStr;
    Vector<String> emailsV;
    int numLinks;
    int distance;
    
    
    Website(String URLStr, Vector<String> emailsV, int numLinks, int distance) {
        
        this.URLStr = URLStr;
        this.emailsV = emailsV;
        this.numLinks = numLinks;
        this.distance = distance;
    }
    
    
    void sortEmailsByDomain() {
        
        int n = emailsV.size();
        for (int i = 0; i < (n - 1); i++) {
            for (int j = 0; j < n - i - 1; j++) {
                
                String sub = emailsV.get(j);
                sub = sub.substring(sub.indexOf("@"), sub.length());
                String sub2 = emailsV.get(j+1);
                sub2 = sub2.substring(sub2.indexOf("@"), sub2.length());
                
                if (sub.compareToIgnoreCase(sub2) > 0) {
                    String temp = emailsV.get(j);
                    emailsV.set(j, emailsV.get(j + 1));
                    emailsV.set(j + 1, temp);
                }
            }
        }
    }
    
    
    void sortEmailsByUsername() {
        
        int n = emailsV.size();
        for (int i = 0; i < (n - 1); i++) {
            for (int j = 0; j < n - i - 1; j++) {
                
                String subD = emailsV.get(j);
                subD = subD.substring(subD.indexOf("@"), subD.length());
                String subD2 = emailsV.get(j+1);
                subD2 = subD2.substring(subD2.indexOf("@"), subD2.length());
                
                if(subD.equals(subD2)) {
                
                    String sub = emailsV.get(j);
                    sub = sub.substring(0, sub.indexOf("@"));
                    String sub2 = emailsV.get(j+1);
                    sub2 = sub2.substring(0, sub2.indexOf("@"));
                    
                    if (sub.compareToIgnoreCase(sub2) > 0) {
                        String temp = emailsV.get(j);
                        emailsV.set(j, emailsV.get(j + 1));
                        emailsV.set(j + 1, temp);
                    }
                }
            }
        }
    }
    
    // for displaying in jlist
    @Override
    public String toString() {

        String text = "<html>" + URLStr + "<br/>";
        for(int n = 0; n < emailsV.size(); n++) {
            text += "-     " + emailsV.elementAt(n) + "<br/>";
        }
        
        return text += "<br/>";
    }
}
