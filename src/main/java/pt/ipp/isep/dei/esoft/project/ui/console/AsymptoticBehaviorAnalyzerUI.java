package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AsymptoticBehaviorAnalyzerController;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AsymptoticBehaviorAnalyzerUI implements Runnable {

    private AsymptoticBehaviorAnalyzerController controller;

    public AsymptoticBehaviorAnalyzerUI() {
        this.controller = new AsymptoticBehaviorAnalyzerController();
    }

    @Override
    public void run() {
        try {
            String folderPath;
            int temp = 0;
            System.out.println("Please indicate the folder with the files:\n");
            System.out.println("NOTE: Please only put valid files in the folder!!");
            do {
                folderPath = Utils.readLineFromConsole("Folder path: ");;
                temp++;
                if (temp == 5){
                    System.out.println("5 wrong attempts!");
                }
            }while (! new File(folderPath).isDirectory() && temp < 5);
            controller.startTest(folderPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
