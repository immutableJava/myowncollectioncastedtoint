package pro.sky.java.course2.owncollectionwithint;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
//        StringListImpl stringList = new StringListImpl();
//        stringList.add("hello");
//        stringList.add("hello1");
//        stringList.add("hello2");
//        stringList.add("hello3");
//        stringList.remove(1);
//        stringList.remove("hello2");
//        String[] strings = stringList.toArray();
//        System.out.println(Arrays.toString(strings));
        Set<Question> questions = new HashSet<>();
        questions.add(new Question("q1", "a1"));
        questions.add(new Question("q2", "a2"));
        questions.add(new Question("q3", "a3"));
        int randomDigit = new Random().nextInt(questions.size());
        Iterator<Question> i = questions.iterator();
        int counter = 0;
        while (i.hasNext()) {
            if (counter == randomDigit) {
                System.out.println(i.next());
                return;
            }
            i.next();
            counter++;
        }
    }

}
