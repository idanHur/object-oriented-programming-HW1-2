package id_318247822;

public class SickCitizen extends Citizen implements Coronable {

	private int daysOfSick;
	private boolean isProtected;

	public SickCitizen(String name, int id, int birthYear, PollingStation<?> pollingStation, int sickDays,
			boolean protect) throws Exception {
		super(name, id, birthYear, pollingStation);
		setDayOfSick(sickDays);
		setIsProtected(protect);
	}

	public SickCitizen(String name, int id, int birthYear, int sickDays, boolean protect) throws Exception {
		super(name, id, birthYear);
		setDayOfSick(sickDays);
		setIsProtected(protect);
	}

	public boolean setDayOfSick(int days) {
		if (days > 0) {
			daysOfSick = days;
			return true;
		}

		return false;
	}

	@Override
	public int getDayOfSick() {
		return daysOfSick;
	}

	public boolean setIsProtected(boolean is) {
		isProtected = is;
		return true;
	}

	@Override
	public boolean getIsProtected() {
		return isProtected;
	}

}
