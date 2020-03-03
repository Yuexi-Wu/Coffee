package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import loader.DataFormatException;
import loader.FileCatalogLoader;
import sale.Catalog;

public class loginGUI {
	static private PrintWriter stdErr = new PrintWriter(System.err, true);

	/* Window width in pixels */
	static private int WIDTH = 720;

	/* Window height in pixels */
	static private int HEIGHT = 530;
	private static JFrame frame0 = new JFrame("login");
	private Container c = frame0.getContentPane();
	private JTextField username = new JTextField();
	private JPasswordField password = new JPasswordField();
	private JButton sure = new JButton("sure");
	private JButton cancel = new JButton("cancel");
	private JLabel logLabel = new JLabel("employee log in gourmet coffee");
	private JButton modify = new JButton("modify");


	JFrame frame = new JFrame("Gourmet Coffee");

	public loginGUI() throws IOException {
		frame0.setVisible(true);
		frame0.setSize(420, 240);
		frame0.setBackground(Color.lightGray);
		c.setLayout(new BorderLayout());
		initFrame();
		myEvent1();
	}

	
	private void initFrame() {

		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		logLabel.setBounds(30, 50, 100, 200);
		logLabel.setForeground(Color.darkGray);
		titlePanel.add(logLabel);
		c.add(titlePanel, "North");

		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(null);
		fieldPanel.setBackground(Color.lightGray);
		JLabel a1 = new JLabel("employee number:");
		a1.setBounds(50, 40, 160, 20);
		JLabel a2 = new JLabel("password:");
		a2.setBounds(50, 100, 160, 20);
		fieldPanel.add(a1);
		fieldPanel.add(a2);
		username.setBounds(180, 40, 120, 20);
		password.setBounds(180, 100, 120, 20);
		fieldPanel.add(username);
		fieldPanel.add(password);
		c.add(fieldPanel, "Center");

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBackground(Color.gray);
		buttonPanel.add(sure);
		buttonPanel.add(cancel);
		buttonPanel.add(modify);
		c.add(buttonPanel, "South");
	}

	private void myEvent1() {
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		sure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int c = 0;
				if(c<3){
					if(password.getText().equals("123")){
						c++;
						frame0.setVisible(false);
						frame.setVisible(true);
						String str = username.getText();
						run(str);
						String filename = "";
						
						try {
							Catalog catalog = (new FileCatalogLoader()).loadCatalog("catalog.dat");
							JFrame frame = new JFrame("Gourmet Coffee");
							frame.setContentPane(new GourmetCoffeeGUI(catalog));
							frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							frame.setSize(WIDTH, HEIGHT);
							frame.setResizable(true);
							frame.setVisible(true);

						} catch (FileNotFoundException fnfe) {
							stdErr.println("The file does not exist");

							System.exit(1);

						} catch (DataFormatException dfe) {
							stdErr.println("The file contains malformed data: " + dfe.getMessage());

							System.exit(1);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

				}else{
					password.setText(null);
				}
					}
					else{
						System.exit(0);
					}
				
				
				
			
			}
		});
		modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyGUI fm=new modifyGUI();
			}
		});
	}

	public void run(String str) {
		String rtnFile1 = "employee.txt";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(rtnFile1));
			String str1 = str;
			bw.write(str1);
			bw.newLine();
			bw.flush();
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) throws IOException {
		new loginGUI();

		
	}
}
