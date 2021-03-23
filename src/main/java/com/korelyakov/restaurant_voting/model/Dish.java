package com.korelyakov.restaurant_voting.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "dish")
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    @Range(min = 1, max = 50_000)
    private int price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="menu_id")
    private Menu menu;

    public Dish() {
    }

    public Dish(String name, int price, Menu menu) {
        this(null, name, price, menu);
    }

    public Dish(String name, int price) {
        this(null, name, price, null);
    }

    public Dish(Integer id, String name, int price, Menu menu) {
        super(id, name);
        this.price = price;
        this.menu = menu;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dish dish = (Dish) o;
        return price == dish.price && menu.equals(dish.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price, menu);
    }

    @Override
    public String toString() {
        return "Dish{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", price=" + price +
               ", menu=" + menu +
               '}';
    }
}
