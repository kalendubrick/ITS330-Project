import java.io.Serializable;
public class Operation implements Serializable {
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -902903112050297779L;
		private int JobID, OPID, index;		
		private String JobDescription;
		private boolean isDone = false;
		
		public Operation(){}		
		public Operation(int a, int b, int c){
			this.JobID = a;
			this.OPID = b;
			this.index = c;
		this.setJobDescription(b);
		}	
		
		public void setJobDescription(int r){
			 if (r==2) {  // maybe computing				 
				this.JobDescription = "please compute the  a= "  + this.JobID +" + "+ this.OPID +" + "+ this.index; // you can make it simple to compute some simple equations
				 }
			 else if (r==3) {  // printing job				
				 this.JobDescription = "please print out the job id = " + this.JobID+ " operation id = " +this.OPID
				 +" operation index = "+ this.index;
			 }
			 else {}
		}
		public int getJobID(){return this.JobID;}
		public void setJobID(int d){this.JobID=d;}
		public synchronized boolean isDone(){return this.isDone;}
		public synchronized void setIsDone(boolean d){this.isDone=d;}
		public int getOPID(){return this.OPID;}
		public void setOPID(int d){this.OPID=d;}
		public String getJobDescription(){return this.JobDescription;}
		public void setJobDescription(String d){this.JobDescription=d;}		
	
		public void print(String msg) {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + ": "+msg);			
		}
		
		public void compute(int num1, int num2)
		{
			int temp;
			
			temp = num1 + num2;
			
			System.out.println(num1 + " + " + num2 + " = " + temp);
		}
}


