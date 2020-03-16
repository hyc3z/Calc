import java.util.regex.*;

public class RegexChk {

    public static boolean isMatch(String regex, String original){
        if (original == null || original.trim().equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(original);
        return isNum.matches();
    }

    public static boolean isPositiveInteger(String original) {
        return isMatch("^\\+{0,1}[1-9]\\d*", original);
    }

    public static boolean isNegativeInteger(String original) {
        return isMatch("^-[1-9]\\d*", original);
    }

    public static boolean isZero(String original) {
        return isMatch("^0{1}$", original);
    }

    public static boolean isPlusMinusMultiDiv(String original) {
        return isMatch("^[\\+,\\-,\\*,\\/]{1}$", original);
    }


    public static boolean isInteger(String original) {
        return isZero(original) || isPositiveInteger(original) || isNegativeInteger(original);
    }

    public static boolean isPhoneNumber(String original) {
        return isMatch("^\\d{11}$", original);
    }
    
    public static boolean isEmail(String original){
        return isMatch("[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$", original);
    }

    public static boolean isZipcode(String original){
        return isMatch("^[1-9][0-9]{5}$", original);
    }

    public static boolean isIdNumber(String original){
        return isMatch("^\\d{15}$|^\\d{18}$", original);
    }


    public static boolean isPositiveDecimal(String original){
        return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", original);
    }

    public static boolean isNegativeDecimal(String original){
        return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", original);
    }

    public static boolean isDecimal(String original){
        return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", original);
    }


}
