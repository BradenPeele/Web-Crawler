class DistanceTracker {
    
    int distance;
    int distanceCounter;
    
    
    DistanceTracker() {
        distance = 0;
        distanceCounter = 0;
    }
    
    // called in Crawler.start()
    void setData(int distance, int distanceCounter) {
        this.distance = distance;
        this.distanceCounter = distanceCounter;
    }
    
    // called after every successful loop in Crawler
    void updateDistance(int numLinks) {
        distanceCounter--;
        if(distanceCounter <= 0) {
            distance++;
            distanceCounter = numLinks;
        }
    }
    
    
    int getDistance() {
        return distance;
    }
}
