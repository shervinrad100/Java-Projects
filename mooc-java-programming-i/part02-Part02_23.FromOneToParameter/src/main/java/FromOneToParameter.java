
import java.util.Scanner;
public class FromOneToParameter {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = Integer.valueOf(scan.nextLine());
        printUntilNumber(num);
    }
    
    public static void printUntilNumber(int number) {
        for (int i=1; i<=number;i++) {
            System.out.println(i);
        }
    }
    
}
