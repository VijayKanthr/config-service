package in.jsw.config.service;

import in.jsw.config.model.Role;
import in.jsw.config.model.Screen;

import java.util.List;

public interface ScreenService {

    Screen create(Screen screen);
    List<Screen> findAll() throws InterruptedException;
}
