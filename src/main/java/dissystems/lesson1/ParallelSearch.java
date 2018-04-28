package dissystems.lesson1;

/**
 * Created by apaud on 3/28/18.
 */
public class ParallelSearch {

    static class Search extends Thread{
        int left;
        int right;
        int[] A;
        int pos = -1;
        int x;

        public Search(int left, int right, int[] a, int x) {
            this.left = left;
            this.right = right;
            this.A = a;
            this.x = x;
        }

        @Override
        public void run() {
            for(int i=left;i<=right;i++){
                if(A[i]==x){
                    pos = i;
                    break;
                }
            }
        }

        public int getPos() {
            return pos;
        }
    }
    public static int parallelSearch(int x,int[] A,int numThreads) throws InterruptedException {
        Search[] threads = new Search[numThreads];

        int numofelms = (int)Math.round(A.length/(double)numThreads);
        System.out.println("Array length "+A.length);
        System.out.println("Num of elems in each thread "+numofelms);

        for(int i=0;i<=numThreads-1;i=i+1){
            int left = i*numofelms;
            int right = (left+numofelms) -1;

            if(i==numThreads-1){
                right = A.length - 1;
            }

            System.out.println(left);
            System.out.println(right);
            System.out.println("------");
            Search th = new Search(left,right,A,x);
            threads[i] = th;
            th.start();
        }

        for (Search th : threads){
            th.join();
            if(th.getPos()!=-1){
                return th.getPos();
            }
        }
        return -1;

    }

    public static void main(String[] args) throws InterruptedException {
        int x = 10;
        int[] A = {1,2,3,4,5,1,6,10,7,8,9,11,12};
        System.out.println("Position is: "+parallelSearch(x,A,4));
    }
}
