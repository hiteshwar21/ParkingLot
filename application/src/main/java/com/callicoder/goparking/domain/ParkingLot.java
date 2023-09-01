package com.callicoder.goparking.domain;

import com.callicoder.goparking.exceptions.ParkingLotFullException;
import com.callicoder.goparking.exceptions.SlotNotFoundException;
import com.callicoder.goparking.exceptions.SlotNotOccupiedException;

import java.util.*;

public class ParkingLot {

    private final int numSlots;
    private final int numFloors;
    private SortedSet<ParkingSlot> availableSlots = new TreeSet<>();
    private Set<ParkingSlot> occupiedSlots = new HashSet<>();

    public ParkingLot(int numSlots) {
        if (numSlots <= 0) {
            throw new IllegalArgumentException(
                "Number of slots in the Parking Lot must be greater than zero."
            );
        }

        // Assuming Single floor since only numSlots are specified in the input.
        this.numSlots = numSlots;
        this.numFloors = 1;

        for (int i = 0; i < numSlots; i++) {
            ParkingSlot parkingSlot = new ParkingSlot(i + 1, 1);
            this.availableSlots.add(parkingSlot);
        }
    }

    public synchronized Ticket reserveSlot(Car car) {
        if (car == null) {
            throw new IllegalArgumentException("Car must not be null");
        }

        if (this.isFull()) {
            throw new ParkingLotFullException();
        }

        ParkingSlot nearestSlot = this.availableSlots.first();

        nearestSlot.reserve(car);
        this.availableSlots.remove(nearestSlot);
        this.occupiedSlots.add(nearestSlot);

        return new Ticket(
            nearestSlot.getSlotNumber(),
            car.getRegistrationNumber(),
            car.getColor()
        );
    }

    public boolean isDuplicateVehicle(Car car){
        for (ParkingSlot slot : occupiedSlots) {
            if (slot.getCar().equals(car)) {
                return true;
            }
        }
        return false;
    }

    public ParkingSlot leaveSlot(int slotNumber) {
        //TODO: implement leave
        if (slotNumber < 1 || slotNumber > numSlots) {
            throw new SlotNotFoundException(slotNumber);
        }

        ParkingSlot slotToFree = new ParkingSlot(slotNumber, 1);

        if (!occupiedSlots.contains(slotToFree)) {
            throw new SlotNotOccupiedException(slotNumber);
        }

        // Find the occupied slot in the occupiedSlots set
        ParkingSlot occupiedSlot = null;
        for (ParkingSlot slot : occupiedSlots) {
            if (slot.equals(slotToFree)) {
                occupiedSlot = slot;
                break;
            }
        }

        if (occupiedSlot == null) {
            throw new IllegalStateException("Internal error: Occupied slot not found");
        }

        occupiedSlot.clear();

        // Update the sets
        occupiedSlots.remove(occupiedSlot);
        availableSlots.add(occupiedSlot);
        return occupiedSlot;
    }

    public boolean isFull() {
        return this.availableSlots.isEmpty();
    }

    public List<String> getRegistrationNumbersByColor(String color) {
        //TODO: implement getRegistrationNumbersByColor
        List<String> registrationNumbers = new ArrayList<>();
        for (ParkingSlot slot : occupiedSlots) {
            if (!slot.isAvailable() && slot.getCar().getColor().equalsIgnoreCase(color)) {
                registrationNumbers.add(slot.getCar().getRegistrationNumber());
            }
        }
        return registrationNumbers;
    }

    public List<Integer> getSlotNumbersByColor(String color) {
        //TODO: implement getSlotNumbersByColor
        List<Integer> slotNumbers = new ArrayList<>();
        for (ParkingSlot slot : occupiedSlots) {
            if (!slot.isAvailable() && slot.getCar().getColor().equalsIgnoreCase(color)) {
                slotNumbers.add(slot.getSlotNumber());
            }
        }
        return slotNumbers;
    }

    public Optional<Integer> getSlotNumberByRegistrationNumber(String registrationNumber) {
        for (ParkingSlot slot : occupiedSlots) {
            if (!slot.isAvailable() && slot.getCar().getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                return Optional.of(slot.getSlotNumber());
            }
        }
        return Optional.empty();
    }

    public int getNumSlots() {
        return numSlots;
    }

    public int getNumFloors() {
        return numFloors;
    }

    public SortedSet<ParkingSlot> getAvailableSlots() {
        return availableSlots;
    }

    public Set<ParkingSlot> getOccupiedSlots() {
        return occupiedSlots;
    }
}
