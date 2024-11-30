import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SortingApp extends JFrame {
    private ArrayList<Integer> dataList = new ArrayList<>();
    private JTextField countField, sortedField;
    private JTextArea dataListArea;
    private JRadioButton bubbleSort, selectionSort;

    public SortingApp() {
        setTitle("Antrian Kantin Siswa");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(255, 255, 204));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Jumlah Siswa Dalam Antrian:"), gbc);

        countField = new JTextField(5);
        gbc.gridx = 1;
        add(countField, gbc);

        JButton generateButton = new JButton("Generate Antrian");
        gbc.gridx = 2;
        add(generateButton, gbc);
        generateButton.addActionListener(e -> generateData());

        // Display area for generated data
        dataListArea = new JTextArea(5, 30);
        dataListArea.setEditable(false);
        dataListArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        add(new JScrollPane(dataListArea), gbc);

        // Sorting algorithm selection
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Algoritma Sorting Antrian:"), gbc);

        bubbleSort = new JRadioButton("Bubble Sort");
        selectionSort = new JRadioButton("Selection Sort");
        ButtonGroup sortGroup = new ButtonGroup();
        sortGroup.add(bubbleSort);
        sortGroup.add(selectionSort);

        JPanel sortPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        sortPanel.add(bubbleSort);
        sortPanel.add(selectionSort);
        sortPanel.setBackground(new Color(255, 255, 204));

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(sortPanel, gbc);

        // Sort button and sorted result display
        JButton sortButton = new JButton("Sort");
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(sortButton, gbc);
        sortButton.addActionListener(e -> sortData());

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Hasil Sorting Antrian:"), gbc);

        sortedField = new JTextField(30);
        sortedField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(sortedField, gbc);

        // Reset button
        JButton resetButton = new JButton("Reset");
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(resetButton, gbc);
        resetButton.addActionListener(e -> resetData());
    }

    private void generateData() {
        try {
            int count = Integer.parseInt(countField.getText());
            dataList.clear();
            Random random = new Random();
            for (int i = 0; i < count; i++) {
                dataList.add(random.nextInt(100) + 1); // Random number between 1 and 100
            }
            updateDataList();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan jumlah siswa yang valid.");
        }
    }

    private void updateDataList() {
        dataListArea.setText(dataList.toString());
    }

    private void sortData() {
        if (dataList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tidak ada data untuk diurutkan. Silakan buat antrian terlebih dahulu.");
            return;
        }

        StringBuilder steps = new StringBuilder();

        if (bubbleSort.isSelected()) {
            bubbleSort(dataList, steps);
        } else if (selectionSort.isSelected()) {
            selectionSort(dataList, steps);
        } else {
            JOptionPane.showMessageDialog(this, "Silakan pilih algoritma sorting.");
            return;
        }

        sortedField.setText(dataList.toString());
        JOptionPane.showMessageDialog(this, "Langkah-langkah Sorting:\n" + steps.toString());
    }

    private void bubbleSort(ArrayList<Integer> list, StringBuilder steps) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    Collections.swap(list, j, j + 1);
                }
                steps.append("Langkah ").append(i * (n - i - 1) + j + 1).append(": ").append(list).append("\n");
            }
        }
    }

    private void selectionSort(ArrayList<Integer> list, StringBuilder steps) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j) < list.get(minIdx)) {
                    minIdx = j;
                }
            }
            Collections.swap(list, i, minIdx);
            steps.append("Langkah ").append(i + 1).append(": ").append(list).append("\n");
        }
    }

    private void resetData() {
        dataList.clear();
        countField.setText("");
        sortedField.setText("");
        dataListArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortingApp app = new SortingApp();
            app.setVisible(true);
        });
    }
}
