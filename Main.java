import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean gotPass = true;
        String str = "";
        
        while(gotPass){
            str = getPass();
            System.out.println("\nPASSWORD : "+str);
            System.out.print("Regenerate Password (true : yes || false : no) : ");
            gotPass = sc.nextBoolean();
        }
    }

    private static String getPass(){
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        System.out.print("Enter Length : ");
        int len = sc.nextInt();
        boolean alphabets = false;
        boolean numbers = false;
        boolean symbols = false;
        String chars = "";

        // take input if to include the various sequences
        System.out.print("Include Alphabets (true : yes || false : no) : ");
        alphabets = sc.nextBoolean();
        if(alphabets){
            String charsAlpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            chars = chars.concat(charsAlpha);
        }
        System.out.print("Include Numbers (true : yes || false : no) : ");
        numbers = sc.nextBoolean();
        if(numbers){
            String charNumbers = "0123456789";
            chars = chars.concat(charNumbers);
        }

        System.out.print("Include Symbols (true : yes || false : no) : ");
        symbols = sc.nextBoolean();
        if(symbols){
            String charSym = "@_-%&";
            chars = chars.concat(charSym);
        }

        // create the password
        StringBuilder pass = new StringBuilder();

        for(int i = 0; i<len; i++){
            int index = rd.nextInt(chars.length());
            char randomChar = chars.charAt(index);
            pass.append(randomChar);
        }

        //System.out.println("Total : "+chars);
        //System.out.println("\nPASSWORD : "+pass);
        String ans = pass.toString();
        return ans;
    }
}