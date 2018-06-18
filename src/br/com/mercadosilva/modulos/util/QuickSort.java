package br.com.mercadosilva.modulos.util;

public class QuickSort {

    public void sort (Comparable[] a) {
        quicksort(a, 0, a.length-1);
    }

    private void quicksort(Comparable[] a, int lo, int hi) {
        if(lo >= hi) return;
        int pi = partition(a, lo, hi);
        quicksort(a, lo, pi-1);
        quicksort(a, pi+1, hi);
    }

    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo + 1;
        int j = hi;

        while(i <= j) {
            if(a[i].compareTo(a[lo]) <= 0) {
                i++;
            }
            else if(a[j].compareTo(a[lo]) > 0) {
                j--;
            }
            else if(j < i) {
                break;
            }
            else
                exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }

    private void exchange(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}