package org.acme.Model;

public class Account {
    private String cbuHash;
    private String type;
    private String currency;
    private Boolean isFavorite;

    public Account(){}
    public Account(String cbu, String type, String curr, Boolean is){
        this.cbuHash = cbu;
        this.type = type;
        this.currency = curr;
        this.isFavorite = is;
    }
    
    public String getCbuHash(){return this.cbuHash;}
    public String getType(){return this.type;}
    public String getCurrency(){return this.currency;}
    public Boolean isFavorite(){return this.isFavorite;}

    public void setCbuHash(String cbu) {this.cbuHash = cbu;}
    public void setType(String type) {this.type = type;}
    public void setCurrency(String curr) {this.currency = curr;}
    public void setIsFavorite(Boolean is) {this.isFavorite = is;}

    @Override
    public String toString(){

        return "{" +"\n"+
        "cbu="+this.cbuHash+",\n"+
        "type="+this.type+",\n"+
        "currency="+this.currency+",\n"+
        "is_favorite="+this.isFavorite+",\n"+
        "}";
    }
}