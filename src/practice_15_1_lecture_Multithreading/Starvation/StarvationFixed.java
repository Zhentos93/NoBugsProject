package practice_15_1_lecture_Multithreading.Starvation;

class StarvationFixed {
    public static void main(String[] args) {
        Runnable highPriorityTask = () -> {
            while (true) {
                System.out.println("Высокий приоритет выполняется");
                Thread.yield(); // уступаем CPU другим потокам
            }
        };

        Runnable lowPriorityTask = () -> {
            while (true) {
                System.out.println("Низкий приоритет выполняется");
                Thread.yield(); // уступаем CPU другим потокам
            }
        };

        Thread t1 = new Thread(highPriorityTask);
        Thread t2 = new Thread(lowPriorityTask);

        // Можно уравнять приоритеты
        t1.setPriority(Thread.NORM_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);

        t1.start();
        t2.start();
    }
}