package com.eugeneze.models;

/**
 * Класс Океан
 */

public class Ocean extends GeographicalObject {
    /**
     * Поля, определяющие размер моря
     */
    private int area;
    private int maxDepth;

    public Ocean(int id, String name) {
        super(id, name);
    }

    /**
     * Метод устанавливает размеры моря
     */
    public void addDimensions(int area, int maxDepth) {
            this.area = area;
            this.maxDepth = maxDepth;
    }
}
