package org.texastorque.torquelib.util;

import java.util.Vector;

public class TorqueUtil {

    public static double sqrtHoldSign(double val) {
        return Math.sqrt(Math.abs(val)) * ((val > 0) ? 1 : -1);
    }

    public static double maxOne(double value) {
        if (value > 1) {
            return 1;
        } else if (value < -1) {
            return -1;
        } else {
            return value;
        }
    }

    public static String[] split(String input, String delimiter) {
        Vector node = new Vector();
        int index = input.indexOf(delimiter);

        while (index >= 0) {
            node.addElement(input.substring(0, index));
            input = input.substring(index + delimiter.length());
            index = input.indexOf(delimiter);
        }

        node.addElement(input);

        String[] retString = new String[node.size()];
        for (int i = 0; i < node.size(); ++i) {
            retString[i] = (String) node.elementAt(i);
        }

        return retString;
    }
}
