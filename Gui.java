import java.io.IOException;

public class Gui {
    public static void main(String[] args) throws IOException {
        LanguageModel model = new LanguageModel();
        String res = model.generateResponse("On the weekends");
        System.out.println(res);
    }
}