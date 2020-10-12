package Java;

import java.util.Arrays;

/**
 * Java implementation of selection sort algorithm, which repeatedly finds the
 * minimum element (in ascending order)
 */

public class SelectionSort {

    public static void main(String[] args) {
        int[] arr ={5, 6, 8, 1, 3};
        sort(arr);
    }
    public static void sort(int[] array) {
        int sizeOfArray = array.length;
        int[] newArray = {};
        for(int i = 0; i < sizeOfArray - 1; i++) {
            int min_index = i;
            for(int j = i+1; j < sizeOfArray; j++) {
                if(array[j] < array[min_index]) {
                    min_index = j;
                }
            }
            int temp = array[min_index];
            array[min_index] = array[i];
            array[i] = temp;
        }
        System.out.println(Arrays.toString(array));
    }
}