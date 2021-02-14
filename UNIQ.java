package cs131.pa1.filter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import cs131.pa1.filter.sequential.SequentialFilter;

/**
 * take care of command uniq
 */
public class UNIQ extends SequentialFilter{
	/**
	 * hashset for avoiding duplicates
	 */
	Set<String> set;
	boolean done;
	/**
	 * constructor
	 */
    public UNIQ() {
    	this.output=new LinkedList<>();
    	this.set=new HashSet<>();
    	this.done=false;
    }
    /**
     * process the input
     */
    @Override
    public void process() {
    	if(this.input.isEmpty()) {
    		System.out.print(Message.NEWCOMMAND+Message.REQUIRES_INPUT.with_parameter("uniq"));
    		return;
    	}
    	for(String line:this.input) {
    		if(!set.contains(line)) {
    			set.add(line);
    			this.output.add(line);
    		}
    	}
    	done=true;
    }
    
	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * indicates whether this command is done
	 * @return return if the filter is done
	 */
	public boolean isDone() {
    	return this.done;
    }
}
