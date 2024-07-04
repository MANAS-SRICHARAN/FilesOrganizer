import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Organizer {
    public static void main(String[] args) {
        String path = "/users/manassricharanvarri/Desktop/";
        organize(path);
    }

    public static String grabextensions(String x) {
        short index;
        if (x.matches(".*[.].*$")) {
            index = (short) x.lastIndexOf(".");
            index = (short) (index + (short) 1);
            return x.substring(index);
        } else {
            return "";
        }
    }
    static boolean filterFiles(String x,Object[] unique_extensions){

        for(Object o: unique_extensions){
    if(x.endsWith(o.toString())){

   return true;
    }
   }

    return false;
    }
    static void organize(String s_path) {
        Path path = Paths.get(s_path);
        Object[] unique_extensions = null;
        try {
           unique_extensions = Files.list(path).map(Path::toString).map(x -> grabextensions(x)).
                    filter(x -> (!(x.equals("DS_Store")) && !(x.equals("localized")) && !(x.equals("")))).distinct().toArray();

        } catch (IOException e) {
            System.err.println(e);
        }
        //creating directories.
        ArrayList<String> directories_created = new ArrayList<String>();
        try {
            for (Object x : unique_extensions) {
                directories_created.add((String) x + "_files_collections");
                Files.createDirectories(Paths.get(path.toString() + "/" + (String) x + "_files_collections"));
            }
        } catch (IOException E) {
            System.err.println(E);
        }
        System.out.println(directories_created);
        try {
            Object[] finalUnique_extensions = unique_extensions;

            Files.list(path).map(Path::toString).filter(x -> filterFiles(x, finalUnique_extensions)).forEach( element -> {
                try {
                    for (String s : directories_created) {
                        if (s.substring(0, s.indexOf("_")).equals(element.substring(element.lastIndexOf(".") + 1))) {
                            System.out.println("there is a match");
                            Files.move(Paths.get(element).toAbsolutePath(), Paths.get(path.toString() +"/"+ s + "/" + element.substring(element.lastIndexOf("/")+1)).toAbsolutePath(), StandardCopyOption.REPLACE_EXISTING);
                        }
                    }
                }catch(IOException E){
                    System.err.println(E);
                }
            });

        } catch(IOException E){
            System.err.println(E);
        }

    }
}

