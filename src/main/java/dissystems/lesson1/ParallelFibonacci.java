package dissystems.lesson1;

/**
 * Created by apaud on 3/28/18.
 */
public class ParallelFibonacci extends Thread{
    int n;
    int result;

    public ParallelFibonacci(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        if((n==0) || (n==1)) result = 1;
        else{
            ParallelFibonacci f1 = new ParallelFibonacci(n-1);
            ParallelFibonacci f2 = new ParallelFibonacci(n-2);
            f1.start();
            f2.start();

            try {
                f1.join();
                f2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = f1.getResult() + f2.getResult();
        }
    }

    public int getResult() {
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        ParallelFibonacci f1 = new ParallelFibonacci(5);
        f1.start();
        f1.join();

        System.out.println(f1.getResult());
    }

}
