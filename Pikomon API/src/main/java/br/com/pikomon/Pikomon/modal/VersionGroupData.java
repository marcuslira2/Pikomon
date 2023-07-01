package br.com.pikomon.Pikomon.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VersionGroupData {

    private Integer level_learned_at;

    private LearnByData move_learn_method;

    public Integer getLevel_learned_at() {
        return level_learned_at;
    }

    public void setLevel_learned_at(Integer level_learned_at) {
        this.level_learned_at = level_learned_at;
    }

    public LearnByData getMove_learn_method() {
        return move_learn_method;
    }

    public void setMove_learn_method(LearnByData move_learn_method) {
        this.move_learn_method = move_learn_method;
    }
}
