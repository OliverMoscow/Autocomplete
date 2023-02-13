import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class Gui {

    public Gui() throws IOException {
        JFrame frame = new JFrame("Chat with Chad");
        JButton button = new JButton("Generate");
        JPanel panel = new JPanel();
        JTextArea text = new JTextArea();
        
        //note: input sopposed to be called "output"
        //simply 2 lzy to fx
        JTextArea input = new JTextArea();
        frame.add(panel);
        panel.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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

        input.setForeground(Color.black);
        input.setBackground(Color.GREEN);
        input.setColumns(20);
        input.setRows(7);
        input.setTabSize(1);
        input.setVisible(true);
        input.setLineWrap(true);
        input.setWrapStyleWord(true);
        input.setEditable(false);

        frame.setSize(800, 200);
        input.setText("\tOutput");
        text.setText("\tType Here");

        JScrollPane scroll = new JScrollPane(input);
        JScrollPane textScroll = new JScrollPane(text);
        panel.setBackground(Color.YELLOW);
        panel.add(textScroll);
        panel.add(scroll);

            text.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (text.getText().equals("") || text.getText().equals("\tType Here"))
                    text.setText("");
                }

                @Override
                public void focusLost(FocusEvent e) { 
                    if(text.getText().equals("")){
                        text.setText("\tType Here");
                    }                   
                }
              });

            button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(text.getText().substring((text.getText().length()) - 1).equals(".") || text.getText().substring((text.getText().length()) - 1).equals("!") || text.getText().substring((text.getText().length()) - 1).equals("?"))){
                try {  
                    final LanguageModel impliment = new LanguageModel(text.getText());
                    input.setText(impliment.generateResponse());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            else{
                System.out.println("pass");
                input.setText(text.getText());
            }
              }
          });
        }

    public static void main(String[] args) throws IOException {
        Gui gui = new Gui();
    }
}