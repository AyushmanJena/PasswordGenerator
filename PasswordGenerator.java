import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
public class PasswordGenerator {
    boolean includeAlpha;
    boolean includeNum;
    boolean includeSym;
    int len;
    int r;
    public static void main(String[] args) {
        PasswordGenerator obj = new PasswordGenerator();

        Scanner sc = new Scanner(System.in);
        boolean gotPass = true;

        while(gotPass){
            char[][] chars = obj.getArray();
            char[] pass = obj.newPassword(chars); //doesnot work for custom constraints
            if(obj.isStrong(pass, chars)){
                System.out.print("  [Strong Enough]");
                //break;
            }
            //System.out.println("\nPASSWORD : "+str);
            System.out.print("\nRegenerate Password ? (yes : true || no : false) : ");
            gotPass = sc.nextBoolean();
            if(!gotPass){
                System.out.println("Save password ? (yes : true || no : false) : ");
                boolean savePass = sc.nextBoolean();
                if(savePass){
                    obj.savePassword(pass);
                }
            }
        }
    }


    // takes input for which types of characters to include
    private PasswordGenerator(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Custom Password ? ");
        boolean custom = sc.nextBoolean();
        if(custom){
            System.out.print("\nEnter Length : ");
            len = sc.nextInt();

            System.out.print("Include Alphabets (yes : true || no : false) : ");
            includeAlpha = sc.nextBoolean();

            System.out.print("Include Numbers (yes : true || no : false) : ");
            includeNum = sc.nextBoolean();

            System.out.print("Include Symbols (yes : true || no : false) : ");
            includeSym = sc.nextBoolean();
            return;
        }
        len = 8;
        includeAlpha = true;
        includeNum = true;
        includeSym = true;
    }

    // set all available characters from which the password would be formed
    private char[][] getArray(){
        r = 1;
        if(includeAlpha)
            r++;
        if(includeNum)
            r++;
        if(includeSym)
            r++;
        char [][]chars = new char[r][];
        r = 0;

        // takes input if to include the various sequences
        if(includeAlpha){
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
        if(includeNum){
            chars[r] = new char[10];
            for (int i = 0; i < 10; i++) {
                chars[r][i] = (char) ('0' + i);
            }
            r++;
        }
        if(includeSym){
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

    // forms password
    private char[] newPassword(char[][] arr){
        Random rd = new Random();
        char[] pass = new char[len];
        try{
            int c= 0;
            if(includeAlpha)
                c = c+2; //for cap as well as small alphabets
            if(includeNum)
                c++;
            if(includeSym)
                c++;

            for(int i = 0; i<len; i++){
                int k = rd.nextInt(c); // change value as per r in getArray
                int index = rd.nextInt(arr[k].length);
                pass[i] = arr[k][index];
            }

            System.out.print("Password : ");
            for(int i = 0; i<len; i++){
                System.out.print(pass[i]);
            }
        }
        catch(Exception e){
            System.out.println("Error : One choice must be true!");
            return null;
        }
        return pass;
    }

    // check if the password is strong enough
    // i.e. includes all types of specified characters
    private boolean isStrong(char[] pass, char[][] chars){

        int c= 0;
        if(includeAlpha)
            c = c+2; //for cap as well as small alphabets
        if(includeNum)
            c++;
        if(includeSym)
            c++;

        boolean[] check = new boolean[c];

        int l = 0;
        while(l <pass.length){
            for(int i = 0; i< chars.length; i++){
                if (chars[i] == null) {
                    break;
                }
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

    // save password to txt file
    private void savePassword(char[] pass){
        String filePath = "Password Generator/SavedPasswords.txt";

        try {
            // Create FileWriter with append mode set to false
            FileWriter fileWriter = new FileWriter(filePath, true);

            // Wrap FileWriter in BufferedWriter for better performance
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Iterate through the char array and write each character to the file
            for (char c : pass) {
                bufferedWriter.write(c);
            }

            bufferedWriter.newLine();

            // Close the writers
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Password Saved!");
        } catch (Exception e) {
            System.err.println("Error occurred while saving password: " + e.getMessage());
        }
    }
}