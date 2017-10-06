package itcs4180.hw3;

import android.util.Log;

import java.io.Serializable;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by Aileen on 10/5/2017.
 */

public class Question implements Serializable {

    int questionNumber;
    String question;
    String imageURL;
    String[] answerChoices;
    int correctAnswer;

    ///////////////////////////////////////////////////////////////////////////
    // Constructors
    ///////////////////////////////////////////////////////////////////////////
    public Question(int questionNumber, String question, String imageURL, String[] answerChoices, int correctAnswer) {
        this.questionNumber = questionNumber;
        this.question = question;
        this.imageURL = imageURL;
        this.answerChoices = answerChoices;
        this.correctAnswer = correctAnswer;
    }

    public Question(String data){
        String line = data;

        StringTokenizer st = new StringTokenizer(data, ";");
        int size = st.countTokens();

        questionNumber = Integer.parseInt(st.nextToken());
        //Log.d("test", "Number: " + questionNumber);

        question = st.nextToken();
        //Log.d("test", "Question: " + question);

        int num;
        if(line.contains(";;")){
            imageURL = "";
            //Log.d("test", "Image: " + imageURL);
            num = size - 3;
        }
        else {
            imageURL = st.nextToken();
            //Log.d("test", "Image: " + imageURL);
            num = size - 4;
        }

        answerChoices = new String[num];
        for(int i = 0; i < answerChoices.length; i++){
            answerChoices[i] = st.nextToken();
            //Log.d("test", "Choice " + i + ": " + answerChoices[i]);
        }

        correctAnswer = Integer.parseInt(st.nextToken());

        /*
        String results[] = data.split(";");
        int size = data.length();

        // Question # and Question String
        questionNumber = Integer.parseInt(results[0]);
        question = results[1];

        // Image URL
        if(results[2] != null) {
            imageURL = results[2];
        }
        else imageURL = "";

        // Correct Answer Index
        correctAnswer = Integer.parseInt(results[size-1]);

        // Array of Answer Choices
        answerChoices = new String[size - 4];
        for(int i = 3; i < size - 1; i++){
            answerChoices[i-3] = results[i];
        }
        */

    }

    ///////////////////////////////////////////////////////////////////////////
    // toString
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "Question{" +
                "questionNumber=" + questionNumber +
                ", question='" + question + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", answerChoices=" + Arrays.toString(answerChoices) +
                ", correctAnswer=" + correctAnswer +
                '}';
    }

    ///////////////////////////////////////////////////////////////////////////
    // Getters and Setters
    ///////////////////////////////////////////////////////////////////////////

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String[] getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(String[] answerChoices) {
        this.answerChoices = answerChoices;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
