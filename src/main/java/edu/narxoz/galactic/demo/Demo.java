package edu.narxoz.galactic.demo;

import edu.narxoz.galactic.bodies.Planet;
import edu.narxoz.galactic.cargo.Cargo;
import edu.narxoz.galactic.dispatcher.Dispatcher;
import edu.narxoz.galactic.drones.HeavyDrone;
import edu.narxoz.galactic.drones.LightDrone;
import edu.narxoz.galactic.task.DeliveryTask;

public class Demo {
    public static void main(String[] args) {
        var earth = new Planet("Earth", 0, 0, "Nitrogen and oxygen");
        var mars = new Planet("Mars", 30, 40, "Carbon dioxide");

        var cargo = new Cargo(12.0, "Food");
        var task = new DeliveryTask(earth, mars, cargo);

        var factory = new DroneFactory();

        Drone light = factory.createDrone("LIGHT", "Light Drone 1", 5.0);
        Drone heavy = factory.createDrone("HEAVY", "Heavy Drone 1", 20.0);

        var dispatcher = new Dispatcher();

        var r1 = dispatcher.assignTask(task, light);
        System.out.println("Assign to Light Drone: ok=" + r1.ok() + ", reason=" + r1.reason());

        var r2 = dispatcher.assignTask(task, heavy);
        System.out.println("Assign to Heavy Drone: ok=" + r2.ok() + ", reason=" + r2.reason());

        if (r2.ok()) {
            System.out.println("Estimated time (min): " + task.estimateTime());
        }

        var r3 = dispatcher.completeTask(task);
        System.out.println("Complete task: ok=" + r3.ok() + ", reason=" + r3.reason());

        System.out.println("Task state: " + task.getState());
        System.out.println("Heavy drone status: " + heavy.getStatus());
    }
}