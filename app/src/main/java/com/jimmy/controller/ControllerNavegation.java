package com.jimmy.controller;
import android.app.Activity;
import android.content.Intent;
public class ControllerNavegation {
    public void goInterfaz(Activity InterfazInicial, Class<?> InterfazDestino){
        Intent i = new Intent(InterfazInicial, InterfazDestino);
        InterfazInicial.startActivity(i);
    }
}