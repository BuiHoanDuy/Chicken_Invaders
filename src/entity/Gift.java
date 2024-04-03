package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Gift extends Entity {
    private int type;
    
    private void initVariable() {
        String path = "/resource/gitf/" + type + ".png";
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Gift(float x, float y, float speed, int type) {
        super(x, y, speed);
        this.type = type;
        this.initVariable();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        super.update();
    }    
}
