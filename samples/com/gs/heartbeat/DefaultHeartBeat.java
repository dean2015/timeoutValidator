package com.gs.heartbeat;

import java.io.Serializable;

import com.gs.timeout.model.TimeoutCounter;
import com.gs.timeout.model.UnitTime;

/**
 * Same code with DefaultTimeoutValidator.
 * 
 * Hope it will help to understand the usage of this project.
 * 
 * @author gs
 * 
 */
public class DefaultHeartBeat implements HeartBeat, Serializable {

	private static final long serialVersionUID = -1135045526612332804L;

	private TimeoutCounter timeoutCounter;

	/**
	 * @param maxHeartBeatStopTimes
	 */
	public DefaultHeartBeat(Integer maxHeartBeatStopTimes) {
		timeoutCounter = new TimeoutCounter(maxHeartBeatStopTimes);
	}

	@Override
	public Boolean isActiveHeartBeatStopped() {
		return timeoutCounter.getTimeoutCounter().get() > timeoutCounter
				.getMaxTimeoutTimes();
	}

	@Override
	public void updateActiveLastHearBeatTimepoint(UnitTime LastHearBeatTimepoint) {
		timeoutCounter.getLastCheckpoint().setTime(LastHearBeatTimepoint.getTime());
		timeoutCounter.getLastCheckpoint().setUnit(LastHearBeatTimepoint.getUnit());
		timeoutCounter.getTimeoutCounter().set(0);
	}

	@Override
	public Boolean checkActiveLashHeartBeatTimepoint() {
		Boolean result = Boolean.FALSE;
		if (timeoutCounter.getLastCheckpoint().getUnit()
				.toMillis(timeoutCounter.getLastCheckpoint().getTime()) <= timeoutCounter
				.getLastCheckpoint().getUnit()
				.toMillis(timeoutCounter.getLastCheckpoint().getTime())) {
			timeoutCounter.getTimeoutCounter().addAndGet(1);
			result = Boolean.TRUE;
		}
		timeoutCounter.getLast2Checkpoint().setTime(
				timeoutCounter.getLastCheckpoint().getTime());
		timeoutCounter.getLast2Checkpoint().setUnit(
				timeoutCounter.getLastCheckpoint().getUnit());
		return result;
	}

}
