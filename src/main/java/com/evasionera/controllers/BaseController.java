package com.evasionera.controllers;

import com.evasionera.EvasionEraApplication;

public abstract class BaseController {
    protected EvasionEraApplication main;

    public void setMain(EvasionEraApplication main) {
        this.main = main;
    }
}