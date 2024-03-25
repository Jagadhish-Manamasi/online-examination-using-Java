import java.util.*;

public class OnlineExaminationSystem {
    
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, String> userCredentials = new HashMap<>();
    private static boolean isLoggedIn = false;
    private static int totalQuestions = 5;
    private static int currentQuestion = 0;
    private static int score = 0;
    private static Timer timer;

    public static void main(String[] args) {
        while (true) {
            showMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    updateProfileAndPassword();
                    break;
                case 4:
                    startExam();
                    break;
                case 5:
                    closeSessionAndLogout();
                    break;
                case 6:
                    System.out.println("You are exited from the Exam ");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMainMenu() {
    	System.out.println("--------------------------------------------------------------");
        System.out.println("Welcome to Online Examination System , Please elect an option ");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Update Profile and Password");
        System.out.println("4. Start Exam");
        System.out.println("5. Close Session and Logout");
        System.out.println("6. Exit");
        System.out.println("--------------------------------------------------------------");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return choice;
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            isLoggedIn = true;
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void register() {
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();
        
        if (userCredentials.containsKey(newUsername)) {
            System.out.println("Username already exists. Please choose a different one.");
            return;
        }
        
        System.out.print("Enter password: ");
        String newPassword = scanner.nextLine();
        userCredentials.put(newUsername, newPassword);
        System.out.println("Registration successful.");
    }

    private static void updateProfileAndPassword() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }
        
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        userCredentials.put(getLoggedInUsername(), newPassword);
        System.out.println("Password updated successfully.");
    }

    private static void startExam() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }
        
        System.out.println("Starting exam...");
        currentQuestion = 0;
        score = 0;
        startTimer();
        askNextQuestion();
    }
    private static void askNextQuestion() {
        currentQuestion++;
        if (currentQuestion <= totalQuestions) {
            switch (currentQuestion) {
                case 1:
                    System.out.println("Question " + currentQuestion + ": What is 2 + 2?");
                    System.out.println("A) 3");
                    System.out.println("B) 4");
                    System.out.println("C) 5");
                    System.out.println("D) 6");
                    break;
                case 2:
                    System.out.println("Question " + currentQuestion + ": What is the capital of Italy?");
                    System.out.println("A) London");
                    System.out.println("B) Paris");
                    System.out.println("C) Rome");
                    System.out.println("D) Berlin");
                    break;
                case 3:
                    System.out.println("Question " + currentQuestion + ": Which planet is known as the Red Planet?");
                    System.out.println("A) Jupiter");
                    System.out.println("B) Saturn");
                    System.out.println("C) Mars");
                    System.out.println("D) Venus");
                    break;
                case 4:
                    System.out.println("Question " + currentQuestion + ": Who wrote India's National Anthem'?");
                    System.out.println("A) Ravindra Nath Tagore");
                    System.out.println("B) Bhagath Singh");
                    System.out.println("C) Mahathma Gandhi");
                    System.out.println("D) Jawaharlal Nehru");
                    break;
                case 5:
                    System.out.println("Question " + currentQuestion + ": What is the chemical symbol for water?");
                    System.out.println("A) H2O");
                    System.out.println("B) CO2");
                    System.out.println("C) CH4");
                    System.out.println("D) NaCl");
                    break;
                default:
                    break;
            }
            System.out.print("Your answer: ");
            String answer = scanner.nextLine().toUpperCase();
            
            switch (currentQuestion) {
                case 1:
                    if (answer.equals("B")) {
                        score++;
                    }
                    break;
                case 2:
                    if (answer.equals("C")) {
                        score++;
                    }
                    break;
                case 3:
                    if (answer.equals("C")) {
                        score++;
                    }
                    break;
                case 4:
                    if (answer.equals("A")) {
                        score++;
                    }
                    break;
                case 5:
                    if (answer.equals("A")) {
                        score++;
                    }
                    break;
                default:
                    break;
            }
            
            askNextQuestion();
        } else {
            submitExam();
        }
    }

    private static void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up! Submitting exam...");
                submitExam();
            }
        }, 50 * 1000); 
    }

    private static void submitExam() {
        if (timer != null) {
            timer.cancel();
        }
        System.out.println("Exam completed.");
        System.out.println("Your score: " + score + " out of " + totalQuestions);
        currentQuestion = 0;
        score = 0;
    }

    private static void closeSessionAndLogout() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }
        isLoggedIn = false;
        System.out.println("Session closed. Logged out.");
    }
    
    private static String getLoggedInUsername() {
        for (Map.Entry<String, String> entry : userCredentials.entrySet()) {
            if (entry.getValue().equals(getLoggedInPassword())) {
                return entry.getKey();
            }
        }
        return null;
    }
    
    private static String getLoggedInPassword() {
        for (Map.Entry<String, String> entry : userCredentials.entrySet()) {
            if (entry.getKey().equals(getLoggedInUsername())) {
                return entry.getValue();
            }
        }
        return null;
    }
}