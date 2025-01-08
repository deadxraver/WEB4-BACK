package datamodels;

import java.time.LocalDateTime;
import java.util.LongSummaryStatistics;

public class DotDTO {

	private Double x, y, r;
	private Long execTime;
	private LocalDateTime curTime;

	public DotDTO(Dot dot) {
		this.x = dot.getX();
		this.y = dot.getY();
		this.r = dot.getR();
		this.execTime = dot.getExecTime();
		this.curTime = dot.getCurTime();
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

	@Override
	public String toString() {
		return "{ x: %f, y: %f, r: %f, execTime: %d, curTime: %s }".formatted(
				this.x, this.y, this.r, this.execTime, this.curTime.toString()
		);
	}
}
