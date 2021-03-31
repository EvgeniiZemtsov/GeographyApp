package com.eugeneze.models;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс Глава государства
 */

public class HeadOfState {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    /**
     * Поле, хранящее название должности
     */
    private String title;

    /**
     * Страна, главой которой является человек
     */
    private Country workPlace;

    public HeadOfState(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Медод добавляет информацию о главе государства
     */
    public void addExtraInformation(LocalDate dateOfBirth, Country country, String title) {
        this.dateOfBirth = dateOfBirth;
        workPlace = country;
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
}
