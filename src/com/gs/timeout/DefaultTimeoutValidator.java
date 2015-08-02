package com.gs.timeout;

import java.io.Serializable;

import com.gs.timeout.model.TimeoutCounter;
import com.gs.timeout.model.UnitTime;

/**
 * 
 * @author Administrator
 * 
 */
public class DefaultTimeoutValidator implements TimeoutValidator, Serializable {

	private static final long serialVersionUID = 537087455643573408L;
	
	private TimeoutCounter timeoutCounter;

	/**
	 * @param maxTimeoutTimes
	 *            Max timeout times. Once the counter is larger than the max
	 *            times, the method isTimeout() returns TRUE.
	 */
	public DefaultTimeoutValidator(Integer maxTimeoutTimes) {
		timeoutCounter = new TimeoutCounter(maxTimeoutTimes);
	}

	@Override
	public Boolean isTimeout() {
		return timeoutCounter.getTimeoutCounter().get() > timeoutCounter
				.getMaxTimeoutTimes();
	}

	@Override
	public void updateLastCheckpoint(UnitTime lastCheckpoint) {
		timeoutCounter.getLastCheckpoint().setTime(lastCheckpoint.getTime());
		timeoutCounter.getLastCheckpoint().setUnit(lastCheckpoint.getUnit());
		timeoutCounter.getTimeoutCounter().set(0);
	}

	@Override
	public void checkLastCheckpoint() {
		if (timeoutCounter.getLastCheckpoint().getUnit()
				.toMillis(timeoutCounter.getLastCheckpoint().getTime()) <= timeoutCounter
				.getLastCheckpoint().getUnit()
				.toMillis(timeoutCounter.getLastCheckpoint().getTime())) {
			timeoutCounter.getTimeoutCounter().addAndGet(1);
		}
		timeoutCounter.getLast2Checkpoint().setTime(
				timeoutCounter.getLastCheckpoint().getTime());
		timeoutCounter.getLast2Checkpoint().setUnit(
				timeoutCounter.getLastCheckpoint().getUnit());
	}

}
