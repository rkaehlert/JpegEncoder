package main.calculator;

import java.util.LinkedList;
import java.util.List;

public class CalculatorDeltaCategory {

    public static List<Integer> calculate(List<Integer> values) {
        List<Integer> output = new LinkedList<Integer>();
        for (Integer value : values) {
            output.add(UtilityCalculateBitLength.calculate(value));
        }
        return output;
    }

}
