package profile;

import commands.Command;
import commands.profile.AddProfile;
import commands.profile.DeleteProfile;
import commands.profile.EditProfile;
import commands.profile.EndProfile;
import commands.profile.HelpProfile;
import commands.profile.ViewProfile;
import exceptions.profile.InvalidAgeException;
import exceptions.profile.InvalidCommandFormatException;
import exceptions.profile.InvalidCommandWordException;
import exceptions.profile.InvalidHeightException;
import exceptions.profile.InvalidWeightException;
import exceptions.profile.RedundantParamException;

import java.util.HashMap;

import static profile.Constants.COMMAND_ARGS_INDEX;
import static profile.Constants.COMMAND_SPLIT_LIMIT;
import static profile.Constants.COMMAND_TYPE_INDEX;
import static profile.Constants.COMMAND_WORD_ADD;
import static profile.Constants.COMMAND_WORD_DELETE;
import static profile.Constants.COMMAND_WORD_EDIT;
import static profile.Constants.COMMAND_WORD_END;
import static profile.Constants.COMMAND_WORD_HELP;
import static profile.Constants.COMMAND_WORD_VIEW;
import static profile.Constants.EMPTY_STRING;
import static profile.Constants.GREEDY_WHITE_SPACE;

/**
 * A class that deals with making sense of user's command inside Profile Session.
 */
public class ProfileParser {

    /**
     * Parses and returns the Command associated with the user input.
     *
     * @param userInputString User's raw input string.
     * @return Associated command.
     * @throws InvalidCommandWordException If command word is invalid.
     * @throws RedundantParamException If redundant parameters are provided.
     */
    public Command parseCommand(String userInputString) throws InvalidCommandWordException, RedundantParamException {
        String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        String commandType = commandTypeAndParams[COMMAND_TYPE_INDEX].toLowerCase();
        String commandArgs = commandTypeAndParams[COMMAND_ARGS_INDEX];

        switch (commandType) {
        case COMMAND_WORD_HELP:
            return new HelpProfile(commandArgs);
        case COMMAND_WORD_ADD:
            return new AddProfile(commandArgs);
        case COMMAND_WORD_DELETE:
            return new DeleteProfile(commandArgs);
        case COMMAND_WORD_VIEW:
            return new ViewProfile(commandArgs);
        case COMMAND_WORD_EDIT:
            return new EditProfile(commandArgs);
        case COMMAND_WORD_END:
            return new EndProfile(commandArgs);
        default:
            throw new InvalidCommandWordException();
        }
    }

    /**
     * Splits raw user's input into command word and command arguments string.
     *
     * @param rawUserInput User's raw input.
     * @return Size 2 array; first element is the command type and second element is the arguments string.
     */
    public static String[] splitCommandWordAndArgs(String rawUserInput) {
        String[] split = rawUserInput.trim().split(GREEDY_WHITE_SPACE,
                COMMAND_SPLIT_LIMIT);

        return split.length == COMMAND_SPLIT_LIMIT ? split : new String[]{split[COMMAND_TYPE_INDEX], EMPTY_STRING};
    }

    /**
     * Extracts command tags from user input to get option indicator and parsed option.
     *
     * @param command Command being executed.
     * @param commandArgs User's input arguments.
     * @return HashMap containing option indicator and parsed option pairs.
     * @throws InvalidCommandFormatException If the input command has invalid format.
     */
    public static HashMap<String, String> extractCommandTagAndInfo(String command, String commandArgs)
            throws InvalidCommandFormatException {
        if (!commandArgs.contains("/")) {
            throw new InvalidCommandFormatException(command);
        }

        HashMap<String, String> parsedParams = new HashMap<>();
        int startIndex = 0;
        int endIndex = 0;

        try {
            while (commandArgs.indexOf("/", startIndex) != -1) {
                endIndex = commandArgs.indexOf("/", startIndex + 1);

                if (endIndex == -1) {
                    endIndex = commandArgs.length();
                }

                String parsedOption = commandArgs.substring(startIndex + 2, endIndex).trim();
                String optionIndicator = commandArgs.substring(startIndex, startIndex + 2).trim();
                parsedParams.put(optionIndicator, parsedOption);

                startIndex = endIndex;
            }

            return parsedParams;
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandFormatException(command);
        }
    }

    /**
     * Extracts name from parsed HashMap.
     *
     * @param parsedParams HashMap containing option indicator and parsed option pairs.
     * @return User's name.
     */
    public static String extractName(HashMap<String, String> parsedParams) {
        return parsedParams.get("/n");
    }

    /**
     * Extracts age from parsed HashMap.
     *
     * @param parsedParams HashMap containing option indicator and parsed option pairs.
     * @return User's age.
     * @throws InvalidAgeException If input age is invalid.
     */
    public static int extractAge(HashMap<String, String> parsedParams) throws InvalidAgeException {
        try {
            int age = Integer.parseInt(parsedParams.get("/a"));

            if (!Utils.checkValidAge(age)) {
                throw new InvalidAgeException();
            }
            return age;
        } catch (NumberFormatException e) {
            throw new InvalidAgeException();
        }
    }

    /**
     * Extracts height from parsed HashMap.
     *
     * @param parsedParams HashMap containing option indicator and parsed option pairs.
     * @return User's height.
     * @throws InvalidHeightException If input height is invalid.
     */
    public static int extractHeight(HashMap<String, String> parsedParams) throws InvalidHeightException {
        try {
            int height = Integer.parseInt(parsedParams.get("/h"));

            if (!Utils.checkValidHeight(height)) {
                throw new InvalidHeightException();
            }
            return height;
        } catch (NumberFormatException e) {
            throw new InvalidHeightException();
        }
    }

    /**
     * Extracts weight from parsed HashMap.
     *
     * @param parsedParams HashMap containing option indicator and parsed option pairs.
     * @return User's weight.
     * @throws InvalidWeightException If input weight is invalid.
     */
    public static double extractWeight(HashMap<String, String> parsedParams) throws InvalidWeightException {
        try {
            double weight = Double.parseDouble(parsedParams.get("/w"));

            if (!Utils.checkValidWeight(weight)) {
                throw new InvalidWeightException();
            }
            return weight;
        } catch (NumberFormatException e) {
            throw new InvalidWeightException();
        }
    }

    /**
     * Extracts expected weight from parsed HashMap.
     *
     * @param parsedParams HashMap containing option indicator and parsed option pairs.
     * @return User's expected weight.
     * @throws InvalidWeightException If input expected weight is invalid.
     */
    public static double extractExpectedWeight(HashMap<String, String> parsedParams) throws InvalidWeightException {
        try {
            double expectedWeight = Double.parseDouble(parsedParams.get("/e"));

            if (!Utils.checkValidWeight(expectedWeight)) {
                throw new InvalidWeightException();
            }
            return expectedWeight;
        } catch (NumberFormatException e) {
            throw new InvalidWeightException();
        }
    }
}
