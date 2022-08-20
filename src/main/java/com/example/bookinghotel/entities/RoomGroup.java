package com.example.bookinghotel.entities;

import java.util.List;

public class RoomGroup {


    private List<Room> empty_rooms;
    private Room specificRoom;


    public RoomGroup(List<Room> empty_rooms, Room specificRoom) {
        this.empty_rooms = empty_rooms;
        this.specificRoom = specificRoom;
    }

    public Room getSpecificRoom() {
        return specificRoom;
    }

    public void setSpecificRoom(Room specificRoom) {
        this.specificRoom = specificRoom;
    }

    public RoomGroup() {

    }


    public List<Room> getEmpty_rooms() {
        return empty_rooms;
    }

    public void setEmpty_rooms(List<Room> empty_rooms) {
        this.empty_rooms = empty_rooms;
    }

}
