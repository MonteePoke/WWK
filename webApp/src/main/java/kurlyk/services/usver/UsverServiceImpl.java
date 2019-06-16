package kurlyk.services.usver;

import kurlyk.model.Usver;
import kurlyk.repositories.UsverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsverServiceImpl implements UsverService {

    @Autowired
    private UsverRepository usverRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(Usver usver) {
        usver.setPassword(
                passwordEncoder.encode(usver.getPassword())
        );
        usverRepository.save(usver);
    }

    @Override
    public List<Usver> findAll() {
        return usverRepository.findAll();
    }

    @Override
    public Optional<Usver> findById(Long usverId) {
        return usverRepository.findById(usverId);
    }
}
