package com.packtpub.apps.rxjava_essentials.mysample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.packtpub.apps.rxjava_essentials.R;

import butterknife.InjectView;
import rx.Observable;

/**
 * Created by 152317 on 2017-03-10.
 */

public class FirstSampleFragment extends BaseSampleFragment {

    @InjectView(R.id.textView_hello)
    TextView textView;

    public FirstSampleFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /**
         * This is the basic usage.
         */
        Observable<Object> observable = getObserverHelloWorld();
        /*observable.subscribe(new Observer<Object>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(Object object) {
                if(object instanceof String) {
                    String text = (String) object;
                    textView.setText("rxJava " + text);
                }
            }
        });*/
        observable.subscribe(o -> {
            if(o instanceof String) {
                String text = (String) o;
                textView.setText("rxJava " + text);
            }
        }, error -> {

        }, () -> {
            Log.d("warrenth","complete");
            //complete
        });
    }

    public Observable<Object> getObserverHelloWorld() {
        /*return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                subscriber.onNext(textView.getText().toString());
                if(!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        });*/
        return Observable.create(subscriber -> {
            subscriber.onNext(textView.getText().toString());
            if(!subscriber.isUnsubscribed()) {
                subscriber.onCompleted();
            }
        });
    }
}
