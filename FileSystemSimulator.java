import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileSystemSimulator {

    static class Node {
        String name;
        List<Node> children;

        public Node(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }
    }

    static Node root;
    static Node current;

    public static void main(String[] args) {
        root = new Node("/");
        current = root;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Simple File System Simulator!");

        while (true) {
            System.out.println("Current directory: " + current.name);
            System.out.println("\nChoose an option:");
            System.out.println("1. Create Directory");
            System.out.println("2. Change Directory");
            System.out.println("3. List Directory");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createDirectory(scanner);
                    break;
                case 2:
                    changeDirectory(scanner);
                    break;
                case 3:
                    listDirectory();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createDirectory(Scanner scanner) {
        System.out.print("Enter directory name: ");
        String dirName = scanner.nextLine();
        Node newNode = new Node(dirName);
        current.children.add(newNode);
        System.out.println("Directory '" + dirName + "' created!");
    }

    private static void changeDirectory(Scanner scanner) {
        System.out.print("Enter directory name: ");
        String dirName = scanner.nextLine();
        Node target = findDirectory(current, dirName);
        if (target != null) {
            current = target;
            System.out.println("Current directory: " + current.name);
        } else {
            System.out.println("Directory not found.");
        }
    }

    private static Node findDirectory(Node node, String dirName) {
        if (node.name.equals(dirName)) {
            return node;
        }
        for (Node child : node.children) {
            Node found = findDirectory(child, dirName);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    private static void listDirectory() {
        System.out.println("Directory Contents:");
        if (current.children.isEmpty()) {
            System.out.println("-");
        } else {
            for (Node child : current.children) {
                System.out.println("- " + child.name);
            }
        }
    }
}
