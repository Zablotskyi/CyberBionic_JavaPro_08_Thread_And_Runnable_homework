//Завдання 5
//Створити 2 класи. Реалізувати взаємне блокування цих класів.
public class Main {
    public static void main(String[] args) {
        FirstClass firstClass = new FirstClass();
        SecondClass secondClass = new SecondClass();

        Thread firstThread = new Thread(() -> firstClass.firstAction(secondClass), "Перший потік");
        Thread secondThread = new Thread(() -> secondClass.secondAction(firstClass), "Другий потік");

        firstThread.start();
        secondThread.start();

        System.out.println("Програма має зависнути...");
    }
}
