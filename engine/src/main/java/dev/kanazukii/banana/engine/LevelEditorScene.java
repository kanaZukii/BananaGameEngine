package dev.kanazukii.banana.engine;

import org.joml.Vector2f;
import org.joml.Vector4f;

import dev.kanazukii.banana.engine.components.SpriteRenderer;

public class LevelEditorScene extends Scene {

    public LevelEditorScene(){
        System.out.println("Level Editor Scene");
    }

    @Override
    public void init() {

        camera = new Camera(new Vector2f());

        int xOffset = 10;
        int yOffset = 10;

        float totalWidth = (float)(600 - xOffset * 2);
        float totalHeight = (float)(300 - yOffset * 2);

        float sizeX = totalWidth/ 100.0f;
        float sizeY = totalHeight/ 100.0f;

        for(int x=0; x < 100; x++){
            for(int y=0; y < 100; y++){
                float xPos = xOffset + (x * sizeX);
                float yPos = yOffset + (y * sizeY);

                GameObject go = new GameObject("Object" + String.valueOf(y+x-20), new Transform(new Vector2f(xPos, yPos), new Vector2f(sizeX, sizeY)));
                go.addComponent(new SpriteRenderer(new Vector4f(xPos/totalWidth, yPos/totalHeight, 1,1)));
                addGameObject(go);
            }
        }

    }

    @Override
    public void update(float deltaTime) {
        camera.getPosition().x -= deltaTime * 50.0f;
        camera.getPosition().y -= deltaTime * 50.0f;
        System.out.println("FPs: " + String.valueOf(Window.FPS));
        for (GameObject gameObject: gameObjects){
            gameObject.update(deltaTime);
        }

        renderer.render();
    }

    
}