package Model;

public class QuestionSA extends Question{

    QuestionSA(final String theQuestion, final String theAnswer) {
        super(theQuestion, theAnswer);
        super.setQuestionType("ShortAnswer");
    }
}
