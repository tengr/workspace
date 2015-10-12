import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Notice {
	public String id;
	public String parent;
	public String msg;
	public int depth;
	public int startTime;
	public String isFinished;
	public int children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public String isFinished() {
		return isFinished;
	}
	public void setFinished(String isFinished) {
		this.isFinished = isFinished;
	}
	
	public Notice(String notice){
		String arr[] = notice.split(",");
		id = arr[0];
		parent = arr[1];
		msg = arr[2];
		depth = Integer.parseInt(arr[3]);
		startTime = Integer.parseInt(arr[4]);
		isFinished = arr[5];
		children = 0;
	}
	public void incrementChildren(){
		children++;
	}
	public static void main(String[] agrs){
		/*
		Scanner sc = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<String>();
		HashMap<String, Notice> map = new HashMap<String, Notice>();
		HashMap<Integer, String> depthMap = new HashMap<Integer, String>();
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			Notice notice = new Notice(line);
			list.add(notice.id);
			depthMap.put(notice.depth, notice.id);
			map.get(notice.getParent()).incrementChildren();
			map.put(line.split(",")[0], notice);			
		}
		int i = 1;
		for(String id : list){
			Notice notice = map.get(id);
			if(notice.getParent().equals("null")) {
				System.out.println("1 " + notice);
			}
		}
		sc.close();
		*/
		
		double f = 3.56;
		
		System.out.println(7.9999%1);
	}
	
	
	
}
