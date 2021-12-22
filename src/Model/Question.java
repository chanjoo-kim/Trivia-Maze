package Model;

public abstract class Question {
    private String myQuestionType;
    private String myAnswerString;
    private String myQuestionString;

    Question (final String theQuestion, final String theAnswer){
        myAnswerString = theAnswer;
        myQuestionString = theQuestion;
    }

    public String getQuestionType() {
        return myQuestionType;
    }

    public void setQuestionType(final String theQuestionType) {
        myQuestionType = theQuestionType;
    }

    public String getAnswerString() {
        return myAnswerString;
    }

    public String getQuestionString() {
        return myQuestionString;
    }


}
