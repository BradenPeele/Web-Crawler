import javax.swing.text.html.parser.*;
import java.util.*;
import java.net.*;
import java.io.*;


class Crawler {
    
    ListModel listModel;
    LinksVSub linksVSub;
    Callback callback;
    TagSort tagSort;
    DistanceTracker distanceTracker;
    TimeTracker timeTracker;
    URL url;
    InputStreamReader isr;
    Vector<Website> unsortedWebsites;
    
    
    Crawler(ListModel listModel) {
        this.listModel = listModel;
    }
    
   
    void start(String seed) {
    
        // resets all data / subclasses
        linksVSub = new LinksVSub();
        callback = new Callback(linksVSub);
        callback.resetData();
        linksVSub.addLink(seed);
        unsortedWebsites = new Vector<Website>();
        tagSort = new TagSort();
        distanceTracker = new DistanceTracker();
        timeTracker = new TimeTracker();
        
        // attemps to parse seed
        if(parse()) {
            // adds seed to list
            unsortedWebsites.add(new Website(url.toString(), callback.getEmails(), callback.getNumLinks(), 0));
            tagSort.sort(unsortedWebsites, listModel); // sorts / updates view
            distanceTracker.setData(0, callback.getNumLinks()); // update distance
            crawl(); // start crawling loop
        }
    }

    
    boolean parse() {
        
        System.out.println("Attempting to create a ParserDelegator");
        
        try {
            callback.resetData();
            url = new URL(linksVSub.getElementAtIndex());
            //*************************
            isr = new InputStreamReader(url.openStream()); // sometimes gets hung up here for some reason
            //*************************
            callback.setURL(url);
            new ParserDelegator().parse(isr, callback, true);
        }
        catch (Exception e) {
            System.out.println("Exception: Couldn't create ParserDelegator");
            return false;
        }
        
        return true;
    }
    
    
    void crawl() {
        
        while(!timeTracker.isOutOfRunTime() && !linksVSub.isEnd() && distanceTracker.getDistance()+1 <= Main.MAX_RADIUS) {
            
            System.out.println("Crawler looped");
            
            if(timeTracker.isOutOfExpansionTime()) // expansion time check : no more links added to "queue"
                callback.dontAddLinks(); // sets boolean to false in callback which will stop adding links
            
            if(parse() && callback.getNumEmails() > 0) {
                
                unsortedWebsites.add(new Website(url.toString(), callback.getEmails(), callback.getNumLinks(), distanceTracker.getDistance()+1)); // add to vector of websites
        
                tagSort.sort(unsortedWebsites, listModel); // sorts and adds websites in order to listModel which will update view
                
                distanceTracker.updateDistance(unsortedWebsites.elementAt(distanceTracker.getDistance()).numLinks); // update distance
            }
            
            linksVSub.incIndex(); // go to next link
        }
    }
}
