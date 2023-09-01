package com.callicoder.goparking.interaction.commands;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;

public class RegNumberByColor implements Command {

    private ParkingLotCommandHandler parkingLotCommandHandler;

    public RegNumberByColor(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
        return "registration_numbers_for_cars_with_colour";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (params.length != 1) {
            throw new InvalidParameterException(
                    "Expected 1 parameters <color>"
            );
        }
        this.parkingLotCommandHandler.getRegNumberByColor(params[0]);
    }
}
