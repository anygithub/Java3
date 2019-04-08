import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class RaceLauncher {
	private static final int CARS_COUNT = 4;
	private static final CountDownLatch START = new CountDownLatch(CARS_COUNT + 1);
	private static final Semaphore TUNNEL = new Semaphore(CARS_COUNT / 2, false);
	private static final CountDownLatch FINISH = new CountDownLatch(CARS_COUNT);
	
    public static void main(String[] args) throws InterruptedException {
		System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        
        while (START.getCount() > 1)   
            Thread.sleep(100);         

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        START.countDown();             

        while (FINISH.getCount() > 0);       
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
	}
}
