
public class ArrayPrinter {

    public static void main(String[] args) {
        // You can test your method here
        int[] array = {5, 1, 3, 4, 2};
        printNeatly(array);
    }

    public static void printNeatly(int[] array) {
        String str = "" ;
        int index = 0;
        while (index < array.length) {
            str += String.valueOf(array[index]);
            if (index != array.length-1) {
                str += ", ";
            }            
            index ++ ;
        }
        System.out.println(str);
    }
}

