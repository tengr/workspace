import java.util.ArrayList;
public class Story {
	private int id;
	private String description;
	private ArrayList<Task> taskList;
	private boolean isCompleted;
	
	public Story(){
		super();
	}
	
	public Story(int num, String s){
		this.id = num;
		this.description = s;
	}
	
	public String toString(){
		return id + "\t" + description;
	}
	
	public void Complete(){
		this.isCompleted = true;
	}
	
	public Story findById(int storyId) {
		if (this.id == storyId){
			return this;
		}
		else return null;
	}
	
	public ArrayList<Task> getTaskList(){
		return taskList;
	}
}
