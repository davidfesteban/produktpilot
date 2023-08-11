package dev.misei.domain;

public class ProduktPilotException extends RuntimeException {
    public ProduktPilotException(String message) {
        super(message);
    }

    public enum Type {
        WRONG_AUTHENTICATION("Invalid authentication"),
        NOT_ENOUGH_PRIVILEGES("Resource only valid for administrators"),
        RESOURCE_NOT_FOUND("Resource not found"),
        RESOURCE_ALREADY_EXISTS("Resource already exists");

        private final String message;

        Type(String message) {
            this.message = message;
        }

        public ProduktPilotException boom() {
            return new ProduktPilotException(message);
        }

    }

}
