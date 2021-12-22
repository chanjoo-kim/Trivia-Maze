package Model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class QuestionPool {
    private static QuestionPool mySingleQuestionPoolInstance = new QuestionPool();
    
    /** Where the questions will be placed and retrieved from */
    ArrayList<Question> myQuestions = new ArrayList<>();

    /**
     * Constructor for the QuestionPool. Should only be instantiated when the maze is being constructed. As of right now
     * questions are being added by hard coding them in. This is where the database should be created, accessed and
     * closed. The database needs to supply the myQuestions list with Question objects, that means the database should
     * build Questions by retrieving question Strings and answer Strings and then add these Questions to the list. I
     * assume this can be done with a for loop of some type.
     */
    private QuestionPool(){
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl("jdbc:sqlite:trivia_questions.db");
        String query = "SELECT * FROM questions";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String questionType = resultSet.getString("question_type");

                switch (questionType) {
                    case "MC" :
                        myQuestions.add(new QuestionMC(resultSet.getString("question_name"),
                                resultSet.getString("question_answer"),
                                resultSet.getString("question_MCanswerOne"),
                                resultSet.getString("question_MCanswerTwo")));
                        break;

                    case "T/F" :
                        myQuestions.add(new QuestionTF(resultSet.getString("question_name"),
                                resultSet.getString("question_answer")));
                        break;

                    case "SA" :
                        myQuestions.add(new QuestionSA(resultSet.getString("question_name"),
                                resultSet.getString("question_answer")));
                        break;

                    default:
                        break;
                }
            }
        } catch (Exception theException) {
            theException.printStackTrace();
            System.exit(0);
        }
    }

    public static QuestionPool getInstance() {
        return mySingleQuestionPoolInstance;
    }
    /**
     * This Method randomly pulls a question from the QuestionPool and then removes that Question from the QuestionPool.
     * This make it so that the questions associated with doors are random each time a new game is started and that
     * there are no duplicate Questions.
     *
     * @return The question that is going to be attached to the door.
     */
    Question getQuestionFromPool() {
        Random randGen = new Random();
        int index = randGen.nextInt(myQuestions.size());
        Question returnedQuestion = myQuestions.get(index);
        myQuestions.remove(index);
        return returnedQuestion;
    }
}
