package com.example.course_project.database;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class ScoringLogger {

    private static ScoringLogger programLogger;

    public static synchronized ScoringLogger getScoringLogger(){
        if(programLogger == null){
            programLogger = new ScoringLogger();
        }
        return programLogger;
    }

    private ScoringLogger(){
    }

    public void addScoringLogInfo(String logInfo){
        Date date = new Date();
        String logFile = date + " " + logInfo + "\n";

        try(FileWriter writer = new FileWriter(
                "E:\\ProgSP_course_project\\ScoringLogs\\ScoringLogs.txt", true))
        {
            writer.write(logFile);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}

