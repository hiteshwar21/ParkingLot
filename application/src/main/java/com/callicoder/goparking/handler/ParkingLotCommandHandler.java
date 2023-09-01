package com.callicoder.goparking.handler;

import static com.callicoder.goparking.utils.MessageConstants.*;

import com.callicoder.goparking.domain.Car;
import com.callicoder.goparking.domain.ParkingLot;
import com.callicoder.goparking.domain.ParkingSlot;
import com.callicoder.goparking.domain.Ticket;
import com.callicoder.goparking.exceptions.ParkingLotFullException;
import com.callicoder.goparking.exceptions.SlotNotFoundException;
import com.callicoder.goparking.exceptions.SlotNotOccupiedException;
import com.callicoder.goparking.utils.StringUtils;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

public class ParkingLotCommandHandler {

    private ParkingLot parkingLot;

    public void createParkingLot(int numSlots) {
        if (isParkingLotCreated()) {
            System.out.println(PARKING_LOT_ALREADY_CREATED);
            return;
        }

        try {
            parkingLot = new ParkingLot(numSlots);
            System.out.println(
                String.format(PARKING_LOT_CREATED_MSG, parkingLot.getNumSlots())
            );
        } catch (IllegalArgumentException ex) {
            System.out.println("Bad input: " + ex.getMessage());
        }
    }

    public void park(String registrationNumber, String color) {
        if (!isParkingLotCreated()) {
            System.out.println(PARKING_LOT_NOT_CREATED);
            return;
        }
        //TODO: VALIDATION FOR DUPLICATE VEHICLE
        Car car = new Car(registrationNumber, color);
        if(parkingLot.isDuplicateVehicle(car)){
            System.out.println(DUPLICATE_VEHICLE_MESSAGE);
            return;
        }
        try {
            Ticket ticket = parkingLot.reserveSlot(car);
            System.out.println(
                String.format(
                    PARKING_SLOT_ALLOCATED_MSG,
                    ticket.getSlotNumber()
                )
            );
        } catch (IllegalArgumentException ex) {
            System.out.println("Bad input: " + ex.getMessage());
        } catch (ParkingLotFullException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void leave(int slotNumber) {
        if (!isParkingLotCreated()) {
            System.out.println(PARKING_LOT_NOT_CREATED);
            return;
        }
        try {
            ParkingSlot ticket = parkingLot.leaveSlot(slotNumber);
            System.out.println(
                    String.format(
                            PARKING_LOT_EMPTIED,
                            ticket.getSlotNumber()
                    )
            );
        } catch (IllegalArgumentException ex) {
            System.out.println("Bad input: " + ex.getMessage());
        } catch (SlotNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SlotNotOccupiedException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void getSlotByColor(String color){
        if (!isParkingLotCreated()) {
            System.out.println(PARKING_LOT_NOT_CREATED);
            return;
        }

        List<Integer> slotNumbers = parkingLot.getSlotNumbersByColor(color);
        if(!slotNumbers.isEmpty()) {
            System.out.print(SLOT_NO);
            slotNumbers.forEach(slotNumber -> System.out.print(slotNumber + " "));
        } else {
            System.out.println(CAR_NOT_PRESENT + Color);
        }
    }

    public void getRegNumberByColor(String color){
        if (!isParkingLotCreated()) {
            System.out.println(PARKING_LOT_NOT_CREATED);
            return;
        }
        List<String> registrationNumbers = parkingLot.getRegistrationNumbersByColor(color);
        if(!registrationNumbers.isEmpty()) {
            System.out.println(REGISTRATION_NO + " ");
            registrationNumbers.forEach(registrationNumber -> System.out.println(registrationNumber + " "));
        } else {
            System.out.println(CAR_NOT_PRESENT + Color);
        }
    }

    public void getSlotByRegNumber(String regNumber){
        if (!isParkingLotCreated()) {
            System.out.println(PARKING_LOT_NOT_CREATED);
            return;
        }
        Optional<Integer> slotNo = parkingLot.getSlotNumberByRegistrationNumber(regNumber);
        if (slotNo.isPresent()){
            System.out.println(SLOT_NO + parkingLot.getSlotNumberByRegistrationNumber(regNumber));
        } else {
            System.out.println(CAR_NOT_PRESENT + REGISTRATION_NO);
        }
    }

    public void status() {
        if (!isParkingLotCreated()) {
            System.out.println(PARKING_LOT_NOT_CREATED);
            return;
        }

        System.out.println(SLOT_NO + "    " + REGISTRATION_NO + "    " + Color);
        parkingLot
            .getOccupiedSlots()
            .forEach(
                parkingSlot -> {
                    System.out.println(
                        StringUtils.rightPadSpaces(
                            Integer.toString(parkingSlot.getSlotNumber()),
                            SLOT_NO.length()
                        ) +
                        "    " +
                        StringUtils.rightPadSpaces(
                            parkingSlot.getCar().getRegistrationNumber(),
                            REGISTRATION_NO.length()
                        ) +
                        "    " +
                        parkingSlot.getCar().getColor()
                    );
                }
            );
    }

    private boolean isParkingLotCreated() {
        if (parkingLot == null) {
            return false;
        }
        return true;
    }
}
