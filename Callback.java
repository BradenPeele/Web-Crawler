import javax.swing.text.html.*;
import javax.swing.text.*;
import java.util.*;
import java.net.*;
import java.util.regex.*;


class Callback extends HTMLEditorKit.ParserCallback {
    
    String emailRegex;
    Pattern emailPattern;
    String testString;
    Matcher emailMatcher;
    boolean done;
    String urlRegex;
    Pattern urlPattern;
    Matcher urlMatcher;
    LinksVSub linksVSub;
    Vector<String> emailsV;
    int numLinks;
    boolean addLinks;
    URL url;
    
    
    Callback(LinksVSub linksVSub) {
        
        this.linksVSub = linksVSub;
        emailRegex = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
        urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        emailPattern = Pattern.compile(emailRegex);
        urlPattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        resetData();
        addLinks = true;
    }
    
    
    void resetData() {
        emailsV = new Vector<String>();
        numLinks = 0;
    }
    
    
    Vector<String> getEmails() {
        return emailsV;
    }
    
    
    int getNumEmails() {
        return emailsV.size();
    }
    
    
    int getNumLinks() {
        return numLinks;
    }
    
    
    void dontAddLinks() {
        addLinks = false;
    }
    
    
    void setURL(URL url) {
        this.url = url;
    }
    
    // attemps tor resolve links with base domain
    void resolve(String s) {
        
        try {
            String path = url.getFile().substring(0, url.getFile().lastIndexOf('/'));
            String base = url.getProtocol() + "://" + url.getHost() + path;
        
            if(!s.startsWith("/"))
                s = "/" + s;
        
            URI uri = new URI(base);
            uri = uri.resolve(s);
            
            s = uri.toString();
            if(!linksVSub.contains(s)) {
                linksVSub.addLink(s);
                numLinks++;
            }
        }
        catch(Exception e) {
            System.out.println("Exception: link could't be resolved");
        }
    }
    
         
    public void handleText(char[] data, int pos) {
        
        System.out.println("handleText started");

        testString = String.valueOf(data);
        emailMatcher = emailPattern.matcher(testString);
        urlMatcher = urlPattern.matcher(testString);
         
        // loop to search for emails
        done = false;
        while(!done) {
            
            if(emailMatcher.find()) {
                emailsV.add(testString.substring(emailMatcher.start(), emailMatcher.end()));
                emailMatcher.region(emailMatcher.end(), testString.length());
            }
            else {
                done = true;
            }
        }
        
        // loop to search for links
        if(addLinks) {
            done = false;
            while(!done) {
            
                if(urlMatcher.find()) {
                    
                    String testSubstring = testString.substring(urlMatcher.start(), urlMatcher.end());
                    urlMatcher.region(urlMatcher.end(), testString.length());
                    // check if link has already been addded
                    if(!linksVSub.contains(testSubstring) && (testSubstring.contains("http://") || testSubstring.contains("https://"))) {
                        linksVSub.addLink(testSubstring);
                        numLinks++;
                    }
                    
                    if(!testSubstring.contains("http://") && !testSubstring.contains("https://")) {
                       resolve(testSubstring);
                    }
                }
                else {
                    done = true;
                }
            }
        }
        
        System.out.println("handleText ended");
    }
    
         
     public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
         
        System.out.println("handleStartTag started");
         
        if(t == HTML.Tag.A) {
             
            testString = String.valueOf(a.getAttribute(HTML.Attribute.HREF));
            emailMatcher = emailPattern.matcher(testString);
            
            // loop to search for emails
            if(testString.contains("mailto:")) {
                done = false;
                while(!done) {
                    if(emailMatcher.find()) {
                        emailsV.add(testString.substring(emailMatcher.start(), emailMatcher.end()));
                        emailMatcher.region(emailMatcher.end(), testString.length());
                    }
                    else {
                        done = true;
                    }
                }
            }
             
            // add links
            if(addLinks) {
                // check if link has already been addded
                if(!linksVSub.contains(testString) && (testString.contains("http://") || testString.contains("https://"))) {
                    linksVSub.addLink(testString);
                    numLinks++;
                }
                
                if(!testString.contains("http://") && !testString.contains("https://")) {
                    resolve(testString);
                }
            }
            
        }
         
         System.out.println("handleStartTag ended");
    }
}
