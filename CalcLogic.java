import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
public class CalcLogic implements ActionListener {
    private JTextField jtf;
    // 创建字符缓冲区域
    String content = "";
    String digitConcate = "";
    String lastCommand = "";
    Stack<String> computeStack = new Stack<String>();
    private StringBuffer sb = new StringBuffer();

    public CalcLogic(JTextField jtf) {
        this.jtf = jtf;
    }

    private boolean isDigit(String character){
        try{
            int a = Integer.parseInt(character);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isOperator(String character){
        return RegexChk.isPlusMinusMultiDiv(character);
    }

    private boolean isClear(String character){
        return RegexChk.isMatch("^[C]{1}$", character);
    }

    private boolean isEqual(String character){
        return RegexChk.isMatch("^[\\=]{1}$", character);
    }

    private void updateTextField(){
        StringBuilder buffer = new StringBuilder();
        for(String i:computeStack){
            buffer.append(i);
        }
        String displayInfo = buffer.toString();
        if (displayInfo.length() > 0){
            jtf.setText(displayInfo);
        }
        else{
            jtf.setText("0");
        }
    }

    private boolean compute(){
        String cachedOperand = "";
        String cachedOperator = "";
        String currentElem = "";
        while(!computeStack.empty()){
//            System.out.println(cachedOperand+cachedOperator+currentElem);
            currentElem = computeStack.pop();
            if(isDigit(currentElem)){
                if (cachedOperand.length() > 0 && cachedOperator.length() == 0){
                    return false;
                }
                else if (cachedOperand.length() > 0 && cachedOperator.length() > 0){
                    int result;
                    switch (cachedOperator){
                        case "+":
                            result = Integer.parseInt(currentElem) + Integer.parseInt(cachedOperand);
                            break;
                        case "-":
                            result = Integer.parseInt(currentElem) - Integer.parseInt(cachedOperand);
                            break;
                        case "*":
                            result = Integer.parseInt(currentElem) * Integer.parseInt(cachedOperand);
                            break;
                        case "/":
                            result = Integer.parseInt(currentElem) / Integer.parseInt(cachedOperand);
                            break;
                        default:
                            result = 0;
                            break;
                    }
                    cachedOperand = "";
                    cachedOperator = "";
//                    System.out.println(result);
                    computeStack.push(Integer.toString(result));
                }
                else{
                    cachedOperand = currentElem;
                }
            }
            else{
                if(cachedOperator.length() > 0){
                    return false;
                }
                else{
                    cachedOperator = currentElem;
                }
            }

        }
        computeStack.push(currentElem);
        updateTextField();
        return true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(isDigit(command)){
            if(lastCommand.equals("=")){
                computeStack.clear();
            }
            if(!computeStack.empty()){
                String stackTop = computeStack.peek();
                if (isDigit(stackTop)) {
                    String last = computeStack.pop();
                    computeStack.push(last+command);
                }
                else{
                    computeStack.push(command);
                }
            }
            else{
                computeStack.push(command);
            }
        }
        else if(isOperator(command)){
            if(!computeStack.empty()){
                if(computeStack.size() >= 3){
                    compute();
                }
                else{
                    String stackTop = computeStack.peek();
                    if (isOperator(stackTop)) {
                        computeStack.pop();
                    }
                }
                computeStack.push(command);
            }
        }
        else if(isClear(command)){
            computeStack.clear();
        }
        else{
            compute();
        }
        lastCommand = command;
        updateTextField();
    }
}
