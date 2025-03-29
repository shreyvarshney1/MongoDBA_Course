package project;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.Scanner;

public class studentRecordLinkedListLogin {

    private static final String DATABASE_NAME = "studentDB";
    private static final String COLLECTION_NAME = "students";
    private static MongoCollection<Document> studentCollection;

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        studentCollection = database.getCollection(COLLECTION_NAME);

        Scanner input = new Scanner(System.in);

        if (loginUser(input)) {
            int option;
            do {
                menu();
                option = input.nextInt();
                input.nextLine();

                switch (option) {
                    case 1:
                        addStudent(input);
                        break;
                    case 2:
                        deleteStudent(input);
                        break;
                    case 3:
                        updateStudent(input);
                        break;
                    case 4:
                        searchStudent(input);
                        break;
                    case 5:
                        displayStudents();
                        break;
                    case 9:
                        System.out.println("Thank you for using the program. Goodbye!");
                        mongoClient.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            } while (option != 9);
        } else {
            System.out.println("Login failed");
        }
    }

    private static boolean loginUser(Scanner input) {
        System.out.print("Enter Username: ");
        String name = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        Document user = studentCollection.find(Filters.and(
                Filters.eq("username", name),
                Filters.eq("password", password)
        )).first();

        return user != null;
    }

    public static void menu() {
        System.out.println("MENU");
        System.out.println("1: Add Student");
        System.out.println("2: Delete Student");
        System.out.println("3: Update Student");
        System.out.println("4: Search Student");
        System.out.println("5: Display Students");
        System.out.println("9: Exit program");
        System.out.print("Enter your selection: ");
    }

    private static void addStudent(Scanner input) {
        System.out.print("Enter Student ID: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Enter Student Name: ");
        String name = input.nextLine();

        System.out.print("Enter Contact Number: ");
        int contact = input.nextInt();
        input.nextLine();

        System.out.print("Enter Username: ");
        String username = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        Document student = new Document("id", id)
                .append("name", name)
                .append("contact", contact)
                .append("username", username)
                .append("password", password);

        studentCollection.insertOne(student);
        System.out.println("Student added successfully.");
    }

    private static void deleteStudent(Scanner input) {
        System.out.print("Enter Student ID to delete: ");
        int id = input.nextInt();
        Document student = studentCollection.find(Filters.eq("id", id)).first();
        studentCollection.deleteOne(Filters.eq("id", id));
        if (student != null) {
            System.out.println("Student deleted successfully");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void updateStudent(Scanner input) {
        System.out.print("Enter Student ID to update: ");
        int id = input.nextInt();
        input.nextLine();
        Document student = studentCollection.find(Filters.eq("id", id)).first();
        System.out.print("Enter new Name: ");
        String name = input.nextLine();

        System.out.print("Enter new Contact Number: ");
        int contact = input.nextInt();

        studentCollection.updateOne(Filters.eq("id", id),
                new Document("$set", new Document("name", name).append("contact", contact)));
        if (student != null) {
            System.out.println("Student updated successfully");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void searchStudent(Scanner input) {
        System.out.print("Enter Student ID to search: ");
        int id = input.nextInt();
        Document student = studentCollection.find(Filters.eq("id", id)).first();
        if (student != null) {
            System.out.println("Student found: " + student.toJson());
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void displayStudents() {
        FindIterable<Document> students = studentCollection.find();
        for (Document student : students) {
            System.out.println(student.toJson());
        }
    }
}
