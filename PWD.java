package cs131.pa1.filter;

import java.util.LinkedList;

import cs131.pa1.filter.sequential.SequentialFilter;
/**
 * this class take care of the pwd command
 */
public class PWD extends SequentialFilter{
	/**
	 * the path of the current working directory
	 */
    String currentDir;
    boolean done;
    /**
	 * constructor of pwd class
	 * @param current directory
	 */
	public PWD(String current) {
    	this.currentDir=current;
    	this.output=new LinkedList<>();
    	this.done=false;
    }
	/**
	 * not necessary to process in PWD, which ignores all the input
	 */
	@Override
	public void process(){
		if(this.input!=null&&!this.input.isEmpty()) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("pwd"));
			return;
		}
		this.output.add(currentDir);
		done=true;
	}
	/**
	 * not necessary to processLine in PWD
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
