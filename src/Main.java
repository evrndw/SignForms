import Forms.*;
import Data.Database;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Database.initializeDB();
        SignIn signIn = new SignIn(null);
    }
}