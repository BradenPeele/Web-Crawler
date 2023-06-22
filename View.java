import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;


class View extends JFrame {
    
    JList<Website> listView;
    JScrollPane listViewScrollPane;
    JTextField textField;
    JButton goButton;
    
    URL url;
    BufferedReader pageReader;
    String inputStr;
  
    
    View(ListModel listModel) {
        
        setupPanel();
        setupJList(listModel);
        setupMainFrame();
    }

    
    void setupPanel() {
        
        JPanel panel = new JPanel();
        
        textField = new JTextField(20);
        panel.add(textField);
        
        goButton = new JButton("Go");
        panel.add(goButton);
        
        add(panel, BorderLayout.NORTH);
    }
    
    
    void setupJList(ListModel listModel) {
        
        listView = new JList<Website>(listModel);
        listViewScrollPane = new JScrollPane(listView);
        add(listViewScrollPane, BorderLayout.CENTER);
    }
    
    
    void setupMainFrame() {
        
        Toolkit tk;
        Dimension d;
        
        tk = Toolkit.getDefaultToolkit();
        d = tk.getScreenSize();
        
        setSize(d.width / 2, d.height / 2);
        setLocation(d.width / 4, d.height / 4);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setVisible(true);
        setResizable(false);
    }
    
    // Controller class is passed in 
    void addListener(ActionListener listener) {
        goButton.addActionListener(listener);
    }
    
    // returns text to Controller class
    String getTextFieldText() {
        return textField.getText();
    }
}

    
    
   
