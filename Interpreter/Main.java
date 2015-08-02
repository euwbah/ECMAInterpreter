import Parser.*;

public class Main {
    public static void main(String[] args) {
        //Testing... testing...
        String randomCode =
                "asdf123\n" +
                "a";
        CodePosition pos = new CodePosition();
        pos.increment(randomCode);

        System.out.print(pos.toString());
    }
}
