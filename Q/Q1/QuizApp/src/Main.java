import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner QuestionScanner = new Scanner (new File("Q\\Q1\\QuizApp\\src\\questions.txt"));
        ArrayList<Question> questionList = new ArrayList<>();

        while(QuestionScanner.hasNextLine())
        {
            String line = QuestionScanner.nextLine();
            String [] parts = line.split(",");
            Question nextQuestion = new Question(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
            questionList.add(nextQuestion);
        }
        for(Question q : questionList)
        {
            System.out.println(q.Question);
            System.out.println(q.Answer1);
            System.out.println(q.Answer2);
            System.out.println(q.Answer3);
            System.out.println(q.Answer4);
            System.out.println(q.CorrectAnswer);
        }

        System.out.println("Working Directory = " + System.getProperty("user.dir"));


    }
}