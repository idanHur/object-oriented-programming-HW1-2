package id_318247822;

import java.util.Vector;

public class PollingStation<T extends Citizen> {

	private static int serialNumCounter = 1;

	protected int serialNum;
	protected String address;
	protected Vector<T> stationVoters;
	protected double votingPresenteg;
	protected int numOfVoters;
	protected int numThatVoted;
	protected Vector<Integer> voteRes;

	public PollingStation(String address) {
		serialNum = serialNumCounter;
		serialNumCounter++;
		this.address = address;
		stationVoters = new Vector<T>();
		voteRes = new Vector<Integer>();
	}

	public boolean addVoter(T other) {
		stationVoters.add(other);
		numOfVoters++;
		return true;
	}

	public boolean calculateVotingPresenteg() {
		if (numOfVoters == 0) {
			System.out.println("there is no voters");
			return false;
		}
		votingPresenteg = ((double) numThatVoted / (double) numOfVoters) * 100;
		return true;
	}

	public void vote(int partyNum) {
		numThatVoted++;
		int numAtPlace = voteRes.get(partyNum);
		voteRes.set(partyNum, (numAtPlace + 1));
		calculateVotingPresenteg();
	}

	public boolean equals(Object other) {
		if (!(other instanceof PollingStation)) {
			return false;
		}
		PollingStation<?> s = ((PollingStation<?>) other);
		if (s.getSerialNum() == this.serialNum && s.getAddress().equals(this.address)
				&& s.getNumOfVoters() == this.numOfVoters) {
			return true;
		}
		return false;
	}

	public String getAddress() {
		return address;
	}

	public boolean setAddress(String address) {
		this.address = address;
		return true;
	}

	public int getSerialNum() {
		return serialNum;
	}

	public Vector<T> getStationVoters() {
		return stationVoters;
	}

	public double getVotingPresenteg() {
		return votingPresenteg;
	}

	public int getNumOfVoters() {
		return numOfVoters;
	}

	public int getNumThatVoted() {
		return numThatVoted;
	}

	public Vector<Integer> getVoteRes() {
		return voteRes;
	}

	public String getVotersList() {
		String s = "";
		for (int i = 0; i < stationVoters.size(); i++) {
			if (stationVoters.get(i) != null) {
				s += stationVoters.get(i).toString();
				s += "\n";
			}
		}
		return s;
	}

	public String toString() {
		return "Polling station located in :" + this.address + " number: " + getSerialNum() + " has: " + numOfVoters
				+ " Eligable voters";
	}

	public String showInfo() {
		return toString() + "\n" + getVotersList();
	}

	public boolean setVoteResForElection(int size) {
		for (int i = 0; i < size; i++) {
			voteRes.add(0);
		}
		return true;
	}

}
