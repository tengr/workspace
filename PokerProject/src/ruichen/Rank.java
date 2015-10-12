package ruichen;

public enum Rank {
	_2("2"), _3("3"), _4("4"), _5("5"), _6("6"), _7("7"), _8("8"), _9("9"), _T("10"), _J("Jack"), _Q("Queen"), _K("King"), _A("Ace");
	
	private String fullName;
	
	private Rank(String fullName){
		this.fullName = fullName;
	}
	
	public String toString(){
		return this.fullName;
	}
}
