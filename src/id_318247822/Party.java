package id_318247822;

import java.util.Collections;
import java.util.Vector;

public class Party {
	private enum faction {
		right, center, left
	}

	private static int partyNumGenorator = 1;
	private String name;
	private faction agenda;
	private String dateOfFoundation;
	private Vector<Candidate> candidates;
	private int numOfCandidates;
	private int partyNum;

	public Party(String name, String agenda, String foundationDate) {
		this.name = name;
		setAgenda(agenda);
		dateOfFoundation = foundationDate;
		candidates = new Vector<Candidate>();
		partyNum = partyNumGenorator;
		partyNumGenorator++;

	}

	public faction getAgenda() {
		return agenda;
	}

	public boolean setAgenda(String agenda) {
		String lCAgenda = agenda.toLowerCase();
		if (lCAgenda.equals("right") || lCAgenda.equals("center") || lCAgenda.equals("left")) {
			this.agenda = faction.valueOf(agenda);
			return true;
		}
		return false;
	}

	public Vector<Candidate> getCandidates() {
		return candidates;
	}

	public int getPartyNum() {
		return partyNum;
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		this.name = name;
		return true;
	}

	public boolean addCandidate(Candidate candidate, int primariz) throws Exception { // need to change how to set
																						// candidate
		if (candidates.size() >= primariz && candidates.get(primariz) != null) {
			throw new Exception("the primariz number at this party is ocuupide");
		}

		candidates.add(candidate);
		candidates.get(numOfCandidates).setCandidateOfParty(this);
		numOfCandidates++;
		Collections.sort(candidates);
		return true;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Party)) {
			return false;
		}
		Party s = (Party) other;
		if (this.name.matches(s.getName()) && this.dateOfFoundation == s.getDateOfFoundation()
				&& this.candidates.equals(s.candidates)) {
			return true;
		}
		return false;
	}

	public String getDateOfFoundation() {
		return dateOfFoundation;
	}

	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("Party name: " + name + " party agenda: " + agenda + " founded on :" + dateOfFoundation + "\n");
		return str.toString();
	}

	public String toStringFullInfo() {
		StringBuffer str = new StringBuffer();
		str.append(this.toString());
		for (int i = 0; i < candidates.size(); i++) {
			str.append(candidates.get(i) + "\n");
		}

		return str.toString();
	}

}
