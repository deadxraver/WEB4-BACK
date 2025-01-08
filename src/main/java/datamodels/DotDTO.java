package datamodels;

import java.time.LocalDateTime;

public class DotDTO {

	private final Double x, y, r;
	private final Long execTime;
	private final LocalDateTime curTime;
	private final boolean hit;

	public DotDTO(Dot dot) {
		this.x = dot.getX();
		this.y = dot.getY();
		this.r = dot.getR();
		this.execTime = dot.getExecTime();
		this.curTime = dot.getCurTime();
		this.hit = dot.getHit();
	}

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}

	public Double getR() {
		return r;
	}

	public Long getExecTime() {
		return execTime;
	}

	public LocalDateTime getCurTime() {
		return curTime;
	}

	public boolean isHit() {
		return hit;
	}

	@Override
	public String toString() {
		return "{ x: %f, y: %f, r: %f, hit: %b, execTime: %d, curTime: %s }".formatted(
				this.x, this.y, this.r, this.hit, this.execTime, this.curTime.toString()
		);
	}
}
