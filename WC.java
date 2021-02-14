package cs131.pa1.filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import cs131.pa1.filter.sequential.SequentialFilter;

/**
 * take care of command wc
 */
public class WC extends SequentialFilter{
	boolean done;
	String out;
	String currentDir;
    /**
     * constructor of wc class
     */
   public WC(String currentDir) {
	  this.currentDir=currentDir;
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
   		System.out.print(Message.REQUIRES_INPUT.with_parameter("wc"));
   		return;
   	}
   	else {
        int line_num=0;
        int word_num=0;
        int char_num=0;
        for(String line:this.input) {
        	if(line.equals("")) {
        		break;
        	}
        	line_num++;
        	char_num+=line.length();
        	if(line.trim().length()!=0)
        	  word_num+=line.trim().split("\\s+").length;
        }
        String out=""+line_num+" "+word_num+" "+char_num;
        output.add(out);
        done=true;
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
