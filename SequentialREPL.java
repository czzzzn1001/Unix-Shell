package cs131.pa1.filter.sequential;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import cs131.pa1.filter.Message;

/**
 * The main implementation of the REPL loop (read-eval-print loop).
 * It reads commands from the user, parses them, executes them and displays the result.
 * @author cs131a
 *
 */
public class SequentialREPL {
	
	/**
	 * the path of the current working directory
	 */
	static String currentWorkingDirectory;
	public static final String SEP = System.getProperty("file.separator");
	/**
	 * judge first if the first command contains cd ,if so, we dont need commandBuilder
	 * if not, we then initialize CB to parse the command string in the loop
	 * this function also parses the cd command
	 * @param the command string
	 * @return true if this command is cd, false if not
	 */
	static boolean parse_cd(String command) {
		command=command.trim();
		boolean res=false;
		String[] words=command.split(" ");
		if(words.length>0&&words[0].equals("cd")) {
			res=true;
			if(words.length==1) {
				System.out.print(Message.REQUIRES_PARAMETER.with_parameter("cd"));
				return res;
			}else if(words.length>2) {
				if(Arrays.asList(words).contains("|")) {
				   System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(command.substring(0,command.indexOf("|"))));
				}
				else if(Arrays.asList(words).contains(">")) {
				 System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(command.substring(0,command.indexOf(">"))));
				}
				else
				   System.out.print(Message.INVALID_PARAMETER.with_parameter("cd")); 
				return res;
			}
			if(words[1].equals(".")) {
				return res;
			}
			else if(words[1].equals("..")) {
				File curr=new File(currentWorkingDirectory);
				currentWorkingDirectory=curr.getParent();
				return res;
			}
			String temp=currentWorkingDirectory+SEP+words[1];
			File file=new File(temp);
			if(file.exists()) {
				currentWorkingDirectory=temp;
			}
			else{
				System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter("cd "+words[1]));
			}
		}
		return res;
	}
	/**
	 * judge first if the first command contains exit
	 * this function also parses the exit command
	 * @param the command string
	 * @return true if this command is exit, false if not
	 */
	static boolean parse_exit(String command) {
		boolean res=false;
		String[] words=command.split(" ");
		if(words.length>0&&words[0].equals("exit")) {
			res=true;
			System.out.print(Message.GOODBYE.toString());
		}
		return res;
	}
	/**
	 * The main method that will execute the REPL loop
	 * @param args not used
	 */
	public static void main(String[] args){
		boolean exit=false;
		currentWorkingDirectory=""+System.getProperty("user.dir");
		System.out.print(Message.WELCOME.toString());
		Scanner console=new Scanner(System.in);
		while(!exit) {
			System.out.print(Message.NEWCOMMAND);
			String command=console.nextLine();
			if(parse_exit(command)) {
				exit=true;
			}
			//if parse_cd, do nothing
			else if(parse_cd(command)) {
				
			}
			else{
				SequentialCommandBuilder.createFiltersFromCommand(command, currentWorkingDirectory);
			}
			//System.out.println(currentWorkingDirectory);
		}
	}

}
