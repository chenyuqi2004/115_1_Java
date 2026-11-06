// 先只編譯 Rover 與本檔，確認「狀態搬到物件裡」的意思。
public class RoverDemo {
    public static void main(String[] args) {
        Rover rover = new Rover(20, 0);
        System.out.println(rover.execute(1) + "/" + rover.getEnergy()
                + "/" + rover.getSamples() + "/" + rover.getActionCount());
        Rover empty = new Rover(0, 0);
        System.out.println(empty.execute(3) + "/" + empty.getEnergy());
        Rover done = new Rover(20, 5);
        System.out.println(done.execute(1) + "/" + done.getEnergy());
    }
}
