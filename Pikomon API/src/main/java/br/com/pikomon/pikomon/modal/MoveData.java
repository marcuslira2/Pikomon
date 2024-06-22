package br.com.pikomon.pikomon.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveData {

    private MovesData move;
    private List<VersionGroupData> version_group_details;

    public MovesData getMove() {
        return move;
    }

    public void setMove(MovesData move) {
        this.move = move;
    }

    public List<VersionGroupData> getVersion_group_details() {
        return Collections.unmodifiableList(version_group_details);
    }

    public MoveData() {
        this.version_group_details = new ArrayList<>();
    }

    public void setVersion_group_details(List<VersionGroupData> version_group_details) {
        this.version_group_details = version_group_details;
    }
}
