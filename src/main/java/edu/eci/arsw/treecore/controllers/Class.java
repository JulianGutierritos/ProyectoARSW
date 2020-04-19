package edu.eci.arsw.treecore.controllers;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Rama;



public class Class {
    @JsonIgnore
    private Map<Proyecto, Rama> teamMap;

    // getter and setter

    @JsonProperty("team")
    private List<KeyValueContainer<Proyecto, Rama>> getTeamList() {
        return ObjectUtils.toList(teamMap);
    }

    @JsonProperty("team")
    private void setTeamList(List<KeyValueContainer<Proyecto, Rama>> list) {
        teamMap = ObjectUtils.toMap(list);
    }
}
