import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Question {
    String questionText;
    List<String> options;
    int correctOption;

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }
}

class Quiz {
    private Map<String, List<Question>> quizzes = new HashMap<>();

    public void createQuiz(String quizName) {
        quizzes.putIfAbsent(quizName, new ArrayList<>());
        System.out.println("Quiz '" + quizName + "' created.");
    }

    public void addQuestion(String quizName, Question question) {
        List<Question> questions = quizzes.get(quizName);
        if (questions != null) {
            questions.add(question);
            System.out.println("Question added.");
        } else {
            System.out.println("No quiz found with the name '" + quizName + "'.");
        }
    }

    public void takeQuiz(String quizName) {
        List<Question> questions = quizzes.get(quizName);
        if (questions == null) {
            System.out.println("No quiz found with the name '" + quizName + "'.");
            return;
        }

        int score = 0;
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + q.questionText);
            for (int j = 0; j < q.options.size(); j++) {
                System.out.println("  " + (j + 1) + ". " + q.options.get(j));
            }
            System.out.print("Your answer (1-4): ");
            if (scanner.nextInt() == q.correctOption) score++;
        }
        System.out.println("You scored " + score + "/" + questions.size() + ".");
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nQuiz Generator");
            System.out.println("1. Create a new quiz");
            System.out.println("2. Add a question to a quiz");
            System.out.println("3. Take a quiz");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter quiz name: ");
                    createQuiz(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter quiz name: ");
                    String quizName = scanner.nextLine();
                    System.out.print("Enter the question: ");
                    String questionText = scanner.nextLine();
                    List<String> options = new ArrayList<>();
                    for (int i = 0; i < 4; i++) {
                        System.out.print("Enter option " + (i + 1) + ": ");
                        options.add(scanner.nextLine());
                    }
                    System.out.print("Enter the number of the correct option (1-4): ");
                    addQuestion(quizName, new Question(questionText, options, scanner.nextInt()));
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.print("Enter quiz name: ");
                    takeQuiz(scanner.nextLine());
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

public class QuizGenerator {
    public static void main(String[] args) {
        new Quiz().displayMenu();
    }
}