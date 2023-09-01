package com.callicoder.goparking.interaction.commands;

import com.callicoder.goparking.exceptions.InvalidParameterException;
import com.callicoder.goparking.handler.ParkingLotCommandHandler;
import com.callicoder.goparking.utils.StringUtils;

public class LeaveCommand implements Command {

    private ParkingLotCommandHandler parkingLotCommandHandler;

    public LeaveCommand(ParkingLotCommandHandler parkingLotCommandHandler) {
        this.parkingLotCommandHandler = parkingLotCommandHandler;
    }

    @Override
    public String helpText() {
        return "leave <slotNumber>";
    }

    @Override
    public void execute(String[] params) throws InvalidParameterException {
        if (params.length != 1) {
            throw new InvalidParameterException(
                "Expected one parameter <slotNumber>"
            );
        }
        if(!StringUtils.isInteger(params[0])) {
            throw new InvalidParameterException(
                    "Expected Integer value in <slotNumber>"
            );
        }
        parkingLotCommandHandler.leave(Integer.parseInt(params[0]));
    }
}
