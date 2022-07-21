public class MainApp {
    public static void main(String[] args) throws InterruptedException {
    Massive m = new Massive();
        System.out.println(m.getTimeT1());
        Thread.sleep(1000);
        System.out.println(m.getTimeT2());
    }
}
