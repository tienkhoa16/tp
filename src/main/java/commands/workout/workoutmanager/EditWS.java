package commands.workout.workoutmanager;

import commands.Command;
import commands.CommandResult;
import commands.ExecutionResult;
import exceptions.SchwarzeneggerException;
import exceptions.workoutmanager.InsufficientArgumentException;
import exceptions.workoutmanager.NotANumberException;
import exceptions.workoutmanager.OutOfArrayException;
import models.PastRecordList;
import workout.workoutsession.WorkoutSession;

import static ui.workout.workoutmanager.WorkoutManagerUi.EDIT_SUCCESS;

public class EditWS extends Command {

    /**
     * Edits a record at a given index.
     *
     * @param args Index of the record to be edited.
     * @return Status OK and feedback message if file is edit.
     * @throws SchwarzeneggerException If there are caught exceptions.
     */
    @Override
    public CommandResult execute(String args) throws SchwarzeneggerException {
        super.execute(args);
        int index = -1;
        try {
            index = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            logger.warning("Number format exception caught");
            throw new NotANumberException();
        } catch (IndexOutOfBoundsException e) {
            logger.warning("Insufficient arguments given!");
            throw new InsufficientArgumentException();
        }

        String filePath;
        try {
            filePath = PastRecordList.getInstance().edit(index);
        } catch (IndexOutOfBoundsException e) {
            logger.warning("Index Out Of Bounds Exception caught");
            throw new OutOfArrayException();
        }
        WorkoutSession ws = new WorkoutSession(filePath, false, index);
        logger.info("editing workout session created");
        ws.workoutSessionStart();

        logger.info("edited successfully");
        return new CommandResult(EDIT_SUCCESS, ExecutionResult.OK);
    }

}
