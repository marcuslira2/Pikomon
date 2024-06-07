package br.com.pikomon.Pikomon.service;

import br.com.pikomon.Pikomon.dto.log.LogDTO;
import br.com.pikomon.Pikomon.persistence.Log;
import br.com.pikomon.Pikomon.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogService {

    private final LogRepository logRepository;

    private final String LOGGER_NOT_FOUND = "Log not found";

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public LogDTO convertToDTO(Log log) throws Exception{
        Log logger = logRepository.findById(log.getId()).orElseThrow(()-> new Exception(LOGGER_NOT_FOUND));
        return new LogDTO(
                logger.getUserUuid(),
                logger.getTrainerUuid(),
                logger.getDescription()
        );
    }

    public LogDTO save(String userUuid, String trainerUuid, String decription) throws Exception {

        Log log = new Log();
        log.setDescription(decription);
        log.setUserUuid(userUuid);
        log.setTrainerUuid(trainerUuid);
        log.setCreateDate(new Date());
        logRepository.save(log);
        return this.convertToDTO(log);
    }

    public Log saveBattle(String trainerUUID, String description, String battleUUId){
        Log log = new Log();

        log.setTrainerUuid(trainerUUID);
        log.setDescription(description);
        log.setBattleuuid(battleUUId);
        log.setCreateDate(new Date());
        return logRepository.save(log);
    }
}
