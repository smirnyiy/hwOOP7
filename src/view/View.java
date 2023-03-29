package view;

import controller.Controller;

public abstract class View<T> {
    protected Controller<T> controller;

    public abstract void run();
}