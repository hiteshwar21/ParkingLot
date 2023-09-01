package com.callicoder.goparking.interaction.command;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;
import com.callicoder.goparking.interaction.commands.LeaveCommand;
import com.callicoder.goparking.interaction.commands.ParkCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LeaveCommandTests {

    private static ParkingLotCommandHandler parkingLotCommandHandler;
    private static LeaveCommand leaveCommand;

    @BeforeAll
    public static void createCommand() {
        parkingLotCommandHandler = new ParkingLotCommandHandler();
        leaveCommand = new LeaveCommand(parkingLotCommandHandler);
    }

    @Test
    public void executeWithNoArg_shouldThrowError() {
        String[] params = {};
        assertThrows(
            InvalidParameterException.class,
            () -> leaveCommand.execute(params)
        );
    }

    @Test
    public void executeWithoutMoreThanOneArgs_shouldThrowError() {
        String[] params = { "4","5" };
        assertThrows(
            InvalidParameterException.class,
            () -> leaveCommand.execute(params)
        );
    }

    @Test
    public void executeWithNonIntegerArgs_shouldThrowError() {
        String[] params = { "Foo" };
        assertThrows(
                InvalidParameterException.class,
                () -> leaveCommand.execute(params)
        );
    }

    @Test
    public void executeWithValidArgs_shouldWork() {
        String[] params = { "4"};
        Assertions.assertDoesNotThrow(() -> leaveCommand.execute(params));
    }
}
