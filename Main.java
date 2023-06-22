class Main {
    
    final static long MAX_RUN_TIME = 120000;
    final static long MAX_EXPANSION_TIME = 60000;
    final static int MAX_RADIUS = 3;
    
    
    public static void main(String[] args) {
        ListModel listModel = new ListModel();
        new Controller(new Crawler(listModel), new View(listModel), listModel);
    }
}
