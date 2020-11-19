package org.hbrs.se.ws20;

public class MemberObject implements org.hbrs.se.ws2020.uebung2.Member {
    private int id;

    public MemberObject(int id){
        this.id = id;
    }
    @Override
    public Integer getID() {
        return this.id;
    }
    @Override
    public String toString(){
        return "Member (ID = ["+getID()+"])";
    }
}
