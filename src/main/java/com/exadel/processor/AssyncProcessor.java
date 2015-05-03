package com.exadel.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

public final class AssyncProcessor {
	private final static Queue<AsyncContext> storage = new ConcurrentLinkedQueue<AsyncContext>();

	public static void notifyAllClients(final String message) {
		for (AsyncContext ac : storage) {
			try {
				final PrintWriter writer = ac.getResponse().getWriter();
				writer.println(message);
				writer.flush();
				ac.complete();
			} catch (IOException ex) {
				// Connection was most likely closed by client -> clean up in finally
			} finally {
				storage.remove(ac);
			}
		}
	}

	public static void addAsyncContext(final AsyncContext context) {
		context.addListener(new AsyncListener() {

			public void onTimeout(AsyncEvent event) throws IOException {
				removeAsyncContext(context);
			}

			public void onStartAsync(AsyncEvent event) throws IOException {
			}

			public void onError(AsyncEvent event) throws IOException {
				removeAsyncContext(context);
			}

			public void onComplete(AsyncEvent event) throws IOException {
				removeAsyncContext(context);
			}
		});

		storage.add(context);

	}

	private static void removeAsyncContext(final AsyncContext context) {
		storage.remove(context);
	}

}
