package com.callicoder.goparking.interaction.commands;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;

public class SlotNumberByColor implements Command {

    private ParkingLotCommandHandler parkingLotCommandHandler;

    public SlotNumberByColor(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
        return "slot_numbers_for_cars_with_colour";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (params.length != 1) {
            throw new InvalidParameterException(
                    "Expected 1 parameters <Color>"
            );
        }
        this.parkingLotCommandHandler.getSlotByColor(params[0]);
    }
}
