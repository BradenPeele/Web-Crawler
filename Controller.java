import java.awt.event.*;


class Controller implements ActionListener {
    
    Crawler crawler;
    View view;
    ListModel listModel;
    
    Controller(Crawler crawler, View view, ListModel listModel) {
        this.crawler = crawler;
        this.view = view;
        view.addListener(this);
        this.listModel = listModel;
    }
  
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("Go")) {
            
            listModel.clearAll();
            crawler.start(view.getTextFieldText());
            System.out.println("Finished");
        }
    }
}

       
