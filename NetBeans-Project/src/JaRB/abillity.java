package JaRB;

public class abillity {

	projectil geschoss;
	double abklingzeit;
	
	public abillity(projectil geschoss, double abklingzeit) {
		this.geschoss = geschoss;
		this.abklingzeit = abklingzeit;
	}
	
	private void update() {
		if (abklingzeit > 0) {
			--abklingzeit;
		}
		geschoss.update();
	}
	
}
