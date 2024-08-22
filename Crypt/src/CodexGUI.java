import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class CodexGUI extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;
    private JTextField keyField;
    private JButton encryptButton;
    private JButton decryptButton;
    private JButton exitButton;

    public CodexGUI() {
        // Set up the frame
        setTitle("Anthony's Codex");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create components
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JLabel inputLabel = new JLabel("Enter phrase:");
        inputField = new JTextField();
        JLabel keyLabel = new JLabel("Enter key:");
        keyField = new JTextField();
        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");
        exitButton = new JButton("Exit");
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        // Add components to input panel
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(keyLabel);
        inputPanel.add(keyField);
        inputPanel.add(encryptButton);
        inputPanel.add(decryptButton);

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(exitButton, BorderLayout.SOUTH);

        // Add action listeners
        encryptButton.addActionListener(new EncryptAction());
        decryptButton.addActionListener(new DecryptAction());
        exitButton.addActionListener(e -> System.exit(0));
    }

    private class EncryptAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String phrase = inputField.getText();
            int seed;
            try {
                seed = Integer.parseInt(keyField.getText());
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid key. Please enter a valid integer.");
                return;
            }

            Random rand = new Random();
            long outputKey = seed;
            char[] arrEn = phrase.toCharArray();
            StringBuilder encryptedPhrase = new StringBuilder();

            for (char letter : arrEn) {
                seed += 1;
                rand.setSeed(seed);
                int num = rand.nextInt(10);
                letter += num;
                encryptedPhrase.append(letter);
            }

            outputArea.setText("Encrypted phrase: " + encryptedPhrase + "\nKey used: " + outputKey);
        }
    }

    private class DecryptAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String phrase = inputField.getText();
            int seed;
            try {
                seed = Integer.parseInt(keyField.getText());
            } catch (NumberFormatException ex) {
                outputArea.setText("Invalid key. Please enter a valid integer.");
                return;
            }

            Random rand2 = new Random();
            char[] arrEn = phrase.toCharArray();
            StringBuilder decryptedPhrase = new StringBuilder();

            for (char letter : arrEn) {
                seed += 1;
                rand2.setSeed(seed);
                int num = rand2.nextInt(10);
                letter -= num;
                decryptedPhrase.append(letter);
            }

            outputArea.setText("Decrypted phrase: " + decryptedPhrase);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CodexGUI frame = new CodexGUI();
            frame.setVisible(true);
        });
    }
}