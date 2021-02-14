package cs131.pa1.filter;

import java.util.LinkedList;

import cs131.pa1.filter.sequential.SequentialFilter;
/**
 * this class take care of the grep command
 */
public class GREP extends SequentialFilter{
    String currentDir;
    /**
     * keyword for search
     */
    String search;
    /**
     * isDone indicator
     */
    boolean done;
    /**
     * constructor of grep class
     */
	public GREP(String currentDir,String search) {
    	this.currentDir=currentDir;
    	this.search=search.trim();
    	this.done=false;
    	this.output=new LinkedList<>();
    }
	/**
	 * processes the input and push result to output
	 */
	@Override
	public void process() {
		if(this.input==null||this.input.size()==0) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter("grep "+search));
			return;
		}
		done=true;
		for(String line:this.input) {
			if(line.contains(search)) {
				this.output.add(line);			
			}
		}
	}
	/**
	 * unnecessary implementation
	 */
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * indicates if the processing is done
	 * @return true if done, false if not
	 */
    public boolean isDone() {
    	return this.done;
    }
}
