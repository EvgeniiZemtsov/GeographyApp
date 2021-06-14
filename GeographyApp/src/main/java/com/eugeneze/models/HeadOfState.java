package com.eugeneze.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс Глава государства
 */
@Entity
@Table(name = "current_heads_of_states")
public class HeadOfState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private int id;
    @Column(name = "first_name")
    @JsonProperty
    private String firstName;
    @Column(name = "last_name")
    @JsonProperty
    private String lastName;
    @Column(name = "date_of_birth")
    @JsonProperty
    private LocalDate dateOfBirth;
    /**
     * Поле, хранящее название должности
     */
    @Column(name = "title")
    @JsonProperty
    private String title;

    /**
     * Страна, главой которой является человек
     */
    @OneToOne(mappedBy="headOfState")
    private Country workPlace;

    private HeadOfState(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.dateOfBirth = builder.dateOfBirth;
        this.title = builder.title;
        this.workPlace = builder.workPlace;

    }

    public HeadOfState() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Метод выдвигает человека в качестве кандидата для участия в голосовании
     * @param country Место проведения голосования
     */
    public void takePartInTheElection(Country country) {

    }

    /**
     * Метод снимает главу государства с должности
     */
    public void leaveThePostOfHeadOfState() {
        workPlace = null;
    }

    @JsonIgnore
    public Object[] getObjects() {
        return new Object[] {
                id,
                firstName,
                lastName,
                dateOfBirth,
                title,
                workPlace
        };
    }

    public static final class Builder {
        private int id;
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private String title;
        private Country workPlace;

        public Builder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public Builder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setWorkPlace(Country workPlace) {
            this.workPlace = workPlace;
            return this;
        }

        public HeadOfState build() {
            return new HeadOfState(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HeadOfState)) return false;

        HeadOfState that = (HeadOfState) o;

        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!dateOfBirth.equals(that.dateOfBirth)) return false;
        if (!Objects.equals(title, that.title)) return false;
        return Objects.equals(workPlace, that.workPlace);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (workPlace != null ? workPlace.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HeadOfState{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", title='" + title + '\'' +
                '}';
    }
}
