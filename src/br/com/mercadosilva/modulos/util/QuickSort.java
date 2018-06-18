package br.com.mercadosilva.modulos.util;

public class QuickSort {

    public QuickSort (Comparable[] a) {
        quicksort(a, 0, a.length-1);
    }

    private static void quicksort(Comparable[] a, int low, int high) {
        if(low >= high) return;

        int pi = partition(a, low, high);

        quicksort(a, low, pi-1);
        quicksort(a, pi+1, high);
    }

    private static int partition(Comparable[] a, int low, int high) {
        int i = low + 1;
        int j = high;

        while(i <= j) {
            if(a[i].compareTo(a[low]) <= 0) {
                i++;
            } else if(a[j].compareTo(a[low]) > 0) {
                j--;
            } else if(j < i) {
                break;
            } else
                exchange(a, i, j);
        }

        exchange(a, low, j);
        return j;
    }

    private static void exchange(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}