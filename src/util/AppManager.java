package util;

import controller.Controller;
import controller.impl.NoteController;
import model.Note;
import model.repository.Operational;
import model.repository.impl.NoteRepository;
import util.connector.Connector;
import util.connector.impl.FileDBConnector;
import view.View;
import view.impl.ConsoleView;

public class AppManager {
    private Connector connector;
    private Operational<Note> repository;
    private Controller<Note> controller;
    private View<Note> view;

    public AppManager() {
        this.connector = new FileDBConnector();
        this.repository = new NoteRepository(connector);
        this.controller = new NoteController(repository);
        this.view = new ConsoleView(controller);
    }

    public void run() {
        connector.connect();
        view.run();
    }
}