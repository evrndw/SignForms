package Data;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class Database {
    private static final HashMap<String, String[]> USERS = new HashMap<>(); // lista de usuários

    public static void initializeDB() throws IOException {
        // Inicializador dos dados
        // Le conteudo do arquivo data.csv e escreve no hashmap USERS

        File data = new File("src\\Data\\data.csv");
        BufferedReader reader = new BufferedReader(new FileReader(data));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] row = line.split(",");
            String key = row[0];
            String[] values = Arrays.copyOfRange(row,1,4);

            USERS.put(key, values);
        }

        reader.close();
    }

    public static void addUser(User user) {
        // Adiciona novo usuario no hashmap

        String key;
        String[] values = new String[3];

        key = user.getEmail();
        values[0] = user.getName();
        values[1] = user.getSurname();
        values[2] = user.getPassword();

        USERS.put(key, values);
    }

    public static void updateCSV(User user) throws IOException {
        // Insere usuarios criados ao fim do arquivo data.csv

        FileWriter writer = new FileWriter("src\\Data\\data.csv", true);

        writer.append("\n");
        writer.append(user.getEmail());
        writer.append(",");
        writer.append(user.getName());
        writer.append(",");
        writer.append(user.getSurname());
        writer.append(",");
        writer.append(user.getPassword());

        writer.flush();
        writer.close();
    }

    public static boolean checkUser(String email) {
        // Verifica se um usuario ja existe (atraves do email digitado)

        return USERS.containsKey(email);
    }

    public static boolean checkPwd(String email, String password) {
        // Verificacao de senha

        String[] values = USERS.get(email);
        String correctPwd = values[2];

        return password.equals(correctPwd);
    }
}
