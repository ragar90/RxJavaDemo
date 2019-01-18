# RxJavaDemo
## Introduccion

Observables fill the gap by being the ideal way to access asynchronous sequences of multiple items
single items	multiple items
synchronous	T getData()	Iterable<T> getData()
asynchronous	Future<T> getData()	Observable<T> getData()
It is sometimes called “functional reactive programming” but this is a misnomer. ReactiveX may be functional, and it may be reactive, but “functional reactive programming” is a different animal. One main point of difference is that functional reactive programming operates on values that change continuously over time, while ReactiveX operates on discrete values that are emitted over time. 
