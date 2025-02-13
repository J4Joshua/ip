package parser;

import command.Command;
import command.AddCommand;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import exception.EkkoException;

/**
 * Parses user input and returns the corresponding Command object.
 */
public class Parser {

    /**
     * Parses user input and returns the corresponding command.
     *
     * @param input The user input string.
     * @return A Command object based on the user's input.
     * @throws EkkoException If the command is invalid or input is malformed.
     */
    public static Command parse(String input) throws EkkoException {
        String[] inputArr = input.split(" ", 2);
        String commandWord = inputArr[0];

        switch (commandWord) {
        case "list":
            return new ListCommand();

        case "mark":
            return new MarkCommand(parseIndex(inputArr));

        case "unmark":
            return new UnmarkCommand(parseIndex(inputArr));

        case "find":
            if (inputArr.length < 2) {
                throw new EkkoException("Please provide a keyword to search.");
            }
            return new FindCommand(inputArr[1]);

        case "delete":
            return new DeleteCommand(parseIndex(inputArr));

        case "bye":
            return new ExitCommand();

        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(input); // Passes full command to AddCommand for further parsing

        default:
            throw new EkkoException("Invalid command! Available commands: list, mark, unmark, find, delete, bye, todo, deadline, event");
        }
    }

    private static int parseIndex(String[] inputArr) throws EkkoException {
        if (inputArr.length < 2 || !inputArr[1].matches("\\d+")) {
            throw new EkkoException("Invalid index format.");
        }
        return Integer.parseInt(inputArr[1]) - 1;
    }
}