package com.bac.until;

import javax.swing.*;
import java.awt.*;

public class ImageLoader {
    public static Image getImage(String name, Class cls){
        return new ImageIcon(cls.getResource("/images/"+name)).getImage();
    }
}
