package launcher;

import timertask.OverdueOrderTask;
import timertask.ServerTimer;

public final class TimerTaskLauncher {
	public static final void launch() {
		launchOverdueTimerTask();
	}

	private static final void launchOverdueTimerTask() {
		ServerTimer.addTask(OverdueOrderTask.launch(), 1);
	}

	private TimerTaskLauncher() {
	}
}
