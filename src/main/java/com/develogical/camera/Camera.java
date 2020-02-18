package com.develogical.camera;

public class Camera {

    private final Sensor sensor;
    private final MemoryCard card;
    private boolean powerOff;

    public Camera(Sensor sensor, MemoryCard card) {

        this.sensor = sensor;
        this.card = card;
    }

    public void pressShutter() {
        if (powerOff){

        }
        else {
            card.write(sensor.readData(), new WriteCompleteListener() {
                @Override
                public void writeComplete() {

                }
            });
        }
    }

    public void powerOn() {
        sensor.powerUp();
    }

    public boolean powerOff() {
        sensor.powerDown();
        return powerOff = true;
    }
}

