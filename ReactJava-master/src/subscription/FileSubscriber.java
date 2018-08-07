package subscription;

import java.io.File;

import processor.Flow;

public class FileSubscriber implements Flow.Subscriber<File> {
	private Flow.Subscription subscription;
	@Override
	public void onSubscribe(Flow.Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
		

	}

	@Override
	public void onNext(File item) {
		subscription.request(1);
		System.out.println("File " +item.getName()+ "delete" + item.delete()+"thread:"+ Thread.currentThread.getName());

	}

	@Override
	public void onError(Throwable throwable) {
		throwable.printStackTrace();
	}

	@Override
	public void onComplete() {
		
		System.out.println("FileSubscription complete");
	}
}
