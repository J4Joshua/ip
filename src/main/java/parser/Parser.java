package parser;

import command.Command;
import command.FindCommand;
import command.ListCommand;
import command.UnmarkCommand;
import command.MarkCommand;
import command.ExitCommand;
import command.DeleteCommand;
import exception.EkkoException;

/**
 * Parses user input and returns the corresponding Command object.
 */
public class Parser {
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

            default:
                throw new EkkoException("Invalid command! Available commands: list, mark, unmark, find, delete, bye");
        }
    }

    private static int parseIndex(String[] inputArr) throws EkkoException {
        if (inputArr.length < 2 || !inputArr[1].matches("\\d+")) {
            throw new EkkoException("Invalid index format.");
        }
        return Integer.parseInt(inputArr[1]) - 1;
    }
}
