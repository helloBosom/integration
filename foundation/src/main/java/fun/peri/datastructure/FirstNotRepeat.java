package fun.peri.datastructure;

/**
 * find the first not repeat letter in character string
 */
public class FirstNotRepeat {

    public static void main(String[] args) {
        String str = "";
        char c = firstNotRepeat(str);
        System.out.println("the first not repeat letter is:" + c);
    }

    /**
     * method:build a array which include the ascii of letter
     * loop str
     * the count of letter addSelf
     * <p/>
     *
     * @param str
     * @return
     */
    public static char firstNotRepeat(String str) {
        if (str == null)
            return '0';
        int[] hashTable = new int[256];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            hashTable[c]++;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (hashTable[c] == 1)
                return c;
        }
        return '0';
    }
}
