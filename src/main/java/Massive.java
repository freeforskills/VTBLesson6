import java.util.Arrays;

public class Massive{
    static final int SIZE = 1000000 ;
    static final int HALF = SIZE / 2 ;

    public long getTimeT1(){
        float [] arr = new float[SIZE];                 // Создают одномерный длинный массив
        Arrays.fill(arr,1);                         // Заполняют этот массив единицами
        long timeStart = System.currentTimeMillis();    // Засекают время выполнения
        for (int i = 0; i< arr.length;i++) {            // Проходят по всему массиву и для каждой ячейки считают новое значение по формуле

            arr [i] = (float)(arr[i] * Math.sin(0.2f + i/5) *
                    Math.cos(0.2f + i/5) * Math.cos(0.4f + i/2 ));
        }
        long timeEnd = System.currentTimeMillis();      // Проверяется время окончания метода

        return timeEnd-timeStart;
    }

    public long getTimeT2(){
        float [] arr = new float[SIZE];                 // Создают одномерный длинный массив
        Arrays.fill(arr,1);                         // Заполняют этот массив единицами
        long timeStart = System.currentTimeMillis();    // Засекают время выполнения
                                                        // Разбивает массив на два массива
        float[] arrA = Arrays.copyOfRange(arr,0,HALF);
        float[] arrB = Arrays.copyOfRange(arr,(HALF),(arr.length));

        Thread t1 = new Thread(() -> {
                for (int i = 0; i < arrA.length; i++) {            // Проходят по всему массиву и для каждой ячейки считают новое значение по формуле

                    arrA[i] = (float) (arrA[i] * Math.sin(0.2f + i / 5) *
                            Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread t2 = new Thread(() -> {
                for (int i = 0, j = HALF; i < arrB.length; i++, j++) {            // Проходят по всему массиву и для каждой ячейки считают новое значение по формуле
                    arrB[i] = (float) (arrB[i] * Math.sin(0.2f + j / 5) *
                            Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.arraycopy(arrA,0,arr,0,HALF);
        System.arraycopy(arrB,0,arr,HALF,HALF);

        long timeEnd = System.currentTimeMillis();      // Проверяется время окончания метода

        return timeEnd-timeStart;
    }
}

