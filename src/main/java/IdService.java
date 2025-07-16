import java.util.UUID;

public class IdService {
    public String generateID(){
        return UUID.randomUUID().toString();
    }
}
