package timertask;

import java.util.Timer;
import java.util.TimerTask;

public final class ServerTimer {
	private static final Timer timer = new Timer();

	public static final void addTask(TimerTask task, int gapHr) {
		timer.scheduleAtFixedRate(task, 60000, gapHr * 60 * 60 * 1000);
	}
}
