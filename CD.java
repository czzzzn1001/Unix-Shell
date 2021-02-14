package cs131.pa1.filter;

import java.util.LinkedList;

import cs131.pa1.filter.sequential.SequentialFilter;

/**
 * take care of cd error message rather than changing dir
 */
public class CD extends SequentialFilter{
	
   public CD() {
	   this.output=new LinkedList<>();   
   }
   /**
    * process nothing
    */
   @Override
   public void process() {
   	 System.out.print(Message.NEWCOMMAND+Message.CANNOT_HAVE_INPUT.with_parameter("cd"));
   }
   @Override
   protected String processLine(String line) {
	// TODO Auto-generated method stub
	return null;
   }
   @Override
   public boolean isDone() {
	   return false;
   }
}
