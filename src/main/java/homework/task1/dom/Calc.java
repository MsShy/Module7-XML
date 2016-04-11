package homework.task1.dom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Calc {
    public static int getCount(String person, Map<String, ArrayList<String>> persons) {
        ArrayList<String> list = persons.get(person);
        return list.size();
    }

    public static int gatAverage(String persona, Map<String, ArrayList<String>> persons) {
        int sum = 0;
        final List<String> phrases = persons.get(persona);
        if (phrases != null) {
            int count = getCount(persona, persons);
            for (String phrase : phrases) {
                sum += Pattern.compile("[ ]").split(phrase).length;
            }
            return sum / count;
        } else {
            return -1;
        }
    }
}
