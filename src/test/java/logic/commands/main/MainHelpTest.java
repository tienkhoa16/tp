package logic.commands.main;

import exceptions.SchwarzeneggerException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ui.CommonUi.EMPTY_STRING;

//@@author tienkhoa16
class MainHelpTest {

    @Test
    void testExecute_inputNullArguments_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            new MainHelp().execute(null);
        });
    }

    @Test
    void testExecute_inputEmptyArguments_returnSuccess() throws SchwarzeneggerException {
        assertEquals(EMPTY_STRING, new MainHelp().execute(EMPTY_STRING).getFeedbackMessage());
    }
}
