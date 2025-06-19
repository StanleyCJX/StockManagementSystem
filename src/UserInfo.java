import java.util.Scanner;

public class UserInfo {
    private String name;
    private String userID;

    public void getNameAndGenerateUserID() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter full name: ");
        name = scanner.nextLine();
        
        if (name == null || name.trim().isEmpty()) {
            userID = "guest";
            System.out.println("Warning: No name provided, defaulting to 'guest'");
        } else if (name.contains(" ")) {
            String[] nameParts = name.split(" ");
            if (nameParts.length < 2) {
                userID = "guest";
            } else {
                String firstNameInitial = nameParts[0].substring(0, 1).toUpperCase();
                String surname = nameParts[nameParts.length - 1];
                userID = firstNameInitial + surname;
            }
        } else {
            userID = "guest";
        }
        scanner.close();
        System.out.println("User ID: " + userID);
    }

    public String getUserID() {
        return userID;
    }

    public static void main(String[] args) {
        UserInfo user = new UserInfo();
        user.getNameAndGenerateUserID();
    }
}