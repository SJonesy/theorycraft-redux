import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Party {
    public String name;
    public List<Character> characters = new ArrayList<>();

    public Party (String partyFile)  {
        Path path = Paths.get(partyFile);
        Scanner scanner = null;

        try {
            scanner = new Scanner(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        name = scanner.nextLine();

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            characters.add(new Character(line));
        }
        scanner.close();
    }
}
