package project;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame{
	
	static Statement stm = null;
	static ResultSet rs = null;
	static Connection conn = null;
	
	private Container c;
	private static JTextField idTxt;
	
	private String id = null;
	
	Home(String s){
		setBounds(400, 100, 800, 600);
		setTitle("Java Lab Project");
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		c = this.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.gray);
		
		initComponents();
		show(s);
	}
	
	public void initComponents(){
		
		JLabel heading = new JLabel("Student Information");
		heading.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 30));
		heading.setBounds(225, 12, 357, 55);
		c.add(heading);
		
		JLabel lblid = new JLabel("Student ID :" );
		lblid.setFont(new Font("Dialog", Font.BOLD, 15));
		lblid.setBounds(88, 118, 129, 29);
		c.add(lblid);
		
		JLabel lblname = new JLabel("Name :" );
		lblname.setFont(new Font("Dialog", Font.BOLD, 15));
		lblname.setBounds(88, 165, 129, 29);
		c.add(lblname);
		
		JLabel lbldept = new JLabel("Dept :" );
		lbldept.setFont(new Font("Dialog", Font.BOLD, 15));
		lbldept.setBounds(88, 224, 129, 29);
		c.add(lbldept);
		
		JLabel lblphone = new JLabel("Phone no. :" );
		lblphone.setFont(new Font("Dialog", Font.BOLD, 15));
		lblphone.setBounds(88, 276, 129, 29);
		c.add(lblphone);
		
		JLabel lblcgpa = new JLabel("CGPA :");
		lblcgpa.setFont(new Font("Dialog", Font.BOLD, 15));
		lblcgpa.setBounds(88, 331, 129, 29);
		c.add(lblcgpa);
		
		
	}
	
	void show(String sid) {
		
		String name = null;
		String dept = null;
		String phone = null;
		String cgpa = null;
		try{
			String sql = "SELECT * FROM students WHERE id='"+sid+"';";	
			conn = DBConnection.ConnecrDb();
			stm = (Statement) conn.createStatement();
			rs = stm.executeQuery(sql);
			if(rs.next()) {
				id =  rs.getString("id");
				name =  rs.getString("name");
				dept =  rs.getString("dept");
				phone =  rs.getString("phone");
				cgpa =  rs.getString("cgpa");		
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		JLabel ID = new JLabel(id);
		ID.setBounds(266, 118, 459, 29);
		c.add(ID);
		
		JLabel Name = new JLabel(name);
		Name.setBounds(266, 165, 459, 29);
		c.add(Name);
		
		JLabel Dept = new JLabel(dept);
		Dept.setBounds(266, 224, 459, 29);
		c.add(Dept);
		
		JLabel Phone = new JLabel(phone);
		Phone.setBounds(266, 276, 459, 29);
		c.add(Phone);
		
		JLabel Cgpa = new JLabel(cgpa);
		Cgpa.setBounds(266, 331, 459, 29);
		c.add(Cgpa);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginPage log = new LoginPage();
				setVisible(false);
				log.setVisible(true);
			}
		});
		btnLogout.setBounds(190, 452, 142, 25);
		c.add(btnLogout);
		
		JButton btnDeleteProfile = new JButton("Delete Profile");
		btnDeleteProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "DELETE FROM students WHERE id='"+id+"';";
					conn = DBConnection.ConnecrDb();
					stm = (Statement) conn.createStatement();
					stm.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Profile Deleted!");
					LoginPage log = new LoginPage();
					setVisible(false);
					log.setVisible(true);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnDeleteProfile.setBounds(455, 452, 142, 25);
		getContentPane().add(btnDeleteProfile);
		
	}
}
