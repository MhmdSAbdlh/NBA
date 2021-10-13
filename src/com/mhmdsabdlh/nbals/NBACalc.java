package com.mhmdsabdlh.nbals;

class NBACalc {
	
	static void gamePR(Team[] team) {
		for(int i=0;i<team.length;i++) {
			team[i].gameP = team[i].win+team[i].loss;
			team[i].gameR = 82-(team[i].win+team[i].loss);
			team[i].per = Math.round((float) team[i].win/(team[i].win+team[i].loss)*100)/100.f;
			}
		}
	
	static void FinalRank(Team[] team) {
		for(int i=0;i<team.length;i++)
			if(team[i].avLow==team[i].avUp)
				team[i].fRank=Integer.toString(team[i].avUp);
			else
				team[i].fRank="NOT YET";
	}
	
	static void rankAva(Team[] team) {
		int j;
		for(int i=0;i<team.length;i++) {
			j=0;
			while((team[i].GB-team[j].GB)>(double)(team[i].gameR+team[j].gameR)/2)
				j++;
			team[i].avUp = team[j].rank;
			j=0;
			while((team[team.length-1-j].GB-team[i].GB)>(double)(team[i].gameR+team[team.length-1-j].gameR)/2)
				j++;
			team[i].avLow = team[team.length-1-j].rank;
		}
	}

	static void teamStatus(Team[] team) {
		for(int i=0;i<team.length;i++)
			if(team[i].avLow<=6)
				team[i].status = "PLAYOFFS";
			else
				if(team[i].avUp>10)
					team[i].status = "OUT";
				else
					if(team[i].avUp>=7 && team[i].avLow<=10)
						team[i].status = "PLAY-IN";
					else
						team[i].status = "NOT YET";
	}

	static void gameBehind(Team[] team) {
		team[0].GBU=0;
		team[0].GB=0;
		for (int i = 1; i < team.length; i++) {
			team[i].GBU=(float)((team[i-1].win-team[i-1].loss)-(team[i].win-team[i].loss))/2;
			team[i].GB=team[i].GBU+team[i-1].GB;
		}
	}

	static void rank(Team[] team) {
		int k=1;
		for (int i = 0; i < team.length; i++){
			for (int j = i + 1; j < team.length; j++) {
				Team temp = new Team(null);
				if (team[i].per < team[j].per){
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