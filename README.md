# rxstudy
## 도서는 Rx Java Essentials(에이콘 출판사) 로 학습하였습니다. 위 소스 예제는 Rx Java Essentials 셈플 소스에서 개인적인 학습으로 사용되었습니다.

# 기본적인 RxJava 패턴
- FirstSampleFragment 참조
<pre><code>
 Observable<Object> observable = getObserverHelloWorld();
        observable.subscribe(new Observer<Object>() {
            @Override public void onCompleted() {}
            @Override public void onError(Throwable e) {}
            @Override
            public void onNext(Object object) {
                if(object instanceof String) {
                    String text = (String) object;
                    textView.setText("rxJava " + text);
                }
            }
        });
    }

    public Observable<Object> getObserverHelloWorld() {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            @Override public void call(Subscriber<? super Object> subscriber) {
                subscriber.onNext(textView.getText().toString());
                if(!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        });
    }
</code></pre>

# 람다식으로 변경
<pre><code>
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

public Observable<Object> getObserverHelloWorld() {
        return Observable.create(subscriber -> {
            subscriber.onNext(textView.getText().toString());
            if(!subscriber.isUnsubscribed()) {
                subscriber.onCompleted();
            }
        });
    }
</code></pre>

