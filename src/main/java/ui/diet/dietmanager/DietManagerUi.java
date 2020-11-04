package ui.diet.dietmanager;

import ui.CommonUi;

public class DietManagerUi extends CommonUi {

    public static void printHelp() {
        StringBuilder helpMessage = new StringBuilder();

        helpMessage.append(helpFormatter("New", "new </d [DATE]> </t [TAG]>",
                "Create a new diet session"));
        helpMessage.append(helpFormatter("List", "list",
                "Show all past diet sessions"));
        helpMessage.append(helpFormatter("Delete", "delete [INDEX]",
                "Delete the diet session at the input index"));
        helpMessage.append(helpFormatter("Edit", "edit [INDEX]",
                "Edit the diet session at the input index"));
        helpMessage.append(helpFormatter("Search",
                "search </s [STARTING_DATE]> </e [END_DATE]> </t [TAG]>",
                "Search the diet session in between starting and end dates with a specific tag"));
        helpMessage.append(helpFormatter("Clear", "clear",
                "Clear all past diet sessions"));
        helpMessage.append(helpFormatter("End", "end",
                "Go back to Main Menu"));
        showUser(helpMessage.toString().trim());
    }

    public static String DIET_NEW_SUCCESS = "Exiting Diet Session!";
    public static String DIET_DELETE_SUCCESS = "You have deleted that diet session!";
}
