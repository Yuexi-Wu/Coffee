package gui;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
 
public class modifyGUI extends Frame implements ActionListener{
	public static void main(String args[]){
		modifyGUI fm=new modifyGUI();
	}

	FileDialog op,sv;
	Button btn1,btn2,btn3;
	TextArea tarea;
	modifyGUI(){
		super("catalog modify");
		setLayout(null);
		setBackground(Color.gray);
		setSize(600,425);
		setVisible(true);
		btn1=new Button("open");
		btn2=new Button("save");
		btn3=new Button("close");
		tarea=new TextArea("");
		add(btn1);add(btn2);add(btn3);add(tarea);
		tarea.setBounds(0,20,460,400);
		btn1.setBounds(480,60,100,30);
		btn2.setBounds(480,120,100,30);
		btn3.setBounds(480,180,100,30);
		op=new FileDialog(this,"open",FileDialog.LOAD);
		sv=new FileDialog(this,"save",FileDialog.SAVE);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				setVisible(false);
				System.exit(0);
			}
		});
	}
 
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==btn1){
			String str;
			op.setVisible(true);
			try{
				File f1=new File(op.getDirectory(),op.getFile());
				FileReader fr=new FileReader(f1);
				BufferedReader br=new BufferedReader(fr);
				tarea.setText("");
				while((str=br.readLine())!=null)tarea.append(str+'\n');
				fr.close();
			}
			catch(Exception e1)
			{}
		}
 
		if(e.getSource()==btn2){
			sv.setVisible(true);
			try{	
				File f1=new File(sv.getDirectory(),sv.getFile());
				FileWriter fw=new FileWriter(f1);
				BufferedWriter bw=new BufferedWriter(fw);
				String gt=tarea.getText();
				bw.write(gt,0,gt.length());
				bw.flush();
				fw.close();
			}
			catch ( Exception e2)
			{}
		}
 
		if(e.getSource()==btn3){
			System.exit(0);
		}
 
	}
}