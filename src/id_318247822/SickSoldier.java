package id_318247822;

public class SickSoldier extends Citizen implements Coronable, Soldierable {

	private int daysOfSick;
	private boolean isProtected;
	private boolean CarryWeapon;

	public SickSoldier(String name, int id, int birthYear, boolean weapon, int sickDays, boolean protect)
			throws Exception {
		super(name, id, birthYear);
		setCarryWeapon(weapon);
		setDayOfSick(sickDays);
		setIsProtected(protect);

	}

	public SickSoldier(String name, int id, int birthYear, PollingStation<?> pollingStation, boolean weapon,
			int sickDays, boolean protect) throws Exception {
		super(name, id, birthYear, pollingStation);
		setCarryWeapon(weapon);
		setDayOfSick(sickDays);
		setIsProtected(protect);
	}

	@Override
	public int getDayOfSick() {
		return daysOfSick;
	}

	public boolean setDayOfSick(int days) {
		if (days > 0) {
			daysOfSick = days;
			return true;
		}
		return false;
	}

	@Override
	public boolean getIsProtected() {
		return isProtected;
	}

	public boolean setIsProtected(boolean protecte) {
		isProtected = protecte;
		return true;
	}

	@Override
	public boolean setCarryWeapon(boolean is) {
		CarryWeapon = is;
		return true;
	}

	@Override
	public boolean getCarryWeapon() {
		return CarryWeapon;
	}

}
