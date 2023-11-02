package com.openclassrooms.arista;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LiveDataTestUtil {

    /**
     * Obtient la valeur d'un LiveData, en attendant s'il n'y a pas encore de valeur.
     * @param liveData Le LiveData à tester.
     * @param <T> Le type de donnée détenu par le LiveData.
     * @return La valeur détenu par le LiveData.
     * @throws InterruptedException Si le thread est interrompu en attendant la valeur du LiveData.
     */
    public static <T> T getValue(final LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        latch.await(2, TimeUnit.SECONDS);
        //noinspection unchecked
        return (T) data[0];
    }
}
