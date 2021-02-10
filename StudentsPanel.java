package bn026Work;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class StudentsPanel extends JPanel{
	//the gui elements
		JPanel displayPanel, addPanel, deletePanel;
		private JTextArea displayArea;
		JButton addButton, deleteButton;
		private JTextField nameField, idField, ageField, deleteField;
		//the data
		private BN026 bn026Year2 = BN026Year2App.getBN026();
		
		JLabel nameLabel = new JLabel("Name:"), addIDLabel = new JLabel("ID:"), deleteIDLabel = new JLabel("ID:"),
				ageLabel = new JLabel("Age:");
		
		TitledBorder addStudents = new TitledBorder("Add new students..."),
				deleteStudents = new TitledBorder("Delete students...");
		
		
		
		public StudentsPanel() {
			//instantiate and add the panels to the frame
			this.add(displayPanel = new JPanel(), BorderLayout.NORTH);
			displayPanel.setLayout(new FlowLayout());
			this.add(addPanel = new JPanel(), BorderLayout.CENTER);
			addPanel.setLayout(new FlowLayout());
			this.add(deletePanel = new JPanel(), BorderLayout.SOUTH);
			deletePanel.setLayout(new FlowLayout());
			
			//add the area to the display panel
			displayPanel.add(displayArea = new JTextArea("", 20, 40));
			try(BN026Queries queries = new BN026Queries()){	
				bn026Year2.setStudents(queries.getAllStudents());
				for(Student stu : bn026Year2.getStudents())
					displayArea.append(stu.toString() + "\n");
			}catch(SQLException | IOException ex) {
				JOptionPane.showMessageDialog(getRootPane(), "A problem has occurred when trying to load records from the database");
				ex.printStackTrace();
			}
			//add 3 labels, 3 fields,  and the add button to the add panel
			addPanel.setBorder(addStudents);
			addPanel.add(nameLabel);
			addPanel.add(nameField = new JTextField(16));
			addPanel.add(addIDLabel);
			addPanel.add(idField = new JTextField(10));
			addPanel.add(ageLabel);
			addPanel.add(ageField = new JTextField(10));
			addPanel.add(addButton = new JButton("Add"));
			
			//add the 1 label, 1 field,  and the delete button to the delete panel
			deletePanel.setBorder(deleteStudents);
			deletePanel.add(deleteIDLabel);
			deletePanel.add(deleteField = new JTextField(10));
			deletePanel.add(deleteButton = new JButton("Delete By Id"));
			
			//register events with the 2 buttons
			addButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String name = nameField.getText();
					if(name == null || name.equals("")) 
						JOptionPane.showMessageDialog(getRootPane(), "Name cannot be empty!");
					
					//Regex exercises
					String id = idField.getText();
					if(!Pattern.matches("[bB]00[0-9]{6}", id)) {
						JOptionPane.showMessageDialog(getRootPane(), "Enter a student number in the following format: B00XXXXXX or b00XXXXXX, where X is a digit");
					}else {
						id =idField.getText();
					}
					
					//same as above but by using a method studentIDRegex() 
					/*
					if(!studentIDRegex(id)) {
						JOptionPane.showMessageDialog(getRootPane(), "Enter a student number in the following format: B00XXXXXX or b00XXXXXX, where X is a digit");
					}
					*/
					
					int age;
					String ageString = ageField.getText();
					if(!Pattern.matches("[0-9]{1,3}", ageString)) {
						JOptionPane.showMessageDialog(getRootPane(), "Age cannot be more than 3 digits");
						return;
					}else {
						age=Integer.parseInt(ageString);
					}
					
					try(BN026Queries queries = new BN026Queries()){
						displayArea.setText("");
						queries.insertStudent(id, age, name, 2);	
						bn026Year2.setStudents(queries.getAllStudents());
						for(Student stu : bn026Year2.getStudents())
							displayArea.append(stu.toString() + "\n");
					}catch(SQLException | IOException ex) {
						JOptionPane.showMessageDialog(getRootPane(), "A problem has occurred while trying to access the database");

						//clear the fields
						nameField.setText("");
						idField.setText("");
						ageField.setText("");
						ex.printStackTrace();
					}
					
					/*
					Student s = new Student(name, age, id, 2);
					bn026Year2.addStudent(s);
					displayArea.append(s.toString() + "\n");
					*/
					
					//clear the fields
					nameField.setText("");
					idField.setText("");
					ageField.setText("");
				}
			});
			deleteButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//Regex exercises
					String id = deleteField.getText();
					if(!Pattern.matches("[bB]00[0-9]{6}", id)) {
						JOptionPane.showMessageDialog(getRootPane(), "Enter a student number in the following format: B00XXXXXX or b00XXXXXX, where X is a digit");
					}else {
						id =idField.getText();
					}
					
					//same as above but by using a method studentIDRegex() 
					/*
					if(!studentIDRegex(id)) {
						JOptionPane.showMessageDialog(getRootPane(), "Enter a student number in the following format: B00XXXXXX or b00XXXXXX, where X is a digit");
					}
					*/
					try(BN026Queries queries = new BN026Queries()){
						displayArea.setText("");
						queries.deleteStudent(id);
						bn026Year2.setStudents(queries.getAllStudents());
						for(Student stu : bn026Year2.getStudents())
							displayArea.append(stu.toString() + "\n");
					}catch(SQLException | IOException ex) {
						JOptionPane.showMessageDialog(getRootPane(), "A problem has occurred while trying to access the database");
						//clear the fields
						deleteField.setText("");
						ex.printStackTrace();
					}
					
					/*
					Student s = bn026Year2.removeStudentByID(id);
					if(s == null)
						JOptionPane.showMessageDialog(null, "No such student!");
					displayArea.setText("");
					for(Student student: bn026Year2.getStudents())
						displayArea.append(student + "\n");
						*/
					
					//clear the fields
					deleteField.setText("");
				}
			});
		}
		
		//Regex exercises
		// creating a method to match the pattern required for an ID field
		/*
		private boolean studentIDRegex(String s) {
			if(!Pattern.matches("[bB]00[0-9]{6}", s)) {
				return false;
			}else {
				return true;
			}
		}
		*/
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(this.getWidth(), this.getHeight());
		}
}

