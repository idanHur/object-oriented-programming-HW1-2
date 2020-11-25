package id_318247822;

public class Soldier extends Citizen implements Soldierable {

	private final int MAX_AGE = 21;
	private boolean CarryWeapon;

	public Soldier(String name, int id, int birthYear, PollingStation<?> pollingStation, boolean weapon)
			throws Exception {
		super(name, id, birthYear, pollingStation);
		setCarryWeapon(weapon);

	}

	public Soldier(String name, int id, int birthYear, boolean weapon) throws Exception {
		super(name, id, birthYear);
		setCarryWeapon(weapon);
	}

	@Override
	public boolean getCarryWeapon() {
		return CarryWeapon;
	}

	@Override
	public boolean setCarryWeapon(boolean carrys) {
		CarryWeapon = carrys;
		return true;
	}

	@Override
	public void setBirthYear(int birthYear) throws Exception {
		if ((Election.getYear() - birthYear) > MIN_AGE && (Election.getYear() - birthYear) < MAX_AGE) {
			throw new Exception("Soldier age must be between 18 to 21");
		}
		this.birthYear = birthYear;
	}

}
