package pl.agnieszkacicha.magazyn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.agnieszkacicha.magazyn.gui.GUI;

public class App {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        GUI gui = context.getBean(GUI.class);
        gui.showLoginMenu();

    }
    }
