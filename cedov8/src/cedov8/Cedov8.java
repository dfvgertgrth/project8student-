
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Cedov8 extends JFrame {
    private JTextField usernameField, passwordField;
    private JPanel loginPanel, mainPanel;
    private CardLayout cardLayout;

    private JTextField nameField, rollField, mathField, scienceField, englishField;
    private JTextArea resultArea;

    public Cedov8() {
        setTitle("📚 Система управления успеваемостью");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        loginPanel = createLoginPanel();
        mainPanel = createMainPanel();

        add(loginPanel, "login");
        add(mainPanel, "main");

        cardLayout.show(getContentPane(), "login");
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(240, 248, 255));

        JLabel label = new JLabel("🔐 Вход в систему", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(new Color(33, 47, 61));
        label.setBounds(150, 40, 300, 40);
        panel.add(label);

        JLabel userLabel = new JLabel("Имя пользователя:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userLabel.setBounds(120, 120, 130, 30);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(250, 120, 180, 30);
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Пароль:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passLabel.setBounds(120, 170, 130, 30);
        panel.add(passLabel);

        passwordField = new JTextField();
        passwordField.setBounds(250, 170, 180, 30);
        panel.add(passwordField);

        JButton loginBtn = new JButton("🚪 Войти");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setBackground(new Color(46, 134, 222));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setBounds(250, 220, 180, 40);
        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = passwordField.getText();
            if (user.equals("admin") && pass.equals("1234")) {
                cardLayout.show(getContentPane(), "main");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Неверный логин или пароль!");
            }
        });
        panel.add(loginBtn);

        return panel;
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(240, 248, 255));

        JLabel title = new JLabel("📝 Ввод данных студента", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(33, 47, 61));
        title.setBounds(150, 10, 300, 40);
        panel.add(title);

        JLabel nameLabel = new JLabel("Имя студента:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nameLabel.setBounds(50, 70, 100, 30);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 70, 150, 30);
        panel.add(nameField);

        JLabel rollLabel = new JLabel("№ зачетки:");
        rollLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        rollLabel.setBounds(350, 70, 80, 30);
        panel.add(rollLabel);

        rollField = new JTextField();
        rollField.setBounds(440, 70, 100, 30);
        panel.add(rollField);

        JLabel mathLabel = new JLabel("Математика:");
        mathLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        mathLabel.setBounds(50, 120, 100, 30);
        panel.add(mathLabel);

        mathField = new JTextField();
        mathField.setBounds(150, 120, 150, 30);
        panel.add(mathField);

        JLabel sciLabel = new JLabel("Физика:");
        sciLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        sciLabel.setBounds(350, 120, 80, 30);
        panel.add(sciLabel);

        scienceField = new JTextField();
        scienceField.setBounds(440, 120, 100, 30);
        panel.add(scienceField);

        JLabel engLabel = new JLabel("Английский:");
        engLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        engLabel.setBounds(50, 170, 100, 30);
        panel.add(engLabel);

        englishField = new JTextField();
        englishField.setBounds(150, 170, 150, 30);
        panel.add(englishField);

        JButton calcBtn = new JButton("🧮 Рассчитать результат");
        calcBtn.setFont(new Font("Arial", Font.BOLD, 12));
        calcBtn.setBackground(new Color(39, 174, 96));
        calcBtn.setForeground(Color.WHITE);
        calcBtn.setFocusPainted(false);
        calcBtn.setBounds(200, 220, 200, 40);
        calcBtn.addActionListener(e -> calculateResult());
        panel.add(calcBtn);

        JButton saveBtn = new JButton("💾 Сохранить результат");
        saveBtn.setFont(new Font("Arial", Font.BOLD, 12));
        saveBtn.setBackground(new Color(41, 128, 185));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);
        saveBtn.setBounds(200, 270, 200, 40);
        saveBtn.addActionListener(e -> saveResult());
        panel.add(saveBtn);

        JButton viewBtn = new JButton("📋 Все результаты");
        viewBtn.setFont(new Font("Arial", Font.BOLD, 12));
        viewBtn.setBackground(new Color(155, 89, 182));
        viewBtn.setForeground(Color.WHITE);
        viewBtn.setFocusPainted(false);
        viewBtn.setBounds(200, 320, 200, 40);
        viewBtn.addActionListener(e -> viewResults());
        panel.add(viewBtn);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setBorder(BorderFactory.createTitledBorder("📊 Результат"));
        scroll.setBounds(50, 370, 500, 80);
        panel.add(scroll);

        return panel;
    }

    private void calculateResult() {
        try {
            int math = Integer.parseInt(mathField.getText());
            int science = Integer.parseInt(scienceField.getText());
            int english = Integer.parseInt(englishField.getText());

            int total = math + science + english;
            double percent = total / 3.0;
            String grade = (percent >= 40) ? "✅ Сдал" : "❌ Не сдал";

            resultArea.setText("📊 ИТОГОВЫЙ РЕЗУЛЬТАТ:\n" +
                             "Всего баллов: " + total + "\n" +
                             "Средний балл: " + String.format("%.2f", percent) + "%\n" +
                             "Статус: " + grade);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "❌ Пожалуйста, введите корректные числовые значения!", 
                "Ошибка", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveResult() {
        String name = nameField.getText().trim();
        String roll = rollField.getText().trim();
        String resultText = resultArea.getText();

        if (name.isEmpty() || roll.isEmpty() || resultText.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "❌ Заполните все поля и рассчитайте результат!", 
                "Предупреждение", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true))) {
            writer.write("Имя: " + name + ", № зачетки: " + roll + ", " + 
                        resultText.replace("\n", ", ") + "\n");
            JOptionPane.showMessageDialog(this, 
                "✅ Результат успешно сохранен!", 
                "Успех", 
                JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "❌ Ошибка при сохранении результата.", 
                "Ошибка", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewResults() {
        try (BufferedReader reader = new BufferedReader(new FileReader("results.txt"))) {
            resultArea.setText("📋 ВСЕ СОХРАНЕННЫЕ РЕЗУЛЬТАТЫ:\n\n");
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                resultArea.append(++count + ". " + line + "\n");
            }
            if (count == 0) {
                resultArea.append("Нет сохраненных результатов.\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, 
                "📭 Сохраненных результатов не найдено.", 
                "Информация", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        rollField.setText("");
        mathField.setText("");
        scienceField.setText("");
        englishField.setText("");
        resultArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Cedov8::new);
    }
}