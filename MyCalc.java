import javax.swing.*;
import java.awt.*;

public class MyCalc extends JFrame {

    GridLayout buttonLayout;
    public MyCalc(String s) {
        init(s);
    }

    private JTextField diaplayPanel(){
        JPanel topPanel = new JPanel();
//        topPanel.setBounds(0,0,309,5);
        JTextField jtf = new JTextField(16);
        // 设置文件右边往左边输出
        jtf.setHorizontalAlignment(JTextField.RIGHT);
        jtf.setForeground(Color.red);
        jtf.setFont(new Font("SansSerif",Font.PLAIN,20));
        jtf.setEditable(false);
        jtf.setText("0");
        topPanel.add(jtf);
        this.add(topPanel);
        return jtf;
    }

    private void buttonArea(JTextField jtf){
        JPanel bottomPanel = new JPanel();
//        bottomPanel.setLocation(0,50);
        bottomPanel.setLayout(new GridLayout(4,4,1,1));

        String[] buttonValue = {
                "0","1","2","3",
                "4","5","6","7",
                "8","9","+","-",
                "*","/","C","="
        };
        // 监听器不能重复创建
        CalcLogic cl = new CalcLogic(jtf);
        for (int i = 0; i < buttonValue.length; i++) {
            JButton button = new JButton(buttonValue[i]);
            button.setBorder(BorderFactory.createRaisedBevelBorder());
            button.setFont(new Font("宋体", 0, 25));
            button.setForeground(Color.blue);
            bottomPanel.add(button);
            button.addActionListener(cl);
        }

        this.add(bottomPanel);
    }

    void init(String s) {
        setTitle(s);
        Container c=getContentPane();
        c.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        buttonArea(diaplayPanel());
    }
}
