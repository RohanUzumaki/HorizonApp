package com.example.horizonapp;


import java.util.function.Consumer;

public abstract class SuccessListener {
    private String userId;
    private Runnable onSuccessListener;
    private Consumer<Exception> onFailureListener;

    public SuccessListener() {}

    public void setOnSuccessListener(Runnable onSuccessListener) {
        this.onSuccessListener = onSuccessListener;
    }

    public void setOnFailureListener(Consumer<Exception> onFailureListener) {
        this.onFailureListener = onFailureListener;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void onSuccess() {
        if (onSuccessListener != null) {
            onSuccessListener.run();
        }
    }

    public void onFailure(Exception e) {
        if (onFailureListener != null) {
            onFailureListener.accept(e);
        }
    }
}
