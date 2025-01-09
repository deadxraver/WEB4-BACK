package datamodels;

public class UserDTO {

	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getLogin();
	}

	public Long id;
	public String username;

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "{ \"id\": %d, \"login\": \"%s\" }".formatted(this.id, this.username);
	}
}
