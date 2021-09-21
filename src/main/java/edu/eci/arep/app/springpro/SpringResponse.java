package edu.eci.arep.app.springpro;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SpringResponse {

    Map<String, Method> webservices = new HashMap<>();

    public void mapService(String componentName) throws Exception {
        int nMethods = 0;
        for (Method m : Class.forName(componentName).getMethods()) {
            System.out.println("Revisando metodo: " + m.getName());
            if (m.isAnnotationPresent(RequestSpringPro.class)) {
                System.out.println("Tiene anotacion @RequestMapping");
                RequestSpringPro rm = m.getAnnotation(RequestSpringPro.class);
                webservices.put(rm.value(), m);
                nMethods++;
            }
        }

        System.out.printf("No. of web services %d %n", nMethods);

    }

    public String executeService(String theURI) {
        try {
            return webservices.get(theURI).invoke(null).toString();
        } catch (IllegalAccessException e) {
            Logger.getLogger(SpringResponse.class.getName()).log(Level.SEVERE, null, e);
        } catch (IllegalArgumentException e) {
            Logger.getLogger(SpringResponse.class.getName()).log(Level.SEVERE, null, e);
        } catch (InvocationTargetException e) {
            Logger.getLogger(SpringResponse.class.getName()).log(Level.SEVERE, null, e);
        }
        return "Error";
    }
}