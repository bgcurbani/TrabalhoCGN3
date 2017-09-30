import java.util.LinkedList;
import javax.media.opengl.GL;

public final class ObjetoGrafico {
    GL gl;
    private float tamanho = 2.0f;

    private int primitiva = GL.GL_LINE_STRIP;
    private LinkedList<Ponto4D> vertices;
    BoundingBox bBox = null;
    private boolean ehLineLoop;


//	private int primitiva = GL.GL_POINTS;
//	private Ponto4D[] vertices = { new Ponto4D(10.0, 10.0, 0.0, 1.0) };	

    private Transformacao4D matrizObjeto = new Transformacao4D();

    /// Matrizes temporarias que sempre sao inicializadas com matriz Identidade entao podem ser "static".
    private static Transformacao4D matrizTmpTranslacao = new Transformacao4D();
    private static Transformacao4D matrizTmpTranslacaoInversa = new Transformacao4D();
    private static Transformacao4D matrizTmpEscala = new Transformacao4D();		
//	private static Transformacao4D matrizTmpRotacaoZ = new Transformacao4D();
    private static Transformacao4D matrizGlobal = new Transformacao4D();
//	private double anguloGlobal = 0.0;

    public ObjetoGrafico() {
        vertices = new LinkedList();
    }

    public void atribuirGL(GL gl) {
        this.gl = gl;
    }

    public double obterTamanho() {
        return tamanho;
    }

    public double obterPrimitava() {
        return primitiva;
    }

    public void desenha() {
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glLineWidth(tamanho);
        gl.glPointSize(tamanho);

        gl.glPushMatrix();
        gl.glMultMatrixd(matrizObjeto.GetDate(), 0);
        gl.glBegin(primitiva);
        for (byte i=0; i < vertices.size(); i++) {
            gl.glVertex2d(vertices.get(i).obterX(), vertices.get(i).obterY());
        }
        gl.glEnd();

            //////////// ATENCAO: chamar desenho dos filhos... 

        gl.glPopMatrix();
    }

    public void translacaoXYZ(double tx, double ty, double tz) {
        Transformacao4D matrizTranslate = new Transformacao4D();
        matrizTranslate.atribuirTranslacao(tx,ty,tz);
        matrizObjeto = matrizTranslate.transformMatrix(matrizObjeto);		
    }

    public void escalaXYZ(double Sx,double Sy) {
        Transformacao4D matrizScale = new Transformacao4D();		
        matrizScale.atribuirEscala(Sx,Sy,1.0);
        matrizObjeto = matrizScale.transformMatrix(matrizObjeto);
    }

    ///TODO: erro na rotacao
    public void rotacaoZ(double angulo) {
        //anguloGlobal += 10.0; // rotacao em 10 graus
        //Transformacao4D matrizRotacaoZ = new Transformacao4D();		
        //matrizRotacaoZ.atribuirRotacaoZ(Transformacao4D.DEG_TO_RAD * angulo);
        //matrizObjeto = matrizRotacaoZ.transformMatrix(matrizObjeto);
    }

    public void atribuirIdentidade() {
//		anguloGlobal = 0.0;
            matrizObjeto.atribuirIdentidade();
    }

    public void escalaXYZPtoFixo(double escala, Ponto4D ptoFixo) {
        matrizGlobal.atribuirIdentidade();

        matrizTmpTranslacao.atribuirTranslacao(ptoFixo.obterX(),ptoFixo.obterY(),ptoFixo.obterZ());
        matrizGlobal = matrizTmpTranslacao.transformMatrix(matrizGlobal);

        matrizTmpEscala.atribuirEscala(escala, escala, 1.0);
        matrizGlobal = matrizTmpEscala.transformMatrix(matrizGlobal);

        ptoFixo.inverterSinal(ptoFixo);
        matrizTmpTranslacaoInversa.atribuirTranslacao(ptoFixo.obterX(),ptoFixo.obterY(),ptoFixo.obterZ());
        matrizGlobal = matrizTmpTranslacaoInversa.transformMatrix(matrizGlobal);

        matrizObjeto = matrizObjeto.transformMatrix(matrizGlobal);
    }

    public void rotacaoZPtoFixo(double angulo, Ponto4D ptoFixo) {
        matrizGlobal.atribuirIdentidade();

        matrizTmpTranslacao.atribuirTranslacao(ptoFixo.obterX(),ptoFixo.obterY(),ptoFixo.obterZ());
        matrizGlobal = matrizTmpTranslacao.transformMatrix(matrizGlobal);

        matrizTmpEscala.atribuirRotacaoZ(Transformacao4D.DEG_TO_RAD * angulo);
        matrizGlobal = matrizTmpEscala.transformMatrix(matrizGlobal);

        ptoFixo.inverterSinal(ptoFixo);
        matrizTmpTranslacaoInversa.atribuirTranslacao(ptoFixo.obterX(),ptoFixo.obterY(),ptoFixo.obterZ());
        matrizGlobal = matrizTmpTranslacaoInversa.transformMatrix(matrizGlobal);

        matrizObjeto = matrizObjeto.transformMatrix(matrizGlobal);
    }

    public void exibeMatriz() {
        matrizObjeto.exibeMatriz();
    }

    public void exibeVertices() {
//        System.out.println("P0[" + vertices[0].obterX() + "," + vertices[0].obterY() + "," + vertices[0].obterZ() + "," + vertices[0].obterW() + "]");
//        System.out.println("P1[" + vertices[1].obterX() + "," + vertices[1].obterY() + "," + vertices[1].obterZ() + "," + vertices[1].obterW() + "]");
//        System.out.println("P2[" + vertices[2].obterX() + "," + vertices[2].obterY() + "," + vertices[2].obterZ() + "," + vertices[2].obterW() + "]");
//        System.out.println("P3[" + vertices[3].obterX() + "," + vertices[3].obterY() + "," + vertices[3].obterZ() + "," + vertices[3].obterW() + "]");
//        System.out.println("anguloGlobal:" + anguloGlobal);
    }

    public void AdicionaPonto(double x, double y) {
        Ponto4D ponto = new Ponto4D();
        Ponto4D ponto2 = new Ponto4D();
        ponto.atribuirX(x);
        ponto.atribuirY(y);
        
        ponto2.atribuirX(x);
        ponto2.atribuirY(y);
        

        if (vertices.isEmpty()) {
            vertices.add(ponto);
            vertices.add(ponto2);
        } else {
            vertices.set(vertices.size() - 1, ponto);
            vertices.add(ponto2);
          }
    }

    public void TrocaPrimitiva(boolean desenhaLoop, boolean fecha) {
        ehLineLoop = desenhaLoop;
        
        if (fecha && desenhaLoop)
            this.primitiva = GL.GL_LINE_LOOP;
        else
            this.primitiva = GL.GL_LINE_STRIP;
    }

    public void setPronto() {
        if(vertices.size() >= 3 && this.ehLineLoop){
            TrocaPrimitiva(ehLineLoop, true);
        }
            criaBBox();
        // TODO: Criar bbox e varer pontos pegando maior/menor x e y.
    }

    public LinkedList<Ponto4D> getVertices() {
        return vertices;
    }

    public BoundingBox getBbox(){
        return bBox;
    }
    
    private void criaBBox() {
        double menorX, maiorX;
        double menorY, maiorY;
        double menorZ, maiorZ;
        Ponto4D pontoAtual = vertices.get(0);
        menorX = maiorX = pontoAtual.obterX();
        menorY = maiorY = pontoAtual.obterY();
        menorZ = maiorZ = pontoAtual.obterZ();
        
        for (int i = 1; i < vertices.size(); i++) {
            pontoAtual = vertices.get(i);
            
            if (menorX > pontoAtual.obterX()) {
                menorX = pontoAtual.obterX();
            } else {
                if (maiorX < pontoAtual.obterX()) {
                    maiorX = pontoAtual.obterX();
                }
            }
            
            if (menorY > pontoAtual.obterY()) {
                menorY = pontoAtual.obterY();
            } else {
                if (maiorY < pontoAtual.obterY()) {
                    maiorY = pontoAtual.obterY();
                }
            }
            
            if (menorZ > pontoAtual.obterZ()) {
                menorZ = pontoAtual.obterZ();
            } else {
                if (maiorZ < pontoAtual.obterZ()) {
                    maiorZ = pontoAtual.obterZ();
                }
            }
            
        }
        
        bBox = new BoundingBox(menorX, menorY, menorZ, maiorX, maiorY, maiorZ);
        bBox.processarCentroBBox();

    }

}