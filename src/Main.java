import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    public static String csv = "D:\\DataBase_VSS\\DB_VSS.csv";

    public static void main(String[] args) throws Exception {

        CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add new line to DataBase? (y/n)");
        String questionAddDB = scanner.nextLine();

        if (questionAddDB.equals("y")) {

            String inputDB_1 = DATABASE_CONSOLE.addDB();

            String[] record = inputDB_1.split(",");
            writer.writeNext(record);
            writer.close();

            System.out.println("OK");

        }
        if (questionAddDB.equals("n")) {
            System.out.println("OK");
        }

        System.out.println("\nRead DataBase? (y/n)");
        String questionReadDB = scanner.nextLine();

        if (questionReadDB.equals("y")) {

            System.out.println();
            DATABASE_CONSOLE.readDB(csv);

        }
        if (questionReadDB.equals("n")) {
            System.out.println("OK");
        }

        new DATABASE_CONSOLE.dbConsole().start();

    }
}
