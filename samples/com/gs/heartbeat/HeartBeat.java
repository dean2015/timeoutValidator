package com.gs.heartbeat;

import java.util.concurrent.TimeUnit;

import com.gs.timeout.model.UnitTime;

public interface HeartBeat {

	/**
	 * If Active heart beat has stopped.
	 * 
	 * @return
	 */
	Boolean isActiveSideHeartBeatStopped();

	void updateActiveSideLastHearBeatTimepoint(UnitTime LastHearBeatTimepoint);

	void updateActiveSideLastHearBeatTimepoint(Long time, TimeUnit unit);

	Boolean checkActiveSideLastHeartBeatTimepoint();

	void reset();

	int getCurrentStopCounter();

}
