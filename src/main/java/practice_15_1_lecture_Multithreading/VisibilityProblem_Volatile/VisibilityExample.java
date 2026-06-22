package practice_15_1_lecture_Multithreading.VisibilityProblem_Volatile;

class VisibilityProblem {
    private boolean running = true;

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

public class VisibilityExample {
    public static void main(String[] args) throws InterruptedException {
        VisibilityProblem problem = new VisibilityProblem();

        Thread thread = new Thread(problem::run);
        thread.start();

        Thread.sleep(1000); // main ждет 1 секунду
        problem.stop();      // пробуем остановить поток
    }
}