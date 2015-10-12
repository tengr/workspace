
public class Task {
	private int id;
	private Boolean isComplete;
	private Story story;
	private String description;
	
	public Task(){
		super();
	}
	
	public Task(int id, int storyId, String description){
		this.id = id;
		this.story = story.findById(storyId);
		this.description = description;
	}
	
}
