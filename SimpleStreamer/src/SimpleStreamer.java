public class SimpleStreamer {
	public static void main(String[] args) {
		int serverPort = 6262;
		String remote = "";
		String remotePort = "";
		int width = 320;
		int height = 240;
		double rate = 100;
		for(int i=0; i < args.length; i++){
			if(args[i].equals("-sport")||args[i].equals("-remote")||args[i].equals("-rport")||args[i].equals("-width")||args[i].equals("-height")||args[i].equals("-rate")){
				
			}else{
				if(args[i-1].equals("-sport")){
					serverPort = Integer.parseInt(args[i]);
				}else if(args[i-1].equals("-remote")){
					remote = args[i];
				}else if(args[i-1].equals("-rport")){
					remotePort = args[i];
				}else if(args[i-1].equals("-width")){
					width = Integer.parseInt(args[i]);
				}else if(args[i-1].equals("-height")){
					height = Integer.parseInt(args[i]);
				}else if(args[i-1].equals("-rate")){
					rate = Double.parseDouble(args[i]);
				}
			}
		}
		Node service = new Node(serverPort,remote,remotePort,width,height,rate);
		service.startService();
	}
}
