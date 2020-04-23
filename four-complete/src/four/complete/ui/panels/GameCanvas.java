package four.complete.ui.panels;

import four.complete.core.engine.MathEngine;
import four.complete.core.engine.GameEngine;
import four.complete.model.BoxModel;
import four.complete.model.GameBoardModel;
import four.complete.model.LineSegmentModel;
import four.complete.model.PointDistanceModel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;

/**
 *
 * @author Keshan De Silva
 */
public class GameCanvas extends javax.swing.JPanel
{
    private final static int MARGIN_GAP = 20;
    private final static int DOT_SIZE = 8;
    private static int BOX_SIZE;
    
    private GameEngine gameEngine;
    private GameBoardModel gameBoardModel;
    private Point currentMousePoint;
    private Point[][] dotPoints;
    private PointDistanceModel[] currentSelectedPoints;

    public GameCanvas()
    {
        initComponents();
        this.addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent event)
            {
                currentMousePoint = event.getPoint();
                repaint();
            }    
        });
        
        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent event)
            {
                if (gameEngine.isPlayerTurn())
                {
                    currentMousePoint = event.getPoint();
                    repaint();

                    LineSegmentModel lineSegmentModel = new LineSegmentModel(currentSelectedPoints[0], currentSelectedPoints[1]);
                    if (!gameBoardModel.getLineSegmentSet().contains(lineSegmentModel))
                    {
                        lineSegmentModel.setPlayerModel(gameEngine.getCurrentPlayer());

                        gameBoardModel.addLineSegment(lineSegmentModel);
                        boolean isBoxComplete = gameEngine.processBoxComplete(lineSegmentModel);
                        repaint();

                        gameEngine.processLineSegment(lineSegmentModel, isBoxComplete);
                        if (!isBoxComplete) gameEngine.setPlayerTurn(false);
                    }
                }
            } 
        });
    }

    public void setGameBoardModel(GameEngine gameEngine)
    {
        this.gameEngine = gameEngine;
        this.gameBoardModel = gameEngine.getGameBoardModel();
        dotPoints =  new Point[gameBoardModel.getBoardSize() + 1][gameBoardModel.getBoardSize() + 1];
    }

    @Override
    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        if (gameBoardModel != null)
        {
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (currentMousePoint != null)
            {
                drawRealTimeConnectionLine(g2d);
            }
            
            drawLines(g2d);
            drawBoxes(g2d);
            
            drawInitilaDots(g2d);
        }
        
    }
    
    private void drawInitilaDots(Graphics2D graphics2D)
    {
        int horizontalSize = getWidth() - 2 * MARGIN_GAP;
        int verticalSize = getHeight()- 2 * MARGIN_GAP;
        
        int hGap = horizontalSize / gameBoardModel.getBoardSize() + 1;
        int vGap = verticalSize / gameBoardModel.getBoardSize() + 1;
        BOX_SIZE = Math.min(vGap, hGap);
        
        graphics2D.setColor(Color.BLACK);
        for (int i = 0; i <= gameBoardModel.getBoardSize(); i++)
        {
            for (int j = 0; j <= gameBoardModel.getBoardSize(); j++)
            {
                int xPos = MARGIN_GAP + (i * BOX_SIZE);
                int yPos = MARGIN_GAP + (j * BOX_SIZE);
                
                graphics2D.fillOval(xPos, yPos, DOT_SIZE , DOT_SIZE);
                dotPoints[j][i] = new Point(xPos, yPos);
                
                if ((i != gameBoardModel.getBoardSize()) && (j != gameBoardModel.getBoardSize()))
                {
                    int boxID = (gameBoardModel.getBoardSize() * j ) + i;
                    if (gameBoardModel.getMagicBoxPosition() == boxID)
                    {
                        graphics2D.setStroke(new BasicStroke(1));
                        int posX = (int)Math.round(xPos + BOX_SIZE / 2 - (BOX_SIZE / 8.0));
                        int posY = (int)Math.round(yPos + BOX_SIZE / 2 - (BOX_SIZE / 8.0));
                        graphics2D.setColor(new Color(255, 0, 0, 150));
                        graphics2D.fillRect(posX, posY, BOX_SIZE / 4, BOX_SIZE / 4);
                        graphics2D.setColor(new Color(0, 255, 0, 150));
                        graphics2D.fillRect(posX + 3, posY + 3, BOX_SIZE / 4, BOX_SIZE / 4);
                        graphics2D.setColor(new Color(0, 0, 255, 150));
                        graphics2D.fillRect(posX + 6, posY + 6, BOX_SIZE / 4, BOX_SIZE / 4);
                        graphics2D.setColor(Color.BLACK);
                    }
                }
            }
        }
    }
    
    private void drawRealTimeConnectionLine(Graphics2D graphics)
    {
        graphics.setColor(Color.BLACK);
        currentSelectedPoints = MathEngine.getClosedPoints(currentMousePoint, dotPoints);
        
        if (!gameBoardModel.isLineSegmentAvailable(new LineSegmentModel(currentSelectedPoints[0], currentSelectedPoints[1])))
        {
            Stroke dashed = new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 1);
            graphics.setStroke(dashed);
            graphics.draw(new Line2D.Float(
                    (int)(dotPoints[currentSelectedPoints[0].getRowIndex()][currentSelectedPoints[0].getColumnIndex()].getX()+ DOT_SIZE / 2),
                    (int)(dotPoints[currentSelectedPoints[0].getRowIndex()][currentSelectedPoints[0].getColumnIndex()].getY() + DOT_SIZE / 2),
                    (int)(dotPoints[currentSelectedPoints[1].getRowIndex()][currentSelectedPoints[1].getColumnIndex()].getX() + DOT_SIZE / 2),
                    (int)(dotPoints[currentSelectedPoints[1].getRowIndex()][currentSelectedPoints[1].getColumnIndex()].getY() + DOT_SIZE / 2))); 
        }
    }
    
    private void drawLines(Graphics2D graphics)
    {
        for(LineSegmentModel lineSegmentModel : gameBoardModel.getLineSegmentSet())
        {
            graphics.setColor(lineSegmentModel.getPlayerModel().getPlayerColor());
            graphics.setStroke(new BasicStroke(4));
            
            Point startPoint = dotPoints[lineSegmentModel.getStartRowIndex()][lineSegmentModel.getStartColumnIndex()];
            Point endPoint = dotPoints[lineSegmentModel.getEndRowIndex()][lineSegmentModel.getEndColumnIndex()];
            
            graphics.draw(new Line2D.Float(
                (int)(startPoint.getX() + DOT_SIZE / 2),
                (int)(startPoint.getY() + DOT_SIZE / 2),
                (int)(endPoint.getX() + DOT_SIZE / 2),
                (int)(endPoint.getY() + DOT_SIZE / 2)));  
        }
    }
    
    private void drawBoxes(Graphics2D graphics)
    {
        for(BoxModel boxModel : gameBoardModel.getBoxModelSet())
        {
            graphics.setColor(boxModel.getPlayerModel().getPlayerColor());
            Point startPoint = dotPoints[boxModel.getTopRowIndex()][boxModel.getTopColumnIndex()];
            
            // Draw solid box
            graphics.fillRoundRect(
                    (int)(startPoint.getX() + DOT_SIZE),
                    (int)(startPoint.getY() + DOT_SIZE),
                    BOX_SIZE - DOT_SIZE, BOX_SIZE - DOT_SIZE, 10, 10);
            
            // Draw white cross
            graphics.setColor(Color.WHITE);
            Stroke capStroke = new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
            graphics.setStroke(capStroke);
            
            graphics.draw(new Line2D.Float(                    
                    (int)(startPoint.getX() + 2 * DOT_SIZE),
                    (int)(startPoint.getY() + 2 * DOT_SIZE),
                    (int)(startPoint.getX() + BOX_SIZE - DOT_SIZE),
                    (int)(startPoint.getY() + BOX_SIZE - DOT_SIZE)));
            
            graphics.draw(new Line2D.Float(                    
                    (int)(startPoint.getX() + BOX_SIZE - DOT_SIZE),
                    (int)(startPoint.getY() + 2 * DOT_SIZE),
                    (int)(startPoint.getX() + 2 * DOT_SIZE),
                    (int)(startPoint.getY() + BOX_SIZE - DOT_SIZE)));
            
        }
    }
                
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
