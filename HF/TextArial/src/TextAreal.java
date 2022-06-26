import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TextAreal {

    JTextArea text;
    JCheckBox checkBox;

    String[] listEntries = {"A", "B", "C", "D"};
    JList list;


    public static void main(String[] args) {
        TextAreal gui = new TextAreal();
        gui.go();
    }
    public void go() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton button = new JButton("Just click it");

        list = new JList(listEntries);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(new ValueListListener());

        checkBox = new JCheckBox("Check");
        checkBox.addItemListener(new CheckBoxListener());
        checkBox.setSelected(false);

        button.addActionListener(new ButtonListener());

        text = new JTextArea(10, 20);
        text.setLineWrap(true);

        JScrollPane scroller = new JScrollPane(text);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scroller);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.getContentPane().add(BorderLayout.WEST, checkBox);
        frame.getContentPane().add(BorderLayout.EAST, scrollPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 300);
        frame.setVisible(true);


    }
    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            DailyAdviceClient client = new DailyAdviceClient();
            client.go();
            text.setText("");
            text.append(client.advice);
        }
    }

    public class CheckBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent ev) {
            String onOrOff = "off";
            if (checkBox.isSelected()) onOrOff = "on";
            text.append("Check box is " + onOrOff +"\n");
        }
    }
    public class ValueListListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent lse) {
            if(!lse.getValueIsAdjusting()) {
                String selection = (String) list.getSelectedValue();
                text.append(selection +"\n");
            }
        }

    }

}
