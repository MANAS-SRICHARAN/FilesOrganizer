import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.util.TreeSet;
public class Organizer {
    public static void main(String[] args){
        System.out.println("Enter the path");
        Scanner scan = new Scanner(System.in);
        String path = scan.nextLine();
        organize(path);

    }

    static void organize(String path){
        File f = new File(path);
        File[] files = f.listFiles();
        TreeSet<String> set = new TreeSet<String>();
        short index; //by , default it is set to 0.
        for(File s: Objects.requireNonNull(files)){
            if(s.getName().toLowerCase().matches(".*[.].*$")){
                //there is an extension that shall be added to the set
                index = (short)s.getName().toLowerCase().lastIndexOf(".");
                System.out.println(s.getName().toLowerCase().substring(index));

            }
       }


    }
}
