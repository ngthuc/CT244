/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Orther;

import CacLoaiThe.Liica;
import CacLoaiThe.Mastercard1;
import CacLoaiThe.Mastercard2;
import CacLoaiThe.Mastercard3;
import CacLoaiThe.Mastercard4;
import CacLoaiThe.Rem;
import CacLoaiThe.solria;
import CacLoaiThe.thur;
import CardObject.InfoThe;
import CardObject.KieuTheBai;
import CardObject.banco;
import Center.Game;
import Center.GameObject;
import Center.Handler;
import Center.ID;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author TLiem
 */
public class chosemenu extends GameObject{
    ImageIcon nen = new ImageIcon("data/an/nen3.png");
    ImageIcon khung = new ImageIcon("data/an/khung.png");
    ImageIcon luachon = new ImageIcon("data/an/luachon.png");
    ImageIcon luachon2 = new ImageIcon("data/an/luachon.png");
    public button trolai, chon;
    int nx=200;
    int xl1=240;
    int xl2=240;
    public double xv=330;
    InfoThe info;
    public String nhapten="Name";
    mainmenu m;
    public int playerchon= 1;
    public int sevantchon= 1;
    public KieuTheBai[] the = new KieuTheBai[8];
    public chosemenu(Handler h) {
        super(0, 0, ID.menuchonlua);
        this.handler=h;
        the[0] = new KieuTheBai(230, 100, ID.chonmaster, this.handler, new Mastercard1(this.handler, ID.card, 0, 0), 0);
        the[1] = new KieuTheBai(355, 100, ID.chonmaster, this.handler, new Mastercard2(this.handler, ID.card, 0, 0), 0);
        the[2] = new KieuTheBai(480, 100, ID.chonmaster, this.handler, new Mastercard3(this.handler, ID.card, 0, 0), 0);
        the[3] = new KieuTheBai(605, 100, ID.chonmaster, this.handler, new Mastercard4(this.handler, ID.card, 0, 0), 0);
        the[4] = new KieuTheBai(230, 300, ID.chonsevant, this.handler, new solria(), 1);
        the[5] = new KieuTheBai(355, 300, ID.chonsevant, this.handler, new Liica(), 1);
        the[6] = new KieuTheBai(480, 300, ID.chonsevant, this.handler, new thur(), 1);
        the[7] = new KieuTheBai(605, 300, ID.chonsevant, this.handler, new Rem(), 1);
        trolai = new button(800, 610, 140, 70, new ImageIcon("data/an/menubt8.png"));
        chon = new button(650, 610, 140, 70, new ImageIcon("data/an/menubt7.png"));
    }

    @Override
    public void tick() {
        if(nx==200){
            info = new InfoThe(nx+735, y+25, this.handler, null);
            this.handler.addobject(info);
            for(int i=0; i<8; i++){
                this.handler.addobject(the[i]);
            }
        }
        if(playerchon==1 && xl1>the[0].getx()-10){
            xl1=xl1-5;
        }
        if(playerchon==2){
            if(xl1>the[1].getx()-10) xl1=xl1-5;
            if(xl1<the[1].getx()-10) xl1=xl1+5;
        }
        if(playerchon==3){
            if(xl1>the[2].getx()-10) xl1=xl1-5;
            if(xl1<the[2].getx()-10) xl1=xl1+5;
        }
        if(playerchon==4 && xl1<the[3].getx()-10){
            xl1=xl1+5;
        }
        if(sevantchon==1 && xl2>the[4].getx()-10){
            xl2=xl2-5;
        }
        if(sevantchon==2){
            if(xl2>the[5].getx()-10) xl2=xl2-5;
            if(xl2<the[5].getx()-10) xl2=xl2+5;
        }
        if(sevantchon==3){
            if(xl2>the[6].getx()-10) xl2=xl2-5;
            if(xl2<the[6].getx()-10) xl2=xl2+5;
        }
        if(sevantchon==4 && xl2<the[7].getx()-10){
            xl2=xl2+5;
        }
        if(nx>0){
            nx=nx-5;
            info.setx(nx+735);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(nen.getImage(), 0, 0, Game.width-6, Game.height-28, null);
        g.drawImage(khung.getImage(), nx, 0, null);
        g.drawImage(luachon.getImage(),xl1, 95, 120, 180, null);
        g.drawImage(luachon2.getImage(),xl2, 295, 120, 180, null);
        g.setColor(Color.white);
        g.setFont(new Font("VNI-Ariston", 0, 30));
        g.drawString("Teân ngöôøi chôi: ", 10, 60);
        g.drawString("(Khoâng quaù 10 töø)", 5, 90);
        g.drawString(nhapten, 250, 60);
        g.drawString("______________________________", 250, 60);
        g.drawString("|", (int) xv, 55);
        g.drawString("Choïn nhaân vaät:", 20, 150);
        g.drawString("Choïn Sevant:", 20, 350);
        trolai.render(g);
        chon.render(g);
    }
    @Override
    public Rectangle getbounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void ontrolai(){
        m= new mainmenu(this.handler);
        this.handler.addobject(m);
        for(int i=0; i<3; i++){
            this.handler.removeobject(the[i]);
        }
        this.handler.removeobject(this);
    }
    public void onchon(){
        banco bc = new banco(0, 0, ID.banco, this.handler);
        bc.setplayer(playerchon);
        switch(sevantchon){
            case 1: bc.bbA.themsevant(the[4].getCard()); break;
            case 2: bc.bbA.themsevant(the[5].getCard()); break;
            case 3: bc.bbA.themsevant(the[6].getCard()); break;
            case 4: bc.bbA.themsevant(the[7].getCard()); break;
        }
        int r= (((int)(Math.random()*29))/10) +1;
        switch(r){
            case 1: bc.bbB.themsevant(the[4].getCard()); break;
            case 2: bc.bbB.themsevant(the[5].getCard()); break;
            case 3: bc.bbB.themsevant(the[6].getCard()); break;
            case 4: bc.bbB.themsevant(the[7].getCard()); break;
        }
        r= (((int)(Math.random()*29))/10) +1;
        bc.setdoithu(r);
        bc.player = this.nhapten;
        this.handler.addobject(bc);
        for(int i=0; i<8; i++){
            this.handler.removeobject(the[i]);
        }
        this.handler.removeobject(this);
    }
    public void setchon(int i){
        this.playerchon=i;
    }
}
