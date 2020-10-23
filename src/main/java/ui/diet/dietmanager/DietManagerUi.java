package ui.diet.dietmanager;

import ui.CommonUi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DietManagerUi extends CommonUi {

    private static CommonUi printer = new CommonUi();

    public static void printOpening() {
        showUser("You're now in diet manager!");
    }

    public static void printBye() {
        showUser("Exiting diet manager!!");
    }

    /**
     * Extracts out date and time by looking for date strings in YYYY-MM-DD format.
     *
     * @param input date string
     * @return date in MMM dd yyyy if the user inputs date in YYYY-MM-DD format;
     *         else returns original string
     * @throws DateTimeParseException if the date string input is not a valid date
     */
    public String extractDate(String input) throws DateTimeParseException, IllegalStateException {
        try {
            String dateString = input.split("/t")[0].split(" ", 2)[1].trim();
            Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
            Matcher matcher = pattern.matcher(dateString);
            boolean isValidDate = matcher.find();
            String match = matcher.group();
            return LocalDate.parse(match).format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        } catch (IndexOutOfBoundsException e) {
            showToUser("I do not understand your date input!");
        } catch (NullPointerException e) {
            showToUser("It looks like there is no date input");
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        showToUser("I've replaced it with today's date.");
        return dtf.format(now);
    }

    public String extractMeal(String input) {
        try {
            return input.split("/t")[1].trim();
        } catch (IndexOutOfBoundsException e) {
            showToUser("Please specify your diet session tag, i.e. breakfast, lunch, dinner");
        } catch (NullPointerException e) {
            System.out.println("It looks like there's no input after /t");
        }
        showToUser("Session is tagged as unspecified.");
        return "unspecified";
    }

    public static void printHelp() {
        StringBuilder helpMessage = new StringBuilder();

        helpMessage.append(helpFormatter("List", "list",
                "Show all past diet session"));
        helpMessage.append(helpFormatter("Meal", "meal /d date /t tag",
                "Create a new diet session"));
        helpMessage.append(helpFormatter("Delete", "delete x",
                "Delete the diet session indexed at x"));
        helpMessage.append(helpFormatter("Edit", "edit x",
                "Edit the diet session indexed at x"));
        helpMessage.append(helpFormatter("Clear", "clear",
                "Clear all past diet sessions"));
        helpMessage.append(helpFormatter("End", "end",
                "Go back to main menu"));
        printer.showToUser(helpMessage.toString().trim());
    }
}