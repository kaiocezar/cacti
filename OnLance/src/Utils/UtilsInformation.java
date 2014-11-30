package Utils;

public class UtilsInformation {

	static UtilsInformation utils;

	private String time;
	private String play;
	private String gol;
	
	public static UtilsInformation getInscace(){
		
		if(utils == null){
			utils = new UtilsInformation();
		}

		return utils;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPlay() {
		return play;
	}

	public void setPlay(String play) {
		this.play = play;
	}

	public String getGol() {
		return gol;
	}

	public void setGol(String gol) {
		this.gol = gol;
	}
	
	
	
}
