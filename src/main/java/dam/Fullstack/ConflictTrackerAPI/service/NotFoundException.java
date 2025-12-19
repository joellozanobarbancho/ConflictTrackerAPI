package dam.Fullstack.ConflictTrackerAPI.service;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
