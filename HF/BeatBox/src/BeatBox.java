import javax.imageio.IIOException;
import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.ArrayList;

public class BeatBox {
    JPanel mainPanel;
    ArrayList<JCheckBox> checkBoxesList;
    Sequencer sequencer;
    Sequence sequence;
    Track track;
    JFrame frame;
    JCheckBox c;
    String labelTempo;
    JLabel jLabelTempo;
    float tempo;
    JTextArea textIn;
    JTextArea textOut;

    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
            "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap",
            "Low-mid Tom", "High Agogo", "Open Hi Conga"};
    int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main(String[] args) {
        new BeatBox().buildGui();
    }

    public void buildGui() {
        frame = new JFrame("Cyber BeatBox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        checkBoxesList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(new MyStopListener());
        buttonBox.add(stop);

        JButton upTempo = new JButton("Tempo Up");
        upTempo.addActionListener(new MyUpTempoListener());
        buttonBox.add(upTempo);

        JButton downTempo = new JButton("Tempo Down");
        downTempo.addActionListener(new MyDownTempoListener());
        buttonBox.add(downTempo);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ResetCheckBoxListener());
        buttonBox.add(resetButton);

        jLabelTempo = new JLabel();

        buttonBox.add(jLabelTempo);

        textIn = new JTextArea(20,50);
        textIn.setLineWrap(true);
        textIn.setEditable(false);
        textOut = new JTextArea(5, 50);
        textOut.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textIn);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        .add(scrollPane);
        JScrollPane scrollPaneAria = new JScrollPane(textOut);
        scrollPaneAria.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneAria.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPaneAria);

        JButton button = new JButton("Отправить");
        button.addActionListener(new SendListener());


        Box nameBox = new Box((BoxLayout.Y_AXIS));
        for (int i = 0; i < 16; i++) {
            nameBox.add(new Label(instrumentNames[i]));
        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        frame.getContentPane().add(background);

        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(1);
        grid.setHgap(2);
        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);

        for (int i = 0; i < 256; i++) {
            c = new JCheckBox();
            c.setSelected(false);
            c.addItemListener(new CheckBoxListener());
            checkBoxesList.add(c);
            mainPanel.add(c);
        }

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        loadMenuItem.addActionListener(new LoadMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);

        setUpMidi();

        frame.setBounds(50, 50, 300, 300);
        frame.pack();
        frame.setVisible(true);

    }

    public void setUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
            tempo = sequencer.getTempoInBPM();
            updateTempoInfo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildTrackAndStart() {
        int[] trackList = null;

        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i = 0; i < 16; i++) {
            trackList = new int[16];

            int key = instruments[i];

            for (int j = 0; j < 16; j++) {
                JCheckBox jc = ((JCheckBox) checkBoxesList.get(j + 16 * i));
                if (jc.isSelected()) {
                    trackList[j] = key;
                } else {
                    trackList[j] = 0;
                }
            }

            makeTrack(trackList);

            track.add(makeEvent(176, 1, 127, 0, 16));
        }

        track.add(makeEvent(192, 9, 1, 0, 15));
        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
            sequencer.addControllerEventListener(new MainPanel(), new int[]{127});
            updateTempoInfo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateTempoInfo() {
        labelTempo = String.format("%.2f", sequencer.getTempoInBPM());
        jLabelTempo.setText("Temp: " + labelTempo);
    }

    public class MyStartListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            buildTrackAndStart();
            updateTempoInfo();
        }
    }

    public class MyStopListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            sequencer.stop();
        }
    }

    public class MyUpTempoListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            sequencer.setTempoInBPM(sequencer.getTempoInBPM() + 5);
            tempo = sequencer.getTempoInBPM();
            updateTempoInfo();
        }
    }

    public class MyDownTempoListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            sequencer.setTempoInBPM(sequencer.getTempoInBPM() - 5);
            tempo = sequencer.getTempoInBPM();
            updateTempoInfo();
        }
    }

    public class ResetCheckBoxListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            for (JCheckBox ch : checkBoxesList) {
                ch.setSelected(false);
                c = ch;
                sequencer.stop();
            }
            sequencer.setTempoInBPM(120);
            updateTempoInfo();
        }
    }

    public void makeTrack(int[] list) {
        for (int i = 0; i < 16; i++) {
            int key = list[i];
            if (key != 0) {
                track.add(makeEvent(144, 9, key, 100, i));
                track.add(makeEvent(128, 9, key, 100, i + 1));
            }
        }
    }

    public MidiEvent makeEvent(int comd, int chan, int one, int tow, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, tow);
            event = new MidiEvent(a, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }


        class MainPanel implements ControllerEventListener {
            public void controlChange(ShortMessage event) {
                System.out.println(sequencer.getTickPosition());
            }

        }
    /*
        public void tikTak() {
            while (sequencer.isRunning()) {
                System.out.println(sequencer.getTickPosition());
            }
        }
     */
    public class LoadMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
        }
    }

    private void loadFile(File file) {
        boolean[] checkBoxSaveList = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            checkBoxSaveList = (boolean[]) objectInputStream.readObject();

        } catch (Exception ex) {
            System.out.println("couldn't read the file");
            ex.printStackTrace();
        }
        for (int i = 0; i < 256; i++) {
            JCheckBox check = (JCheckBox) checkBoxesList.get(i);
            if (checkBoxSaveList[i]) {
                check.setSelected(true);
            } else {
                check.setSelected(false);
            }
        }
        sequencer.stop();
        buildTrackAndStart();
    }

    public class SaveMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    private void saveFile(File file) {
        boolean[] checkBoxSaveList = new boolean[256];
        for (int i = 0; i < 256; i++) {
            JCheckBox check = (JCheckBox) checkBoxesList.get(i);
            if (check.isSelected()) {
                checkBoxSaveList[i] = true;
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(checkBoxSaveList);
        } catch (Exception ex) {
            System.out.println("couldn't create the file");
            ex.printStackTrace();
        }
    }

    public class CheckBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent ev) {
            if (sequencer.isRunning()) {
                buildTrackAndStart();
            }
        }
    }
}

