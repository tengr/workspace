package ruichen;

public enum Suit {
	C("Club"), D("Diamond"), H("Heart"), S("Speed");
	private String fullName;
	
	private Suit(String fullName){
		this.fullName = fullName;
	}
	
	@Override
	public String toString(){
		return this.fullName;
	}
}
