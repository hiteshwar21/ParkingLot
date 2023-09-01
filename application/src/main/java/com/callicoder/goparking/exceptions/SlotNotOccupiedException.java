package com.callicoder.goparking.exceptions;

public class SlotNotOccupiedException extends RuntimeException {

    private int slotNumber;

    public SlotNotOccupiedException(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public String getMessage() {
        return "Slot number " + slotNumber + " is not occupied!";
    }
}
