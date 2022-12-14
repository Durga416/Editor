import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class SimpleTextEditor implements ActionListener {
    JFrame jFrame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu File;
    JMenu Edit;
    JMenu Close;
    JMenuItem NewFile;
    JMenuItem OpenFile;
    JMenuItem SaveFile;
    JMenuItem PrintFile;
    JMenuItem Cut;
    JMenuItem Copy;
    JMenuItem Paste;
    JMenuItem CloseEditor;

    SimpleTextEditor() {
        jFrame = new JFrame("Simple text editor");
        jFrame.setBounds(0, 0, 800, 600);

        jTextArea = new JTextArea("welcome to the text editor");

        jMenuBar = new JMenuBar();
        File = new JMenu("File");
        Edit = new JMenu(" Edit");
        Close = new JMenu("Close");
        jMenuBar.add(File);
        jMenuBar.add(Edit);
        jMenuBar.add(Close);

        NewFile = new JMenuItem("New");
        NewFile.addActionListener(this);

        OpenFile = new JMenuItem("Open");
        OpenFile.addActionListener(this);

        SaveFile = new JMenuItem("Save");
        SaveFile.addActionListener(this);

        PrintFile = new JMenuItem("Print");
        PrintFile.addActionListener(this);

        Cut = new JMenuItem("Cut");
        Cut.addActionListener(this);

        Copy = new JMenuItem("Copy");
        Copy.addActionListener(this);

        Paste = new JMenuItem("Paste");
        Paste.addActionListener(this);

        CloseEditor = new JMenuItem("CloseEditor");
        CloseEditor.addActionListener(this);

        File.add(NewFile);
        File.add(OpenFile);
        File.add(SaveFile);
        File.add(PrintFile);

        Edit.add(Cut);
        Edit.add(Copy);
        Edit.add(Paste);

        Close.add(CloseEditor);


        jFrame.setJMenuBar(jMenuBar);
        jFrame.add(jTextArea);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }


    public static void main(String[] args) {
        SimpleTextEditor editor = new SimpleTextEditor();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("Cut")) {
            jTextArea.cut();
        } else if (s.equals("Copy")) {
            jTextArea.copy();
        } else if (s.equals("Paste")) {
            jTextArea.paste();
        } else if (s.equals("Print")) {
            try {
                jTextArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        } else if (s.equals("New")) {
            jTextArea.setText("");
        } else if (s.equals("CloseEditor")) {
            jFrame.setVisible(false);
        } else if (s.equals("Open")) {
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if (ans == jFileChooser.APPROVE_OPTION) {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1 = "", s2 = "";
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while ((s1 = bufferedReader.readLine()) != null) {
                        s2 += s1 + "\n";
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            //save the file
        } else if (s.equals("Save")) {
            JFileChooser jFileChooser = new JFileChooser("C");
            int ans = jFileChooser.showOpenDialog(null);
            if (ans ==jFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
                    writer.write((jTextArea.getText()));
                    writer.flush();
                    writer.close();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

        }
    }
}

