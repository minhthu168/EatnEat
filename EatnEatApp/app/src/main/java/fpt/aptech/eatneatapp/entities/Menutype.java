package fpt.aptech.eatneatapp.entities;

import java.io.Serializable;

public class Menutype implements Serializable {
    private int menutypeid;
    private String type;
    private String typeimg;

    public Menutype(String type, String typeimg) {
        this.type = type;
        this.typeimg = typeimg;
    }

    public int getMenutypeid() {
        return menutypeid;
    }

    public void setMenutypeid(int menutypeid) {
        this.menutypeid = menutypeid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeimg() {
        return typeimg;
    }

    public void setTypeimg(String typeimg) {
        this.typeimg = typeimg;
    }
}
