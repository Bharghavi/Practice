package com.bhar.ProducerConsumer;

import java.io.*;

/**
 * Created by bharghav on 3/3/2016.
 */
public class Launcher {

    final String endOfFileMarker = "EOF";
    String inputFileName;
    String outputFileName;

    public Launcher(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public void launch() {
        Producer<String> producer = new Producer.ProducerBuilder<String>(getFileReaderProducer()).queueSize(3).status(new TaskUpdatedStatus()).build();
        ProducerConsumer<String, String> producerConsumer = new ProducerConsumer<String, String>(3, getUpperCaseProcessor());
        Consumer<String> consumer = new Consumer<>(getFileWriterConsumer());

        producer.pipe(producerConsumer).pipe(consumer);

        createThreadsAndStart(producer, 1);
        createThreadsAndStart(producerConsumer, 1);
        createThreadsAndStart(consumer, 1);
        producer.printStatus();
    }

    private void createThreadsAndStart(Runnable runnable, int numberOfThreads) {
        Thread t;
        for (int i = 0; i < numberOfThreads; i++) {
            t = new Thread(runnable);
            t.start();
        }
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
                            return endOfFileMarker;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return endOfFileMarker;
                    }
                }

                @Override
                public boolean isEndMarker(String element) {
                    return endOfFileMarker.equals(element);
                }
            };

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return producerFactory;
    }

    private ProducerConsumerFactory<String, String> getUpperCaseProcessor() {
        ProducerConsumerFactory<String, String> factory = new ProducerConsumerFactory<String, String>() {
            @Override
            public String produceConsume(String inputElement) {
                if (inputElement != null)
                    return inputElement.toUpperCase();
                return null;
            }

            @Override
            public boolean isEndMarker(String element) {
                return element == null || endOfFileMarker.equals(element);
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
                    if (!endOfFileMarker.equals(data))
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
                    return data == null || endOfFileMarker.equals(data);
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consumerFactory;
    }


}
