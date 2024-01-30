package tuile;

import java.awt.Image;

public class Tuile {
    private Image img;
    public Image getImg() {return img;}
    public void setImg(Image img) {this.img = img;}

    private boolean zoneCliquable = false; //pour savoir si une zone est une zone cliquable (hors zone pour plante)
    private boolean zonePlantable = false; //pour savoir si une zone est une zone où une tour peut être posée

    private String note;

    public String getNote() {return note;}
    public void setNote(String note) {this.note = note;}
    
    public boolean isZoneCliquable() {return zoneCliquable;}
    public void setZoneCliquable(boolean zoneCliquable) {this.zoneCliquable = zoneCliquable;}

    public boolean isZonePlantable() {return zonePlantable;}
    public void setZonePlantable(boolean zonePlantable) {this.zonePlantable = zonePlantable;}

    
}
