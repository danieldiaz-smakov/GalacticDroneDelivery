package edu.narxoz.galactic.dispatcher;

import edu.narxoz.galactic.drones.Drone;
import edu.narxoz.galactic.drones.DroneStatus;
import edu.narxoz.galactic.task.DeliveryTask;
import edu.narxoz.galactic.task.TaskState;

public class Dispatcher {

    public Result assignTask(DeliveryTask task, Drone drone) {
        if (task == null) {
            return fail("task is null");
        }
        if (drone == null) {
            return fail("drone is null");
        }

        if (task.getState() != TaskState.CREATED) {
            return fail("task state is not CREATED");
        }

        if (drone.getStatus() != DroneStatus.IDLE) {
            return fail("drone is not IDLE");
        }

        var cargo = task.getCargo();
        if (cargo == null) {
            return fail("cargo is null");
        }

        if (cargo.getWeightKg() > drone.getMaxPayloadKg()) {
            return fail("cargo overweight");
        }

        task.setAssignedDrone(drone);
        task.setState(TaskState.ASSIGNED);
        drone.setStatus(DroneStatus.IN_FLIGHT);

        return ok();
    }

    public Result completeTask(DeliveryTask task) {
        if (task == null) {
            return fail("task is null");
        }

        if (task.getState() != TaskState.ASSIGNED) {
            return fail("task state is not ASSIGNED");
        }

        var drone = task.getAssignedDrone();
        if (drone == null) {
            return fail("assigned drone is null");
        }

        if (drone.getStatus() != DroneStatus.IN_FLIGHT) {
            return fail("drone is not IN_FLIGHT");
        }

        task.setState(TaskState.DONE);
        drone.setStatus(DroneStatus.IDLE);

        return ok();
    }

    private static Result ok() {
        return new Result(true, "");
    }

    private static Result fail(String reason) {
        return new Result(false, reason);
    }
}
