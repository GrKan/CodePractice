import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

class HW4 extends JFrame implements ActionListener {

    public static final String NAME_FILE = "SaveText.txt";
    public static final String ENTER = "Enter";
    final String TITLE_OF_PROGRAM = "Chat";
    final int START_LOCATION = 200;
    final int WINDOW_WIDTH = 350;
    final int WINDOW_HEIGHT = 450;

    JTextArea dialogue; // area for dialog
    JTextField message; // field for entering messages

    public static void main(String[] args) {
        new HW4();
    }

    /**
     * Constructor:
     * Creating a window and all the necessary elements on it
     */
    HW4() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);
        setVisible(true);
        // area for dialog
        dialogue = new JTextArea();
        dialogue.setLineWrap(true);
        dialogue.setEditable(false);
        JScrollPane scrollBar = new JScrollPane(dialogue);
        // panel for checkbox, message field and button
        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
        message = new JTextField();
        message.addActionListener(this);
        JButton enter = new JButton(ENTER);
        enter.addActionListener(this);
        // adding all elements to the window
        bp.add(message);
        bp.add(enter);
        add(BorderLayout.CENTER, scrollBar);
        add(BorderLayout.SOUTH, bp);
        setVisible(true);
    }

    /**
     * Listener of events from message field and enter button
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        OutputStream os = null;
        if (message.getText().trim().length() > 0) {
            dialogue.append(message.getText() + "\n");
            try {
                PrintStream printStream = new PrintStream(new FileOutputStream(NAME_FILE, true), true);
                printStream.println(message.getText());
                printStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        message.setText("");
        message.requestFocusInWindow();
    }
}