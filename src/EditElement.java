import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

public class EditElement extends JFrame implements ActionListener {
    JPanel form = new JPanel();
    JLabel actionOrgNameLabel = new JLabel("Original Action Name");
    JTextField actionOrgNameText = new JTextField();
    JCheckBox checkBox = new JCheckBox("Edit All Occurrences");
    JLabel actionNewNameLabel = new JLabel("New Action Name");
    JTextField actionNewNameText = new JTextField();
    JButton confirmAction = new JButton("Edit Action");
    JButton cancel = new JButton("Cancel");

    public EditElement() {
        this.form.setLayout(null);
        this.setTitle("Edit Action");
        this.setLocation();
        this.addComponents();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(this.form);
        this.setVisible(true);
    }

    private void setLocation() {
        this.setSize(350, 200);
        this.actionOrgNameLabel.setBounds(10, 10, 150, 30);
        this.actionOrgNameText.setBounds(150, 10, 175, 30);
        this.actionNewNameLabel.setBounds(10, 45, 150, 30);
        this.actionNewNameText.setBounds(150, 45, 175, 30);
        this.checkBox.setBounds(10, 75, 150, 30);
        this.confirmAction.setBounds(10, 110, 150, 30);
        this.cancel.setBounds(225, 110, 100, 30);
    }

    private void addComponents() {
        this.form.add(this.actionOrgNameLabel);
        this.form.add(this.actionOrgNameText);
        this.form.add(this.actionNewNameLabel);
        this.form.add(this.actionNewNameText);
        this.form.add(this.cancel);
        this.form.add(this.confirmAction);
        this.form.add(this.checkBox);
        confirmAction.addActionListener(this);
        cancel.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        FileWriter writeToList = null;
        if( str.equals("Edit Action"))
        {
            StringBuilder fileContents = new StringBuilder();
            int counter = 0;
            try {
                File myObj = new File("src/ToDoList.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if( counter > 0 )
                    {
                        fileContents.append('\n');
                    }
                    fileContents.append(data);
                    counter++;
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            String stringFileContents = String.valueOf(fileContents);
            String newFileContents = "";
            if( checkBox.isSelected() )
            {
                newFileContents = stringFileContents.replace(actionOrgNameText.getText(), actionNewNameText.getText());
            }
            else
            {
                newFileContents = stringFileContents.replaceFirst(actionOrgNameText.getText(), actionNewNameText.getText());
            }
            try {
                writeToList = new FileWriter("src/ToDoList.txt");
                writeToList.write(newFileContents);
                writeToList.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            MainListForm mlf = new MainListForm();
            this.setVisible(false);
            mlf.setVisible(true);
        }
        else if(str.equals("Cancel"))
        {
            MainListForm mlf = new MainListForm();
            this.setVisible(false);
            mlf.setVisible(true);
        }
    }
}
