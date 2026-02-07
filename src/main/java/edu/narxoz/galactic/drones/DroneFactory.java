package edu.narxoz.galactic.drones;

public class DroneFactory {
    public Drone createDrone(String type, String id, double maxPayloadKg) {
        if (type == null) {
            throw new IllegalArgumentException("type cannot be null");
        }

        return switch (type.toUpperCase()) {
            case "LIGHT" -> new LightDrone(id, maxPayloadKg);
            case "HEAVY" -> new HeavyDrone(id, maxPayloadKg);
            default -> throw new IllegalArgumentException("Unknown drone type: " + type);
        };
    }
}