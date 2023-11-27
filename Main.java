import java.util.*;
public class Main {
    static int len;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean gotPass = true;

        while(gotPass){
            char[][] chars = getArray();
            char[] pass = newPassword(chars);
            if(isStrong(pass, chars)){
                System.out.print("  [Strong Enough]");
                break;
            }
            //System.out.println("\nPASSWORD : "+str);
            //System.out.print("\nRegenerate Password (true : yes || false : no) : ");
            //gotPass = sc.nextBoolean();
        }
    }

    private static char[][] getArray(){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Length : ");
        len = sc.nextInt();

        System.out.print("Include Alphabets (true : yes || false : no) : ");
        boolean alphabets = sc.nextBoolean();

        System.out.print("Include Numbers (true : yes || false : no) : ");
        boolean numbers = sc.nextBoolean();

        System.out.print("Include Symbols (true : yes || false : no) : ");
        boolean symbols = sc.nextBoolean();
        int r = 1;
        if(alphabets)
            r++;
        if(numbers)
            r++;
        if(symbols)
            r++;
        char [][]chars = new char[r][];
        r = 0;

        // take input if to include the various sequences
        if(alphabets){
            chars[r] = new char[26];
            char c  = 'a';
            for(int i = 0; i < 26; i++, c++){
                chars[r][i] = c;
            }
            r++;
            chars[r] = new char[26];
            c = 'A';
            for(int i = 0; i < 26; i++, c++){
                chars[r][i] = c;
            }
            r++;
        }
        if(numbers){
            chars[r] = new char[10];
            for (int i = 0; i < 10; i++) {
                chars[r][i] = (char) ('0' + i);
            }
            r++;
        }
        if(symbols){
            chars[r] = new char[4];
            chars[r][0] = '@';
            chars[r][1] = '_';
            chars[r][2] = '%';
            chars[r][3] = '&';
        }

        // to display the completely available characters to create the password
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != null) { // Check if the array is initialized
                for (int j = 0; j < chars[i].length; j++) {
                    System.out.print(chars[i][j] + " ");
                }
                System.out.println();
            }
        }
        return chars;
    }

    private static char[] newPassword(char[][] arr){
        Random rd = new Random();
        char[] pass = new char[len];

        for(int i = 0; i<len; i++){
            int k = rd.nextInt(4); // change value as per r in getArray
            int index = rd.nextInt(arr.length);
            pass[i] = arr[k][index];
        }

        System.out.print("Password : ");
        for(int i = 0; i<len; i++){
            System.out.print(pass[i]);
        }

        return pass;
    }

    private static boolean isStrong(char[] pass, char[][] chars){
        // considering user wants all types of characters
        boolean[] check = {false,false,false,false}; // smallAlpha, capAlpha, numbers, symbols
        int l = 0;
        while(l < pass.length){
            for(int i = 0; i< chars.length; i++){
                for(int j = 0; j<chars[i].length; j++){
                    if(l < pass.length){
                        if(pass[l] == chars[i][j]){
                            check[i] = true;
                            l++;
                            break;
                        }
                    }
                }
            }
        }

        //System.out.println(Arrays.toString(check));
        for (boolean element : check) {
            if(!element){
                return false;
            }
        }
        return true;
    }
}