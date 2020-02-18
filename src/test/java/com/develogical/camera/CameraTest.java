package com.develogical.camera;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

public class CameraTest {
    Sensor sensor = mock(Sensor.class);
    MemoryCard card = mock(MemoryCard.class);
    Camera camera = new Camera(sensor, card);

    @Test
    public void switchingTheCameraOnPowersUpTheSensor() {
        camera.powerOn();
        verify(sensor).powerUp();
    }

    @Test
    public void switchingTheCameraOffPowersDownTheSensor() {
        camera.powerOff();
        verify(sensor).powerDown();
    }

    @Test
    public void pressingTheShutterCopies() {
        camera.powerOn();
        when(sensor.readData()).thenReturn(new byte[]{42});

        camera.pressShutter();
        verify(card).write(eq(new byte[]{42}), any());
    }

    @Test
    public void pressingTheShutterReturnNothing() {
        camera.powerOff();
        camera.pressShutter();
        verifyNoMoreInteractions(card);
    }

    @Test
    public void CameraOffSensorPowerOn() {
        camera.powerOn();
        camera.pressShutter();
        camera.powerOff();
        verify(sensor,never()).powerDown();
    }

//    @Test
//    public void CameraPowersDownSensor() {
//        camera.powerOn();
//
//        ArgumentCaptor<WriteCompleteListener> captor = ArgumentCaptor.forClass(WriteCompleteListener.class);
//
//        Camera camera = new Camera(sensor, card);
//        camera.powerOn();
//        camera.pressShutter();
//        camera.powerOff();
//
//        verify(card).write(any(), captor.capture());
//
//        verify(sensor,never()).powerDown();
//        captor.getValue().writeComplete();
//        verify(sensor).powerDown();
//    }
}
