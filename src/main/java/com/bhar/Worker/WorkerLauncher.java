package com.bhar.Worker;

import java.io.*;

/**
 * Created by bharghav on 3/9/2016.
 */
public class WorkerLauncher {

    final String END_OF_FILE = "EOF";
    String inputFileName;
    String outputFileName;

    public WorkerLauncher(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public void launch() {

    }


    private DataInputStore<String> getFileInputStore() throws FileNotFoundException {
        DataInputStore<String> fileInputStore = new DataInputStore<String>() {

            File file = new File(inputFileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            @Override
            public String getData() {
                String str;
                try {
                    if ((str = reader.readLine()) != null)
                        return str;
                    else
                        return END_OF_FILE;
                } catch (IOException e) {
                    e.printStackTrace();
                    return END_OF_FILE;
                }
            }
        };
        return fileInputStore;
    }

}
