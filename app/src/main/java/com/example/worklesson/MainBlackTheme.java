package com.example.worklesson;

public final class MainBlackTheme {
    // Внутреннее поле будет хранить единственный экземляр
    private static MainBlackTheme blackTheme = null;
    //Поле для синхронизации
    private static final Object synsObj = new Object();
    private boolean isBlackTheme;

    private MainBlackTheme(){
        this.isBlackTheme = false;
    }

    public boolean getIsBlackTheme(){
        return this.isBlackTheme;
    }

    public static MainBlackTheme getBlackTheme(){
        synchronized (synsObj){
            if (blackTheme == null){
                blackTheme = new MainBlackTheme();
            }
            return blackTheme;
        }
    }
}
