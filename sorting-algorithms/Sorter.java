import java.lang.reflect.Array;
import java.util.Arrays;

public class Sorter implements GodricsHat {
    public void insertion(int[] array) { 
        for (int i = 0; i < array.length; i++) {
            int k = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > k) {
                array[j + 1] = array[j];
                j = j - 1;
            }

            array[j + 1] = k;
        }
    }

    public void merge(int[] array) {
        if (array.length < 2)
            return;
        int mid = array.length / 2;
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }
        merge(left);
        merge(right);
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    public void quick(int[] array, int p, int r) {
        if (p >= r)
            return;
        int pivot = array[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (array[j] <= pivot) {
                i++;
                int cursor = array[i];
                array[i] = array[j];
                array[j] = cursor;
            }
        }
        int cursor = array[i + 1];
        array[i + 1] = array[r];
        array[r] = cursor;
        quick(array, p, i);
        quick(array, i + 2, r);
    }

    public void quickLoopy(int[] array) {
        int p = 0; 
        int r = array.length - 1;
        int[] stack = new int[r - p + 1];
        int top = -1;

        stack[++top] = p;
        stack[++top] = r;

        while (top >= 0) {
            r = stack[top--];
            p = stack[top--];

            int pivot = array[r];
            int i = p - 1;
            for (int j = p; j < r; j++) {
                if (array[j] <= pivot) {
                    i++;
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            int temp = array[i + 1];
            array[i + 1] = array[r];
            array[r] = temp;
            int partition = i + 1;

            if (partition - 1 > p) {
                stack[++top] = p;
                stack[++top] = partition - 1;
            }
            if (partition + 1 < r) {
                stack[++top] = partition + 1;
                stack[++top] = r;
            }
        }
    }

    public void counting(int[] array) {
        int i = 0, j = 0, k = 0, max = Integer.MIN_VALUE;
        for (i = 0; i < array.length; i++)
            max = array[i] > max ? array[i] : max;
        int[] counts = new int[max + 1];
        for (i = 0; i < array.length; i++)
            counts[array[i]]++;
        for (i = 0; i < counts.length; i++)
            for (j = 0; j < counts[i]; j++)
                array[k++] = i;
    }
}