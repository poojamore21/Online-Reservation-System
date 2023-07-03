/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package online.reservation.system;

/**
 *
 * @author Ananda
 */
public class TicketInfo {
    private String name;
    private String trainName;
    private String trainNumber;
    private String date;
    private String classType;
    private String from;
    private String to;

    public TicketInfo(String name, String trainName, String trainNumber, String date, String classType, String from, String to) {
        this.name = name;
        this.trainName = trainName;
        this.trainNumber = trainNumber;
        this.date = date;
        this.classType = classType;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public String getDate() {
        return date;
    }

    public String getClassType() {
        return classType;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}

