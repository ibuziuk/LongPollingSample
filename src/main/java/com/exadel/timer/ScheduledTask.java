package com.exadel.timer;

import java.util.Date;
import java.util.TimerTask;

import com.exadel.processor.AssyncProcessor;

public class ScheduledTask extends TimerTask {
	public void run() {
		AssyncProcessor.notifyAllClients(new Date().toString());
	}
}