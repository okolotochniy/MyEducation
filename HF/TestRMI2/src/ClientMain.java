import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class ClientMain {

    public static final String UNIQUE_BINDING_NAME = "server.calculator";

    public static void main(String[] args) throws RemoteException, NotBoundException {

        final Registry registry = LocateRegistry.getRegistry(2732);

        Calculator calculator = (Calculator) registry.lookup(UNIQUE_BINDING_NAME);

        IntObject intObject = new IntObject(3, 4);

         int[] multiplyResult = calculator.multiply(intObject);

        System.out.println(Arrays.toString(multiplyResult));
    }
}