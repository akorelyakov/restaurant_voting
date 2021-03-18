package com.korelyakov.restaurant_voting.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractNamedEntity {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Menu> menus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Vote> votes;

    public Restaurant() {
    }

    public Restaurant(String name, List<Menu> menus) {
        this(null, name, menus);
    }

    public Restaurant(Integer id, String name, List<Menu> menus) {
        super(id, name);
        this.name = name;
        this.menus = menus;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(menus, that.menus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), menus);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", menus=" + menus +
               '}';
    }
}
