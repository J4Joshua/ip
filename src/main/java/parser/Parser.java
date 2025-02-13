package parser;

import command.Command;
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
     * Parses the user's input into a Command.
     *
     * @param input The user input string.
     * @return A Command object based on the user's input.
     * @throws EkkoException If the command is invalid or input is malformed.
     */
    public static Command parse(String input) throws EkkoException {
        assert input != null : "Input should not be null";
        String[] inputArr = input.split(" ", 2);
        String commandWord = inputArr[0];

        switch (commandWord) {
        case "list":
            return new ListCommand();

        case "mark":
            assert inputArr.length == 2 : "Mark command must have an index";
            return new MarkCommand(parseIndex(inputArr));

        case "unmark":
            assert inputArr.length == 2 : "Unmark command must have an index";
            return new UnmarkCommand(parseIndex(inputArr));

        case "find":
            assert inputArr.length == 2 : "Find command must have a keyword";
            return new FindCommand(inputArr[1]);

        case "delete":
            assert inputArr.length == 2 : "Delete command must have an index";
            return new DeleteCommand(parseIndex(inputArr));

        case "bye":
            return new ExitCommand();

        default:
            throw new EkkoException("Invalid command! Available commands: list, mark, unmark, find, delete, bye");
        }
    }

    /**
     * Parses the index from the user's input.
     *
     * @param inputArr The array of input strings split by spaces.
     * @return The parsed index as an integer (0-based).
     * @throws EkkoException If the index is missing or in an invalid format.
     */
    private static int parseIndex(String[] inputArr) throws EkkoException {
        assert inputArr.length == 2 : "Input must have exactly 2 parts for index parsing";
        if (!inputArr[1].matches("\\d+")) {
            throw new EkkoException("Invalid index format.");
        }
        return Integer.parseInt(inputArr[1]) - 1;
    }
}