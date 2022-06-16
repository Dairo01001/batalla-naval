package utils;

public class CheckInputs {

    public static boolean isNumber(String in) {

        if (in.isEmpty()) {
            return false;
        }

        char[] input = in.toCharArray();

        for (int i = 0; i < input.length; i++) {
            if (input[i] < '0' || input[i] > '9') {
                return false;
            }
        }

        return true;
    }
}
