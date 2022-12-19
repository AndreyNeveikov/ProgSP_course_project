package com.example.course_project.database;

        import java.io.*;
        import java.util.Date;
        import java.util.LinkedList;

public class ActionLogger {

    private static ActionLogger programLogger;

    private static final String fileName = "E:\\ProgSP_course_project\\ScoringLogs\\ActionLogs.txt";

    public static synchronized ActionLogger getActionLogger(){
        if(programLogger == null){
            programLogger = new ActionLogger();
        }
        return programLogger;
    }

    private ActionLogger(){
    }

    public void addActionLogInfo(String logInfo){
        Date date = new Date();
        String logFile = date + " " + logInfo + "\n";

        try(FileWriter writer = new FileWriter(
                fileName, true))
        {
            writer.write(logFile);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static String readActionData() {
        LinkedList<String> ActionLogConteiner = new LinkedList<>();
        try {
            File file = new File(fileName);

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            ActionLogConteiner.add(line);
            while (line != null) {
                line = reader.readLine();
                ActionLogConteiner.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(ActionLogConteiner);
    }
}


