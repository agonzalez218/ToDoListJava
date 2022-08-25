import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

public class AddElement extends JFrame implements ActionListener {
    JPanel form = new JPanel();
    JLabel actionNameLabel = new JLabel("Action Name");
    JTextField actionNameText = new JTextField();
    JButton confirmAction = new JButton("Add Action");
    JButton cancel = new JButton("Cancel");

    public AddElement() {
        this.form.setLayout(null);
        this.setTitle("Add New Action");
        this.setLocation();
        this.addComponents();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(this.form);
        this.setVisible(true);
    }

    private void setLocation() {
        this.setSize(350, 200);
        this.actionNameLabel.setBounds(10, 30, 150, 30);
        this.actionNameText.setBounds(150, 30, 175, 30);
        this.confirmAction.setBounds(10, 110, 150, 30);
        this.cancel.setBounds(225, 110, 100, 30);
    }

    private void addComponents() {
        this.form.add(this.actionNameLabel);
        this.form.add(this.actionNameText);
        this.form.add(this.cancel);
        this.form.add(this.confirmAction);
        confirmAction.addActionListener(this);
        cancel.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        int counter = 0;
        if( str.equals("Add Action")) {
            StringBuilder fileContents = new StringBuilder();
            FileWriter writeToList = null;
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
            try {
                writeToList = new FileWriter("src/ToDoList.txt");
                if(String.valueOf(fileContents).equals(""))
                {
                    fileContents.append(actionNameText.getText());
                }
                else
                {
                    fileContents.append('\n'+actionNameText.getText());
                }
                writeToList.write(String.valueOf(fileContents));
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
