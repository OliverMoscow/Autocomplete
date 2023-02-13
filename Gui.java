import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Gui {

    public Gui() {
        JFrame frame = new JFrame();
        JButton button = new JButton("Generate");
        JPanel panel = new JPanel();
        String data [] = {"acad", "blog", "fic", "mag", "news", "spok", "tvm", "web"};
        JComboBox dropDown = new JComboBox<>(data);
        JTextArea text = new JTextArea();
        frame.add(panel);
        panel.add(dropDown);
        panel.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        button.setLayout(null);
        button.setSize(20, 20);
        button.setForeground(Color.CYAN);
        button.setBackground(Color.RED);
        text.setForeground(Color.black);
        text.setBackground(Color.PINK);
        text.setColumns(20);
        text.setRows(7);
        text.setTabSize(1);
        text.setVisible(true);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        frame.setSize(800, 200);
        JScrollPane scroll = new JScrollPane(text);
        panel.add(scroll);
        panel.setBackground(Color.YELLOW);
        String file = "acad";
        dropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              // file = (String)dropDown.getSelectedItem();
            }
        });


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String t = text.getText();
                String newText = t;
                ArrayList<String> words = new ArrayList<>();

                // /*
                    while (newText.length() != 0) {
                            if (newText.indexOf(" ") != -1) {
                                if (newText.substring(0, newText.indexOf(" ")).indexOf(";") != -1
                                        || newText.substring(0, newText.indexOf(" ")).indexOf(":") != -1) {
                                    words.add(newText.substring(0, (newText.indexOf(" ") - 1)));
                                } else {
                                    words.add(newText.substring(0, newText.indexOf(" ")));
                                }
                                newText = newText.substring(newText.indexOf(" ") + 1);
                            }
                        else {
                            if (newText.indexOf(".") != -1 
                                    || newText.indexOf("!") != -1 
                                    || newText.indexOf("?") != -1){
                                words.add(newText.substring(0, newText.length() - 1));
                                words.add(newText.substring(newText.length() - 1));
                                newText = "";
                               // words.add("passed");
                                    }
                            else{
                                words.add(newText);
                                newText = "";
                            }
                        }
                    }
                // */
                for (String s : words)
                    newText += s;
                // text.setText(newText);
                text.setText(" " + newText);

            }
        });

    }

    public static void main(String[] args) {
        Gui gui = new Gui();
    }
}