package Utils;

import java.util.ArrayList;
import java.util.List;

import com.app.onlance.JogadorForList;

public class UtilsInformation {

	static UtilsInformation utils;

	private String time;
	private String play;
	private String gol;
	private List<JogadorForList> time1;
	private List<JogadorForList> time2;
	
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

	public void addTime1(JogadorForList jogador){
		
		if(time1 == null){
			time1 = new ArrayList<JogadorForList>();
		}
		time1.add(jogador);
	}
	
	public void addTime2(JogadorForList jogador){
		
		if(time2 == null){
			time2 = new ArrayList<JogadorForList>();
		}
		time2.add(jogador);
	}
	
	public List<JogadorForList> getTime1(){
		return time1;
	}
	public List<JogadorForList> getTime2(){
		return time2;
	}
	
	public void cleanList1(){
		
		if(time1 != null){
			time1.clear();
		}
		
	}
	public void cleanList2(){
		if(time2 != null){
			time2.clear();
		}
	}
	public void cleanListAll(){
		cleanList1();
		cleanList2();
	}
}
