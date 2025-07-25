package dev.kanazukii.ether.engine;

import java.util.ArrayList;
import java.util.List;

import dev.kanazukii.ether.engine.components.Component;
import dev.kanazukii.ether.engine.components.Transform;

public class GameObject {
    
    private static int ID_Count = 0;
    private int uID = -1;

    private String name;
    private int zIndex;
    // List of all components (Sprite render and etc.) of the object
    private List<Component> components;
    public Transform transform;

    // Constructor of a game object without the transform
    public GameObject(String name){
        this.name = name;
        this.components = new ArrayList<>();
        this.transform = new Transform();
        this.zIndex = 0;

        // TODO: Fix uID assignment when loading serialized saved scenes
        generateID();
    }

    // Constructor for game object with a transform (Scale and position)
    public GameObject(String name, Transform transform, int zIndex){
        this.name = name;
        this.zIndex = zIndex;
        this.components = new ArrayList<>();
        this.transform = transform;

        // TODO: Fix uID assignment when loading serialized saved scenes
        generateID();
    }

    public String getName(){
        return name;
    }

    // Retrieves a component based on the passed class use object.class
    public <T extends Component> T getComponent(Class<T> componentClass){
        for(Component comp : components){
            if(componentClass.isAssignableFrom(comp.getClass())){
                try{
                    return componentClass.cast(comp);
                } catch (ClassCastException e){
                    e.printStackTrace();
                    assert false : "Error Casting component";
                }
            }
        }

        return null;
    }

     // Remove a component based on the passed class use object.class
    public <T extends Component> void removeComponent(Class<T> componentClass){
        for(int i = 0; i < components.size(); i++){
            Component comp = components.get(i);
            if(componentClass.isAssignableFrom(comp.getClass())){
                components.remove(i);
                return;
            }
        }
    }

    public List<Component> getComponentList(){
        return components;
    }

    // Add a component to the Object
    public void addComponent (Component component){
        component.generateID();
        components.add(component);
        component.gameObject = this;
    }

    public int zIndex(){
        return zIndex;
    }

    public void ImGUI(){
        for (Component component : components) {
            component.ImGUI();
        }
    }

    // Starts all the components
    public void start(){
        for(int i = 0; i < components.size(); i++){
            components.get(i).start();
        }
    }

    // Calls all the update method of each component
    public void update(float deltaTime){
        for(int i = 0; i < components.size(); i++){
            components.get(i).update(deltaTime);
        }
    }

    private void generateID(){
        if(this.uID == -1){
            this.uID = ID_Count;
            ID_Count++;
        }
    }

    public static void init(int maxID){
        ID_Count = maxID;
    }

    public int getUID(){
        return uID;
    }
}
