package datamodels;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "DOTS")
public class Dot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "x", nullable = false)
	private Double x;

	@Column(name = "y", nullable = false)
	private Double y;

	@Column(name = "r", nullable = false)
	private Double r;

	@Column(name = "hit", nullable = false)
	private boolean hit;

	@Column(name = "exec_time", nullable = false)
	private Long execTime;

	@Column(name = "cur_time", nullable = false)
	private LocalDateTime curTime;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getR() {
		return r;
	}

	public void setR(Double r) {
		this.r = r;
	}

	public Long getExecTime() {
		return execTime;
	}

	public void setExecTime(Long execTime) {
		this.execTime = execTime;
	}

	public LocalDateTime getCurTime() {
		return curTime;
	}

	public void setCurTime(LocalDateTime curTime) {
		this.curTime = curTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean getHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	@Override
	public String toString() {
		return "{ \"x\": %f, \"y\": %f, \"r\": %f, \"hit\": %b, \"execTime\": %d, \"curTime\": \"%s\" }".formatted(
				this.x, this.y, this.r, this.hit, this.execTime, this.curTime.toString()
		);
	}
}
