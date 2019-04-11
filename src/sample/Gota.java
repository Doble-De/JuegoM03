package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Gota {
    private Image image;
    private double posX, posY, velX, velY, width, height, caida;
    private boolean decirpos = true;
    private int opcion;
    private float vel1 = 2.5f;
    private float vel4 = 5f;
    private int dificultad = 1000;
    Controller controller;


    public Gota(Controller controller){
        this.posX = 0.0f;
        this.posY = -20.0f;
        this.velY = 1.0f;
        this.controller = controller;
    }


    public double caer(double posotros, String tipo){
        if (decirpos) {
            opcion = (int) Math.floor(Math.random() * 8) + 1;
        }
        if (controller.score >= dificultad){
            dificultad += 1000;
            vel1+= 0.5;
            vel4 += 0.2;
        }
        else if(controller.score == 0){
            vel1 = 2.5f;
            vel1 = 5f;
        }
        switch (opcion){
            case 1:
                vel1(posotros, tipo);
                break;
            case 2:
                vel2(posotros, tipo);
                break;
            case 3:
                vel3(posotros, tipo);
                break;
            case 4:
                vel4(posotros, tipo);
                break;
            case 5:
                vel1(posotros, tipo);
                break;
            case 6:
                vel2(posotros, tipo);
                break;
            case 7:
                vel3(posotros, tipo);
                break;
            case 8:
                vel2(posotros, tipo);
                break;
        }
        return posX;
    }

    public void vel1(double posotros, String tipo){
        velY = vel1;
        if (decirpos){
            posX = dondeCae(posotros);
            decirpos = false;
        }else if (posY > 500){
            posY = -100;
            decirpos = true;
            if (tipo.equals("gota") && controller.score > 0){
                controller.score = controller.score-10;
                System.out.println("holo");
            }
        }
        posY += velY;

    }

    public void vel2(double posotros, String tipo){
        velY = vel1+0.5f;
        if (decirpos){
            posX = dondeCae(posotros);
            decirpos = false;
        }else if (posY > 500){
            posY = -100;
            decirpos = true;
            if (tipo.equals("gota") && controller.score > 0){
                controller.score = controller.score-10;
                System.out.println("holo");
            }
        }
        posY += velY;
    }

    public void vel3(double posotros, String tipo){
        velY = vel1+1.7f;
        if (decirpos){
            posX = dondeCae(posotros);
            decirpos = false;
        }else if (posY > 500){
            posY = -100;
            decirpos = true;
            System.out.println(tipo);
            if (tipo.equals("gota") && controller.score > 0){
                controller.score = controller.score-10;
                System.out.println("holo");
            }
    }
        posY += velY;

    }

    public void vel4(double posotros, String tipo){
        velY = vel4;
        if (decirpos){
            posX = dondeCae(posotros);
            decirpos = false;
        }else if (posY > 500){
            posY = -100;
            decirpos = true;
            if (tipo.equals("gota") && controller.score > 0){
                controller.score = controller.score-10;
                System.out.println("holo");
            }
        }
        posY += velY;

    }

    public void reset(){
        posY = -100;
        decirpos = true;
    }

    public double dondeCae(double posotros){
        caida = (int) Math.floor(Math.random()*680+100);
        if (caida < posotros +100 && caida > posotros - 100){
            dondeCae(posotros);
        }
        return caida;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, posX, posY);
        //System.out.println(posX + ":" + posY);
    }

    public void clear(GraphicsContext gc) {
        gc.clearRect(posX,posY, width, height);
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(posX,posY,width,height);
    }

    public void setImage(Image i) {
        image = i;
        width = image.getWidth();
        height = image.getHeight();
    }
}
