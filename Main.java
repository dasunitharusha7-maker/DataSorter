package sorter;

import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static int[] numbers = new int[0];

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Data Sorter: Sorting Algorithm Comparison Tool ---");
            System.out.println("1. Enter numbers manually");
            System.out.println("2. Generate random numbers");
            System.out.println("3. Perform Bubble Sort");
            System.out.println("4. Perform Merge Sort");
            System.out.println("5. Perform Quick Sort");
            System.out.println("6. Compare all algorithms");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> enterNumbers();
                case 2 -> generateRandom();
                case 3 -> runAlgorithm("Bubble");
                case 4 -> runAlgorithm("Merge");
                case 5 -> runAlgorithm("Quick");
                case 6 -> compareAll();
                case 7 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 7);
    }

    private static void enterNumbers() {
        System.out.print("Enter numbers separated by spaces: ");
        sc.nextLine(); // clear buffer
        String[] input = sc.nextLine().split(" ");
        numbers = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }
        System.out.println("Numbers saved!");
    }

    private static void generateRandom() {
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        Random rand = new Random();
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = rand.nextInt(100);
        }
        System.out.println("Random dataset generated!");
        System.out.println(Arrays.toString(numbers));
    }

    private static void runAlgorithm(String type) {
        if (numbers.length == 0) {
            System.out.println("No data! Please enter or generate numbers first.");
            return;
        }
        int[] copy = Arrays.copyOf(numbers, numbers.length);
        long start = System.nanoTime();
        long steps = 0;

        switch (type) {
            case "Bubble" -> steps = BubbleSort.sort(copy);
            case "Merge" -> steps = MergeSort.sort(copy);
            case "Quick" -> steps = QuickSort.sort(copy);
        }

        long end = System.nanoTime();
        System.out.println(type + " Sort Result: " + Arrays.toString(copy));
        System.out.println("Steps: " + steps + " | Time: " + (end - start) / 1_000_000.0 + " ms");
    }

    private static void compareAll() {
        if (numbers.length == 0) {
            System.out.println("No data available. Enter or generate numbers first.");
            return;
        }

        System.out.println("\n--- Performance Comparison ---");
        System.out.printf("%-12s %-15s %-15s%n", "Algorithm", "Steps", "Time (ms)");
        System.out.println("------------------------------------------");

        compare("Bubble", BubbleSort::sort);
        compare("Merge", MergeSort::sort);
        compare("Quick", QuickSort::sort);
    }

    private static void compare(String name, SortFunction func) {
        int[] copy = Arrays.copyOf(numbers, numbers.length);
        long start = System.nanoTime();
        long steps = func.sort(copy);
        long end = System.nanoTime();
        System.out.printf("%-12s %-15d %-15.3f%n", name, steps, (end - start) / 1_000_000.0);
    }

    interface SortFunction {
        long sort(int[] arr);
    }
}

