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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JButton button = new JButton("Generate");
        JPanel panel = new JPanel();
        button.setLayout(null);
        button.setSize(20, 20);
        button.setForeground(Color.CYAN);
        button.setBackground(Color.RED);
        frame.add(panel);
        panel.add(button);
        JTextArea text = new JTextArea();
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
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String t = text.getText();
                String newText = t;
                ArrayList<String> words = new ArrayList<>();
                while ((newText.indexOf(".") != -1) || (newText.indexOf("?") != -1) || (newText.indexOf("!") != -1)) {
                    if (newText.indexOf(".") != -1) {
                        newText = newText.substring(newText.indexOf(".") + 1);
                    }
                    if (newText.indexOf("?") != -1) {
                        newText = newText.substring(newText.indexOf("?") + 1);
                    }
                    if (newText.indexOf("!") != -1) {
                        newText = newText.substring(newText.indexOf("!") + 1);
                    }
                }
                // /*
                    while (newText.length() != 0) {
                            if (newText.indexOf(" ") != -1) {
                                if (newText.substring(0, newText.indexOf(" ")).indexOf(",") != -1
                                        || newText.substring(0, newText.indexOf(" ")).indexOf(";") != -1
                                        || newText.substring(0, newText.indexOf(" ")).indexOf(":") != -1
                                        || newText.substring(0, newText.indexOf(" ")).indexOf(":") != -1) {
                                    words.add(newText.substring(0, (newText.indexOf(" ") - 1)));
                                } else {
                                    words.add(newText.substring (0, newText.indexOf(" ")));
                                }
                                newText = newText.substring(newText.indexOf(" ") + 1);
                            }
                        else {
                            if (newText.indexOf(".") != -1 
                                    || newText.indexOf(",") != -1 
                                    || newText.indexOf(";") != -1
                                    || newText.indexOf(":") != -1){
                                words.add(newText.substring(0, newText.length() - 1));
                               // words.add("passed");
                                    }
                            else
                                words.add(newText);
                            newText = "";
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