package id_318247822;

import java.util.Scanner;
import java.util.Vector;

public class Election {

	private static int year;
	private int month;
	private boolean isConcluded;
	private Vector<Party> partys;
	private Set<Citizen> citizens;
	private Vector<PollingStation<Citizen>> pollStationsCitizen;
	private Vector<PollingStation<SickCitizen>> pollStationsSickCitizen;
	private Vector<PollingStation<Soldier>> pollStationsSoldier;
	private Vector<PollingStation<SickSoldier>> pollStationsSickSoldier;
	private int stationCount;
	private int CitizenCount;
	private int partysCount;

	public Election(int year, int month) {
		if (!setYear(year)) {
			Election.year = 2000;
		}
		if (!setMonth(month)) {
			this.month = 1;
		}
		isConcluded = false;
		citizens = new Set<>();
		partys = new Vector<>();
		pollStationsCitizen = new Vector<>();
		pollStationsSickCitizen = new Vector<>();
		pollStationsSickSoldier = new Vector<>();
		pollStationsSoldier = new Vector<>();

	}

	public boolean addParty(Party p) {

		partys.add(p);
		partysCount++;
		return true;

	}

	public boolean addCitizen(Citizen c) {

		citizens.add(c);
		CitizenCount++;
		return true;

	}

	public boolean addCitizenToStation(Citizen c, PollingStation pol) {
		addCitizen(c);

		if (c.getPollingStation() == null) {
			c.setPollingStation(pol);
		}

		if (c.getClass() == Soldier.class) {
			if (!(findStationByIdSoldier(pol.getSerialNum()).addVoter((Soldier) c))) {
				pollStationsSoldier.get((pollStationsSoldier.size() - 1)).addVoter((Soldier) c);
			}
		}

		if (c.getClass() == SickSoldier.class) {
			if (!(findStationByIdSickSoldier(pol.getSerialNum()).addVoter((SickSoldier) c))) {
				pollStationsSickSoldier.get((pollStationsSickSoldier.size() - 1)).addVoter((SickSoldier) c);
			}
		}
		if (c.getClass() == SickCitizen.class) {
			if (!(findStationByIdForSickCitizen(pol.getSerialNum()).addVoter((SickCitizen) c))) {
				pollStationsSickCitizen.get((pollStationsSickCitizen.size() - 1)).addVoter((SickCitizen) c);
			}
		}

		if (c.getClass() == Citizen.class) {
			if (!(findStationByIdForCitizen(pol.getSerialNum()).addVoter((Citizen) c))) {
				pollStationsCitizen.get((pollStationsCitizen.size() - 1)).addVoter((Citizen) c);
			}
		}

		return true;
	}

	public boolean addCandidate(Candidate c) throws Exception {
		citizens.add(c);
		if (c.getPollingStation() != null && !(pollStationsCitizen.contains(c))) {
			findStationByIdForCitizen(c.getPollingStation().getSerialNum()).addVoter(c);
		}
		if (c.getCandidateOfParty() != null)
			if (partys.contains(c.getCandidateOfParty()))
				if (!(findPartyByName(c.getCandidateOfParty().getName()).getCandidates().contains(c)))
					return findPartyByName(c.getCandidateOfParty().getName()).addCandidate(c, c.getPrimarizNum());

		if ((c.getCandidateOfParty() != null) && !(partys.contains(c.getCandidateOfParty()))) {
			addParty(c.getCandidateOfParty());
			if (!(findPartyByName(c.getCandidateOfParty().getName()).getCandidates().contains(c))) {
				return findPartyByName(c.getCandidateOfParty().getName()).addCandidate(c, c.getPrimarizNum());
			}
			return true;
		}
		return false;
	}

	public boolean addCandidateWithPartyName(Candidate c, String partyName) throws Exception {
		citizens.add(c);
		if (c.getPollingStation() != null && !(pollStationsCitizen.contains(c))) {
			findStationByIdForCitizen(c.getPollingStation().getSerialNum()).addVoter(c);
		}
		if (partys.contains(partyName))
			if (!(findPartyByName(partyName).getCandidates().contains(c)))
				return findPartyByName(partyName).addCandidate(c, c.getPrimarizNum());

		if ((c.getCandidateOfParty() != null) && !(partys.contains(c.getCandidateOfParty()))) {
			addParty(c.getCandidateOfParty());
			if (!(findPartyByName(c.getCandidateOfParty().getName()).getCandidates().contains(c))) {
				return findPartyByName(c.getCandidateOfParty().getName()).addCandidate(c, c.getPrimarizNum());
			}
			return true;
		}
		return false;
	}

	public boolean addPollStationsCitizen(PollingStation<Citizen> p) {
		pollStationsCitizen.add(p);
		return true;
	}

	public boolean addPollStationsSickCitizen(PollingStation<SickCitizen> p) {
		pollStationsSickCitizen.add(p);
		return true;
	}

	public boolean addPollStationsSoldier(PollingStation<Soldier> p) {
		pollStationsSoldier.add(p);
		return true;
	}

	public boolean addPollStationsSickSoldier(PollingStation<SickSoldier> p) {
		pollStationsSickSoldier.add(p);
		return true;
	}

	public String showAllCitizens() {
		StringBuffer str = new StringBuffer();
		str.append("citizens:\n");
		for (int i = 0; i < citizens.size(); i++)
			str.append(citizens.get(i).toString() + "\n");

		return str.toString();
	}

	public String showAllPartys() {
		StringBuffer str = new StringBuffer();
		str.append("partys:\n");
		for (int i = 0; i < partys.size(); i++)
			str.append(partys.get(i).toString() + "\n");

		return str.toString();
	}

	public String showAllPartysFullInfo() {
		StringBuffer str = new StringBuffer();
		str.append("partys:\n");
		for (int i = 0; i < partys.size(); i++)
			str.append(partys.get(i).toStringFullInfo() + "\n");

		return str.toString();
	}

	public String showAllPollingStations() {
		StringBuffer str = new StringBuffer();
		str.append("polling stations:\n");
		for (int i = 0; i < pollStationsCitizen.size(); i++)
			str.append(pollStationsCitizen.get(i).toString() + "\n");

		for (int i = 0; i < pollStationsSickCitizen.size(); i++)
			str.append(pollStationsSickCitizen.get(i).toString() + "\n");

		for (int i = 0; i < pollStationsSoldier.size(); i++)
			str.append(pollStationsSoldier.get(i).toString() + "\n");

		for (int i = 0; i < pollStationsSickSoldier.size(); i++)
			str.append(pollStationsSickSoldier.get(i).toString() + "\n");

		return str.toString();

	}

	public String showCitizenPollingStations() {
		StringBuffer str = new StringBuffer();
		str.append("polling stations:\n");
		for (int i = 0; i < pollStationsCitizen.size(); i++)
			str.append(pollStationsCitizen.get(i).toString() + "\n");

		return str.toString();

	}

	public String showSickCitizenPollingStations() {
		StringBuffer str = new StringBuffer();
		str.append("polling stations:\n");
		for (int i = 0; i < pollStationsSickCitizen.size(); i++)
			str.append(pollStationsSickCitizen.get(i).toString() + "\n");

		return str.toString();

	}

	public String showSoldierPollingStations() {
		StringBuffer str = new StringBuffer();
		str.append("polling stations:\n");
		for (int i = 0; i < pollStationsSoldier.size(); i++)
			str.append(pollStationsSoldier.get(i).toString() + "\n");

		return str.toString();

	}

	public String showSickSoldierPollingStations() {
		StringBuffer str = new StringBuffer();
		str.append("polling stations:\n");

		for (int i = 0; i < pollStationsSickSoldier.size(); i++)
			str.append(pollStationsSickSoldier.get(i).toString() + "\n");

		return str.toString();

	}

	public String showAllPollingStationsFullInfo() {
		StringBuffer str = new StringBuffer();
		str.append("polling stations:\n");
		for (int i = 0; i < pollStationsCitizen.size(); i++)
			str.append(pollStationsCitizen.get(i).showInfo() + "\n");

		for (int i = 0; i < pollStationsSickCitizen.size(); i++)
			str.append(pollStationsSickCitizen.get(i).showInfo() + "\n");

		for (int i = 0; i < pollStationsSoldier.size(); i++)
			str.append(pollStationsSoldier.get(i).showInfo() + "\n");

		for (int i = 0; i < pollStationsSickSoldier.size(); i++)
			str.append(pollStationsSickSoldier.get(i).showInfo() + "\n");

		return str.toString();
	}

	public String showCitizenPollingStationsFullInfo() {
		StringBuffer str = new StringBuffer();
		str.append("polling stations:\n");
		for (int i = 0; i < pollStationsCitizen.size(); i++)
			str.append(pollStationsCitizen.get(i).showInfo() + "\n");

		return str.toString();
	}

	public String showSickCitizenPollingStationsFullInfo() {
		StringBuffer str = new StringBuffer();
		str.append("polling stations:\n");
		for (int i = 0; i < pollStationsSickCitizen.size(); i++)
			str.append(pollStationsSickCitizen.get(i).showInfo() + "\n");

		return str.toString();
	}

	public String showSoldierPollingStationsFullInfo() {
		StringBuffer str = new StringBuffer();
		str.append("polling stations:\n");
		for (int i = 0; i < pollStationsSoldier.size(); i++)
			str.append(pollStationsSoldier.get(i).showInfo() + "\n");

		return str.toString();
	}

	public String showSickSoldierPollingStationsFullInfo() {
		StringBuffer str = new StringBuffer();
		str.append("polling stations:\n");
		for (int i = 0; i < pollStationsSickSoldier.size(); i++)
			str.append(pollStationsSickSoldier.get(i).showInfo() + "\n");

		return str.toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof Election)) {
			return false;
		}
		Election s = (Election) other;
		if (this.month != s.getMonth()) {
			return false;
		}
		if (this.year != s.getYear()) {
			return false;
		}
		for (int i = 0; i < citizens.size(); i++) {
			if (s.getCitizens().size() > i) {
				if (!(this.citizens.contains(s.getCitizens().get(i)))) {
					return false;
				}
			}
		}
		for (int i = 0; i < partys.size(); i++) {
			if (s.getPartys().size() > i) {
				if (!(this.partys.contains(s.getPartys().get(i)))) {
					return false;
				}
			}
		}
		for (int i = 0; i < pollStationsCitizen.size(); i++)
			if (s.getPollStationsCitizen().size() > i)
				if (!(this.pollStationsCitizen.contains(s.getPollStationsCitizen().get(i))))
					return false;
		for (int i = 0; i < pollStationsSickCitizen.size(); i++)
			if (s.getPollStationsSickCitizen().size() > i)
				if (!(this.pollStationsSickCitizen.contains(s.getPollStationsSickCitizen().get(i))))
					return false;
		for (int i = 0; i < pollStationsSoldier.size(); i++)
			if (s.getPollStationsSoldier().size() > i)
				if (!(this.pollStationsSoldier.contains(s.getPollStationsSoldier().get(i))))
					return false;

		for (int i = 0; i < pollStationsSickSoldier.size(); i++)
			if (s.getPollStationsSickSoldier().size() > i)
				if (!(this.pollStationsSickSoldier.contains(s.getPollStationsSickSoldier().get(i))))
					return false;

		return true;
	}

	public Vector<Party> getPartys() {
		return partys;
	}

	public Set<Citizen> getCitizens() {
		return citizens;
	}

	public Vector<PollingStation<SickSoldier>> getPollStationsSickSoldier() {
		return pollStationsSickSoldier;
	}

	public Party findPartyByName(String name) throws Exception {
		for (int i = 0; i < partys.size(); i++) {
			if (partys.get(i) != null && partys.get(i).getName().equalsIgnoreCase(name))
				return partys.get(i);
		}
		throw new Exception("cant find party");
	}

	public Citizen findVoterByID(int ID) {
		for (int i = 0; i < citizens.size(); i++) {
			if (ID == citizens.get(i).getId())
				return citizens.get(i);
		}
		return null;
	}

	public PollingStation<Citizen> findStationByIdForCitizen(int ID) {
		for (int i = 0; i < pollStationsCitizen.size(); i++)
			if (pollStationsCitizen.get(i).getSerialNum() == ID)
				return pollStationsCitizen.get(i);

		return null;
	}

	public PollingStation<Soldier> findStationByIdSoldier(int ID) {
		for (int i = 0; i < pollStationsSoldier.size(); i++)
			if (pollStationsSoldier.get(i).getSerialNum() == ID)
				return pollStationsSoldier.get(i);
		return null;
	}

	public PollingStation<SickSoldier> findStationByIdSickSoldier(int ID) {

		for (int i = 0; i < pollStationsSickSoldier.size(); i++)
			if (pollStationsSickSoldier.get(i).getSerialNum() == ID)
				return pollStationsSickSoldier.get(i);

		return null;
	}

	public PollingStation<SickCitizen> findStationByIdForSickCitizen(int ID) {
		for (int i = 0; i < pollStationsSickCitizen.size(); i++)
			if (pollStationsSickCitizen.get(i).getSerialNum() == ID)
				return pollStationsSickCitizen.get(i);
		return null;
	}

	private void showPartySelectionMenu() {
		System.out.println("Pick a party:");
		for (int i = 1; i <= partys.size(); i++)
			System.out.println(i + ": " + partys.get((i - 1)));
		System.out.println("Press the number of the party you choose: ");
	}

	public Boolean performElection(Scanner scan) {
		int willVote, choice;
		System.out.println("Starting Election!");
		setVoteResSize();
		for (int i = 0; i < citizens.size(); i++) { // loop per citizen / voter.
			System.out.println("hello " + citizens.get(i).getName() + ", would you like to vote?");
			System.out.println("1: yes");
			System.out.println("2: no");
			willVote = scan.nextInt();
			scan.nextLine();
			if (willVote == 1) {
				showPartySelectionMenu();
				choice = scan.nextInt();
				citizens.get(i).getPollingStation().vote(choice - 1); // party menu start from 1, so reduce 1.
				System.out.println("thank you the vote has been recorded");
			}
		}
		this.isConcluded = true;
		System.out.println("The voting has concluded. Go see results.");
		return true;
	}

	public int getage(Citizen c) {
		return (year - c.getBirthYear());
	}

	public int getAgeWithYear(int birthYear) {
		return (year - birthYear);
	}

	public static int getYear() {
		return year;
	}

	public boolean setYear(int year) {
		if (year > 2020 || year < 2000) {
			return false;
		}
		Election.year = year;
		return true;
	}

	public int getMonth() {
		return month;
	}

	public boolean setMonth(int month) {
		if (month > 12 || month < 1) {
			return false;
		}
		this.month = month;
		return true;
	}

	public boolean isConcluded() {
		return isConcluded;
	}

	public boolean setConcluded(boolean isConcluded) {
		this.isConcluded = isConcluded;
		return true;
	}

	public int getStationCount() {
		return stationCount;
	}

	public int getCitizenCount() {
		return CitizenCount;
	}

	public int getPartysCount() {
		return partysCount;
	}

	public String showResult() {
		StringBuffer str = new StringBuffer();
		Vector<Integer> allPullingStationRes = new Vector<>();
		setVoteResForAllStations(allPullingStationRes);
		for (int i = 0; i < pollStationsCitizen.size(); i++) {
			str.append("in polling station that is located in " + pollStationsCitizen.get(i).getAddress() + " there is "
					+ pollStationsCitizen.get(i).getVotingPresenteg() + " % voting rate\n");
			for (int j = 0; j < partys.size(); j++) {
				str.append(pollStationsCitizen.get(i).getVoteRes().get(j) + " voted for " + partys.get(j).getName()
						+ "\n");
				allPullingStationRes.set(j,
						allPullingStationRes.get(j) + pollStationsCitizen.get(i).getVoteRes().get(j));
			}
		}
		str.append("corona citizen station: \n");
		for (int i = 0; i < pollStationsSickCitizen.size(); i++) {
			str.append("in polling station that is located in " + pollStationsSickCitizen.get(i).getAddress()
					+ " there is " + pollStationsSickCitizen.get(i).getVotingPresenteg() + " % voting rate\n");
			for (int j = 0; j < partys.size(); j++) {
				str.append(pollStationsSickCitizen.get(i).getVoteRes().get(j) + " voted for " + partys.get(j).getName()
						+ "\n");
				allPullingStationRes.set(j,
						allPullingStationRes.get(j) + pollStationsSickCitizen.get(i).getVoteRes().get(j));
			}
		}
		str.append("soldier station: \n");
		for (int i = 0; i < pollStationsSoldier.size(); i++) {
			str.append("in polling station that is located in " + pollStationsSoldier.get(i).getAddress() + " there is "
					+ pollStationsSoldier.get(i).getVotingPresenteg() + " % voting rate\n");
			for (int j = 0; j < partys.size(); j++) {
				str.append(pollStationsSoldier.get(i).getVoteRes().get(j) + " voted for " + partys.get(j).getName()
						+ "\n");
				allPullingStationRes.set(j,
						allPullingStationRes.get(j) + pollStationsSoldier.get(i).getVoteRes().get(j));
			}
		}
		str.append("corona soldier station: \n");
		for (int i = 0; i < pollStationsSickSoldier.size(); i++) {
			str.append("in polling station that is located in " + pollStationsSickSoldier.get(i).getAddress()
					+ " there is " + pollStationsSickSoldier.get(i).getVotingPresenteg() + " % voting rate\n");
			for (int j = 0; j < partys.size(); j++) {
				str.append(pollStationsSickSoldier.get(i).getVoteRes().get(j) + " voted for " + partys.get(j).getName()
						+ "\n");
				allPullingStationRes.set(j,
						allPullingStationRes.get(j) + pollStationsSickSoldier.get(i).getVoteRes().get(j));
			}
		}

		str.append("the final result are:\n");
		int winnerIndex = 0;
		int winnerVotes = 0;
		for (int i = 0; i < allPullingStationRes.size(); i++) {
			str.append(allPullingStationRes.get(i) + " voted for " + partys.get(i) + "\n");
			if (allPullingStationRes.get(i) > winnerVotes) {
				winnerIndex = i;
				winnerVotes = allPullingStationRes.get(i);
			}
		}
		str.append("the wining party is " + partys.get(winnerIndex).getName() + " with "
				+ allPullingStationRes.get(winnerIndex) + " votes");
		return str.toString();
	}

	public Vector<PollingStation<Citizen>> getPollStationsCitizen() {
		return pollStationsCitizen;
	}

	public Vector<PollingStation<SickCitizen>> getPollStationsSickCitizen() {
		return pollStationsSickCitizen;
	}

	public Vector<PollingStation<Soldier>> getPollStationsSoldier() {
		return pollStationsSoldier;
	}

	public boolean addCitizenPollingStation(PollingStation<Citizen> s1) {
		pollStationsCitizen.add(s1);
		return true;
	}

	public boolean addSickCitizenPollingStation(PollingStation<SickCitizen> s1) {
		pollStationsSickCitizen.add(s1);
		return true;
	}

	public boolean addSickSoldierPollingStation(PollingStation<SickSoldier> s1) {
		pollStationsSickSoldier.add(s1);
		return true;
	}

	public boolean addSoldierPollingStation(PollingStation<Soldier> s1) {
		pollStationsSoldier.add(s1);
		return true;
	}

	public void setVoteResSize() {
		for (int i = 0; i < pollStationsCitizen.size(); i++) {
			pollStationsCitizen.get(i).setVoteResForElection(partys.size());
		}
		for (int i = 0; i < pollStationsSickCitizen.size(); i++) {
			pollStationsSickCitizen.get(i).setVoteResForElection(partys.size());
		}
		for (int i = 0; i < pollStationsSickSoldier.size(); i++) {
			pollStationsSickSoldier.get(i).setVoteResForElection(partys.size());
		}
		for (int i = 0; i < pollStationsSoldier.size(); i++) {
			pollStationsSoldier.get(i).setVoteResForElection(partys.size());
		}
	}

	private void setVoteResForAllStations(Vector<Integer> arr) {
		for (int i = 0; i < partys.size(); i++) {
			arr.add(0);
		}
	}

}
