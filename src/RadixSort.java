import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RadixSort {

    public static void sort(int[] A) {
        Queue<Integer> q = new LinkedList<>();
        for (int i : A) {
            q.add(i);
        }
        Queue<Integer>[] bucket = new Queue[2];
        bucket[0] = new LinkedList<>();
        bucket[1] = new LinkedList<>();

        for (int r = 32; r >= 1; r--) {
            bucket[0].clear();
            bucket[1].clear();
            while (!q.isEmpty()) {
                int a = q.poll();
                int i = getBit(a, r);
                bucket[i].add(a);
            }
            for (Queue<Integer> integers : bucket) {
                if (!integers.isEmpty()) {
                    q.addAll(integers);
                }
            }
        }
        int[] B = new int[q.size()];
        for (int i = 0; i < q.size(); i++) {
            B[i] = q.poll();
        }
        A = B;
    }

    private static int getBit(int n, int k) {
        return (n >> k) & 1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> list = new ArrayList<>();

        // Datei einlesen
        String path = args[0];
        Scanner read = new Scanner(new File(path));
        while (read.hasNextLine()) {
            list.add(Integer.valueOf(read.nextLine()));
        }
        read.close();

        // arrayList für QuickSort in normales Array kopieren
        int[] A = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            A[i] = list.get(i);
        }

        final long timeStart = System.nanoTime();
        sort(A);
        final long timeEnd = System.nanoTime();

        // Sortiertes Array Zeilenweise auf dem Bildschirm ausgeben
        //for (int i : A) {
        //    System.out.println(i);
        //}

        // Laufzeit ausgeben
        System.out.println("Laufzeit des Sortieralgorithmus: " + (timeEnd - timeStart) + " Nanosek.");
    }
}
