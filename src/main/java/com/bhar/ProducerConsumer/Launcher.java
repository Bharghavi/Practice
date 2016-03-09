package com.bhar.ProducerConsumer;

import com.bhar.Worker.DataInputStore;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by bharghav on 3/3/2016.
 */
public class Launcher {

    final String END_OF_FILE = "EOF";
    String inputFileName;
    String outputFileName;

    public Launcher(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public void launch() {
        Producer<String> producer = new Producer.ProducerBuilder<String>(getFileReaderProducer()).queueSize(3).status(new TaskUpdatedStatus()).build();
        ProducerConsumer<String, String> producerConsumer = new ProducerConsumer<String, String>(3, getLowerCaseProcessor());
        ProducerConsumer<String, String> upperCaseWord = new ProducerConsumer<String, String>(3, upperCaseWord());
        Consumer<String> consumer = new Consumer<>(getFileWriterConsumer());

        producer.pipe(producerConsumer).pipe(upperCaseWord).pipe(consumer);

        producer.start(3);
        producerConsumer.start(3);
        upperCaseWord.start(3);
        consumer.start(3);
    }

    private ProducerFactory<String> getFileReaderProducer() {
        ProducerFactory<String> producerFactory = null;
        try {
            producerFactory = new ProducerFactory<String>() {
                File file = new File(inputFileName);
                BufferedReader reader = new BufferedReader(new FileReader(file));
                @Override
                public String produce() {
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

                @Override
                public boolean isEndMarker(String element) {
                    return END_OF_FILE.equals(element);
                }
            };

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return producerFactory;
    }

    private ProducerConsumerFactory<String, String> getLowerCaseProcessor() {
        ProducerConsumerFactory<String, String> factory = new ProducerConsumerFactory<String, String>() {
            @Override
            public String produceConsume(String inputElement) {
                if (inputElement == null || END_OF_FILE.equals(inputElement))
                    return inputElement;
                return inputElement.toLowerCase();
            }

            @Override
            public boolean isEndMarker(String element) {
                return element == null || END_OF_FILE.equals(element);
            }
        };
        return factory;
    }

    private ProducerConsumerFactory<String, String> upperCaseWord() {
        ProducerConsumerFactory<String, String> factory = new ProducerConsumerFactory<String, String>() {
            @Override
            public String produceConsume(String inputElement) {
                if (inputElement == null || END_OF_FILE.equals(inputElement))
                    return inputElement;
                StringBuilder outputString = new StringBuilder();
                StringTokenizer tokens = new StringTokenizer(inputElement);
                while (tokens.hasMoreElements()) {
                    String eachToken = tokens.nextToken();
                    outputString.append(eachToken.substring(0, 1).toUpperCase());
                    outputString.append(eachToken.substring(1));
                    outputString.append(" ");
                }
                return outputString.toString();
            }

            @Override
            public boolean isEndMarker(String element) {
                return element == null || END_OF_FILE.equals(element);
            }
        };
        return factory;
    }


    private ConsumerFactory<String> getFileWriterConsumer() {
        ConsumerFactory<String> consumerFactory = null;
        try {
            consumerFactory = new ConsumerFactory<String>() {
                File file = new File(outputFileName);
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

                @Override
                public void consume(String data) {
                    if (!END_OF_FILE.equals(data))
                        try {
                            writer.append(data);
                            writer.newLine();
                            writer.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }

                @Override
                public boolean isEndMarker(String data) {
                    return data == null || END_OF_FILE.equals(data);
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consumerFactory;
    }


}
