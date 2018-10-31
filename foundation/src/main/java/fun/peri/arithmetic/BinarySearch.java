package fun.peri.arithmetic;

/**
 * binary search
 */
public class BinarySearch {
    public static void main(String[] args) {

    }

    public int search(int[] numbs, int number) {
        int low = 0;
        int high = numbs.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (number > numbs[mid]) {
                high = mid - 1;
            } else if (number < numbs[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
