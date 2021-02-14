package cs131.pa1.filter;

import java.util.LinkedList;

import cs131.pa1.filter.sequential.SequentialFilter;

/**
 * take care of print output of each command line
 */
public class PrintFilter extends SequentialFilter{
	boolean done;
    public PrintFilter() {
    	this.done=false;
    	this.output=new LinkedList<>();
    }
    /**
     * process each line from file and add it to output
     * print error message if file not found
     */
    @Override
    public void process() {
    	for(String line:this.input) {
    		System.out.println(line);
    	}
    	done=true;
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
	 * indicates whether this command is done
	 * @return return if the filter is done
	 */
  public boolean isDone() {
	   return done;
  }
}
