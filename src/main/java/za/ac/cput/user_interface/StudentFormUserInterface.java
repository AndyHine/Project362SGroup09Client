package za.ac.cput.user_interface;

/**
 * Author: Jason Jaftha 217009301
 * Description: Student form gui interface for students to submit there personal details.
 * File: StudentFormUserInterface.java
 * Date: May 2021
 **/

import za.ac.cput.entity.Student;
import za.ac.cput.factory.StudentFactory;
import za.ac.cput.rest.StudentRestImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentFormUserInterface extends JFrame implements ActionListener {

    //Attributes
        private JPanel northPanel, centerPanel, southPanel;
        private JLabel lblHeading, lblStudentNo, lblFirstName, lblLastName, lblEmailAddress, lblCellNo;
        private  JTextField txtStudentNo, txtFirstName, txtLastName, txtEmailAddress, txtCellNo;
        private JButton btnSubmit, btnExit;
        private Font ftHeading, ftText;
        private JLabel emptySpace1, emptySpace2, emptySpace3, emptySpace4, emptySpace5, emptySpace6, emptySpace7, emptySpace8;

    //Constructor
        public StudentFormUserInterface()
        {
            //Initialize all Attributes
                super("Student Form Screen version: 1.0 by @Jason Jaftha");

                northPanel = new JPanel();
                centerPanel = new JPanel();
                southPanel = new JPanel();

                lblHeading = new JLabel("Student Form", SwingConstants.CENTER);
                lblStudentNo = new JLabel("Student Number: ", SwingConstants.RIGHT);
                lblFirstName = new JLabel("First Name: ", SwingConstants.RIGHT);
                lblLastName = new JLabel("Last Name: ", SwingConstants.RIGHT);
                lblEmailAddress = new JLabel("Email Address: ", SwingConstants.RIGHT);
                lblCellNo = new JLabel("Phone Number: ", SwingConstants.RIGHT);

                txtStudentNo = new JTextField();
                txtFirstName = new JTextField();
                txtLastName = new JTextField();
                txtEmailAddress = new JTextField();
                txtCellNo = new JTextField();

                btnSubmit = new JButton("Submit");
                btnExit = new JButton("Exit");

                ftHeading = new Font("Arial", Font.BOLD, 20);
                ftText = new Font("Arial", Font.PLAIN, 12);

                emptySpace1 = new JLabel();
                emptySpace2 = new JLabel();
                emptySpace3 = new JLabel();
                emptySpace4 = new JLabel();
                emptySpace5 = new JLabel();
                emptySpace6 = new JLabel();
                emptySpace7 = new JLabel();
                emptySpace8 =new JLabel();
        }

    public void setGui()
    {
        //Add Gridlayout to panels
            northPanel.setLayout(new GridLayout(2,1));
            centerPanel.setLayout(new GridLayout(5,3));
            southPanel.setLayout(new GridLayout(2,2));

        //Set font
            lblHeading.setFont(ftHeading);
            lblStudentNo.setFont(ftText);
            lblFirstName.setFont(ftText);
            lblLastName.setFont(ftText);
            lblEmailAddress.setFont(ftText);
            lblCellNo.setFont(ftText);

        //Add components to panels
            northPanel.add(lblHeading);
            northPanel.add(emptySpace1);

            centerPanel.add(lblStudentNo);
            centerPanel.add(txtStudentNo);
            centerPanel.add(emptySpace2);
            centerPanel.add(lblFirstName);
            centerPanel.add(txtFirstName);
            centerPanel.add(emptySpace3);
            centerPanel.add(lblLastName);
            centerPanel.add(txtLastName);
            centerPanel.add(emptySpace4);
            centerPanel.add(lblEmailAddress);
            centerPanel.add(txtEmailAddress);
            centerPanel.add(emptySpace5);
            centerPanel.add(lblCellNo);
            centerPanel.add(txtCellNo);
            centerPanel.add(emptySpace6);
            centerPanel.setPreferredSize(new Dimension(480, 140));

            southPanel.add(emptySpace7);
            southPanel.add(emptySpace8);
            southPanel.add(btnSubmit);
            southPanel.add(btnExit);

        //Add panels to frame
            this.add(northPanel, BorderLayout.NORTH);
            this.add(centerPanel, BorderLayout.CENTER);
            this.add(southPanel, BorderLayout.SOUTH);

        //Add action listener to buttons | mouse listener to hyperlink
            btnSubmit.addActionListener(this);
            btnExit.addActionListener(this);

        //Frame
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.pack();
            this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

            String studentNo = txtStudentNo.getText().trim();
            String firstName = txtFirstName.getText().trim();
            String lastName = txtLastName.getText().trim();
            String emailAddress = txtEmailAddress.getText().trim();
            String cellNo = txtCellNo.getText().trim();

            //Create student object
                Student student = StudentFactory.createStudent(studentNo, firstName,lastName, emailAddress, cellNo);

            if(e.getActionCommand().equals("Submit"))
            {
                //Check if all fields were included
                if(studentNo.isEmpty()|| firstName.isEmpty() || lastName.isEmpty() || emailAddress.isEmpty() || cellNo.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Please enter in all information!");
                }
                else {
                    //Submit information
                        boolean result = StudentRestImpl.saveStudentDetails(student);

                    //Check if query was successful;
                        if(result == true)
                        {
                            JOptionPane.showMessageDialog(null, "Information was successfully submitted.");

                            txtStudentNo.setText("");
                            txtFirstName.setText("");
                            txtLastName.setText("");
                            txtEmailAddress.setText("");
                            txtCellNo.setText("");

                            txtStudentNo.requestFocus();
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Submission unsuccessful!");
                        }
                }
            }
            else if(e.getActionCommand().equals("Exit"))
            {
                System.exit(0);
            }

    }

    public static void main(String[] args) {

        new StudentFormUserInterface().setGui();

    }
}
