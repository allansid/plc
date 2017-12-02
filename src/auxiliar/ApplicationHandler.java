package auxiliar;

public class ApplicationHandler{
    protected InputHandler input;
    protected int largura = 800;
    protected int altura = 800;
    protected int fps = 30;//
    public void init(InputHandler input){
        this.input = input;
    }

    public int getLargura(){return largura;}

    public int getAltura(){return altura;}

    public int getFps(){return fps;}//

    public void setLargura(int largura){this.largura = largura;}

    public void setAltura(int altura){this.altura = altura;}

    public void setFps(int fps){this.fps = fps;}//

    public void createKey(String s, int i){
        input.createKey(s,i);
    }

    public boolean isKeyPressed(String s){
        return input.isKeyPressed(s);
    }

    public boolean isKeyTyped(String s){
        return input.isKeyTyped(s);
    }
}