package dissystems.lesson1;

import java.util.Arrays;

/**
 * Created by apaud on 4/10/18.
 */
public class MergeSort {
    int[] arr;
    public MergeSort(int[] arr) {
        this.arr = arr;
    }

    public static void main(String[] args) {
        int[] arr = {9,7,6,8,5,4,3,2,1,0};

        MergeSort ms = new MergeSort(arr);

        arr = ms.mergesort(arr);

        System.out.println(Arrays.toString(arr));
    }

    private int[] mergesort(int[] arr){
        //System.out.println(Arrays.toString(arr));
        if(arr.length<=1){
            return arr;
        }
        int middle = (arr.length)/2;

        int left = 0;
        int right = arr.length;

        int[] sub1arr = Arrays.copyOfRange(arr,left,middle);
        int[] sub2arr = Arrays.copyOfRange(arr,middle,right);
        sub1arr = mergesort(sub1arr);
        sub2arr = mergesort(sub2arr);
        return merge(sub1arr,sub2arr);
    }

    private int[] merge(int[] sub1arr, int[] sub2arr) {
        int finallength = sub1arr.length+sub2arr.length;
        int[] merged = new int[finallength];
        int i=0;
        int curr1Indx = 0;
        int curr2Indx = 0;

        while(i<finallength){
            if(curr1Indx>sub1arr.length-1){
                for(int j=curr2Indx;j<sub2arr.length;j++){
                    merged[i] = sub2arr[j];
                    ++i;
                }
                break;
            }
            if(curr2Indx>sub2arr.length-1){
                for(int j=curr1Indx;j<sub1arr.length;j++){
                    merged[i] = sub1arr[j];
                    ++i;
                }
                break;
            }

            if(sub1arr[curr1Indx] < sub2arr[curr2Indx]){
                merged[i] = sub1arr[curr1Indx];
                ++curr1Indx;
            }
            else{
                merged[i] = sub2arr[curr2Indx];
                ++curr2Indx;
            }
            ++i;
        }
        return merged;
    }
}
