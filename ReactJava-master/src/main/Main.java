package main;

import java.nio.file.Paths;

import io.ReaderFile;

public class Main {
    public static void main(String[] args) {
      ReaderFile readerFile = newReaderFilePublisher();
      ConverterToPNG converterToPNG = new ConverterToPNGProcessor();
      FileSubscriber fileSubscriber = new FileSubscriber();
      readerFile.subscribe(converterToPNG);
      converterToPNG.subscribe(fileSubsciber);
      Path path = Paths.get("D:\\simpleReactiveNio\\src\\image");
      rederFile.iterateFiles(path);
      
        }
    }

