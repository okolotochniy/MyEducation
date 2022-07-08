import java.rmi.RemoteException;

public class RemoteCalculationServer implements Calculator {
    @Override
    public int[] multiply(IntObject intObject) throws RemoteException {
        int[] result = {intObject.getX() + intObject.getY(), intObject.getX() - intObject.getY(), intObject.getX()
                * intObject.getY(), intObject.getX() / intObject.getY()};
        return result;
    }
}
