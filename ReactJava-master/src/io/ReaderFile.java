package io;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;


public class ReaderFilePublisher extends SubmissionPublisher<File> {
//    private Subscription subscription;
//TODO create Path

    @Override
    public void subscribe(Flow.Subscriber<? super File> subscriber) {
        super.subscribe(subscriber);
    }

    public void iterateFiles(Path path) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            stream.forEach(path1 -> {
                if (path1.toFile().isDirectory()) {
                    iterateFiles(path1);
                } else {
                    if (path1.getFileName().toString().matches(".*\\.jpe?g$")) {
                    validateAndSubmit(path1.toFile());
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void validateAndSubmit(File file){
        //TODO validate file
       System.out.println("Submit file" + file.getName()+ "thread:"+ Thread.currentThread.getName());
       submit(file);
    }
}
