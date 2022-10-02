import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DATABASE_CONSOLE {

    static class dbConsole extends Thread {

        public void run() {

            boolean exitFlag = false;

            CSVReader reader = null;
            try {
                reader = new CSVReader(new FileReader(Main.csv));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Scanner scanner = new Scanner(System.in);

            while (!exitFlag) {

                System.out.print("\n>>> ");
                String dbRequest = scanner.nextLine();

                if (dbRequest.equals("exit")) {
                    exitFlag = true;
                }

                if (dbRequest.equals("read -a")) {
                    System.out.println();
                    try {
                        readDB(Main.csv);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (dbRequest.equals("read -h")){
                    System.out.println("No information now.");
                }

                if (dbRequest.equals("add -l")) {

                    String inputDB_1 = DATABASE_CONSOLE.addDB();
                    String[] record = inputDB_1.split(",");

                    CSVWriter writer = null;
                    try {
                        writer = new CSVWriter(new FileWriter(Main.csv, true));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    assert writer != null;
                    writer.writeNext(record);

                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                if (dbRequest.equals("add -h")){
                    System.out.println("No information now.");
                }

                if (dbRequest.equals("find -id")) {

                    List<String[]> allRows = null;

                    try {
                        assert reader != null;
                        allRows = reader.readAll();
                    } catch (IOException | CsvException e) {
                        e.printStackTrace();
                    }

                    System.out.print("\n>>> ");
                    String y = scanner.nextLine();

                    assert allRows != null;
                    for (String[] row : allRows) {

                        String x = (String) Arrays.stream(row).toArray()[0];

                        if (x.equals(y)) {

                            System.out.println();

                            for (int jj = 0; jj < 4; jj++) {
                                if (jj == 0) {
                                    System.out.print(jj + " | " + Arrays.stream(row).toArray()[jj] + " |");
                                } else {
                                    System.out.print(Arrays.stream(row).toArray()[jj]);
                                }
                            }

                            System.out.println();

                        }
                    }

                }

                /*if (dbRequest.equals("find -n")){

                    System.out.print("\n>>> ");
                    String nFind = scanner.nextLine();

                    List<String[]> allRows = null;

                    try {
                        allRows = reader.readAll();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (CsvException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Arrays.toString(allRows.get(Integer.parseInt(nFind))));

                } */

                if (dbRequest.equals("find -h")){
                    System.out.println("No information now.");
                }

            }

            System.exit(0);

        }
    }

    public static synchronized void readDB(String csvPath) throws Exception {

        System.out.println("N |.ID| ELEMENTS");
        System.out.println("  |   |");
        CSVReader reader = new CSVReader(new FileReader(csvPath));
        List<String[]> allRows = reader.readAll();

        for (String[] row : allRows) {

            for (int i = 0; i < 4; i++) {

                if (i == 0) {
                    System.out.print(allRows.indexOf(row) + " | " + Arrays.stream(row).toArray()[i] + " |");
                } else {
                    System.out.print(Arrays.stream(row).toArray()[i]);
                }

            }

            System.out.println();

        }
    }

    public static synchronized String addDB() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID: ");
        int P1 = scanner.nextInt();
        String remove = scanner.nextLine();

        System.out.print("P2: ");
        String P2 = scanner.nextLine();

        System.out.print("P3: ");
        String P3 = scanner.nextLine();

        System.out.print("P4: ");
        String P4 = scanner.nextLine();

        return P1 + ", " + P2 + ", " + P3 + ", " + P4;
    }

}
