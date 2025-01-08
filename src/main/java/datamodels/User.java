package datamodels;

import db.DBManager;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "login", nullable = false, unique = true)
	private String login;

	@Column(name = "hashed_password", nullable = false)
	private String hashedPassword;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Dot> dots;

	public List<DotDTO> getDTODots() {
		List<DotDTO> dtoDots = new ArrayList<>();
		dots.forEach((item) -> {
			dtoDots.add(new DotDTO(item));
		});
		return dtoDots;
	}

	public void addDot(Dot dot) {
		this.dots.add(dot);
		dot.setUser(this);
		DBManager.getEntityManager().getTransaction().begin();
		DBManager.getEntityManager().merge(this);
		DBManager.getEntityManager().flush();
		DBManager.getEntityManager().getTransaction().commit();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public List<Dot> getDots() {
		return dots;
	}

	public void setDots(List<Dot> dots) {
		this.dots = dots;
	}
}
