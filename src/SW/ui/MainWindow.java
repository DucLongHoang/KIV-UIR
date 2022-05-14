package SW.ui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainWindow {
    private static final String[] featAlgorithms = {"Bag of Words", "Document Frequency", "TF-IDF"};
    private static final String[] classifiers = {"Naive Bayes", "K-Nearest neighbours"};

    public static void main(String[] args) {

        JFrame win = new JFrame();
        win.setTitle("UIR - Semestralni prace: A19B0054P");
        win.setPreferredSize(new Dimension(600, 600));
        makeGui(win);

        win.pack();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setLocationRelativeTo(null);
        win.setVisible(true);
    }

    private static void makeGui(JFrame win) {
        JPanel configPanel = makeRadioButtonsPanel();
        JPanel chooseFilesPanel = makeChooseFilePanel();
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());

        JTextArea inputText = new JTextArea();
        JButton btnClassify = new JButton("Classify");

        middlePanel.add(chooseFilesPanel, BorderLayout.NORTH);
        middlePanel.add(inputText, BorderLayout.CENTER);

        win.setLayout(new BorderLayout());
        win.add(configPanel, BorderLayout.NORTH);
        win.add(middlePanel, BorderLayout.CENTER);
        win.add(btnClassify, BorderLayout.SOUTH);
    }

    private static JPanel makeChooseFilePanel() {
        JPanel chooseFilePanel = new JPanel();
        JButton chooseTrainDataBtn = new JButton("Select training data file");
        JButton chooseTestDataBtn = new JButton("Select test data file");
        JLabel testDataLabel = new JLabel(), trainDataLabel = new JLabel();

        chooseFilePanel.setLayout(new GridLayout(2, 2));
        chooseFilePanel.add(chooseTrainDataBtn);
        chooseFilePanel.add(chooseTestDataBtn);
        chooseFilePanel.add(trainDataLabel);
        chooseFilePanel.add(testDataLabel);

        chooseTrainDataBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(new File("S:/Git/KIV-UIR"));
            int option = fileChooser.showOpenDialog(chooseFilePanel);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                trainDataLabel.setText("Training data file: " + file.getName());
            }else{
                trainDataLabel.setText("No file chosen");
            }
        });

        chooseTestDataBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(new File("S:/Git/KIV-UIR"));
            int option = fileChooser.showOpenDialog(chooseFilePanel);
            if(option == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                testDataLabel.setText("Training data file: " + file.getName());
            }else{
                testDataLabel.setText("No file chosen");
            }
        });


        return chooseFilePanel;
    }

    private static JPanel makeRadioButtonsPanel() {
        JPanel configPanel = new JPanel();
        JPanel panelLeft = new JPanel();
        JPanel panelRight = new JPanel();
        configPanel.setLayout(new BorderLayout());
        configPanel.add(panelLeft, BorderLayout.NORTH);
        configPanel.add(panelRight, BorderLayout.SOUTH);

        ButtonGroup chooseFeatAlgo = new ButtonGroup();
        for (String s: featAlgorithms) {
            JRadioButton tmp = new JRadioButton(s);
            chooseFeatAlgo.add(tmp);
            panelLeft.add(tmp);
        }

        ButtonGroup chooseClassifier = new ButtonGroup();
        for (String s: classifiers) {
            JRadioButton tmp = new JRadioButton(s);
            chooseClassifier.add(tmp);
            panelRight.add(tmp);
        }
        return configPanel;
    }
}
