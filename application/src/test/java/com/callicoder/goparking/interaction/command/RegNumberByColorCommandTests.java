package com.callicoder.goparking.interaction.command;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;
import com.callicoder.goparking.interaction.commands.LeaveCommand;
import com.callicoder.goparking.interaction.commands.RegNumberByColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RegNumberByColorCommandTests {

    private static ParkingLotCommandHandler parkingLotCommandHandler;
    private static RegNumberByColor regNumberByColor;

    @BeforeAll
    public static void createCommand() {
        parkingLotCommandHandler = new ParkingLotCommandHandler();
        regNumberByColor = new RegNumberByColor(parkingLotCommandHandler);
    }

    @Test
    public void executeWithNoArg_shouldThrowError() {
        String[] params = {};
        assertThrows(
            InvalidParameterException.class,
            () -> regNumberByColor.execute(params)
        );
    }

    @Test
    public void executeWithoutMoreThanOneArgs_shouldThrowError() {
        String[] params = { "4","5" };
        assertThrows(
            InvalidParameterException.class,
            () -> regNumberByColor.execute(params)
        );
    }

    @Test
    public void executeWithValidArgs_shouldWork() {
        String[] params = { "White"};
        Assertions.assertDoesNotThrow(() -> regNumberByColor.execute(params));
    }
}
