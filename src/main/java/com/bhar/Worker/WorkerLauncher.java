package com.bhar.Worker;

import java.io.*;
import java.util.StringTokenizer;

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
        try {
            new WorkerChain.Builder<String, Integer>(getFileInputStore(), getFileOutputStore()).addTransformation(upperCaseTransformer(), 5).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    private DataOutputStore<Integer> getFileOutputStore() throws IOException {
        DataOutputStore<Integer> fileOutputStore = new DataOutputStore<Integer>() {

            File file = new File(outputFileName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            @Override
            public void put(Integer data) {
                if (data != -1)
                    try {
                        writer.append(String.valueOf(data));
                        writer.newLine();
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        };
        return fileOutputStore;
    }

    private Transformer<String, String> upperCaseTransformer() {
        Transformer<String, String> transformer = new Transformer<String, String>() {
            @Override
            public String transform(String data) {
                StringBuilder outputString = new StringBuilder();
                StringTokenizer tokens = new StringTokenizer(data);
                while (tokens.hasMoreElements()) {
                    String eachToken = tokens.nextToken();
                    outputString.append(eachToken.substring(0, 1).toUpperCase());
                    outputString.append(eachToken.substring(1).toLowerCase());
                    outputString.append(" ");
                }
                return outputString.toString();
            }

            @Override
            public boolean isEndMarker(String data) {
                return END_OF_FILE.equals(data);
            }

            @Override
            public String getEndMarker() {
                return END_OF_FILE;
            }
        };
        return transformer;
    }

}
