import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RecursiveCruiser extends JFrame {
        public RecursiveCruiser(){
            super("Cruiser");
            // background elements



            //UI elements
            JPanel buttonPanel = new JPanel();
            JPanel tagTextPanel = new JPanel();
            JPanel savePanel = new JPanel();
            JPanel docSPanel = new JPanel();
            JPanel stopSPanel = new JPanel();
            JButton clear = new JButton("Clear Options");
            JButton stopButton = new JButton("Select Stop List");
            JButton docButton = new JButton("Select Directory");
            JButton saveButton = new JButton("Save Tag List");
            JButton processButton = new JButton("Run");
            JButton writeButton = new JButton("Write to File");
            JFileChooser docSelect = new JFileChooser();
            docSelect.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            JFileChooser stopSelect = new JFileChooser();
            JFileChooser saveSelect = new JFileChooser();
            JButton additionalLine = new JButton("Add Line");
            JButton quit = new JButton("Quit");
            JButton render = new JButton("Render Invoice");
            JLabel lineLType = new JLabel("The Recursive Cruiser");
            JLabel lineLUnitPrice = new JLabel("Item Unit Price");
            JLabel lineLQuantity = new JLabel("Item Quantity");
            JTextArea lineIType = new JTextArea("", 1, 5);
            JTextArea lineIUnitPrice = new JTextArea("", 1, 8);
            JTextArea lineIQuantity = new JTextArea("", 1, 7);

            JLabel stopSelectL = new JLabel("");
            JLabel lSSelect = new JLabel("Selected Stop List: ");
            JLabel docSelectL = new JLabel("");
            JLabel lDSelect = new JLabel("Selected Directory: ");
            JLabel saveSelectL = new JLabel("");
            JLabel lSaSelect = new JLabel("Tag List Name: ");
            JTextArea addITitle = new JTextArea("", 1, 10);
            JTextArea addIStreet = new JTextArea("", 1, 10);
            JTextArea addICity = new JTextArea("", 1, 10);

            JRadioButton textRender = new JRadioButton("Render to Textbox");

            textRender.setSelected(true);
            JRadioButton consoleRender = new JRadioButton("Render to Console");
            ButtonGroup renderGroup = new ButtonGroup();
            renderGroup.add(textRender);
            renderGroup.add(consoleRender);
            GridBagConstraints c = new GridBagConstraints();
            JTextArea ta = new JTextArea("", 15, 50); // Text area
            ta.setLineWrap(true);
            JScrollPane sbrText = new JScrollPane(ta); // Scroll pane for text area
            sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            //action listener
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stopSelect.showDialog(new JFrame(), "Choose Stop List");
                    stopSelectL.setText(stopSelect.getSelectedFile().getName());
                }
            });
            docButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    docSelect.showDialog(new JFrame(), "Choose Document");
                    docSelectL.setText(docSelect.getSelectedFile().getName());
                }
            });
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveSelect.showDialog(new JFrame(), "Select Save Document");
                    saveSelectL.setText(saveSelect.getSelectedFile().getName());
                }
            });
            processButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    File doc = docSelect.getSelectedFile();
                    pathFinder(ta,doc,"");




                }
            });
            writeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {


                }
            });
            quit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(new JFrame(),"Sure? You want to exit?", "Quit Confirm",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if(result == JOptionPane.YES_OPTION){
                        System.exit(0);

                    }else if (result == JOptionPane.NO_OPTION){

                    }else {

                    }
                }
            });
            additionalLine.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String type= lineIType.getText();
                    int quantity= Integer.parseInt(lineIQuantity.getText());
                    double unitPrice= Double.parseDouble(lineIUnitPrice.getText());

                    lineIType.setText("");
                    lineIQuantity.setText("");
                    lineIUnitPrice.setText("");


                }
            });
            render.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double amount=0;
                    String msg="";
                    msg+= addITitle.getText()+"\n";
                    msg+= addIStreet.getText()+"\n";
                    msg+= addICity.getText()+"\n";
                    msg+= "\n\n";
                    msg+="=====================================================================\n";

                    msg+=String.format("%-50s %-10s %-10s %-10s\n","Item","Qty","Price","Total");





                    msg+="\n \n";
                    msg+="--------------------------------------------------------------------------------\n";
                    msg+=String.format("%-55s $%-10.2f\n","Total:",amount);



                    msg+="=====================================================================\n";
                    if(textRender.isSelected()==true){

                        ta.setText(msg);
                    }else{
                        System.out.print(msg);
                    }

                }
            });

            //formatting the buttonPanel
            buttonPanel.setLayout(new GridBagLayout());
            buttonPanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Actions", TitledBorder.LEFT, TitledBorder.TOP));
            c.weightx = 1;

            c.ipady = 0;

            c.ipady = 0;
            c.gridx = 1;
            c.gridy = 1;

            buttonPanel.add(quit,c);
            c.gridx = 2;
            c.gridy = 1;
            buttonPanel.add(processButton,c);



            // formatting tagTextPanel
            tagTextPanel.setLayout(new GridBagLayout());
            tagTextPanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Files in the Directory", TitledBorder.LEFT, TitledBorder.TOP));
            c.weightx = 1;

            c.ipady = 0;
            c.gridx = 1;
            c.gridy = 1;
            tagTextPanel.add(sbrText, c);
            //formatting the docSPanel
            docSPanel.setLayout(new GridBagLayout());
            docSPanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Directory Select", TitledBorder.LEFT, TitledBorder.TOP));
            c.anchor = GridBagConstraints.WEST;
            c.gridwidth=1;
            c.ipadx= 0;
            c.gridx = 0;
            c.gridy = 0;
            docSPanel.add(lDSelect,c);
            c.gridx = 1;
            c.gridy = 0;
            docSPanel.add(docSelectL,c);
            c.gridwidth=2;
            c.ipadx= 10;
            c.gridx = 0;
            c.gridy = 1;
            docSPanel.add(docButton,c);
            c.anchor = GridBagConstraints.CENTER;


            c.ipadx= 0;

            //Formatting savePanel
            savePanel.setLayout(new GridBagLayout());
            savePanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Save Output", TitledBorder.LEFT, TitledBorder.TOP));
            c.anchor = GridBagConstraints.WEST;
            c.gridwidth=1;
            c.ipadx= 0;
            c.gridx = 0;
            c.gridy = 0;
            savePanel.add(lSaSelect,c);
            c.gridx = 1;
            c.gridy = 0;
            savePanel.add(saveSelectL,c);
            c.gridwidth=2;
            c.ipadx= 10;
            c.gridx = 0;
            c.gridy = 1;
            savePanel.add(saveButton,c);
            c.anchor = GridBagConstraints.CENTER;


            c.ipadx= 0;

            // Formatting stopSPanel
            stopSPanel.setLayout(new GridBagLayout());
            stopSPanel.setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Stop List Select", TitledBorder.LEFT, TitledBorder.TOP));

            c.anchor = GridBagConstraints.WEST;
            c.gridwidth=1;
            c.ipadx= 0;
            c.gridx = 0;
            c.gridy = 0;
            stopSPanel.add(lSSelect,c);
            c.gridx = 1;
            c.gridy = 0;
            stopSPanel.add(stopSelectL,c);
            c.gridwidth=2;
            c.ipadx= 10;
            c.gridx = 0;
            c.gridy = 1;
            stopSPanel.add(stopButton,c);


            c.ipadx= 0;



            //adding UI Elements
            setLayout(new GridBagLayout());
            c.anchor = GridBagConstraints.CENTER;
            c.weightx = 1;
            c.weighty=1;
            c.ipadx = 0;
            c.ipady = 0;
            c.gridx = 0;
            c.gridy = 4;
            c.gridwidth = 2;
            add(buttonPanel, c);
            c.gridx = 0;
            c.gridy = 0;
            add(lineLType, c);

            c.gridx = 0;
            c.gridy = 3;
            add(tagTextPanel, c);
            c.gridwidth = 1;
            c.gridx = 1;
            c.gridy = 1;
            add(docSPanel, c);







            // formatting the frame
            Toolkit kit = Toolkit.getDefaultToolkit();
            Dimension screenSize = kit.getScreenSize();
            int screenHeight = screenSize.height;
            int screenWidth = screenSize.width;

            // center frame in screen

            setSize((int) (screenWidth / 1.25), (int) (screenHeight / 1.25));

            setLocation((int) (screenWidth / 9.5), (int) (screenHeight / 9));

            // Render the Frame
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.show();



        }
    public void writeText(JTextArea ta, String outText){
        int past = ta.getText().length();
        if(past==0) {
            ta.setText(outText);
        }else {
            ta.setText(ta.getText()+"\n"+outText);

        }

    }
    public void pathFinder(JTextArea ta, File curDir, String starText){
            File dirCont[] = curDir.listFiles();
            for(File file : dirCont){
                if(file.isFile()){
                    writeText(ta,starText+file.getName());
                }else{
                    writeText(ta,starText+file.getName());
                    pathFinder(ta,file,starText+"  ");
                }
            }
    }
}

