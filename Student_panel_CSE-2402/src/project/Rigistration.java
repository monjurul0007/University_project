package project;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import com.mysql.jdbc.Statement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class Rigistration extends JFrame{
	
	static Statement stm = null;
	static ResultSet rs = null;
	static Connection conn = null;
	
	private Container c;
	private JTextField idTxt;
	private JTextField nameTxt;
	private JTextField deptTxt;
	private JTextField phoneTxt;
	private JTextField cgpaTxt;
	private JPasswordField passTxt;
	
	Rigistration(){
		setBounds(400, 100, 800, 600);
		setTitle("Java Lab Project");
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		c = this.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.gray);
		
		initComponents();	
	}
	
	public void initComponents(){
		
		JLabel heading = new JLabel("Student Information Registration");
		heading.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 30));
		heading.setBounds(108, 12, 572, 55);
		c.add(heading);
		
		JLabel lblid = new JLabel("Student ID : ");
		lblid.setFont(new Font("Dialog", Font.BOLD, 15));
		lblid.setBounds(88, 118, 123, 22);
		c.add(lblid);
		
		JLabel lblname = new JLabel("Name : ");
		lblname.setFont(new Font("Dialog", Font.BOLD, 15));
		lblname.setBounds(88, 173, 123, 22);
		c.add(lblname);
		
		JLabel lbldept = new JLabel("Dept : ");
		lbldept.setFont(new Font("Dialog", Font.BOLD, 15));
		lbldept.setBounds(88, 224, 123, 22);
		c.add(lbldept);
		
		JLabel lblphone = new JLabel("Phone no. : ");
		lblphone.setFont(new Font("Dialog", Font.BOLD, 15));
		lblphone.setBounds(88, 276, 123, 22);
		c.add(lblphone);
		
		JLabel lblpass = new JLabel("Password : ");
		lblpass.setFont(new Font("Dialog", Font.BOLD, 15));
		lblpass.setBounds(88, 327, 123, 22);
		c.add(lblpass);
		
		JLabel lblcgpa = new JLabel("CGPA : ");
		lblcgpa.setFont(new Font("Dialog", Font.BOLD, 15));
		lblcgpa.setBounds(88, 382, 123, 22);
		c.add(lblcgpa);
		
		idTxt = new JTextField();
		idTxt.setBounds(229, 114, 494, 31);
		c.add(idTxt);
		idTxt.setColumns(10);
		
		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		nameTxt.setBounds(229, 169, 494, 31);
		c.add(nameTxt);
		
		deptTxt = new JTextField();
		deptTxt.setColumns(10);
		deptTxt.setBounds(229, 220, 494, 31);
		c.add(deptTxt);
		
		phoneTxt = new JTextField();
		phoneTxt.setColumns(10);
		phoneTxt.setBounds(229, 272, 494, 31);
		c.add(phoneTxt);
		
		cgpaTxt = new JTextField();
		cgpaTxt.setColumns(10);
		cgpaTxt.setBounds(229, 378, 494, 31);
		c.add(cgpaTxt);
		
		passTxt = new JPasswordField();
		passTxt.setBounds(229, 323, 494, 31);
		c.add(passTxt);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(185, 480, 114, 25);
		c.add(btnRegister);
		
		JButton btnCancle = new JButton("Cancel");
		btnCancle.setBounds(467, 480, 114, 25);
		c.add(btnCancle);
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String id = idTxt.getText();
					String name = nameTxt.getText();
					String dept = deptTxt.getText();
					String phone = phoneTxt.getText();
					String password = passTxt.getText();
					String cgpa = cgpaTxt.getText();
					String sql = "INSERT INTO students(id,name,dept,phone,password,cgpa) VALUES('"+id+"','"+name+"','"+dept+"','"+phone+"','"+password+"',"+cgpa+");";
					
					conn = DBConnection.ConnecrDb();
					stm = (Statement) conn.createStatement();
					stm.executeUpdate(sql);
					
					JOptionPane.showMessageDialog(null, "Registration Succesfull!");
					LoginPage log = new LoginPage();
					log.setVisible(true);
					setVisible(false);
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				
			}
		});
		
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage log = new LoginPage();
				log.setVisible(true);
				setVisible(false);
			}
		});
		
	}
}
