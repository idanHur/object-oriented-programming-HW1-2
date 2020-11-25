package id_318247822;

public class Candidate extends Citizen implements Comparable<Candidate> {
	private int primarizNum;
	private Party candidateOfParty;

	public Candidate(String name, int id, int birthYear, PollingStation<?> pollingStation, int primarizNum, Party party)
			throws Exception {
		super(name, id, birthYear, pollingStation);
		this.primarizNum = primarizNum;
		candidateOfParty = party;

	}

	public Candidate(String name, int id, int birthYear, int primarizNum, Party party) throws Exception {
		super(name, id, birthYear);
		this.primarizNum = primarizNum;
		candidateOfParty = party;

	}

	public Candidate(String name, int id, int birthYear, int primarizNum) throws Exception {
		super(name, id, birthYear);
		this.primarizNum = primarizNum;
	}

	public Candidate(String name, int id, int birthYear, PollingStation<?> pollingStation, int primarizNum)
			throws Exception {
		super(name, id, birthYear, pollingStation);
		this.primarizNum = primarizNum;
	}

	public Candidate(Candidate other) {
		super(other);
		primarizNum = other.getPrimarizNum();
		candidateOfParty = other.getCandidateOfParty();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Candidate)) {
			return false;
		}
		if (super.equals(other)) {
			return false;
		}
		if (!(candidateOfParty.equals(((Candidate) other).getCandidateOfParty()))) {
			return false;
		}
		if (!(primarizNum == ((Candidate) other).getPrimarizNum())) {
			return false;
		}
		return true;
	}

	public Party getCandidateOfParty() {
		return candidateOfParty;
	}

	public void setCandidateOfParty(Party candidateOfParty) {
		this.candidateOfParty = candidateOfParty;
	}

	public int getPrimarizNum() {
		return primarizNum;
	}

	public boolean setPrimarizNum(int primarizNum) {
		this.primarizNum = primarizNum;
		return true;
	}

	public String toString() {
		return super.toString() + " in party: " + candidateOfParty.getName() + " primariz num: " + primarizNum;
	}

	@Override
	public int compareTo(Candidate o) {
		if (this.primarizNum > o.getPrimarizNum())
			return 1;
		if (primarizNum < o.getPrimarizNum())
			return -1;
		return 0;
	}
}
