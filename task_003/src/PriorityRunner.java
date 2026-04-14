//Завдання 3
//Демонстрація пріоритетів. Створити 2 класи PriorityRunner та PriorityThread. Запустити 3 потоки із пріоритетами
//(min, max, norm). За допомогою циклу for виведемо на екран значення від 1 до 50 і вкажемо, який саме потік цю
//операцію робить.
public class PriorityRunner {
    public static void main(String[] args) {
        PriorityThread min = new PriorityThread("MIN", Thread.MIN_PRIORITY);
        PriorityThread norm = new PriorityThread("NORM", Thread.NORM_PRIORITY);
        PriorityThread max = new PriorityThread("MAX", Thread.MAX_PRIORITY);

        min.start();
        norm.start();
        max.start();
    }
}
