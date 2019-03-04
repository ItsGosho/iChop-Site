package ichop;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Test {

    private String name;
    private int age;
}


class asd {
    public static void main(String[] args) {
        Test test = new Test();
        test.setName("asd");
        System.out.println(test.getName());
    }
}