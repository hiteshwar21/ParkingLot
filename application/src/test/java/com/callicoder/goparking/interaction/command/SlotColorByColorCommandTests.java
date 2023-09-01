package com.callicoder.goparking.interaction.command;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;
import com.callicoder.goparking.interaction.commands.RegNumberByColor;
import com.callicoder.goparking.interaction.commands.SlotNumberByColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SlotColorByColorCommandTests {

    private static ParkingLotCommandHandler parkingLotCommandHandler;
    private static SlotNumberByColor slotNumberByColor;

    @BeforeAll
    public static void createCommand() {
        parkingLotCommandHandler = new ParkingLotCommandHandler();
        slotNumberByColor = new SlotNumberByColor(parkingLotCommandHandler);
    }

    @Test
    public void executeWithNoArg_shouldThrowError() {
        String[] params = {};
        assertThrows(
            InvalidParameterException.class,
            () -> slotNumberByColor.execute(params)
        );
    }

    @Test
    public void executeWithoutMoreThanOneArgs_shouldThrowError() {
        String[] params = { "4","5" };
        assertThrows(
            InvalidParameterException.class,
            () -> slotNumberByColor.execute(params)
        );
    }

    @Test
    public void executeWithValidArgs_shouldWork() {
        String[] params = { "White"};
        Assertions.assertDoesNotThrow(() -> slotNumberByColor.execute(params));
    }
}
