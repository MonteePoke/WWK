package kurlyk;

public class TestMain {
    public static void main(String[] args) {
//        String question = " Законом   Амдалла ";
//        String answer = "закон амдалла";
//
//        String stemQuestion = StemmerPorterRU.stemSentence(question);
//        String stemAnswer = StemmerPorterRU.stemSentence(answer);
//
//        System.out.println(stemQuestion);
//        System.out.println(stemAnswer);
//        System.out.println(stemQuestion.equals(stemAnswer));

        System.out.println(Double.parseDouble("-0.0"));
        System.out.println(Double.parseDouble("-0."));
        System.out.println(Double.parseDouble("-.0"));
//        System.out.println(Double.parseDouble("-."));

        System.out.println(Double.parseDouble("0.0"));
        System.out.println(Double.parseDouble("0."));
        System.out.println(Double.parseDouble(".0"));
//        System.out.println(Double.parseDouble("."));
    }
}
