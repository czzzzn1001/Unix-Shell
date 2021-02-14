package cs131.pa1.filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import cs131.pa1.filter.sequential.SequentialFilter;

/**
 * this class take care of the cat command
 */
public class CAT extends SequentialFilter{
   String currentDir;
   String filename;
   private boolean done;
   /**
	 * constructor of ls class
	 * @param current directory, filename
	 */
	public CAT(String currentDir,String filename) {
	   this.filename=filename.trim();
	   this.currentDir=currentDir;
	   this.output=new LinkedList<>();
	   this.done=false;
   }
	/**
     * process each line from file and add it to output
     * print error message if file not found
     */
    @Override
    public void process() {
    	if(this.input!=null&&!this.input.isEmpty()) {
    		System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("cat "+filename));
    		return;
    	}
    	String[] files=filename.split(" ");
    	for(int i=0;i<files.length;i++) {
    		String dir=currentDir+System.getProperty("file.separator")+files[i];
        	File file=new File(dir);
        	if(!file.exists()) {
        		System.out.print(Message.FILE_NOT_FOUND.with_parameter("cat "+filename));
        		return;
        	}
        	else {
              try {
        		Scanner in=new Scanner(file);
        		while(in.hasNextLine()) {
        			String line=in.nextLine();
        			output.add(line);
        	    }
        		in.close();
        		if(output.isEmpty()) {
        			//add an empty string to output to avoid error message for next subcommand
        			output.add("");
        		}
              } 
              catch (FileNotFoundException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    		  }
        	}
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
	   return this.done;
   }
}
