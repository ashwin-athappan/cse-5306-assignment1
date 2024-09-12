package org.as.service.sort;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SortService {
    public int[] bubbleSort(int[] array) {
        int n = array.length;
        int temp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n - i; j++) {
                if (array[j - 1] > array[j]) {
                    // swap elements
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public int[] insertionSort(int [] array) {
        int n = array.length;
        for (int j = 1; j < n; j++) {
            int key = array[j];
            int i = j-1;
            while ( (i > -1) && ( array [i] > key ) ) {
                array [i+1] = array [i];
                i--;
            }
            array[i+1] = key;
        }
        return array;
    }

    public int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < array.length; j++){
                if (array[j] < array[index]){
                    index = j;//searching for lowest index  
                }
            }
            int smallerNumber = array[index];
            array[index] = array[i];
            array[i] = smallerNumber;
        }
        return array;
    }

    public void merge(int[] array, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] a1 = new int[n1];
        int[] a2 = new int[n2];

        for (int i = 0; i < n1; i++) {
            a1[i] = array[l + i];
        }
        for (int j = 0; j < n2; j++) {
            a2[j] = array[m + 1 + j];
        }

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (a1[i] < a2[j]) {
                array[k] = a1[i];
                i ++;
            } else {
                array[k] = a2[j];
                j ++;
            }
            k ++;
        }

        while (i < n1) {
            array[k] = a1[i];
            i ++;
            k ++;
        }

        while (j < n2) {
            array[k] = a2[j];
            j ++;
            k ++;
        }

    }

    public void mergeSortHelper(int[] array, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSortHelper(array, l, m);
            mergeSortHelper(array, m + 1, r);
            merge(array, l, m, r);
        }
    }

    public int[] mergeSort(int[] array) {
        int l = 0;
        int r = array.length - 1;

        mergeSortHelper(array, l, r);

        return array;
    }
}
