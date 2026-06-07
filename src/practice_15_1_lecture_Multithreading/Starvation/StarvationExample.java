package practice_15_1_lecture_Multithreading.Starvation;

class StarvationExample {
    public static void main(String[] args) {
        Runnable highPriorityTask = () -> {
            while (true) {
                System.out.println("Высокий приоритет выполняется");
            }
        };

        Runnable lowPriorityTask = () -> {
            while (true) {
                System.out.println("Низкий приоритет выполняется");
            }
        };

        Thread t1 = new Thread(highPriorityTask);
        Thread t2 = new Thread(lowPriorityTask);

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
    }
}
