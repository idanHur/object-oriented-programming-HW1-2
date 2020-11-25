package id_318247822;

public class Citizen {

	protected static final int MIN_AGE = 18;

	protected String name;
	protected int id;
	protected int birthYear;
	protected PollingStation<?> pollingStation;
	protected boolean voted;

	public Citizen(String name, int id, int birthYear, PollingStation<?> pollingStation) throws Exception {
		setName(name);
		setId(id);
		setBirthYear(birthYear);
		setPollingStation(pollingStation);
		voted = false;

	}

	public Citizen(String name, int id, int birthYear) throws Exception {
		setName(name);
		setId(id);
		;
		setBirthYear(birthYear);
		;
		voted = false;
	}

	public Citizen(Citizen b) {
		this.name = b.getName();
		this.id = b.getId();
		this.birthYear = b.getBirthYear();
		this.pollingStation = b.pollingStation;
		this.voted = b.voted;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Citizen)) {
			return false;
		}
		Citizen s = (Citizen) other;
		if (s.getId() == id && s.getName().equals(name) && s.getBirthYear() == birthYear) {
			return true;
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public boolean isVoted() {
		return voted;
	}

	public boolean setVoted(boolean voted) {
		this.voted = voted;
		return true;
	}

	public boolean setName(String name) {
		this.name = name;
		return true;
	}

	public PollingStation<?> getPollingStation() {
		return pollingStation;
	}

	public int getAge(Election election) {
		return (birthYear - Election.getYear());
	}

	public boolean setPollingStation(PollingStation<?> pollingStationNum) {
		this.pollingStation = pollingStationNum;
		return true;
	}

	public int getId() {
		return id;
	}

	private void setId(int id) throws Exception {
		int length = String.valueOf(id).length();
		if (length != 9) {
			throw new Exception("Id must be 9 digits");
		}
		this.id = id;
	}

	public void setBirthYear(int birthYear) throws Exception {
		if ((Election.getYear() - birthYear) < MIN_AGE) {
			throw new Exception("voter min age must be 18");
		}
		this.birthYear = birthYear;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public String toString() {
		return "name: " + name + " id: " + id + " birth year: " + birthYear + " polling station number: "
				+ pollingStation.getSerialNum() + " has voted: " + voted;
	}

}
