package dissystems.lesson1;

/**
 * Created by apaud on 4/9/18.
 */
public class ParallelLinkedList{
    Node startNode = null;
    Node endNode = null;
    int length;

    public volatile boolean searchFlag = false;
    public volatile int searchIndex = -1;

    public ParallelLinkedList(int length) {
        this.length = length;
        Node tempNode = null;
        for(int i=1;i<=length;i++){
            Node n = new Node(i);
            if(startNode == null){
                this.startNode = n;
            }
            if(tempNode!=null){
                tempNode.setForward(n);
                n.setBackward(tempNode);
            }
            tempNode = n;
        }
        this.endNode = tempNode;
    }

    public int getLength(){
        return this.length;
    }

    public void searchForward(int searchKey) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Node tempNode = startNode;
                int tmplen = 1;
                while(tempNode!=null && searchFlag!=true){
                    if(tempNode.getData()==searchKey){
                        searchFlag = true;
                        searchIndex = tmplen;
                    }
                    ++tmplen;
                    tempNode = tempNode.getForward();
                }
            }
        });
        t1.start();
        t1.join();

    }

    private void searchBackward(int searchKey) throws InterruptedException {

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Node tempNode = endNode;
                int tmplen = length;
                while(tempNode!=null && searchFlag!=true){
                    if(tempNode.getData()==searchKey){
                        searchFlag = true;
                        searchIndex = tmplen;
                    }
                    --tmplen;
                    tempNode = tempNode.getBackward();
                }
            }
        });
        t2.start();
        t2.join();
    }

    public int search(int searchKey) throws InterruptedException {
        searchFlag = false;
        searchIndex = -1;
        searchForward(searchKey);
        searchBackward(searchKey);
        return this.searchIndex;
    }

    public static void main(String[] args) throws InterruptedException {
        int length = 10;
        ParallelLinkedList ln = new ParallelLinkedList(10);

        int searchKey = 8;
        int searchIndex = ln.search(searchKey);
        System.out.println(searchIndex);

        searchKey = 3;
        searchIndex = ln.search(searchKey);
        System.out.println(searchIndex);
        searchKey = 16;
        searchIndex = ln.search(searchKey);
        System.out.println(searchIndex);

    }
}


class Node{
    Node forward;
    Node backward;
    int data;

    public Node(int data) {
        this.data = data;
    }

    public Node getForward() {
        return forward;
    }

    public void setForward(Node forward) {
        if (forward != null) {
            this.forward = forward;
        }
    }

    public Node getBackward() {
        return backward;
    }

    public void setBackward(Node backward) {
        if (backward != null) {
            this.backward = backward;
        }
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
