package cs131.pa1.filter.sequential;

import java.util.ArrayList;
import java.util.List;

import cs131.pa1.filter.Arrow;
import cs131.pa1.filter.CAT;
import cs131.pa1.filter.CD;
import cs131.pa1.filter.GREP;
import cs131.pa1.filter.LS;
import cs131.pa1.filter.Message;
import cs131.pa1.filter.PWD;
import cs131.pa1.filter.PrintFilter;
import cs131.pa1.filter.UNIQ;
import cs131.pa1.filter.WC;
/**
 * This class manages the parsing and execution of a command.
 * It splits the raw input into separated subcommands, 
 * creates subcommand filters, and links them into a list. 
 * @author cs131a
 *
 */
public class SequentialCommandBuilder {
	/**
	 * list of filters
	 */
	static List<SequentialFilter> filters;
	static SequentialFilter last;
	static String currentDir;
	/**
	 * Creates a list of filters from the specified command, 
	 * links them and print the output or error message
	 * @param command the command to create filters from
	 */
	public static void createFiltersFromCommand(String command,String currDir){
		currentDir=currDir;
		filters=new ArrayList<>();
		command=adjustCommandToRemoveFinalFilter(command.trim());
		if(command.equals("")) {
			return;
		}
		String[] subcommands=command.split("\\|");
		for(int i=0;i<subcommands.length;i++) {
			SequentialFilter temp=constructFilterFromSubCommand(subcommands[i].trim());
			if(temp==null) {
				return;
			}
			filters.add(temp);
		}
		//add the last filter to the tail of the filters list
		filters.add(last);
		if(!linkFilters(filters)) {
			return;
		}

	}
	
	/**
	 * Returns the filter that appears last in the specified command
	 * @param command the command to search from
	 * @return the SequentialFilter that appears last in the specified command
	 */
	private static SequentialFilter determineFinalFilter(String command){
		return null;
	}
	
	/**
	 * Returns a string that contains the specified command without the final filter
	 * @param command the command to parse and remove the final filter from 
	 * @return the adjusted command that does not contain the final filter
	 */
	private static String adjustCommandToRemoveFinalFilter(String command){
		String res="";
		if(command.contains(">")) {
			String[] com=command.split(">");
			if(command.indexOf(">")==0) {
				System.out.print(Message.REQUIRES_INPUT.with_parameter(command));
				return res;
			}
			if(com.length==1) {
				System.out.print(Message.REQUIRES_PARAMETER.with_parameter(">"));
				return res;
			}
			//set the last to arrow
			last=new Arrow(currentDir,com[1]);
			res=command.substring(0,command.indexOf('>'));
		}else {
			//if string does not contain ">", the last filter is normal printing filter
			res=command;
			last=new PrintFilter();
		}
		return res;
	}
	
	/**
	 * Creates a single filter from the specified subCommand
	 * @param subCommand the command to create a filter from
	 * @return the SequentialFilter created from the given subCommand
	 */
	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
		SequentialFilter res=null;
		String[] words=subCommand.split(" ");
		switch(words[0]) {
		case "pwd":
			//even if pwd has parameter, we just ignore rather than send error
			SequentialFilter pwd=new PWD(currentDir);
			res=pwd;
			break;
		case "ls":
			SequentialFilter ls=new LS(currentDir);
			res=ls;
			break;
		case "cat":
			if(words.length==1) {
				System.out.print(Message.REQUIRES_PARAMETER.with_parameter("cat"));
				break;
			}
			String files=subCommand.substring(subCommand.indexOf(" "));
			SequentialFilter cat=new CAT(currentDir,files);
			res=cat;
			break;
		case "grep":
			if(words.length>2) {
				System.out.print(Message.INVALID_PARAMETER.with_parameter(subCommand));
				break;
			}
			if(words.length==1) {
				System.out.print(Message.REQUIRES_PARAMETER.with_parameter("grep"));
				break;
			}
			SequentialFilter grep=new GREP(currentDir,words[1]);
			res=grep;
			break;
		case "wc":
			SequentialFilter wc=new WC(currentDir);
			res=wc;
			break;
		case "uniq":
			SequentialFilter uniq=new UNIQ();
			res=uniq;
			break;
		case "cd":
			if(words.length>1) {
				System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(subCommand));
				break;
			}
			SequentialFilter cd=new CD();
			res=cd;
			break;
		default:
			System.out.print(Message.COMMAND_NOT_FOUND.with_parameter(subCommand));
			break;
		}
		return res;
	}
	
	/**
	 * links the given filters with the order they appear in the list, and process each filter
	 * @param filters the given filters to link
	 * @return true if the link was successful, false if there were errors encountered.
	 * Any error should be displayed by using the Message enum.
	 */
	private static boolean linkFilters(List<SequentialFilter> filters){
		for(int i=0;i<filters.size()-1;i++) {
			filters.get(i).process();
			if(filters.get(i).isDone()) {
			   filters.get(i).setNextFilter(filters.get(i+1));
			}
			else {
				return false;
			}
		}
		//process the last filter
		last.process();
		return last.isDone();
	}
}
