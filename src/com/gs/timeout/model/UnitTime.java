package com.gs.timeout.model;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * The time with an unit.
 * 
 * @author gs
 * 
 */
public class UnitTime implements Serializable {

	private static final long serialVersionUID = -8051804820450321388L;

	private Long time;

	private TimeUnit unit;

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public TimeUnit getUnit() {
		return unit;
	}

	public void setUnit(TimeUnit unit) {
		this.unit = unit;
	}

	public UnitTime(Long time, TimeUnit unit) {
		this.time = time;
		this.unit = unit;
	}

}
