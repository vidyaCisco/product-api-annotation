package com.vidya.productapiannotation.model;

import java.util.Objects;

public class ProductEvent {

    private Long eventIdl;

    private String eventType;


    public ProductEvent() {
    }

    public ProductEvent(Long eventIdl, String eventType) {
        this.eventIdl = eventIdl;
        this.eventType = eventType;
    }

    public Long getEventIdl() {
        return eventIdl;
    }

    public void setEventIdl(Long eventIdl) {
        this.eventIdl = eventIdl;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEvent that = (ProductEvent) o;
        return Objects.equals(eventIdl, that.eventIdl) &&
                Objects.equals(eventType, that.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventIdl, eventType);
    }

    @Override
    public String toString() {
        return "ProductEvent{" +
                "eventIdl=" + eventIdl +
                ", eventType='" + eventType + '\'' +
                '}';
    }

}
