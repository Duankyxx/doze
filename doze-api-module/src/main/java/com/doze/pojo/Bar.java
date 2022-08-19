package com.doze.pojo;

public class Bar {

    private int id;
    private String menuName;
    private String icon;
    private String level;
    private String MenuLink;

    public Bar() {
    }

    public Bar(int id, String menuName, String icon, String level, String menuLink) {
        this.id = id;
        this.menuName = menuName;
        this.icon = icon;
        this.level = level;
        MenuLink = menuLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMenuLink() {
        return MenuLink;
    }

    public void setMenuLink(String menuLink) {
        MenuLink = menuLink;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", icon='" + icon + '\'' +
                ", level='" + level + '\'' +
                ", MenuLink='" + MenuLink + '\'' +
                '}';
    }
}
