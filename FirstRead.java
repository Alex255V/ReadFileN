import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class FirstRead {
    private ArrayList <String> saveWrite = new ArrayList<>();
    void firstReadTextFile() {
        try {
            FileReader fr = new FileReader(new File("src/text.txt"));
            int ch;
            while ((ch = fr.read()) != -1) {
//                System.out.print((char)ch);
                if (((char)ch == ',') || ((char)ch == '.') || ((char)ch == ':') || ((char)ch == ';') || ((char)ch == '?')) {
                    continue;
                }
                saveWrite.add(Character.toString((char) ch));
            }
        } catch (Exception e) {
            System.err.println("ERROR!!! \nFile not found!");
        }
    }

    void nextFileWriteIsNewFilterWords() {
        try {
            FileWriter writer = new FileWriter("src/newFile.txt", false);
            String text = "";
            for (String aSaveWrite : saveWrite) {
                text = text + aSaveWrite;
            }
            writer.write(text);
            writer.append('\n');
//            writer.append('E');
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
