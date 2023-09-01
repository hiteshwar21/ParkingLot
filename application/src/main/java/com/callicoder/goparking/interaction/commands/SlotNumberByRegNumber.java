package com.callicoder.goparking.interaction.commands;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;

public class SlotNumberByRegNumber implements Command {

    private ParkingLotCommandHandler parkingLotCommandHandler;

    public SlotNumberByRegNumber(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
        return "slot_number_for_registration_number";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (params.length != 1) {
            throw new InvalidParameterException(
                    "Expected 1 parameters <registrationNumber>"
            );
        }
        this.parkingLotCommandHandler.getSlotByRegNumber(params[0]);
    }
}
