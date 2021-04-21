package com.eugeneze.models;

/**
 * Класс Гора
 */

public class Mountain extends GeographicalObject {
    /**
     * Поле, определяющее высоту горы
     */
    private int height;

    public Mountain(int id, String name) {
        super(id, name);
    }

    /**
     * Метод устанавливает высоту горы
     */
    public void addHeight(int height) {
            this.height = height;
    }

    public Object[] getObjects() {
        return new Object[] {
                id,
                name,
                height,
                countries
        };
    }

}
