package cs131.pa1.filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;

import cs131.pa1.filter.sequential.SequentialFilter;

/**
 * take care of command ">" arrow
 */
public class Arrow extends SequentialFilter{
	String currentDir;
	String filename;
	boolean done;
    public Arrow(String currentDir,String filename) {
    	this.currentDir=currentDir;
    	//get rid of pre and post whitespace
    	this.filename=filename.trim();
    	this.done=false;
    	this.output=new LinkedList<>();
    }
    /**
     * process each line from file and add it to output
     * print error message if file not found
     */
    @Override
    public void process() {
    	if(this.input==null||this.input.isEmpty()) {
    		System.out.print(Message.REQUIRES_INPUT.with_parameter("> "+filename));
    		return;
    	}
    	if(filename.contains(" ")) {
    		System.out.print(Message.INVALID_PARAMETER.with_parameter(">"));
    		return;
    	}
    	//can not contain > or | after the > call
    	if(filename.contains(">")) {
    		System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter("> "+filename.substring(0,filename.indexOf(">"))));
    		return;
    	}
    	else if(filename.contains("|")) {
    		System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter("> "+filename.substring(0,filename.indexOf("|"))));
    		return;
    	}
    	if(this.input.isEmpty()) {
    		System.out.print(Message.REQUIRES_INPUT.with_parameter("> "+filename));
    		return;
    	}
        try {
        	//create path in the currentDir, this path is dynamic and could change with currentDir
        	String path=currentDir+System.getProperty("file.separator")+filename;
    		PrintStream out=new PrintStream(new File(path));
    		for(String line:this.input) {
    			out.println(line);
    		}
    		out.close();
    		done=true;
          } 
        catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	 * indicates whether this command is done
	 * @return return if the filter is done
	 */
   public boolean isDone() {
	   return this.done;
   }
}
