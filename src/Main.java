import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class Main implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
	private GL gl;
	private GLU glu;
	private GLAutoDrawable glDrawable;
        private boolean desenhaLoop = false;

//	private ObjetoGrafico objeto = new ObjetoGrafico();
//	private ObjetoGrafico[] objetos = {
//                        new ObjetoGrafico(),
//			new ObjetoGrafico()};
        //private ObjetoGrafico objSelecionado = objetos[0];
        private ObjetoGrafico objSelecionado = null;
        private Mundo mundo;
        
	// "render" feito logo apos a inicializacao do contexto OpenGL.
	public void init(GLAutoDrawable drawable) {
		glDrawable = drawable;
		gl = drawable.getGL();
		glu = new GLU();
		glDrawable.setGL(new DebugGL(gl));

		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
                
                mundo = Mundo.getInstance();
//		for (byte i=0; i < objetos.length; i++) {
//			objetos[i].atribuirGL(gl);
//		}
//		objeto.atribuirGL(gl);
	}

	// metodo definido na interface GLEventListener.
	// "render" feito pelo cliente OpenGL.
    public void display(GLAutoDrawable arg0) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
//		glu.gluOrtho2D(-30.0f, 30.0f, -30.0f, 30.0f);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluOrtho2D(mundo.getCamera().getMaxX(), mundo.getCamera().getMinX(), mundo.getCamera().getMaxY(), mundo.getCamera().getMinY());

        gl.glLineWidth(1.0f);
        gl.glPointSize(1.0f);

        desenhaSRU();
        for (byte i = 0; i < mundo.getListaObjGrafico().size(); i++) {
            mundo.getListaObjGrafico().get(i).desenha();
//			objetos[i].desenha();
        }
        if (objSelecionado != null && objSelecionado.getBbox() != null) {
            objSelecionado.getBbox().desenharOpenGLBBox(gl);
        }

//		objeto.desenha();

		gl.glFlush();
	}

	public void desenhaSRU() {
		gl.glColor3f(1.0f, 0.0f, 0.0f);
		gl.glBegin(GL.GL_LINES);
			gl.glVertex2f(-200.0f, 0.0f);
			gl.glVertex2f(200.0f, 0.0f);
		gl.glEnd();
		gl.glColor3f(0.0f, 1.0f, 0.0f);
		gl.glBegin(GL.GL_LINES);
			gl.glVertex2f(0.0f, -200.0f);
			gl.glVertex2f(0.0f, 200.0f);
		gl.glEnd();
	}
	
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
//		case KeyEvent.VK_P:
//			objetos[0].exibeVertices();
//			break;
//		case KeyEvent.VK_M:
//			objetos[0].exibeMatriz();
//			break;
//
//		case KeyEvent.VK_R:
//			objetos[0].atribuirIdentidade();
//			break;
//
//		case KeyEvent.VK_RIGHT:
//			objetos[0].translacaoXYZ(2.0,0.0,0.0);
//			break;
//		case KeyEvent.VK_LEFT:
//			objetos[0].translacaoXYZ(-2.0,0.0,0.0);
//			break;
//		case KeyEvent.VK_UP:
//			objetos[0].translacaoXYZ(0.0,2.0,0.0);
//			break;
//		case KeyEvent.VK_DOWN:
//			objetos[0].translacaoXYZ(0.0,-2.0,0.0);
//			break;
//
//		case KeyEvent.VK_PAGE_UP:
//			objetos[0].escalaXYZ(2.0,2.0);
//			break;
//		case KeyEvent.VK_PAGE_DOWN:
//			objetos[0].escalaXYZ(0.5,0.5);
//			break;
//
//		case KeyEvent.VK_HOME:
////			objetos[0].RoracaoZ();
//			break;
//
//		case KeyEvent.VK_1:
//			objetos[0].escalaXYZPtoFixo(0.5, new Ponto4D(-15.0,-15.0,0.0,0.0));
//			break;
//			
//		case KeyEvent.VK_2:
//			objetos[0].escalaXYZPtoFixo(2.0, new Ponto4D(-15.0,-15.0,0.0,0.0));
//			break;
//			
//                case KeyEvent.VK_3:
//                        objetos[0].rotacaoZPtoFixo(10.0, new Ponto4D(-15.0,-15.0,0.0,0.0));
//			break;
//                        
                case KeyEvent.VK_I:
                        mundo.getCamera().zoomIn();
                        break;
                        
                case KeyEvent.VK_O:
                        mundo.getCamera().zoomOut();
                        break;
                
                case KeyEvent.VK_W:
                        mundo.getCamera().panBaixo();
                        break;
                        
                case KeyEvent.VK_S:
                        mundo.getCamera().panCima();
                        break;
                        
                case KeyEvent.VK_A:
                        mundo.getCamera().panDireita();
                        break;
                        
                case KeyEvent.VK_D:
                        mundo.getCamera().panEsquerda();
                        break;
                    
                case KeyEvent.VK_SPACE:
                    desenhaLoop = !desenhaLoop;
                    if (objSelecionado != null) {
                        desenhaLoop = !objSelecionado.getPrimitiva();
                        objSelecionado.TrocaPrimitiva(desenhaLoop, true);
                    }
                    break;
		}
                
		glDrawable.display();
	}

	// metodo definido na interface GLEventListener.
	// "render" feito depois que a janela foi redimensionada.
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
	    gl.glViewport(0, 0, width, height);
            gl.glMatrixMode(GL.GL_PROJECTION);
            gl.glLoadIdentity();
        
            mundo.getCamera().setTAMANHOX(width);
            mundo.getCamera().setTAMANHOY(height);
            
            System.out.println(" --- reshape ---");
            System.out.println(width);
            System.out.println(height);
	}

	// metodo definido na interface GLEventListener.
	// "render" feito quando o modo ou dispositivo de exibicao associado foi
	// alterado.
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
		// System.out.println(" --- displayChanged ---");
	}

	public void keyReleased(KeyEvent arg0) {
		// System.out.println(" --- keyReleased ---");
	}

	public void keyTyped(KeyEvent arg0) {
		// System.out.println(" --- keyTyped ---");
	}

        @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        // clica com o mouse, se não tiver nada no ponto cria obj
        // clica com o mouse, achou um poligono marca como selecionado e
        // desenha bBox
        double x = (e.getX() - mundo.getCamera().getTAMANHOX()/2) * 2.08;
        double y = (e.getY() - mundo.getCamera().getTAMANHOY()/2) * 2.08;
        
        switch (e.getButton()) {
            case MouseEvent.BUTTON1: {
                if (objSelecionado == null) {
                    if (existePoligono(x, y)) {
                        objSelecionado.getBbox().desenharOpenGLBBox(gl);
                        glDrawable.display();
                    } else {
                        objSelecionado = new ObjetoGrafico();

                        objSelecionado.TrocaPrimitiva(desenhaLoop, false);
                        objSelecionado.AdicionaPonto(x, y);
                        objSelecionado.atribuirGL(gl);

                        mundo.getListaObjGrafico().add(objSelecionado);
                        glDrawable.display();
                    }
                } else {
                    if(!objSelecionado.isPronto()){
                        objSelecionado.TrocaPrimitiva(desenhaLoop, false);
                        objSelecionado.AdicionaPonto(x, y);
                        glDrawable.display();
                    }
                }
                break;
            }
            case MouseEvent.BUTTON3: {
                if (objSelecionado != null) {
                    if (!objSelecionado.isPronto()) {
                        objSelecionado.TrocaPrimitiva(desenhaLoop, false);
                        objSelecionado.setPronto();
                        objSelecionado.getBbox().desenharOpenGLBBox(gl);
                        objSelecionado = null;
                    } else {
                        objSelecionado = null;
                    }
                }
                break;
            }

        }
                    glDrawable.display();
        
        
            
        
//        if (objSelecionado != null) {
//            switch (e.getButton()) {
//                case MouseEvent.BUTTON1: {
//                    objSelecionado.TrocaPrimitiva(desenhaLoop, false);
//                    objSelecionado.AdicionaPonto(x, y);
//                    break;
//                }
//
//                case MouseEvent.BUTTON3: {
//                    objSelecionado.TrocaPrimitiva(desenhaLoop, false);
//                    objSelecionado.setPronto();
//                    objSelecionado.getBbox().desenharOpenGLBBox(gl);
//                    objSelecionado = null;
//                    break;
//                }
//            }
//
//        }
       
        
        
//	    if ((e.getModifiers() & e.BUTTON1_MASK) != 0) {
//        antigoX = e.getX();
//        antigoY = e.getY();
//	    }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

//                int movtoX = e.getX() - antigoX;
//                int movtoY = e.getY() - antigoY;
//                ponto1x += movtoX;
//                ponto1y -= movtoY;
//
//                //Dump ...
//                System.out.println("posMouse: " + movtoX + " / " + movtoY);
//
//                antigoX = e.getX();
//                antigoY = e.getY();
//
//                glDrawable.display();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
//        System.out.println("Mouse X: "+ (e.getX()-191)* 2.08);
//        System.out.println("Mouse Y: "+ (e.getY()-191)* 2.08);
//        System.out.println("---------------------");
//        
        
        if (objSelecionado != null && !objSelecionado.isPronto()) {
            objSelecionado.getVertices().getLast().atribuirX((e.getX() - mundo.getCamera().getTAMANHOX()/2) * 2.08);
            objSelecionado.getVertices().getLast().atribuirY((e.getY() - mundo.getCamera().getTAMANHOY()/2) * 2.08);
            glDrawable.display();
        }
    }

    public boolean existePoligono(double x, double y) {
        LinkedList<ObjetoGrafico> listaObj = mundo.getListaObjGrafico();
        
        for (int i = 0; i < listaObj.size(); i++) {
            BoundingBox bbox = listaObj.get(i).getBbox();
            
            if(x <= bbox.obterMaiorX() && x >= bbox.obterMenorX() &&
               y <= bbox.obterMaiorY() && y >= bbox.obterMenorY()){
                
                objSelecionado = listaObj.get(i);
                return true;
            }
        }
        return false;
    }
    
}
