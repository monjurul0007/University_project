package project;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class LoginPage extends JFrame{
	
	static Statement stm = null;
	static ResultSet rs = null;
	static Connection conn = null;
	
	private Container c;
	private ImageIcon img;
	private static JTextField idTxt;
	private static JPasswordField passTxt;
	static private JButton btnRegister;
	static private JButton btnLogin;
	
	LoginPage(){
		System.out.println("a");
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
		System.out.println("b");
		conn = DBConnection.ConnecrDb();
		
		JLabel lblId = new JLabel("Student ID : ");
		lblId.setFont(new Font("Dialog", Font.BOLD, 15));
		lblId.setBounds(72, 318, 108, 23);
		c.add(lblId);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPassword.setBounds(72, 394, 108, 23);
		c.add(lblPassword);
		
		idTxt = new JTextField();
		idTxt.setBounds(221, 313, 501, 34);
		c.add(idTxt);
		idTxt.setColumns(10);
		
		passTxt = new JPasswordField();
		passTxt.setBounds(221, 389, 501, 34);
		c.add(passTxt);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(192, 475, 114, 25);
		c.add(btnLogin);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(473, 475, 114, 25);
		c.add(btnRegister);
		
		img = new ImageIcon(getClass().getResource("iiuc.png"));
		JLabel image = new JLabel(img);
		image.setBounds(290, 25, img.getIconWidth(), img.getIconHeight());	
		c.add(image);
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String id = idTxt.getText();
					String pass = passTxt.getText();
					String sql = "SELECT * FROM students WHERE id='"+id+"' && password='"+pass+"';";
					
					stm = (Statement) conn.createStatement();
					rs = stm.executeQuery(sql);
					if(rs.next()) {
						setVisible(false);
						Home ho = new Home(id);
						ho.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Incorrect! try again.");
					}
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Rigistration reg = new Rigistration();
				reg.setVisible(true);
			}
		});
		
	}

	public static void main(String[] args) {
		System.out.println("c");
		LoginPage f = new LoginPage();
		f.setVisible(true);
	}
}
