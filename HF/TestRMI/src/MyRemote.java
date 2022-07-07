import java.rmi.Remote;

public interface MyRemote extends Remote {
    String sayHello() throws RuntimeException;
}
