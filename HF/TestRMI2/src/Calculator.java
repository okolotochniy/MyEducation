import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    int[] multiply(IntObject intObject) throws RemoteException;
}
