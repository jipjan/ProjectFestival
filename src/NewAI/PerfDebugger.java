package NewAI;

/**
 * Created by Jaap-Jan on 9-4-2017.
 */
public class PerfDebugger {
    public static void test(Runnable r, String name) {
        long now = System.nanoTime();
        r.run();
        System.out.println(name + ": " + (System.nanoTime() - now)/ 1e6 + "ms");
    }
}
