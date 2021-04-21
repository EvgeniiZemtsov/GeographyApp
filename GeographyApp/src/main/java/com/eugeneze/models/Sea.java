package com.eugeneze.models;

/**
 * Класс Море
 */

public class Sea extends GeographicalObject {
    /**
     * Поля, определяющие размер моря
     */
    private int area;
    private int maxDepth;

    public Sea(int id, String name) {
        super(id, name);
    }

    /**
     * Метод устанавливает размеры моря
     */
    public void addDimensions(int area, int maxDepth) {
            this.area = area;
            this.maxDepth = maxDepth;
    }

    public Object[] getObjects() {
        return new Object[] {
                id,
                name,
                area,
                maxDepth,
                countries
        };
    }

}
