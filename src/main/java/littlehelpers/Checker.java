package littlehelpers;

public record Checker(double x, double y, double r) {

	public boolean checkAll() {
		return checkFirstTerm() || checkSecondTerm() || checkThirdTerm() || checkFourthTerm();
	}

	private boolean checkFirstTerm() {
		return x >= 0 && y >= 0 &&
				x * x + y * y <= r * r;
	}

	private boolean checkSecondTerm() {
		return x >= 0 && y <= 0 &&
				y >= x - r / 2;
	}

	private boolean checkThirdTerm() {
		return x <= 0 && y <= 0 &&
				x >= -r / 2 && y >= -r;
	}

	private boolean checkFourthTerm() {
		return false;
	}
}
