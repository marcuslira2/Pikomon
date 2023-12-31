package br.com.pikomon.Pikomon.service;

import br.com.pikomon.Pikomon.dto.user.ModifyPasswordDTO;
import br.com.pikomon.Pikomon.dto.user.CreateUserDTO;
import br.com.pikomon.Pikomon.dto.user.UserDTO;
import br.com.pikomon.Pikomon.persistence.User;
import br.com.pikomon.Pikomon.repository.TrainerRepository;
import br.com.pikomon.Pikomon.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final LogService logService;

    private static final String USER_NOT_FOUND = "User not found";

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, LogService logService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.logService = logService;
    }

    public List<UserDTO> listAll() {
        log.info("Listing all users");
        return userRepository.findAll().stream()
                .filter(User::isEnabled).toList()
                .stream().map(this::converterToDTO).toList();
    }

    private UserDTO converterToDTO(User user) {
        log.info("Converting user into DTO");
        return new UserDTO(
                user.getId(),
                user.getLogin(),
                user.getTrainers()
        );
    }

    public UserDTO save(CreateUserDTO dto) throws Exception {
        User user = new User();
        log.info("Saving user: " + dto.name());
        user.setLogin(dto.login());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setUuid(UUID.randomUUID().toString());
        user.setCreatedDate(new Date());
        this.userRepository.save(user);

        String logMsg = user.getUsername() + " create account.";
        this.logService.save(user.getUuid(), null, logMsg);

        return this.converterToDTO(user);
    }

    public UserDTO findById(Integer id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception(USER_NOT_FOUND));
        log.info("Searching user...");
        return this.converterToDTO(user);
    }

    public String deleteById(Integer id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception(USER_NOT_FOUND));
        log.info("Deleting user...");
        user.setDeletedDate(new Date());
        user.setDeleted(true);
        userRepository.save(user);

        String logMsg = user.getUsername() + " deleted account.";
        this.logService.save(user.getUuid(), null, logMsg);

        return user.getUsername();
    }

    public String changePWD(Integer id, ModifyPasswordDTO pwddto) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new Exception(USER_NOT_FOUND));
        log.info("Updating user...");
        user.setPassword(pwddto.password());
        userRepository.save(user);

        String logMsg = user.getUsername() + " changed password.";
        this.logService.save(user.getUuid(), null, logMsg);

        return user.getUsername();
    }

}
