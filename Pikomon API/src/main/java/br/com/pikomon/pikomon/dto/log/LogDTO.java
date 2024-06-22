package br.com.pikomon.pikomon.dto.log;

public class LogDTO {

    private String userUuid;
    private String trainerUuid;
    private String decription;

    public LogDTO(String userUuid, String trainerUuid, String decription) {
        this.userUuid = userUuid;
        this.trainerUuid = trainerUuid;
        this.decription = decription;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getTrainerUuid() {
        return trainerUuid;
    }

    public void setTrainerUuid(String trainerUuid) {
        this.trainerUuid = trainerUuid;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
