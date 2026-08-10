package сode_reading.JavaCodeReading;

import java.lang.reflect.Field;

class User4 {
    private String name = "Alex";
}

public class A38 {
    public static void main(String[] args)
            throws Exception {

        User4 user4 = new User4();

        Field field =
                User4.class.getDeclaredField("name");

        field.setAccessible(true);

        System.out.println(field.get(user4));
    }
}
