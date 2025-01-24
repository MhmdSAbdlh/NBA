package com.mhmdsabdlh.nbals;

class NBACalc {

	static void gamePR(Team[] team) {
		for (int i = 0; i < team.length; i++) {
			team[i].gameP = team[i].win + team[i].loss;
			team[i].gameR = 82 - (team[i].win + team[i].loss);
			float temp = ((float) team[i].win / (team[i].win + team[i].loss) * 100) / 100;
			team[i].per = Math.round(temp * 1000) / 1000.0f;
		}
	}

	static void FinalRank(Team[] team) {
		for (int i = 0; i < team.length; i++)
			if (team[i].avLow == team[i].avUp)
				team[i].fRank = Integer.toString(team[i].avUp);
			else
				team[i].fRank = "NOT YET";
	}

	static void rankAva(Team[] team) {
		int j;
		for (int i = 0; i < team.length; i++) {
			j = 0;
			while ((team[i].GB - team[j].GB) > (double) (team[i].gameR + team[j].gameR) / 2
					|| (team[i].adv < team[j].adv
							&& (team[i].GB - team[j].GB) == (double) (team[i].gameR + team[j].gameR) / 2))
				j++;
			team[i].avUp = team[j].rank;
			j = 0;
			while ((team[team.length - 1 - j].GB
					- team[i].GB) > (double) (team[i].gameR + team[team.length - 1 - j].gameR) / 2
					|| (team[team.length - 1 - j].adv < team[i].adv && (team[team.length - 1 - j].GB
							- team[i].GB) == (double) (team[i].gameR + team[team.length - 1 - j].gameR) / 2))
				j++;
			team[i].avLow = team[team.length - 1 - j].rank;
		}
	}

	static void matchNeeded(Team[] team) {
		for (int i = 0; i < team.length; i++) {
			if (i < 6)
				if (team[i].avLow < 7)
					team[i].gN = "PLAYOFFS";
				else if ((team[i].adv <= team[6].adv
						&& (team[6].GB - team[i].GB) == (double) (team[i].gameR + team[6].gameR) / 2))
					team[i].gN = "0.5";
				else
					team[i].gN = "" + ((float) (team[i].gameR + team[6].gameR) / 2 - (team[6].GB - team[i].GB));
			else if (i < 10)
				if ((team[10].GB - team[i].GB) > (double) (team[i].gameR + team[10].gameR) / 2
						|| (team[i].adv > team[10].adv
								&& (team[10].GB - team[i].GB) == (double) (team[i].gameR + team[10].gameR) / 2))
					if (team[i].avUp > 6)
						team[i].gN = "PLAY-IN";
					else
						team[i].gN = "PO or PI";
				else if ((team[i].adv <= team[10].adv
						&& (team[10].GB - team[i].GB) == (double) (team[i].gameR + team[10].gameR) / 2))
					team[i].gN = "0.5";
				else
					team[i].gN = "" + ((float) (team[i].gameR + team[10].gameR) / 2 - (team[10].GB - team[i].GB));
			else if (team[i].avUp > 10)
				team[i].gN = "OUT";
			else if ((team[i].adv >= team[9].adv
					&& (team[i].GB - team[9].GB) == (double) (team[i].gameR + team[9].gameR) / 2))
				team[i].gN = "0.5";
			else
				team[i].gN = "" + ((float) (team[i].gameR + team[9].gameR) / 2 - (team[i].GB - team[9].GB));
		}
	}

	static void gameBehind(Team[] team) {
		team[0].GBU = 0;
		team[0].GB = 0;
		for (int i = 1; i < team.length; i++) {
			team[i].GBU = (float) ((team[i - 1].win - team[i - 1].loss) - (team[i].win - team[i].loss)) / 2;
			team[i].GB = team[i].GBU + team[i - 1].GB;
		}
	}

	static void rank(Team[] team) {
		int k = 1;
		for (int i = 0; i < team.length; i++) {
			for (int j = i + 1; j < team.length; j++) {
				Team temp = new Team(null);
				if (team[i].per < team[j].per || (team[i].per == team[j].per && team[i].adv < team[j].adv)) {
					temp = team[i];
					team[i] = team[j];
					team[j] = temp;
				}
			}
			team[i].rank = k;
			k++;
		}
	}
}