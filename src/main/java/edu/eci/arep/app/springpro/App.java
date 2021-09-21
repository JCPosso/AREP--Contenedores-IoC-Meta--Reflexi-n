package edu.eci.arep.app.springpro;

import edu.eci.arep.app.httpserver.HttpServer;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args){
            try{
                SpringResponse spresponse = new SpringResponse();
                spresponse.mapService(args[0]);
                HttpServer server = new HttpServer(spresponse);
                server.start();
            }catch(Exception ex){
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
