import lombok.Getter;

@Getter
public enum Status {
    PROCESSING("Processing"),
    IN_DELIVERY("In delivery"),
    COMPLETED("Completed");
    private final String value;
    Status(String value) {
        this.value = value;
    }
}
