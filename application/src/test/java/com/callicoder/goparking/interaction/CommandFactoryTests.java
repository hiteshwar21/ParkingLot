package com.callicoder.goparking.interaction;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.callicoder.goparking.exceptions.CommandNotFoundException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;
import com.callicoder.goparking.interaction.CommandFactory;
import org.junit.jupiter.api.Test;

public class CommandFactoryTests {

    @Test
    public void init_shouldInitializeAllCommands() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        CommandFactory commandFactory = CommandFactory.init(
            parkingLotCommandHandler
        );

        assertTrue(
            commandFactory.getCommands().keySet().contains("create_parking_lot")
        );
        assertTrue(commandFactory.getCommands().keySet().contains("park"));
        assertTrue(commandFactory.getCommands().keySet().contains("status"));
        assertTrue(commandFactory.getCommands().keySet().contains("leave"));
        assertTrue(commandFactory.getCommands().keySet().contains("registration_numbers_for_cars_with_colour"));
        assertTrue(commandFactory.getCommands().keySet().contains("slot_numbers_for_cars_with_colour"));
        assertTrue(commandFactory.getCommands().keySet().contains("slot_number_for_registration_number"));
    }

    @Test
    public void executeInvalidCommand_shouldThrowError() {
        ParkingLotCommandHandler parkingLotCommandHandler = new ParkingLotCommandHandler();
        CommandFactory commandFactory = CommandFactory.init(
            parkingLotCommandHandler
        );

        String[] params = { "random" };
        assertThrows(
            CommandNotFoundException.class,
            () -> commandFactory.executeCommand("alpha", params)
        );
    }
}
