import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainLayout {
    public static void main(String[] args) {
        JFrame mf = new JFrame("Image + Story Generation With ChatGPT & DALLE");
        mf.setSize(1000, 800);
        mf.setLayout(new BorderLayout());
//        mf.add(new JLabel("???"), BorderLayout.WEST);
        JPanel top = new JPanel(new GridLayout(5, 1));
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel(" Your Prompt Here: "), BorderLayout.WEST);
        JTextField input = new JTextField();
        p.add(input, BorderLayout.CENTER);
        top.add(p);
//        JPanel g = new JPanel(new GridLayout(3,1));
        JPanel g = new JPanel(new BorderLayout());
        g.add(new JLabel(" Genre of Story (Leave Blank For Default): "), BorderLayout.WEST);
        JTextField field = new JTextField();
        field.setText("");
        g.add(field, BorderLayout.CENTER);
        top.add(g);
//        top.add(, BorderLayout.SOUTH);
        JButton button = new JButton("Go!");
        top.add(button);
        mf.add(top, BorderLayout.NORTH);

        JTextArea caption = new JTextArea();
        caption.setEditable(false);
        caption.setLineWrap(true);
        caption.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(caption);
//        scroll.setMinimumSize(new Dimension(1000, 200));
        mf.add(scroll, BorderLayout.SOUTH);
        final JPanel panel = new JPanel();
        JLabel image = new JLabel();
        panel.add(image);
        mf.add(panel, BorderLayout.CENTER);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prompt = input.getText();
                panel.removeAll();
                panel.add(new JLabel(DALLE.get(prompt)));
                try {
                    caption.setText(Main.chatGPT(prompt, field.getText() + " "));
//                    for(int i = 0; i<20; i++) caption.append("\n");
                }
                catch(Exception e2) {
                    e2.printStackTrace();
                }
//                scroll.setMinimumSize(new Dimension(1000, 320));
                mf.pack();
                mf.setSize(1000, 800);

            }
        });

        mf.setVisible(true);

    }
}
