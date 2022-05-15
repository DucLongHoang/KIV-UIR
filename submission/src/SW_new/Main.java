package SW_new;

import SW_new.classifiers.AbstractClassifier;
import SW_new.classifiers.KNNClassifier;
import SW_new.classifiers.NaiveBayesClassifier;
import SW_new.featurizers.AbstractFeaturizer;
import SW_new.featurizers.BagOfWords;
import SW_new.featurizers.DocumentFrequency;
import SW_new.featurizers.TF_IDF;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class - start program from here
 * @author Long
 * @version 2.0
 */
public class Main {
    private static final String[] F_NAMES = {"Bag of Words", "Document Frequency", "TF-IDF"};
    private static final String[] C_NAMES = {"Naive Bayes", "K-Nearest neighbours"};

    private static final List<JRadioButton> F_BUTTONS = new ArrayList<>();
    private static final List<JRadioButton> C_BUTTONS = new ArrayList<>();
    private static final JTextArea inputText = new JTextArea();

    private static File testFile, trainFile;

    private final static int NEIGHBOUR_COUNT = 13;

    private final static String TRAIN_DATA_FILE_PATH = "test.txt";
    private final static String TEST_DATA_FILE_PATH = "train.txt";
//    private final static String TRAIN_DATA_FILE_PATH = "myTrain.txt";
//    private final static String TEST_DATA_FILE_PATH = "myTest.txt";

    /**
     * Main method - start app from here
     * @param args of command line
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;

        while (true) {
            System.out.println("\n --- Use app options: ---");
            System.out.println("[0] Use with GUI");
            System.out.println("[1] Run all configurations");
            System.out.print("> ");

            try {
                option = Integer.parseInt(sc.nextLine());
                if (option != 0 && option != 1) {
                    System.out.println("Incorrect option, please try again");
                }
                else break;
            }
            catch (NumberFormatException e) {
                System.out.println("Incorrect option, please try again");
            }
        }

        if (option == 0) showGui();
        else {
            System.out.println("This could take some time, please be patient :) \n");
            allOptionsClassify();
        }
    }

    /**
     * Classifies using all classifiers and featurizers
     */
    public static void allOptionsClassify() {
        Parser p = new Parser(TRAIN_DATA_FILE_PATH, true);
        ClassificationHandler handler = new ClassificationHandler(TEST_DATA_FILE_PATH, true);

        AbstractFeaturizer[] featurizers = new AbstractFeaturizer[] {
                new BagOfWords(p.documents), new DocumentFrequency(p.documents), new TF_IDF(p.documents)
        };

        for (AbstractFeaturizer af: featurizers) {
            handler.classifier = new NaiveBayesClassifier(af);
            System.out.println(handler.classifyTestData());
        }

        for (AbstractFeaturizer af: featurizers) {
            handler.classifier = new KNNClassifier(af).setK(NEIGHBOUR_COUNT);
            System.out.println(handler.classifyTestData());
        }
    }

    /**
     * Method shows GUI, create main frame
     */
    private static void showGui() {
        JFrame win = new JFrame();
        win.setTitle("UIR - GUI");
        win.setPreferredSize(new Dimension(600, 500));
        makeGui(win);

        win.pack();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setLocationRelativeTo(null);
        win.setVisible(true);
    }

    /**
     * Method makes all GUI elements
     * @param win frame of GUI
     */
    private static void makeGui(JFrame win) {
        JPanel configPanel = makeRadioButtonsPanel();
        JPanel chooseFilesPanel = makeChooseFilesPanel();
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());

        JButton btnClassify = new JButton("Classify");
        btnClassify.addActionListener(e -> classify());

        middlePanel.add(chooseFilesPanel, BorderLayout.NORTH);
        middlePanel.add(inputText, BorderLayout.CENTER);

        win.setLayout(new BorderLayout());
        win.add(configPanel, BorderLayout.NORTH);
        win.add(middlePanel, BorderLayout.CENTER);
        win.add(btnClassify, BorderLayout.SOUTH);
    }

    /**
     * Method classifies test data or input sentence
     */
    private static void classify() {
        int fIndex, cIndex;
        JRadioButton fSelected = new JRadioButton();
        JRadioButton cSelected = new JRadioButton();

        // get references to clicked radio buttons
        for (JRadioButton btn: F_BUTTONS) if (btn.isSelected()) fSelected = btn;
        for (JRadioButton btn: C_BUTTONS) if (btn.isSelected()) cSelected = btn;

        // indices of radio buttons
        fIndex = F_BUTTONS.indexOf(fSelected);
        cIndex = C_BUTTONS.indexOf(cSelected);

        // prepare doc/s
        Parser p = new Parser(trainFile.getName(), true);

        // prepare featurizers
        AbstractFeaturizer[] af = new AbstractFeaturizer[]{
                new BagOfWords(p.documents), new DocumentFrequency(p.documents), new TF_IDF(p.documents)};

        // prepare classifiers
        AbstractClassifier[] ac = new AbstractClassifier[]{
                new NaiveBayesClassifier(af[fIndex]), new KNNClassifier(af[fIndex]).setK(NEIGHBOUR_COUNT)};

        // classify
        ClassificationHandler ch;
        if (inputText.getText().isBlank())
            ch = new ClassificationHandler(testFile.getName(), true);
        else ch = new ClassificationHandler(inputText.getText(), false);

        ch.classifier = ac[cIndex];

        // show result to user
        String result = ch.classifyTestData();
        if (ch.isFile) inputText.setText(result);
        else inputText.setText(inputText.getText() + "\n" + result);
    }

    /**
     * Method creates panel to choose input files
     * @return panel of GUI
     */
    private static JPanel makeChooseFilesPanel() {
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
                trainFile = fileChooser.getSelectedFile();
                trainDataLabel.setText("Training data file: " + trainFile.getName());
            }else{
                trainDataLabel.setText("No file chosen");
            }
        });

        chooseTestDataBtn.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(new File("S:/Git/KIV-UIR"));
            int option = fileChooser.showOpenDialog(chooseFilePanel);
            if(option == JFileChooser.APPROVE_OPTION){
                testFile = fileChooser.getSelectedFile();
                testDataLabel.setText("Training data file: " + testFile.getName());
            }else{
                testDataLabel.setText("No file chosen");
            }
        });

        return chooseFilePanel;
    }

    /**
     * Method creates panel to choose classifier and featurizer
     * @return panel of GUI
     */
    private static JPanel makeRadioButtonsPanel() {
        JPanel configPanel = new JPanel();
        JPanel panelLeft = new JPanel();
        JPanel panelRight = new JPanel();
        configPanel.setLayout(new BorderLayout());
        configPanel.add(panelLeft, BorderLayout.NORTH);
        configPanel.add(panelRight, BorderLayout.SOUTH);

        ButtonGroup chooseFeaturizer = new ButtonGroup();
        for (String s: F_NAMES) {
            JRadioButton tmp = new JRadioButton(s);
            chooseFeaturizer.add(tmp);
            panelLeft.add(tmp);
            F_BUTTONS.add(tmp);
        }

        ButtonGroup chooseClassifier = new ButtonGroup();
        for (String s: C_NAMES) {
            JRadioButton tmp = new JRadioButton(s);
            chooseClassifier.add(tmp);
            panelRight.add(tmp);
            C_BUTTONS.add(tmp);
        }

        return configPanel;
    }
}
