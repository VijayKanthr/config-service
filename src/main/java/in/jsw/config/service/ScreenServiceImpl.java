package in.jsw.config.service;

import in.jsw.config.model.Role;
import in.jsw.config.model.Screen;
import in.jsw.config.repository.ScreenRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ScreenServiceImpl implements ScreenService{

    @PersistenceContext
    private EntityManager entityManager;

    private final ScreenRepository screenRepository;

    public ScreenServiceImpl(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }


    @Override
    @CacheEvict(value = "screens",allEntries = true)
    public Screen create(Screen screen) {
        return screenRepository.save(screen);
    }

    @Override
    @Cacheable(value = "screens")
    public List<Screen> findAll() throws InterruptedException {
        Thread.sleep(3000);
        return (List<Screen>)screenRepository.findAll();
    }
}
