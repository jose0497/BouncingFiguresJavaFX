/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reboundfigurefx;

import domain.Ball;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author brend
 */
public class ReboundFigureFX extends Application implements Runnable {

    private Pane pane;
    private Canvas canvas;
    private Scene scene;
    private GraphicsContext gc;
    private Ball ball1, ball2, ball3;
    private Thread thread;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rebote Figura FX");
        initComponents(primaryStage);
        primaryStage.show();
        this.gc = this.canvas.getGraphicsContext2D();
    }

    private void initComponents(Stage primaryStage) {
        this.ball1 = new Ball(Color.YELLOW, 100, 100);
        this.ball2 = new Ball(Color.BLUE, 300, 200);
        this.ball3 = new Ball(Color.GREEN, 400, 200);

        this.pane = new Pane();
        this.scene = new Scene(pane, 800, 600);
        this.canvas = new Canvas(800, 600);
        this.canvas.relocate(0, 0);
        this.pane.getChildren().add(this.canvas);
        this.thread = new Thread(this);
        this.thread.start();
       
        
        primaryStage.setScene(this.scene);

    }

    /**
     * @param args the command line arguments
     */
    private void draw(GraphicsContext gc) {

        while (true) {
            try {
                gc.clearRect(0, 0, scene.getWidth(), scene.getHeight());

                this.ball1.rebound((int) canvas.getWidth(), (int) canvas.getHeight());
                this.ball1.paint(gc);
                this.ball1.move();
                this.ball2.rebound((int) canvas.getWidth(), (int) canvas.getHeight());
                this.ball2.paint(gc);
                this.ball2.move();
                this.ball3.rebound((int) canvas.getWidth(), (int) canvas.getHeight());
                this.ball3.paint(gc);
                this.ball3.move();
                Thread.sleep(5);

            } catch (InterruptedException ex) {

            }
        }
    }

    @Override
    public void run() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        draw(gc);

    }

    public static void main(String[] args) {
        launch(args);

    }

}
