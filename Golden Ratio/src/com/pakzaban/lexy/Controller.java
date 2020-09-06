package com.pakzaban.lexy;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class Controller {
    public AnchorPane anchorPane;
    public Pane graphPane;

    private int num = 10;
    private Group group;
    private double beginX = 0;
    private double beginY = 0;

    public void initialize(){
        graphPane.getChildren().clear();
        group = new Group();
        int startX = 350;
        int startY = 500;
        int startAngle = 0;
        int arcLength = 90;
        anchorPane.setStyle("-fx-background-color: rgb(75,75,75)");

        int a = 0;
        int b = 1;

        for (int i = 0; i < num; i++) {
            int c = a + b;
            System.out.println(c);
            a = b;
            b = c;

            Arc arc = new Arc(startX, startY, c, c, startAngle, arcLength);
            arc.setType(ArcType.ROUND);
            arc.setFill(randomColor());
            arc.setStroke(Color.WHITE);
            group.getChildren().add(arc);

            startAngle = (startAngle + 90) % 360;

            if (startAngle == 0){
                startX -= a;
            }

            else if (startAngle == 90){
                startY += a;
            }

            else if (startAngle == 180){
                startX += a;
            }

            else if (startAngle == 270){
                startY -= a;
            }
        }
        graphPane.getChildren().add(group);
    }

    public Color randomColor(){
        String[] colors = {"blue","pink","yellow","purple","orange","red","magenta","turquoise","hotpink"};
        int random = (int) (Math.random()* colors.length);
        String colorString = colors[random];
        return Color.valueOf(colorString);
    }

    public void keyPressed(javafx.scene.input.KeyEvent ke){
        if (ke.getText().equals(",")){
            num--;
            initialize();
        }

        else if (ke.getText().equals(".")){
            num++;
            initialize();
        }
    }

    public void zoom(javafx.scene.input.ZoomEvent ze){
        group.setScaleX(ze.getTotalZoomFactor());
        group.setScaleY(ze.getTotalZoomFactor());
    }

    public void pan(MouseEvent me){
        if(me.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
            beginX = me.getX();
            beginY = me.getY();
        }

        else if (me.getEventType().equals(MouseEvent.MOUSE_DRAGGED)){
            group.setTranslateX(me.getX() - beginX);
            group.setTranslateY(me.getY() - beginY);
        }
    }
}
