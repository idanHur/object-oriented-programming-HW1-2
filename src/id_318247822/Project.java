package id_318247822;

import java.util.Scanner;

import javax.sql.PooledConnection;

public class Project {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Messageable ui = new ConsoleUI();
		int year, month;
		int choice = 0;
		year = ui.getInt("making new election please enter a year for the election: (min year is 2009)");
		month = ui.getInt("enter a month for the election: ");
		Election election = new Election(year, month);
		try {
			statUp(election, ui);
			while (choice != 10) {
				showMenu(ui);
				choice = scan.nextInt();
				if (choice != 10 && performAction(election, choice, scan, ui)) {
					ui.showMessage("action has been preformed");
				}
			}

		} catch (Exception e) {
			ui.showMessage(e.getMessage());
		}
		ui.showMessage("have a good day");
		scan.close();

	}

	public static boolean statUp(Election election, Messageable ui) throws Exception {
		PollingStation<Citizen> s1 = new PollingStation<>("Tel-aviv");
		PollingStation<SickCitizen> s2 = new PollingStation<>("Hadera");
		PollingStation<Soldier> s3 = new PollingStation<>("Eilat");
		PollingStation<SickSoldier> s4 = new PollingStation<>("Natanya");
		Party p1 = new Party("Los Pollos Hermanos", "center", "2009");
		Party p2 = new Party("the cookie dudes", "right", "2014");
		Party p3 = new Party("we like pizza", "left", "2012");
		Candidate c1 = new Candidate("jojo", 311111111, 1990, s1, 1, p1);
		Candidate c2 = new Candidate("dodo", 311111112, 1970, s1, 2, p1);
		Candidate c3 = new Candidate("lalo", 311111113, 1989, s1, 1, p2);
		Candidate c4 = new Candidate("sol", 311111114, 1975, s1, 2, p2);
		Candidate c5 = new Candidate("gostabo", 311111115, 1979, s1, 1, p3);
		Candidate c6 = new Candidate("mike", 311111116, 1968, s1, 2, p3);
		Soldier v1 = new Soldier("kim", 311111117, 1986, false);
		Citizen v2 = new Citizen("ignacio", 311111118, 1990);
		Citizen v3 = new Citizen("hawerd", 311111119, 1983);
		SickCitizen v4 = new SickCitizen("hektor", 311111120, 1956, 5, true);
		SickSoldier v5 = new SickSoldier("toko", 311111121, 1968, false, 2, true);

		if (!(election.addCitizenPollingStation(s1))) {
			ui.showMessage("error adding station 1");
			return false;
		}
		if (!(election.addSickCitizenPollingStation(s2))) {
			ui.showMessage("error adding station 2");
			return false;
		}
		if (!(election.addSoldierPollingStation(s3))) {
			ui.showMessage("error adding station 3");
			return false;
		}
		if (!(election.addSickSoldierPollingStation(s4))) {
			ui.showMessage("error adding station 4");
			return false;
		}
		if (!(election.addParty(p1))) {
			ui.showMessage("error adding party 1");

		}
		if (!(election.addParty(p2))) {
			ui.showMessage("error adding party 2");
			return false;
		}
		if (!(election.addParty(p3))) {
			ui.showMessage("error adding party 3");
			return false;
		}
		if (!(election.addCandidate(c1))) {
			ui.showMessage("error adding candidate 1");
			return false;
		}
		if (!(election.addCandidate(c2))) {
			ui.showMessage("error adding candidate 2");
			return false;
		}
		if (!(election.addCandidate(c3))) {
			ui.showMessage("error adding candidate 3");
			return false;
		}
		if (!(election.addCandidate(c4))) {
			ui.showMessage("error adding candidate 4");
			return false;
		}
		if (!(election.addCandidate(c5))) {
			ui.showMessage("error adding candidate 5");
			return false;
		}
		if (!(election.addCandidate(c6))) {
			ui.showMessage("error adding candidate 6");
			return false;
		}
		if (!(election.addCitizenToStation(v1, s3))) {
			ui.showMessage("error adding citizen 1");
			return false;
		}
		if (!(election.addCitizenToStation(v2, s1))) {
			ui.showMessage("error adding citizen 2");
			return false;
		}
		if (!(election.addCitizenToStation(v3, s1))) {
			ui.showMessage("error adding citizen 3");
			return false;
		}
		if (!(election.addCitizenToStation(v4, s2))) {
			ui.showMessage("error adding citizen 4");
			return false;
		}
		if (!(election.addCitizenToStation(v5, s4))) {
			ui.showMessage("error adding citizen 5");
			return false;
		}
		return true;
	}

	public static void showMenu(Messageable ui) {
		ui.showMessage("MENU: press the number of the desired action:");
		ui.showMessage("1: Add a Polling Station");
		ui.showMessage("2: Add a Voter");
		ui.showMessage("3: Add a Party");
		ui.showMessage("4: Add a Candidate to a party (create a new Voter that is a Candidate)");
		ui.showMessage("5: Show all Polling Stations");
		ui.showMessage("6: Show all Voters");
		ui.showMessage("7: Show all Parties");
		ui.showMessage("8: START THE ELECTION");
		ui.showMessage("9: Show results");
		ui.showMessage("10: EXIT");
	}

	public static Boolean performAction(Election election, int action, Scanner scan, Messageable ui) throws Exception {
		if (election.isConcluded() && (action != 9)) {
			ui.showMessage("But the election has concluded. You may only view results.");
			return false;
		}
		switch (action) {
		case 1:
			return makePollingStation(election, scan, ui);
		case 2:
			boolean choiceMade = false;
			while (!(choiceMade)) {
				int choice = ui.getInt("press: \n1. citizen \n2.soldier");
				switch (choice) {
				case 1:
					return addCitizen(election, scan, ui);
				case 2:
					return addSoldier(election, scan, ui);
				default:
					ui.showMessage("wrong key");
					;
				}
			}
		case 3:
			return addParty(election, scan, ui);
		case 4:
			return addCandidate(election, scan, ui);
		case 5:
			ui.showMessage(election.showAllPollingStationsFullInfo());
			return true;
		case 6:
			ui.showMessage(election.showAllCitizens());
			return true;
		case 7:
			ui.showMessage(election.showAllPartysFullInfo());
			return true;
		case 8:
			return election.performElection(scan);
		case 9:
			ui.showMessage(election.showResult());
			return true;
		}
		ui.showMessage("Invalid command");
		return false;
	}

	public static boolean makePollingStation(Election election, Scanner scan, Messageable ui) {
		int choice;
		boolean loop = false;
		String address;

		while (!loop) {
			choice = ui.getInt(
					"what kind of polling station would you like to make?\n 0. exit\n 1. Regular polling station\n"
							+ " 2. Corona polling station\n 3. Military polling station\n 4. Corona Military polling station\n");

			address = ui.getString("please enter a address");
			switch (choice) {
			case 0:
				loop = true;
				return false;
			case 1:
				PollingStation<Citizen> p = new PollingStation<>(address);
				if (election.addCitizenPollingStation(p)) {
					ui.showMessage("regular polling station added successfully");
					loop = true;
					return true;
				}
				return false;
			case 2:
				PollingStation<SickCitizen> a = new PollingStation<SickCitizen>(address);
				if (election.addPollStationsSickCitizen(a)) {
					ui.showMessage("Corona polling station added successfully");
					loop = true;
					return true;
				}
				return false;
			case 3:
				PollingStation<Soldier> b = new PollingStation<Soldier>(address);
				if (election.addPollStationsSoldier(b)) {
					ui.showMessage("Military polling station added successfully");
					loop = true;
					return true;
				}
				return false;
			case 4:
				PollingStation<SickSoldier> b1 = new PollingStation<SickSoldier>(address);
				if (election.addPollStationsSickSoldier(b1)) {
					ui.showMessage("Corona Military polling station added successfully");
					loop = true;
					return true;
				}
				return false;
			}
			if (choice > 3 || choice < 0) {
				ui.showMessage("wrong command, there is no such choise.");
				return false;
			}
		}
		return false;
	}

	public static boolean addCitizen(Election election, Scanner scan, Messageable ui) throws Exception {
		int pollingStation, id, birthYear, isolated, sickDays;
		String name;
		boolean isIsolated = false;
		boolean choiceMade = false;
		boolean protect = false;
		name = ui.getString("please enter citizen name: ");
		id = ui.getInt("enter " + name + " id: ");
		birthYear = ui.getInt("enter " + name + " birth year");
		while (choiceMade == false) {
			isolated = ui.getInt("is " + name + " isolated?\n" + "1. yes\n" + "2. no");
			if (isolated == 1) {
				isIsolated = true;
				choiceMade = true;
			}
			if (isolated == 2) {
				isIsolated = false;
				choiceMade = true;
			}
			if (choiceMade == false)
				ui.showMessage("invalid choise please try agine");
		}
		ui.showMessage("please pick a polling station " + name + " to visit: ");
		if (isIsolated) {
			ui.showMessage(election.showSickCitizenPollingStations());
			pollingStation = ui
					.getInt("enter the polling station number for " + name + " to visit, make sure he can go there: ");
			sickDays = ui.getInt("for how many days " + name + " had been sick");
			protect = ui.getBoolean("is " + name + " protected? (true\false)");
			SickCitizen c = new SickCitizen(name, id, birthYear, sickDays, protect);
			PollingStation pol = election.findStationByIdForSickCitizen(pollingStation);
			if (pol == null) {
				return false;
			}
			if (!(election.addCitizenToStation(c, pol))) {
				ui.showMessage("error adding citizen to polling station");
				return false;
			}
			if (election.addCitizen(c)) {
				ui.showMessage("citizen added successfully");
				return true;
			}
		} else {
			ui.showMessage(election.showCitizenPollingStations());
			pollingStation = ui
					.getInt("enter the polling station number for " + name + " to visit, make sure he can go there: ");
			Citizen c = new Citizen(name, id, birthYear);
			PollingStation pol = election.findStationByIdForCitizen(pollingStation);
			if (pol == null) {
				return false;
			}
			if (!(election.addCitizenToStation(c, pol))) {
				ui.showMessage("error adding citizen to polling station");
				return false;
			}
			if (election.addCitizen(c)) {
				ui.showMessage("citizen added successfully");
			}
			return true;
		}
		return false;
	}

	public static boolean addParty(Election election, Scanner scan, Messageable ui) {
		String name, dateOfFoundation;
		String agendaString = "";
		int agenda;
		boolean agendaPick = false;
		name = ui.getString("please enter party name: ");
		while (!agendaPick) {
			agenda = ui.getInt("enter " + name + " agenda: \n" + "1. right\n2. center\n3.left");
			switch (agenda) {
			case 1:
				agendaString = "right";
				agendaPick = true;
				break;
			case 2:
				agendaString = "center";
				agendaPick = true;
				break;
			case 3:
				agendaString = "left";
				agendaPick = true;
				break;
			default:
				break;
			}
		}
		dateOfFoundation = ui.getString("enter " + name + " year of foundation: ");
		Party p = new Party(name, agendaString, dateOfFoundation);
		if (!(election.addParty(p))) {
			ui.showMessage("error adding party");
			return false;
		}
		ui.showMessage("party was added sucsesfully");
		return true;
	}

	public static boolean addCandidate(Election election, Scanner scan, Messageable ui) throws Exception {
		int primariz, id, birthYear, pollingStationId, isSick;
		String name, partyName;
		boolean infected = false;
		boolean loop = false;
		PollingStation s = null;
		Party p = null;
		name = ui.getString("please enter candidate name: ");
		id = ui.getInt("enter " + name + " id number: ");
		birthYear = ui.getInt("enter " + name + " birth year: ");
		ui.showMessage("chose a polling station for " + name + " to visit make sure he can go there: ");
		ui.showMessage(election.showCitizenPollingStations());
		while (!loop) {
			pollingStationId = ui.getInt("enter polling station number: ");
			if ((s = election.findStationByIdForCitizen(pollingStationId)) != null) {
				loop = true;
				break;
			}
			ui.showMessage("polling station dosnt existe");
		}
		loop = false;
		ui.showMessage("chose a party for " + name + ": ");
		ui.showMessage(election.showAllPartys());
		while (!loop) {
			partyName = ui.getString("enter party name: ");
			if ((p = election.findPartyByName(partyName)) != null) {
				loop = true;
				break;
			}
			ui.showMessage("party dosnt existe please try agine");
		}
		primariz = ui.getInt(
				"enter " + name + " primariz number:\n " + "note that primariz num 1-2 in partys 1-3 are takken\n");
		Candidate c = new Candidate(name, id, birthYear, s, primariz, p);
		if (election.addCandidate(c)) {
			ui.showMessage("candidate added sucsesfully");
			return true;
		}
		return false;

	}

	private static Boolean addSoldier(Election election, Scanner scan, Messageable ui) throws Exception {
		int pollingStation, id, birthYear, isolated, sickDays, carrys;
		String name;
		boolean isIsolated = false;
		boolean choiceMade = false;
		boolean carryWepan = false;
		boolean protect = false;
		name = ui.getString("please enter citizen name: ");
		id = ui.getInt("enter " + name + " id: ");
		birthYear = ui.getInt("enter " + name + " birth year");
		while (choiceMade == false) {
			isolated = ui.getInt("is " + name + " isolated?\n" + "1. yes\n" + "2. no");
			if (isolated == 1) {
				isIsolated = true;
				choiceMade = true;
			}
			if (isolated == 2) {
				isIsolated = false;
				choiceMade = true;
			}
			if (choiceMade == false)
				ui.showMessage("invalid choise please try agine");
		}
		choiceMade = false;
		while (choiceMade == false) {
			carrys = ui.getInt("is " + name + " carry a wepane?\n" + "1. yes\n" + "2. no");
			if (carrys == 1) {
				carryWepan = true;
				choiceMade = true;
			}
			if (carrys == 2) {
				carryWepan = false;
				choiceMade = true;
			}
			if (choiceMade == false)
				ui.showMessage("invalid choise please try agine");
		}
		ui.showMessage("please pick a polling station " + name + " to visit: ");
		if (isIsolated) {
			ui.showMessage(election.showSickSoldierPollingStations());
			pollingStation = ui
					.getInt("enter the polling station number for " + name + " to visit, make sure he can go there: ");
			sickDays = ui.getInt("for how many days " + name + " had been sick");
			protect = ui.getBoolean("is " + name + " protected? (true\false)");
			SickSoldier c = new SickSoldier(name, id, birthYear, carryWepan, sickDays, protect);
			PollingStation pol = election.findStationByIdSickSoldier(pollingStation);
			if (pol == null) {
				return false;
			}
			if (!(election.addCitizenToStation(c, pol))) {
				ui.showMessage("error adding soldier to polling station");
				return false;
			}
			if (election.addCitizen(c)) {
				ui.showMessage("soldier added successfully");
				return true;
			}
		} else {
			ui.showMessage(election.showSoldierPollingStations());
			pollingStation = ui
					.getInt("enter the polling station number for " + name + " to visit, make sure he can go there: ");
			Soldier c = new Soldier(name, id, birthYear, carryWepan);
			PollingStation pol = election.findStationByIdSoldier(pollingStation);
			if (pol == null) {
				return false;
			}
			if (!(election.addCitizenToStation(c, pol))) {
				ui.showMessage("error adding soldier to polling station");
				return false;
			}
			if (election.addCitizen(c)) {
				ui.showMessage("soldier added successfully");
			}
			return true;
		}
		return false;
	}

}
