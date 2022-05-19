public class HomeWorkApplication{

static final int SIZE = 20000000;
static final int H = SIZE / 2;

    public static void main(String[] args) {
        firstMethod();
        try{
            secondMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void halfCalculations(float[] arr, int srcPos, float[] destArr, int destPos){
        System.arraycopy(arr, srcPos, destArr, destPos, H);
        for (int i = 0; i < H; i++) {
            destArr[i] = (float) (destArr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        if (srcPos > 1) {
            System.arraycopy(destArr, destPos, arr, srcPos, H); // это реализация для копирования второй части массива
        } else System.arraycopy(destArr, srcPos, arr, destPos, H);
    }

    private static void secondMethod() throws InterruptedException {
        float[] arr = new float[SIZE];
        for (float i: arr){
            i = 1;
        }
        long a = System.currentTimeMillis();
        float[] a1 = new float[H];
        float[] a2 = new float[H];
        Thread thread1 = new Thread(() -> {
            halfCalculations(arr, 0, a1, 0);
        });
        Thread thread2 = new Thread(() -> {
            halfCalculations(arr, H, a2, 0);
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void firstMethod() {
        float[] arr = new float[SIZE];
        for (float i: arr){
            i = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }
}
