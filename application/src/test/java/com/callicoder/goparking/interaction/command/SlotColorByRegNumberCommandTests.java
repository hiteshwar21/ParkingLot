package com.callicoder.goparking.interaction.command;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;
import com.callicoder.goparking.interaction.commands.SlotNumberByColor;
import com.callicoder.goparking.interaction.commands.SlotNumberByRegNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SlotColorByRegNumberCommandTests {

    private static ParkingLotCommandHandler parkingLotCommandHandler;
    private static SlotNumberByRegNumber slotNumberByRegNumber;

    @BeforeAll
    public static void createCommand() {
        parkingLotCommandHandler = new ParkingLotCommandHandler();
        slotNumberByRegNumber = new SlotNumberByRegNumber(parkingLotCommandHandler);
    }

    @Test
    public void executeWithNoArg_shouldThrowError() {
        String[] params = {};
        assertThrows(
            InvalidParameterException.class,
            () -> slotNumberByRegNumber.execute(params)
        );
    }

    @Test
    public void executeWithoutMoreThanOneArgs_shouldThrowError() {
        String[] params = { "KA01HQ4669","KA01HQ4670" };
        assertThrows(
            InvalidParameterException.class,
            () -> slotNumberByRegNumber.execute(params)
        );
    }

    @Test
    public void executeWithValidArgs_shouldWork() {
        String[] params = { "White"};
        Assertions.assertDoesNotThrow(() -> slotNumberByRegNumber.execute(params));
    }
}
