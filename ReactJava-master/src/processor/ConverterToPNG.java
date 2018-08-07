package processor;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Flow;

public class ConverterToPNGProcessor extends SubmissionPublisher<File>implements Flow.Processor<File, File> {
	private Flow.Subscription subscription;

	@Override
	public void subscribe(Flow.Subscriber<? super File> subscriber) {
super.subscribe(subscriber);
	}

	@Override
	public void onSubscribe(Flow.Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);

	}

	@Override
	public void onNext(File item) {
		convertImage(item);
		submit(item);
		subscription.request(1);
		
	}

	@Override
	public void onError(Throwable throwable) {
		throwable.printStackTrace();
	}

	@Override
	public void onComplete() {
		subscription.cancel();
		System.out.println("Processor complete");
	}

	public File convertImage(File file){
String fileName = file.getAbsolutePath();
if (fileName.endsWith("jpg".toLowerCase())|| fileName.endsWith(".jpeg".toLowerCase())) {
	fileName = fileName.substring(0, fileName.lastIndexOf(".")) + ".png");
}
        File pngFile = new File(fileName);
        try {
        	BufferedImage input = ImageIO.read(file);
            ImageIO.write((input, "PNG", pngFile);
        } catch (IOException e) {
e.printStackTrace();
        }
        return pngFile;
    }

}
