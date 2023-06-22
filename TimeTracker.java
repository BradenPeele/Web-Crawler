import java.util.*;

class TimeTracker {
    
    long startTime;
    
    
    TimeTracker() {
        startTime = System.currentTimeMillis();
    }
    
    
    boolean isOutOfRunTime() {
        System.out.println("Run Time - " + (System.currentTimeMillis() - startTime));
        if(System.currentTimeMillis() - startTime > Main.MAX_RUN_TIME)
            return true;
            
        return false;
    }
    
    
    boolean isOutOfExpansionTime() {
        System.out.println("Expansion Time - " + (System.currentTimeMillis() - startTime));
        if(System.currentTimeMillis() - startTime > Main.MAX_EXPANSION_TIME)
            return true;
            
        return false;
    }
    
}
