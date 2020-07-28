package com.xr.synctransfer.sync;

import com.xr.synctransfer.entities.MyResponse;

import java.util.concurrent.Future;

public interface WriteFuture<T> extends Future<T> {
    Throwable cause();

    void setCause(Throwable cause);

    boolean isWriteSuccess();

    void setWriteResult(boolean result);

    String requestId();

    T response();

    void setResponse(MyResponse response);

    boolean isTimeout();

}
