import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---- Computação de terras ----\n");
        File f;
        File[] paths;
        File[] textFiles = new File[25];
        long linesCount;
        try {
            f = new File(System.getProperty("user.dir") + "/casos");
            paths = f.listFiles();
            if(paths == null)
                System.exit(-1);
            int c = 0;
            for (File path : paths) {
                if (path.getAbsolutePath().endsWith(".txt")) {
                    textFiles[c] = path;
                    System.out.println("[" + c + "] - " + path);
                    c++;
                }
            }
            System.out.println("Selecione o arquivo (digite o respectivo numero): ");
            int filePos = sc.nextInt();
            File fileToProcess = textFiles[filePos];
            linesCount = Files.lines(fileToProcess.toPath()).count();
            BufferedReader br = new BufferedReader(new FileReader(fileToProcess));
            String st;
            String root = "";
            int opCount = 0;
            LinearHashMap lh = new LinearHashMap((int) (linesCount * 1.3));
            int rootLand = Integer.parseInt(br.readLine());
            while ((st = br.readLine()) != null) {
                String[] split = st.split(" ");
                if (opCount == 0) {
                    root = split[0];
                    lh.add(split[0], rootLand);
                }
                lh.add(split[0], split[1], Integer.parseInt(split[2]));
                opCount++;
            }
            br.close();
            lh.computeLands(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}