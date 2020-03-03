package gui;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import loader.DataFormatException;
import loader.FileCatalogLoader;
import sale.Catalog;
import sale.Order;
import gui.GourmetCoffeeGUI;


public class takeoutGUI extends JFrame {
    private JPanel contentPane;
    private JTextField phoneField;
    private JTextField addressField;
 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	takeoutGUI frame = new takeoutGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    public takeoutGUI() {
    	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, 350, 250);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 60, 20));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        JLabel lblPhone = new JLabel("PHONE NUMBER: ");
        lblPhone.setBounds(12, 13, 150, 15);
        contentPane.add(lblPhone);
        JLabel lblAddress = new JLabel("ADDRESS: ");
        lblAddress.setBounds(12, 38, 150, 15);
        contentPane.add(lblAddress);
        phoneField = new JTextField();
        phoneField.setBounds(160, 10, 144, 21);
        contentPane.add(phoneField);
        phoneField.setColumns(10);
        addressField = new JTextField();
        addressField.setBounds(160, 35, 144, 100);
        contentPane.add(addressField);
        addressField.setColumns(10);
        
        JButton sureButton = new JButton("sure");
        sureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	
            	String s1 = phoneField.getText();
                String s2 = addressField.getText();
                run(s1,s2);
                
            	String filename = "";
            	Catalog catalog = null;
				try {
					catalog = (new FileCatalogLoader()).loadCatalog("catalog.dat");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DataFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JFrame frame = new JFrame("Gourmet Coffee");
				frame.setContentPane(new GourmetCoffeeGUI(catalog));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(720, 530);
				frame.setResizable(true);
				frame.setVisible(true);
				
            }
        });
        sureButton.setBounds(10, 180, 93, 23);
        contentPane.add(sureButton);
         
        JButton cancelButton = new JButton("cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });
        cancelButton.setBounds(180, 180, 93, 23);
        contentPane.add(cancelButton);
         
    }
    
    public void run(String stra, String strb) {
		String rtnFile1 = "take-out.dat";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(rtnFile1));
			String str1 = stra;
			String str2 = strb;
			bw.write(str1);
			bw.write(str2);
			bw.newLine();
			bw.flush();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
     
   
}