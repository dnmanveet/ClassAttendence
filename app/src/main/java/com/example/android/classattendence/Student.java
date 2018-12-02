package com.example.android.classattendence;

public class Student {
    String code = null;

    String idnum = null;
    boolean selected = false;

    public Student(String code, String name,String idnum, boolean selected) {
        super();
        this.code = code;
        this.idnum = idnum;

        this.selected = selected;
    }

    public String getCode() {
        return code;
    }
    public String getIdnum(){
        return idnum;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setIdnum(String idnum){
        this.idnum = idnum;
    }


    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
