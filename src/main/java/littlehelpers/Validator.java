package littlehelpers;

public record Validator(double x, double y, double r) {
	public boolean validateAll() {
		return validateR() && validateX() && validateY();
	}

	private boolean validateX() {
		if (x >= -4 && x <= 4) return true;
		System.err.println("x is out of range");
		return false;
	}

	private boolean validateY() {
		if (y >= -3 && y <= 3) return true;
		System.err.println("y is out of range");
		return false;
	}

	private boolean validateR() {
		if (r >= 0 && r <= 4) return true;
		System.err.println("r is out of range");
		return false;
	}
}
