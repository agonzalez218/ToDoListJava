import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MainListForm extends JFrame implements ActionListener {
    JPanel form = new JPanel();
    JPanel middlePanel = new JPanel ();
    JTextArea display = new JTextArea ( 18, 33 );
    JButton addElement = new JButton();
    JButton removeElement = new JButton();
    JButton editElement = new JButton();
    JButton exit = new JButton();

    public MainListForm() {
        this.form.setLayout(null);
        this.setLocation();
        this.setText();
        this.addComponents();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(this.middlePanel);
        this.add(this.form);
        this.setVisible(true);
    }

    private void setLocation() {
        this.setSize(400, 500);
        this.addElement.setBounds(15, 350, 100, 30);
        this.exit.setBounds(15, 400, 350, 30);
        this.editElement.setBounds(130, 350, 100, 30);
        this.removeElement.setBounds(245, 350, 120, 30);
        this.middlePanel.setBorder(new TitledBorder(new EtchedBorder(), "To-Do List"));
        this.middlePanel.setBounds(0,0,385,340);
        display.setEditable ( false );
    }

    public void setText() {
        int counter = 0;
        StringBuilder fileContents = new StringBuilder();
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
        this.setTitle("Main Menu");
        this.addElement.setText("Add Action");
        this.exit.setText("Exit");
        this.editElement.setText("Edit Action");
        this.removeElement.setText("Remove Action");
        this.display.setText(String.valueOf(fileContents));
    }

    private void addComponents() {
        this.form.add(this.addElement);
        this.form.add(this.exit);
        this.form.add(this.removeElement);
        this.form.add(this.editElement);
        this.form.add(this.display);
        addElement.addActionListener(this);
        exit.addActionListener(this);
        removeElement.addActionListener(this);
        editElement.addActionListener(this);
        JScrollPane scroll = new JScrollPane(display);
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        this.middlePanel.add(scroll);
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        if( str.equals("Add Action"))
        {
            AddElement ael = new AddElement();
            this.setVisible(false);
            ael.setVisible(true);
        }
        else if(str.equals("Exit"))
        {
            System.exit(0);                         // exit program
        }
        else if(str.equals("Edit Action"))
        {
            EditElement ee = new EditElement();
            this.setVisible(false);
            //ee.setVisible(true);
        }
        else if(str.equals("Remove Action"))
        {
            RemoveElement re = new RemoveElement();
            this.setVisible(false);
            //re.setVisible(true);
        }
    }
}
