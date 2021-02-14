package cs131.pa1.filter;

import java.io.File;
import java.util.LinkedList;

import cs131.pa1.filter.sequential.SequentialFilter;

/**
 * this class take care of the ls command
 */
public class LS extends SequentialFilter{
	/**
	 * the path of the current working directory
	 */
	public String currentDir;
	boolean done;
	/**
	 * constructor of ls class
	 * @param current directory
	 */
    public LS(String currentDir) {
    	this.currentDir=currentDir;
    	this.output=new LinkedList<>();
    	this.done=false;
    }
    /**
     * process the list and add the string with space to the output
     */
    @Override
    public void process() {
    	if(this.input!=null&&!this.input.isEmpty()) {
    		System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("ls"));
    		return;
    	}
    	File file=new File(currentDir);
    	String[] files=file.list();
    	for(int i=0;i<files.length;i++) {
    		this.output.add(files[i]);
    	}
    	this.done=true;
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
	@Override
	public boolean isDone() {
		return this.done;
	}
	
   
}
