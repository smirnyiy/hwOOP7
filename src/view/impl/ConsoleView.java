package view.impl;

import controller.Controller;
import model.Note;
import util.Commands;
import view.View;

import java.util.List;
import java.util.Scanner;

public class ConsoleView extends View<Note> {
    public ConsoleView(Controller<Note> controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        Commands com;

        while (true) {
            String command = prompt("Enter the command\n(CREATE, LIST, GET, UPDATE, DELETE, EXIT): ");
            com = Commands.valueOf(command.toUpperCase());

            switch (com) {
                case CREATE -> {
                    Note note = createNote();
                    controller.create(note);
                }

                case GET -> {
                    String id = prompt("Enter note id: ");
                    try {
                        Note note = controller.get(id);
                        System.out.println(note + "\n");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                case LIST -> {
                    List<Note> notes = controller.getAll();

                    System.out.println("----------------");

                    for (Note note: notes) {
                        System.out.println(note);
                        System.out.println("----------------");
                    }
                }

                case UPDATE -> {
                    String id = prompt("Enter note id: ");
                    controller.update(id, createNote());
                }

                case DELETE -> {
                    String id = prompt("Enter note id: ");
                    controller.delete(id);
                }

                case EXIT -> {
                    System.out.println("Exiting...");
                    return;
                }
            }
        }
    }

    private String prompt(String msg) {
        Scanner in = new Scanner(System.in);
        System.out.print(msg);

        return in.nextLine();
    }

    private Note createNote() {
        String title = prompt("Enter title: ");
        String text = prompt("Enter text: ");

        return new Note(title, text);
    }
}