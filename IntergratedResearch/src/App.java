import java.util.ArrayList;

public class App {
	
	private static ArrayList<Story> storyList;
	
	public static void main(String[] agrs){
		
	}
	
	public static void createStory(int id, String description){
		Story story = new Story(id, description);
		storyList.add(story);
	}
	
	public static void listStories(){
		for(Story story:storyList){
			System.out.println(story);
		}
	}
	
	public static void deleteStory(Story story){
		storyList.remove(story);
	}
	
	public static void completeStory(Story story){
		story.Complete();
	}
	
	public static void createTask(int storyId, int id, String description){
		
	}
	
	public static void listTasks(int storyId){
		ArrayList<Task> taskList = null;
		for (Story story : storyList){
			if (story.findById(storyId) != null){
				taskList = story.getTaskList();
				break;
			}
		}
		for(Task task: taskList){
			System.out.println(task);
		}
		
	}
}
