package bn026Work;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;



@SuppressWarnings("serial")
public class BN026Year2App extends JFrame{
	
		private JPanel upper = new JPanel();
		private LogoPanel logoPanel = new LogoPanel(
				"C:\\Users\\digig\\eclipse-workspace\\softDev3\\src\\TUDlogo.jpg");
		private JPanel langPanel = new JPanel();
		private JButton selectLang = new JButton("Change Language");
		private ResourceBundle bun;
		
		private StudentsPanel students= new StudentsPanel();
		private LecturersPanel lecturers = new LecturersPanel();
		private JTabbedPane tabs = new JTabbedPane();
		
		private static BN026 bn026Y2 = new BN026();			
		public static BN026 getBN026() {
			return bn026Y2;
		}
		
	public BN026Year2App() {
		
		super("Application for BN026 Y2 class");
		this.setSize(830, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(upper, BorderLayout.NORTH);
		upper.setLayout(new FlowLayout());
		upper.add(logoPanel);
		upper.add(new JLabel("Welcome to BN026 desktop application..."));
		
		this.add(langPanel, BorderLayout.SOUTH);
		langPanel.setLayout(new FlowLayout());
		langPanel.add(selectLang);
		
		selectLang.addActionListener((ev) -> changeLocale());

		tabs.addTab("Students", students);	
		tabs.addTab("Lecturers", lecturers);
		this.add(tabs, BorderLayout.CENTER);
		this.repaint();	
	}
	
	private void changeLocale() {
				Locale locale = Locale.getDefault();

		        if (selectLang.getText().contains("Change")) {
		        	locale = new Locale ("HU");
		        } else{
		        	locale = Locale.getDefault();
		        }
		        
		        bun = ResourceBundle.getBundle("bn026Bundle", locale);
		        //lecturer panel lang change
		        lecturers.nameLabel.setText(bun.getString("name"));
				lecturers.addIDLabel.setText(bun.getString("id"));
				lecturers.deleteIDLabel.setText(bun.getString("id"));
				lecturers.ageLabel.setText(bun.getString("age"));
				lecturers.subjectLabel.setText(bun.getString("subject"));

				lecturers.addButton.setText(bun.getString("add"));
				lecturers.deleteButton.setText(bun.getString("delete"));
				
				lecturers.addLecturers.setTitle(bun.getString("addLect"));
				lecturers.deleteLecturers.setTitle(bun.getString("delLect"));
				
				//student panel lang change
				students.nameLabel.setText(bun.getString("name"));
				students.addIDLabel.setText(bun.getString("id"));
				students.deleteIDLabel.setText(bun.getString("id"));
				students.ageLabel.setText(bun.getString("age"));

				students.addButton.setText(bun.getString("add"));
				students.deleteButton.setText(bun.getString("delete"));
				
				students.addStudents.setTitle(bun.getString("addStu"));
				students.deleteStudents.setTitle(bun.getString("delStu"));
				
				this.selectLang.setText(bun.getString("change"));
			
	}
}


