package q1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Controls the logic behind the shapeDrawer application.
 */
public class shapeDrawerController {

    // Constant Properties:
    final double RECWIDTH = 60;
    final double RECHEIGHT = 60;
    final int ELLIPSERADIUSX = 60;
    final int ELLIPSERADIUSY = 45;
    final int SHAPEVIEWORDER = -3;
    final double LINELEN = 80;
    final Paint STOKECOLOR = Color.DARKGRAY;
    final int STROKEWIDTH = 5;

    // array of all shapes drawn in the painting pane
    final ArrayList<Shape> paintingShapesList = new ArrayList<>();
    // current shape inside the catalogue pane
    Shape currShape = null;

    // the transparency of a shape
    enum Transparency {
        Transparent,
        Full
    }
    static Transparency defaultTrans = Transparency.Full;

    @FXML
    Pane paintingPane;
    // the color picker button
    @FXML
    ColorPicker colorPicker;
    @FXML
    Pane cataloguePane;
    @FXML
    private ChoiceBox<String> transparencyChoiceBox;
    static double catalogueShapeX;
    static double catalogueShapeY;


    /*
     * Initialize the shape Drawer Logic.
     */
    public static void initialize(Scene currentScene) {
        // init the choice box button
        ChoiceBox<String> choiceBox = (ChoiceBox<String>) currentScene.lookup("#transparencyChoiceBox");
        ArrayList<String> transList = new ArrayList<>(Arrays.asList(Transparency.Full.toString(), Transparency.Transparent.toString()));
        ObservableList<String> transObList = FXCollections.observableList(transList);
        choiceBox.setItems(transObList);
        choiceBox.setValue(defaultTrans.toString());

        // init the color picker
        ColorPicker colorPicker = (ColorPicker) currentScene.lookup("#colorPicker");
        colorPicker.setValue(Color.BLACK);

        // Set the catalogueShapeX,Y parameters
        Pane cataloguePane = (Pane) currentScene.lookup("#cataloguePane");
        catalogueShapeX = cataloguePane.getPrefWidth() / 2;
        catalogueShapeY = cataloguePane.getPrefHeight() / 2;
    }

    /*
     * return the current Color in the color picker, unless
     * the choice box is set to transparent - then return transparent
     */
    Paint getCataloguePaint() {
        Paint color;
        if (transparencyChoiceBox.getValue().equals("Transparent")) {
            color = Color.TRANSPARENT;
        } else {
            color = colorPicker.getValue();
        }
        return color;
    }

    /*
     * Change the color of the current Catalogue shape into the color
     * in the Color picker.
     */
    @FXML
    void updateShapeColor() {
        if (currShape != null) {
            if (transparencyChoiceBox.getValue().equals("Full"))
                currShape.setFill(colorPicker.getValue());
        }
    }

    /*
     * Upon changes to the choice box, change the color of the
     * shape in the catalogue.
     */
    @FXML
    void updateShapeTransparency() {
        if (currShape != null) {
            if (transparencyChoiceBox.getValue().equals("Full")) {
                currShape.setFill(colorPicker.getValue());
            } else {
                currShape.setFill(Color.TRANSPARENT);
            }
        }
    }

    /*
     * NEXT THREE FUNCTIONS - upon picking (clicking) the desired shape available,
     * change the shape in the catalogue.
     */
    @FXML
    void changeToRectangle() {
        cataloguePane.getChildren().remove(currShape);
        currShape = createNewRectangle(cataloguePane, catalogueShapeX,
                catalogueShapeY, getCataloguePaint());
        addCatalogueDragEvent(currShape);
    }

    @FXML
    void changeToLine() {
        cataloguePane.getChildren().remove(currShape);
        currShape = createNewLine(cataloguePane, catalogueShapeX, catalogueShapeY);
        addCatalogueDragEvent(currShape);
    }

    @FXML
    void changeToEllipse() {
        cataloguePane.getChildren().remove(currShape);
        currShape = createNewEllipse(cataloguePane, catalogueShapeX, catalogueShapeY, getCataloguePaint());
        addCatalogueDragEvent(currShape);
    }

    /*
     * Checks if given x,y are in the painting panel.
     */
    private boolean insidePaintingPanel(double x, double y) {
        return x <= paintingPane.getPrefWidth() &&
                y <= paintingPane.getPrefHeight();
    }

    /*
     * Terminates the program.
     */
    @FXML
    void exitProgram() {
        Stage stage = (Stage) cataloguePane.getScene().getWindow();
        stage.close();
    }

    /*
     * Clears the painting pane from all shapes.
     */
    @FXML
    void clearProgram() {
        cataloguePane.getChildren().remove(currShape);
        paintingPane.getChildren().clear();
        paintingShapesList.clear();
    }

    /*
     * Upon clicking a button,
     * Removes the last shape added to the painting pane.
     */
    @FXML
    void undoProgram() {
        if (paintingShapesList.size() > 0) {
            Shape lastShape = paintingShapesList.get(paintingShapesList.size() - 1);
            paintingPane.getChildren().remove(lastShape);
            paintingShapesList.remove(lastShape);
        }
    }
    /*
     * Copy the given shape into an x,y location, and add the new shape
     * to the shapes list.
     */
    void addShapeToPainting(Shape shape, double x, double y) {
        Shape newShape;
        // Handles the three possible shapes:
        if (shape.getClass() == Rectangle.class) {
            newShape = createNewRectangle(paintingPane, x, y, getCataloguePaint());
        } else if (shape.getClass() == Ellipse.class) {
            newShape = createNewEllipse(paintingPane, x, y, getCataloguePaint());
        } else {
            newShape = createNewLine(paintingPane, x, y);
        }
        // add the shape into the list of painting pane shapes
        paintingShapesList.add(newShape);
    }

    /*
     * NEXT THREE FUNCTIONS - create the shape in a pane in given x,y
     * and color (excluding Line).
     */
    Rectangle createNewRectangle(Pane pane, double x, double y, Paint color) {
        Rectangle newRec = new Rectangle();
        setNewDrawableShape(newRec, pane);
        newRec.setX(x - RECWIDTH / 2);
        newRec.setY(y - RECHEIGHT / 2);
        newRec.setFill(color);
        newRec.widthProperty().setValue(RECWIDTH);
        newRec.heightProperty().setValue(RECHEIGHT);
        return newRec;
    }

    Ellipse createNewEllipse(Pane pane, double x, double y, Paint color) {
        Ellipse newEll = new Ellipse();
        setNewDrawableShape(newEll, pane);
        newEll.setCenterX(x);
        newEll.setCenterY(y);
        newEll.setRadiusX(ELLIPSERADIUSX);
        newEll.setRadiusY(ELLIPSERADIUSY);
        newEll.setFill(color);
        return newEll;
    }

    Line createNewLine(Pane pane, double x, double y) {
        Line newLine = new Line();
        setNewDrawableShape(newLine, pane);
        newLine.setStartX(x);
        newLine.setStartY(y);
        newLine.setEndX(x + LINELEN);
        newLine.setEndY(y + LINELEN);
        return newLine;
    }

    /*
     * Settings that are the same between all three shapes.
     * (Prevent redundant code)
     */
    void setNewDrawableShape(Shape shape, Pane pane) {
        pane.getChildren().add(shape);
        shape.setStroke(STOKECOLOR);
        shape.setStrokeWidth(STROKEWIDTH);
        shape.setViewOrder(SHAPEVIEWORDER);
    }

    /*
     * Adds the drag event handle to the catalogue shape -
     * when dragged into the painting pane, copy the shape into
     * the pane.
     */
    void addCatalogueDragEvent(Shape shape) {
        shape.setOnMouseReleased(new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                // if dropped inside the painting panel
                double dropX = event.getSceneX();
                double dropY = event.getSceneY();
                if (insidePaintingPanel(dropX, dropY)) {
                    addShapeToPainting(currShape, dropX, dropY);
                }
            }
        });
    }
}
