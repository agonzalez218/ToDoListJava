import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

public class RemoveElement extends JFrame implements ActionListener {
    JPanel form = new JPanel();
    JLabel actionNameLabel = new JLabel("Action Name");
    JButton clearActionList = new JButton("Clear List");
    JCheckBox checkBox = new JCheckBox("Remove All Occurrences");
    JTextField actionNameText = new JTextField();
    JButton confirmAction = new JButton("Remove Action");
    JButton cancel = new JButton("Cancel");

    public RemoveElement() {
        this.form.setLayout(null);
        this.setTitle("Remove Action");
        this.setLocation();
        this.addComponents();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(this.form);
        this.setVisible(true);
    }

    private void setLocation() {
        this.setSize(350, 200);
        this.checkBox.setBounds(160, 80, 175, 30);
        this.actionNameLabel.setBounds(10, 30, 150, 30);
        this.actionNameText.setBounds(150, 30, 175, 30);
        this.confirmAction.setBounds(10, 120, 120, 30);
        this.clearActionList.setBounds(10, 80, 120, 30);
        this.cancel.setBounds(225, 120, 100, 30);
    }

    private void addComponents() {
        this.form.add(this.actionNameLabel);
        this.form.add(this.actionNameText);
        this.form.add(this.cancel);
        this.form.add(this.confirmAction);
        this.form.add(this.clearActionList);
        this.form.add(this.checkBox);
        clearActionList.addActionListener(this);
        confirmAction.addActionListener(this);
        cancel.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        FileWriter writeToList = null;
        if( str.equals("Remove Action"))
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
                newFileContents = stringFileContents.replace(actionNameText.getText()+"\n", "");
                newFileContents = newFileContents.replace(actionNameText.getText(), "");
            }
            else
            {
                newFileContents = stringFileContents.replaceFirst(actionNameText.getText()+"\n", "");
                newFileContents = newFileContents.replaceFirst(actionNameText.getText(), "");
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
        if( str.equals("Clear List"))
        {
            try {
                writeToList = new FileWriter("src/ToDoList.txt");
                writeToList.write("");
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
