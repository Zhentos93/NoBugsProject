package practice_15_1_lecture_Multithreading.VisibilityProblem_Volatile;

class VisibilityFixed {
    private volatile boolean running = true;

    public void stop() {
        running = false; // ставим флаг false
    }

    public void run() {
        while (running) {
            // бесконечный цикл, пока running == true
        }
        System.out.println("Поток завершился");
    }
}

public class VolatileExample {
    public static void main(String[] args) throws InterruptedException {
        VisibilityFixed fixed = new VisibilityFixed();

        Thread thread = new Thread(fixed::run);
        thread.start();

        Thread.sleep(1000); // main ждет 1 секунду
        fixed.stop();      // пробуем остановить поток
    }
}
