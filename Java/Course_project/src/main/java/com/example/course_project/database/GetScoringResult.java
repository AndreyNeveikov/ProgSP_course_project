package com.example.course_project.database;

import java.io.*;
import java.sql.SQLException;
import java.util.LinkedList;

public class GetScoringResult {

    static String fileName = "E:\\ProgSP_course_project\\ScoringLogs\\ScoringLogs.txt";

    public static String readScoringData() {
        LinkedList<String> ScoringLogConteiner = new LinkedList<>();
        try {
            File file = new File(fileName);

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            ScoringLogConteiner.add(line);
            while (line != null) {
                line = reader.readLine();
                ScoringLogConteiner.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(ScoringLogConteiner);
    }

    public static String ConfirmScoringResultsButtonClick(String record_id) {

        LinkedList<String> ScoringLogConteiner = new LinkedList<>();
        try {
            File file = new File("E:\\ProgSP_course_project\\ScoringLogs\\ScoringLogs.txt");

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            int counter = 1;
            String line = reader.readLine();
            if (record_id.equals("0")) {
                ScoringLogConteiner.add(line);
            }
            while (line != null) {
                line = reader.readLine();
                if (record_id.equals(String.valueOf(counter))) {
                    ScoringLogConteiner.add(line);

                }
                counter++;
            }
            fr.close();

            String[] string_args = String.valueOf(ScoringLogConteiner).
                    substring(1, String.valueOf(ScoringLogConteiner).length() -1).split(" = ");
            String[] get_client_id = string_args[1].split(" Рек");
            String[] get_product_id = string_args[2].split(" по ставке");
            String[] get_sum = string_args[2].split("На сумму ");

            QueriesSQL.ConfirmCredit(get_client_id[0]+"/"+get_product_id[0]+"/"+get_sum[1]);

            CancelScoringResultsButtonClick(record_id);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "Confirmed";
    }
    public static String CancelScoringResultsButtonClick(String record_id) {
        LinkedList<String> ScoringLogConteiner = new LinkedList<>();
        try {
            File file = new File("E:\\ProgSP_course_project\\ScoringLogs\\ScoringLogs.txt");

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            int counter = 1;
            String line = reader.readLine();
            if (!record_id.equals("0")) {
                ScoringLogConteiner.add(line);
            }
            while (line != null) {
                line = reader.readLine();
                if(!record_id.equals(String.valueOf(counter))) {
                    ScoringLogConteiner.add(line);

                }
                counter++;
            }
            fr.close();
            System.out.println(ScoringLogConteiner);

            PrintWriter pw = new PrintWriter(fileName);

            for (int i = 0;  i < ScoringLogConteiner.size() - 1; i++){
                pw.println(ScoringLogConteiner.get(i));
            }
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Canceled";
    }

}
