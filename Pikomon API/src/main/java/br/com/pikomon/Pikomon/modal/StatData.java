package br.com.pikomon.Pikomon.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatData {
    private Integer base_stat;
    private Integer effort;
    private StatsData stat;

    public Integer getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(Integer base_stat) {
        this.base_stat = base_stat;
    }

    public Integer getEffort() {
        return effort;
    }

    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    public StatsData getStat() {
        return stat;
    }

    public void setStat(StatsData stat) {
        this.stat = stat;
    }
}
